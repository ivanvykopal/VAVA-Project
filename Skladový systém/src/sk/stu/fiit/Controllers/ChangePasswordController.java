/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.ChangePasswordWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.SerializationClass;
import sk.stu.fiit.Model.User;

/**
 * Trieda reprezentujúca controller pre zmenu hesla používateľa.
 * 
 * @see Controller
 * 
 * @author Ivan Vykopal
 */
public final class ChangePasswordController implements Controller {
    
    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre zmenu hesla. **/
    private final ChangePasswordWindow window;
    
    /** Atribút user predstavuje používateľa, ktorý si mení heslo. **/
    private User user;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();
    
    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code ChangePasswordController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre zmenu hesla
     * 
     * @param user používate, ktorý požaduje zmenu hesla
     */
    private ChangePasswordController(Database database, ChangePasswordWindow window, User user) {
        this.database = database;
        this.window = window;
        this.user = user;
        window.setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code ChangePasswordController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre zmenu hesla
     * 
     * @param user používate, ktorý požaduje zmenu hesla
     */
    public static void createController(Database database, ChangePasswordWindow window, User user) {
        new ChangePasswordController(database, window, user);
    }

    /**
     * Metóda pre pridanie listenera pre tlačidlo a pridanie listenera pre zmenu
     * udájov nachádzajúcich sa v textovom poli pre kontrolu hesla. 
     */
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

    /**
     * Metóda určená pre pridanie funkcionality zmeny hesla používateľa.
     * 
     * <p> 
     * V rámci tejto triedy sa kontroluje, či nové heslo nie je rovnaké ako 
     * aktuálne a zároveň, či potvrdzujúce heslo je zhodné so zadaným novým heslom.
     * </p>
     */
    private void changePassword() {
        String oldPassword = window.getPfOldPassword();
        String newPassword = window.getPfNewPassword();
        String confirmPassword = window.getPfConfirmPassword();

        if (oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")) {
            JOptionPane.showMessageDialog(window, bundle.getString("EMPTY_ATT_ERROR1"));
            CustomLogger.getLogger(ChangePasswordController.class).warn(bundle.getString("EMPTY_ATT_ERROR2"));
            return;
        }

        if (oldPassword.equals(newPassword)) {
            JOptionPane.showMessageDialog(window, bundle.getString("PASSWORD_ERROR1"));
            CustomLogger.getLogger(ChangePasswordController.class).warn(bundle.getString("PASSWORD_ERROR2"));
            return;
        }

        user.setPassword(newPassword);
        user = database.setUser(user);
        if (user == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_PASS_ERROR"));
            CustomLogger.getLogger(ChangePasswordController.class).warn(bundle.getString("CHANGE_PASS_ERROR"));
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("CHANGED_PASS_INFO"));
            CustomLogger.getLogger(ChangePasswordController.class).info(user.getUsername()+ ": " + bundle.getString("CHANGED_PASS_INFO"));
            SerializationClass.serialize(database);
            window.dispose();
        }
    }

    /**
     * Metóda pre kontrolu, či nové heslo je zhodné s potvrdzujúcim heslom.
     */
    private void warning() {
        String newPassword = window.getPfNewPassword();
        String confirmPassword = window.getPfConfirmPassword();
        if (!newPassword.equals(confirmPassword)) {
            window.setLbInfoMessage(bundle.getString("PASS_NOT_EQUAL"), Color.RED);
        } else {
            window.setLbInfoMessage(bundle.getString("PASS_EQUAL"), Color.GREEN);
        }
    }

}
