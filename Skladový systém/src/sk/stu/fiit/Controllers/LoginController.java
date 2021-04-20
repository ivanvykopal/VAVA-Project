/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.ItemEvent;
import sk.stu.fiit.Controllers.Warehouseman.WarehousemanController;
import sk.stu.fiit.Controllers.Referent.ReferentController;
import sk.stu.fiit.Controllers.Administrator.AdministratorController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.AdministratorWindow;
import sk.stu.fiit.GUI.LoginWindow;
import sk.stu.fiit.GUI.ReferentWindow;
import sk.stu.fiit.GUI.WarehousemanWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class LoginController implements Controller {

    private final Database database;
    private final LoginWindow window;
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    private LoginController(Database database, LoginWindow window) {
        this.database = database;
        this.window = window;
        
        window.setVisible(true);
        initController();
    }

    public static void createController(Database database, LoginWindow window) {
        new LoginController(database, window);
    }

    @Override
    public void initController() {
        window.btnLoginAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                login();
            }
        });
        
        window.getCbLanguage().addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                changeLanguage();
            }
        });
    }
    
    private void changeLanguage() {
        int language = window.getCbLanguage().getSelectedIndex();
        LoginWindow newWindow;
        switch (language) {
            case 0:
                InternationalizationClass.setBundle("bundles/Bundle_SK", "sk", "SK");
                newWindow = new LoginWindow();
                newWindow.getCbLanguage().setSelectedIndex(0);
                LoginController.createController(database, newWindow);
                window.dispose();
                break;
            case 1:
                InternationalizationClass.setBundle("bundles/Bundle_EN", "en", "US");
                newWindow = new LoginWindow();
                newWindow.getCbLanguage().setSelectedIndex(1);
                LoginController.createController(database, newWindow);
                window.dispose();
                break;
        }
    }

    private void login() {
        String userName = window.getTfLoginField();
        String password;
        try {
            password = SHAtoString(getSHA(window.getPfPasswordField()));
        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(window, bundle.getString("PASS_LOAD_ERROR1"));
            CustomLogger.getLogger(LoginController.class).warn(bundle.getString("PASS_LOAD_ERROR2"));
            return;
        }
        
        User user = database.findUser(userName, password);
        if (user == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("USER_ERROR1"));
            CustomLogger.getLogger(LoginController.class).warn(bundle.getString("USER_ERROR1"));
            return;
        }
        
        switch (user.getType()) {
            case ADMINISTRATOR:
                CustomLogger.getLogger(LoginController.class).info(user.getUsername() + ": " + bundle.getString("ADMINISTRATOR_LOGIN_INFO"));
                AdministratorController.createController(database, new AdministratorWindow(), user);
                window.setVisible(false);
                break;
            case WAREHOUSEMAN:
                CustomLogger.getLogger(LoginController.class).info(user.getUsername() + ": " + bundle.getString("WAREHOUSEMAN_LOGIN_INFO"));
                WarehousemanController.createController(database, new WarehousemanWindow(), user);
                window.setVisible(false);
                break;
            case REFERENT:
                CustomLogger.getLogger(LoginController.class).info(user.getUsername() + ": " + bundle.getString("REFERENT_LOGIN_INFO"));
                ReferentController.createController(database, new ReferentWindow(), user);
                window.setVisible(false);
                break;
        }
    }

    //https://www.baeldung.com/sha-256-hashing-java
    private byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private String SHAtoString(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
