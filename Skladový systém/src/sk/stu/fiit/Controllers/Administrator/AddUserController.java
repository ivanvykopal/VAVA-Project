/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Administrator;

import sk.stu.fiit.EmailValidator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.Controllers.LoginController;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.AddUserWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.SerializationClass;
import sk.stu.fiit.Model.User;
import sk.stu.fiit.PasswordGenerator;
import sk.stu.fiit.PasswordHasher;

/**
 * Trieda reprezentujúca controller pre pridávanie používateľov do systému.
 *
 * @see Controller
 * 
 * @author Ivan Vykopal
 */
public final class AddUserController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre pridávanie používateľov. **/
    private final AddUserWindow window;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code AddUserController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre pridávanie používateľov
     */
    private AddUserController(Database database, AddUserWindow window) {
        this.database = database;
        this.window = window;
        window.setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code AddUserController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre pridávanie používateľov
     */
    public static void createController(Database database, AddUserWindow window) {
        new AddUserController(database, window);
    }

    /**
     * Metóda pre pridanie listenera pre tlačidlo. 
     */
    @Override
    public void initController() {
        window.btnAddUserAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                addUser();
            }
        });
    }

    /**
     * Metóda pre pridanie nového používateľa do systému.
     * 
     * <p>
     * V rámci tejto triedy sa kontroluje korektnosť zadaného emailu, to, či sú
     * všetky polia vyplnené.
     * V prípade správne zadaných údajov je daný používateľ do systému pridaný,
     * ak sa ešte v systéme nenachádza.
     * </p>
     */
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
        
        String password = PasswordGenerator.generatePassword();
        try {
            user.setPassword(PasswordHasher.SHAtoString(PasswordHasher.getSHA(password)));
        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(window, bundle.getString("PASS_LOAD_ERROR1"));
            CustomLogger.getLogger(LoginController.class).warn(bundle.getString("PASS_LOAD_ERROR2"));
        }

        user = database.addUser(user);
        if (user == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("USER_ERROR2"));
            CustomLogger.getLogger(AddUserController.class).warn(bundle.getString("USER_ERROR2"));
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("USER_INFO"));
            JOptionPane.showMessageDialog(window, bundle.getString("PASSWORD_INFO") + ": " + password);
            CustomLogger.getLogger(AddUserController.class).info(user.getUsername() + ": " + bundle.getString("USER_INFO"));
            SerializationClass.serialize(database);
            window.dispose();
        }
    }

}
