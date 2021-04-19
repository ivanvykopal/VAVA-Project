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
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.RemoveUserWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.SerializationClass;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class RemoveUserController implements Controller {

    private final Database database;
    private final RemoveUserWindow window;
    private User user = null;
    private final HashMap<String, ArrayList<User>> usersTable = new HashMap<>();

    private RemoveUserController(Database database, RemoveUserWindow window) {
        this.database = database;
        this.window = window;

        fillHashTable();
        fillUsersTable("", "Administrátor");
        window.setVisible(true);

        initController();
    }

    public static void createController(Database database, RemoveUserWindow window) {
        new RemoveUserController(database, window);
    }

    @Override
    public void initController() {
        window.btnChooseUserAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseUser();
            }
        });

        window.btnRemoveUserAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeUser();
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
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam!");
            CustomLogger.getLogger(RemoveUserController.class).warn("Nebol vybraný žiaden záznam!");
            return;
        }

        String username = (String) window.getTbUsersModel().getValueAt(index, 0);

        user = database.findUser(username);
        if (user == null) {
            window.setTfEmail("");
            window.setTfName("");
            window.setTfType("");
            window.setTfUsername("");
        } else {
            window.setTfEmail(user.getEmail());
            window.setTfName(user.getName());
            switch (user.getType()) {
                case ADMINISTRATOR:
                    window.setTfType("Administrátor");
                    break;
                case WAREHOUSEMAN:
                    window.setTfType("Skladník");
                    break;
                default:
                    window.setTfType("Referent");
                    break;
            }
            window.setTfUsername(user.getUsername());
        }
    }

    private void removeUser() {
        if (user == null) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam!");
            CustomLogger.getLogger(RemoveUserController.class).warn("Nebol vybraný žiaden záznam!");
            return;
        }

        user = database.removeUser(user);
        if (user == null) {
            JOptionPane.showMessageDialog(window, "Chyba pri odstraňovaní používateľa!");
            CustomLogger.getLogger(RemoveUserController.class).warn("Chyba pri odstraňovaní používateľa!");
        } else {
            JOptionPane.showMessageDialog(window, "Používateľ bol vymazaný!");
            CustomLogger.getLogger(RemoveUserController.class).info(user.getUsername() + ": " + "Používateľ bol vymazaný!");
            SerializationClass.serialize(database);
            window.dispose();
        }
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
        switch (type) {
            case "Administrátor":
                users = usersTable.get("administrator");
                break;
            case "Skladník":
                users = usersTable.get("warehouseman");
                break;
            case "Referent":
                users = usersTable.get("referent");
                break;
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

    private void filter() {
        String filter = window.getTfFilter();
        fillUsersTable(filter, (String) window.getCbTypeFilter().getSelectedItem());
    }

}
