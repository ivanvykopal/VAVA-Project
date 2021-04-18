/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import sk.stu.fiit.EmailValidator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.AddUserWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.SerializationClass;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class AddUserController implements Controller {
    private final Database database;
    private final AddUserWindow window;

    private AddUserController(Database database, AddUserWindow window) {
        this.database = database;
        this.window = window;
        window.setVisible(true);
        
        initController();
    }

    public static void createController(Database database, AddUserWindow window) {
        new AddUserController(database, window);
    }

    @Override
    public void initController() {
        window.btnAddUserAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                addUser();
            }
        });
    }
    
    private void addUser() {
        User user = new User();
        user.setUsername(window.getTfUsername());
        user.setName(window.getTfName());
        user.setEmail(window.getTfEmail());
        user.setType((String) window.getCbType().getSelectedItem());
        
        if (!EmailValidator.checkEmail(user.getEmail())) {
            JOptionPane.showMessageDialog(window, "Zlý formát pre email!");
            return;
        }
        
        if (user.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, "Je potrebné vyplniť všetky polia!");
            return;
        }
        
        user = database.addUser(user);
        if (user == null) {
            JOptionPane.showMessageDialog(window, "Zadaný používať sa už v systéme nachádza!");
        } else {
            JOptionPane.showMessageDialog(window, "Používateľ bol pridaný!");
            SerializationClass.serialize(database);
            window.dispose();
        }
    }
    
}
