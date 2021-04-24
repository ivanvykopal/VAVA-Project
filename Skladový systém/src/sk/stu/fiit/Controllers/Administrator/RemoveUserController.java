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
import sk.stu.fiit.GUI.RemoveUserWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.SerializationClass;
import sk.stu.fiit.Model.User;

/**
 * Trieda reprezentujúca controller pre odstraňovanie používateľov zo systému.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public final class RemoveUserController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre úpravu používateľov. **/
    private final RemoveUserWindow window;
    
    /** Atribút user predstavuje používateľa, ktorého si používateľ zvolil. **/
    private User user = null;
  
    /** 
     * Atribút usersTable predstavuje tabuľku používateľov rozdelených na základe
     * typu používateľa.
     **/
    private final HashMap<String, ArrayList<User>> usersTable = new HashMap<>();
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code RemoveUserController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * <p>
     * V metóde sa zároveň plní hash tabuľka usersTable používateľmi zo systému.
     * Zároveň sa plní aj tabuľka nachádzajúca sa na obrazovke.
     * </p>
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre odstránenie používateľov
     */
    private RemoveUserController(Database database, RemoveUserWindow window) {
        this.database = database;
        this.window = window;

        fillHashTable();
        fillUsersTable("", bundle.getString("ADMINISTRATOR"));
        window.setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code RemoveUserController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre úpravu používateľov
     */
    public static void createController(Database database, RemoveUserWindow window) {
        new RemoveUserController(database, window);
    }

    /**
     * Metóda pre pridanie listenerov pre jednotlivé tlačidlá a pridanie listeneru
     * pre výber typu používateľa.
     */
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

    /**
     * Metóda pre výber používateľa z tabuľky používateľov. Táto metóda zároveň
     * napĺňa údaje do textových polí pre zobrazenie informácií.
     */
    private void chooseUser() {
        int index = window.getTbUsersTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(RemoveUserController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }

        String username = (String) window.getTbUsersModel().getValueAt(index, 0);

        user = database.findUser(username);
        if (user == null) {
            clear();
        } else {
            window.setTfEmail(user.getEmail());
            window.setTfName(user.getName());
            switch (user.getType()) {
                case ADMINISTRATOR:
                    window.setTfType(bundle.getString("ADMINISTRATOR"));
                    break;
                case WAREHOUSEMAN:
                    window.setTfType(bundle.getString("WAREHOUSEMAN"));
                    break;
                default:
                    window.setTfType(bundle.getString("REFERENT"));
                    break;
            }
            window.setTfUsername(user.getUsername());
        }
    }

    /**
     * Metóda pre odstránenie používateľa zo systému.
     */
    private void removeUser() {
        if (user == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(RemoveUserController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }

        user = database.removeUser(user);
        if (user == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("REMOVE_USER_ERROR"));
            CustomLogger.getLogger(RemoveUserController.class).warn(bundle.getString("REMOVE_USER_ERROR"));
            clear();
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("REMOVE_USER_INFO"));
            CustomLogger.getLogger(RemoveUserController.class).info(user.getUsername() + ": " + bundle.getString("REMOVE_USER_INFO"));
            SerializationClass.serialize(database);
            window.dispose();
        }
    }

    /**
     * Metóda pre naplnenie hash tabuľky s používateľmi na základe typu používateľa.
     */
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
                    usersTable.put("administrator", record);
                    break;
                case WAREHOUSEMAN:
                    record = usersTable.get("warehouseman");
                    if (record == null) {
                        record = new ArrayList<>();
                    }
                    record.add(u);
                    usersTable.put("warehouseman", record);
                    break;
                case REFERENT:
                    record = usersTable.get("referent");
                    if (record == null) {
                        record = new ArrayList<>();
                    }
                    record.add(u);
                    usersTable.put("referent", record);
                    break;
            }
        }
    }

    /**
     * Metóda pre naplnenie tabuľky s existujúcimi používateľmi na základe filtra
     * a typu.
     * 
     * @param filter reťazec podľa, ktorého filtrujeme položky tabuľky 
     * 
     * @param type typ používateľa, na základe ktorého vyhľadávame používateľov
     */
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

        Pattern pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
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

    /**
     * Metóda pre filtrovanie položiek v tabuľke na základe údajov zadaných v 
     * textovom poli.
     */
    private void filter() {
        String filter = window.getTfFilter();
        fillUsersTable(filter, (String) window.getCbTypeFilter().getSelectedItem());
    }
    
    /**
     * Metóda pre premazanie komponentov.
     */
    private void clear() {
        window.setTfEmail("");
        window.setTfName("");
        window.setTfType("");
        window.setTfUsername("");
    }

}
