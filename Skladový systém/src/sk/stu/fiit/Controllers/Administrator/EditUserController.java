/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Administrator;

import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.EditUserWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.SerializationClass;
import sk.stu.fiit.Model.Type;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class EditUserController implements Controller {

    private final Database database;
    private final EditUserWindow window;
    private User user = null;
    private final HashMap<String, ArrayList<User>> usersTable = new HashMap<>();
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    private EditUserController(Database database, EditUserWindow window) {
        this.database = database;
        this.window = window;

        fillHashTable();
        fillUsersTable("", bundle.getString("ADMINISTRATOR"));
        window.setVisible(true);

        initController();
    }

    public static void createController(Database database, EditUserWindow window) {
        new EditUserController(database, window);
    }

    @Override
    public void initController() {
        window.btnChooseUserAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseUser();
            }
        });

        window.btnEditUserAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                editUser();
            }
        });

        window.btnFilterAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                filter();
            }
        });

        window.getCbTypeFilter().addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String filter = window.getTfFilter();
                fillUsersTable(filter, (String) window.getCbTypeFilter().getSelectedItem());
            }
        });
    }

    private void chooseUser() {
        int index = window.getTbUsersTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(EditUserController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }

        String username = (String) window.getTbUsersModel().getValueAt(index, 0);

        user = database.findUser(username);
        if (user == null) {
            window.setTfEmail("");
            window.setTfName("");
            window.setCbType(Type.ADMINISTRATOR);
            window.setTfUsername("");
        } else {
            window.setTfEmail(user.getEmail());
            window.setTfName(user.getName());
            window.setCbType(user.getType());
            window.setTfUsername(user.getUsername());
        }
    }

    private void editUser() {
        if (user == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(EditUserController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }

        user.setEmail(window.getTfEmail());
        user.setName(window.getTfName());
        user.setType((String) window.getCbType().getSelectedItem());
        user.setUsername(window.getTfUsername());

        if (user.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, bundle.getString("EMPTY_ATT_ERROR1"));
            CustomLogger.getLogger(EditUserController.class).warn(bundle.getString("EMPTY_ATT_ERROR2"));
            return;
        }
        
        user = database.setUser(user);
        if (user == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_USER_ERROR"));
            CustomLogger.getLogger(EditUserController.class).warn(bundle.getString("CHANGE_USER_ERROR"));
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_USER_INFO"));
            CustomLogger.getLogger(EditUserController.class).info(user.getUsername() + ": " + bundle.getString("CHANGE_USER_INFO"));
            SerializationClass.serialize(database);
            window.dispose();
        }
    }

    private void filter() {
        String filter = window.getTfFilter();
        fillUsersTable(filter, (String) window.getCbTypeFilter().getSelectedItem());
    }

    private void fillHashTable() {
        ArrayList<User> record;
        for (User u : database.getUserTable()) {
            switch (u.getType()) {
                case ADMINISTRATOR:
                    record = usersTable.get("administrator");
                    if (record == null) {
                        record = new ArrayList<>();
                    }
                    record.add(u);
                    usersTable.replace("administrator", record);
                    break;
                case WAREHOUSEMAN:
                    record = usersTable.get("warehouseman");
                    if (record == null) {
                        record = new ArrayList<>();
                    }
                    record.add(u);
                    usersTable.replace("warehouseman", record);
                    break;
                case REFERENT:
                    record = usersTable.get("referent");
                    if (record == null) {
                        record = new ArrayList<>();
                    }
                    record.add(u);
                    usersTable.replace("referent", record);
                    break;
            }
        }
    }

    private void fillUsersTable(String filter, String type) {
        window.getTbUsersModel().setRowCount(0);
        ArrayList<User> users = new ArrayList<>();
        if (type.equals(bundle.getString("ADMINISTRATOR"))) {
            users = usersTable.get("administrator");
        } else if (type.equals(bundle.getString("REFERENT"))) {
            users = usersTable.get("referent");
        } else if (type.equals(bundle.getString("WAREHOUSEMAN"))) {
            users = usersTable.get("warehouseman");
        }

        if (users == null) {
            return;
        }

        Pattern pattern = Pattern.compile("*" + filter + "*", Pattern.CASE_INSENSITIVE);
        for (User u : users) {
            if (pattern.matcher(u.getName()).find()) {
                Object[] row = new Object[4];
                row[0] = u.getUsername();
                row[1] = u.getName();
                row[2] = u.getEmail();
                row[3] = u.getTypeString();
                window.getTbUsersModel().addRow(row);
            }
        }
    }

}
