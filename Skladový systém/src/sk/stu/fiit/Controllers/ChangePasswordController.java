/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import sk.stu.fiit.GUI.ChangePasswordWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.SerializationClass;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class ChangePasswordController implements Controller {

    private final Database database;
    private final ChangePasswordWindow window;
    private User user;

    private ChangePasswordController(Database database, ChangePasswordWindow window, User user) {
        this.database = database;
        this.window = window;
        this.user = user;
        window.setVisible(true);

        initController();
    }

    public static void createController(Database database, ChangePasswordWindow window, User user) {
        new ChangePasswordController(database, window, user);
    }

    @Override
    public void initController() {
        window.btnChangePasswordAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                changePassword();
            }
        });

        window.pfConfirmPasswordAddListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                warning();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warning();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                warning();
            }
        });
    }

    private void changePassword() {
        String oldPassword = window.getPfOldPassword();
        String newPassword = window.getPfNewPassword();
        String confirmPassword = window.getPfConfirmPassword();

        if (oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")) {
            JOptionPane.showMessageDialog(window, "Je potrebné vyplniť všetky polia.");
            return;
        }

        if (oldPassword.equals(newPassword)) {
            JOptionPane.showMessageDialog(window, "Dané heslo sa aktuálne používa.");
            return;
        }

        user.setPassword(newPassword);
        user = database.setUser(user);
        if (user == null) {
            JOptionPane.showMessageDialog(window, "Chyba pri zemene hesla!");
        } else {
            JOptionPane.showMessageDialog(window, "Heslo bolo zmenené!");
            SerializationClass.serialize(database);
            window.dispose();
        }
    }

    private void warning() {
        String newPassword = window.getPfNewPassword();
        String confirmPassword = window.getPfConfirmPassword();
        if (!newPassword.equals(confirmPassword)) {
            window.setLbInfoMessage("Heslá nie sú rovnaké.", Color.RED);
        } else {
            window.setLbInfoMessage("Heslá sú rovnaké.", Color.GREEN);
        }
    }

}
