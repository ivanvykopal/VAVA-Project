/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Administrator;

import java.util.ResourceBundle;
import sk.stu.fiit.Controllers.ChangePasswordController;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.Controllers.LoginController;
import sk.stu.fiit.GUI.About;
import sk.stu.fiit.GUI.AddGoodsWindow;
import sk.stu.fiit.GUI.AddStorageWindow;
import sk.stu.fiit.GUI.AddUserWindow;
import sk.stu.fiit.GUI.AdministratorWindow;
import sk.stu.fiit.GUI.ChangePasswordWindow;
import sk.stu.fiit.GUI.EditGoodsWindow;
import sk.stu.fiit.GUI.EditUserWindow;
import sk.stu.fiit.GUI.LoginWindow;
import sk.stu.fiit.GUI.RemoveGoodsWindow;
import sk.stu.fiit.GUI.RemoveStorageWindow;
import sk.stu.fiit.GUI.RemoveUserWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 * Trieda reprezentujúca controller pre prihláseného administrátora.
 *
 * @see Controller
 * 
 * @author Ivan Vykopal
 */
public final class AdministratorController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre prihláseného administrátora. **/
    private final AdministratorWindow window;
    
    /** Atribút user predstavujúci prihláseného administrátora. **/
    private final User user;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code AdministratorController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * <p>
     * V tomto konštruktore sa zároveň vypĺňajú informácie o administrátorovi viditeľné
     * na obrazovke ako sú prihlasovacie meno a meno administrátora.
     * </p>
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného administrátora
     * 
     * @param user prihlásený administrátor
     */
    private AdministratorController(Database database, AdministratorWindow window, User user) {
        this.database = database;
        this.window = window;
        this.user = user;

        window.setVisible(true);

        window.setLbName(bundle.getString("NAME_LB") + ": " + user.getName());
        window.setLbUsername(bundle.getString("USER_USERNAME") + ": " + user.getUsername());

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code AdministratorController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre pridávanie tovarov
     * 
     * @param user prihlásený administrátor
     */
    public static void createController(Database database, AdministratorWindow window, User user) {
        new AdministratorController(database, window, user);
    }

    /**
     * Metóda pre pridanie listenerov pre tlačidlá a menu itemy nachádzajúce sa
     * na obrazovke administrátora po prihlásení. 
     */
    @Override
    public void initController() {
        window.addGoodsListener(() -> addGoods());

        window.addStorageListener(() -> addStorage());

        window.addUserListener(() -> addUser());

        window.aboutListener(e -> new About().setVisible(true));

        window.changePasswordListener(() -> changePassword());

        window.editGoodsListener(() -> editGoods());

        window.editUserListener(() -> editUser());

        window.exitListener(e -> System.exit(0));

        window.logoutListener(() -> logout());

        window.removeGoodsListener(() -> removeGoods());

        window.removeStorageListener(() -> removeStorage());

        window.removeUserListener(() -> removeUser());

    }

    /**
     * Metóda, ktorá volá controller pre {@code AddGoodsController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void addGoods() {
        AddGoodsController.createController(database, new AddGoodsWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code AddStorageController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void addStorage() {
        AddStorageController.createController(database, new AddStorageWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code AddUserController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void addUser() {
        AddUserController.createController(database, new AddUserWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code ChangePasswordController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void changePassword() {
        ChangePasswordController.createController(database, new ChangePasswordWindow(), user);
    }

    /**
     * Metóda, ktorá volá controller pre {@code EditGoodsController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void editGoods() {
        EditGoodsController.createController(database, new EditGoodsWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code EditUserController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void editUser() {
        EditUserController.createController(database, new EditUserWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code LoginController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void logout() {
        LoginController.createController(database, new LoginWindow());
        window.setVisible(false);
    }

    /**
     * Metóda, ktorá volá controller pre {@code RemoveGoodsController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void removeGoods() {
        RemoveGoodsController.createController(database, new RemoveGoodsWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code RemoveStorageController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void removeStorage() {
        RemoveStorageController.createController(database, new RemoveStorageWindow());
    }

    /**
     * Metóda, ktorá volá controller pre {@code RemoveUserController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void removeUser() {
        RemoveUserController.createController(database, new RemoveUserWindow());
    }
}
