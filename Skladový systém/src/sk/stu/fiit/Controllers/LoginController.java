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
import sk.stu.fiit.PasswordHasher;

/**
 * Trieda reprezentujúca controller pre prihlásenie používateľov do systému.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public final class LoginController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre prihlásenie používateľov. **/
    private final LoginWindow window;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code LoginController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihlásenie používateľov
     */
    private LoginController(Database database, LoginWindow window) {
        this.database = database;
        this.window = window;
        
        if (InternationalizationClass.getLocale().getLanguage().equals("sk")) {
            window.getCbLanguage().setSelectedIndex(0);
        } else {
            window.getCbLanguage().setSelectedIndex(1);
        }
        
        window.setVisible(true);
        initController();
    }

    /**
     * Metóda pre vytvorenie {@code LoginController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihlásenie používateľov
     */
    public static void createController(Database database, LoginWindow window) {
        new LoginController(database, window);
    }

    /**
     * Metóda pre pridanie listenera pre tlačidlo a pridanie listenera pre zmenu
     * udájov nachádzajúcich sa v rolovacom poli. 
     */
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
    
    /**
     * Metóda určená pre zmenu jazykovej verzie. Podpora pre dve jazykové verzie:
     * SK a EN verzia.
     */
    private void changeLanguage() {
        int language = window.getCbLanguage().getSelectedIndex();
        LoginWindow newWindow;
        switch (language) {
            case 0:
                InternationalizationClass.setBundle("bundles/Bundle_SK", "sk", "SK");
                newWindow = new LoginWindow();
                LoginController.createController(database, newWindow);
                window.dispose();
                break;
            case 1:
                InternationalizationClass.setBundle("bundles/Bundle_EN", "en", "US");
                newWindow = new LoginWindow();
                LoginController.createController(database, newWindow);
                window.dispose();
                break;
        }
    }

    /**
     * Metóda pre prihlásenie používateľa.
     * 
     * <p> 
     * V rámci tejto triedy sa kontroluje existencia používateľa v systéme a 
     * korektnosť zadaných údajov. Pri správne zadaných prihlasovacích údajoch
     * sa spustí požadovaná obrazovka pre prihláseného používateľa.
     * </p>
     */
    private void login() {
        String userName = window.getTfLoginField();
        String password;
        try {
            password = PasswordHasher.SHAtoString(PasswordHasher.getSHA(window.getPfPasswordField()));
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

}
