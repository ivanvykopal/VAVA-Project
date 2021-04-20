/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Administrator;

import sk.stu.fiit.EmailValidator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.AddUserWindow;
import sk.stu.fiit.InternationalizationClass;
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
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

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
            JOptionPane.showMessageDialog(window, bundle.getString("EMAIL_ERROR"));
            CustomLogger.getLogger(AddUserController.class).warn(bundle.getString("EMAIL_ERROR"));
            return;
        }

        if (user.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, bundle.getString("EMPTY_ATT_ERROR1"));
            CustomLogger.getLogger(AddUserController.class).warn(bundle.getString("EMPTY_ATT_ERROR2"));
            return;
        }

        user = database.addUser(user);
        if (user == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("USER_ERROR2"));
            CustomLogger.getLogger(AddUserController.class).warn(bundle.getString("USER_ERROR2"));
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("USER_INFO"));
            CustomLogger.getLogger(AddUserController.class).info(user.getUsername() + ": " + bundle.getString("USER_INFO"));
            SerializationClass.serialize(database);
            window.dispose();
        }
    }

}
