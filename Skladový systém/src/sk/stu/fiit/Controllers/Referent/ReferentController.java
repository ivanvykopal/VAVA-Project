/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Referent;

import java.util.ResourceBundle;
import sk.stu.fiit.Controllers.ChangePasswordController;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.Controllers.LoginController;
import sk.stu.fiit.GUI.About;
import sk.stu.fiit.GUI.ChangePasswordWindow;
import sk.stu.fiit.GUI.LoginWindow;
import sk.stu.fiit.GUI.ReferentWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 * Trieda reprezentujúca controller pre prihláseného referenta.
 *
 * @see Controller
 * 
 * @author Ivan Vykopal
 */
public final class ReferentController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre prihláseného referenta. **/
    private final ReferentWindow window;
    
    /** Atribút user predstavujúci prihláseného referenta. **/
    private final User user;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code ReferentController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * <p>
     * V tomto konštruktore sa zároveň vypĺňajú informácie o referentovi viditeľné
     * na obrazovke ako sú prihlasovacie meno a meno referenta.
     * </p>
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného referenta
     * 
     * @param user prihlásený referent
     */
    private ReferentController(Database database, ReferentWindow window, User user) {
        this.database = database;
        this.window = window;
        this.user = user;
        
        window.setLbName(bundle.getString("NAME_LB") + ": " + user.getName());
        window.setLbUsername(bundle.getString("USER_USERNAME") + ": " + user.getUsername());
       
        window.setVisible(true);
        hideAll();
        window.getpLogin().setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code ReferentController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného referenta
     * 
     * @param user prihlásený referent
     */
    public static void createController(Database database, ReferentWindow window, User user) {
        new ReferentController(database, window, user);
    }

    /**
     * Metóda pre pridanie listenerov pre tlačidlá a menu itemy nachádzajúce sa
     * na obrazovke referenta po prihlásení. 
     */
    @Override
    public void initController() {
        window.changePasswordListener(() -> changePassword());

        window.costsListener(() -> viewCosts());

        window.logoutListener(() -> logout());

        window.goodsOverviewListener(() -> viewGoodsOverview());

        window.profitsListener(() -> viewProfits());
        
        window.exitListener(e -> System.exit(0));
        
        window.loginListener(e -> viewLoginPage());
        
        window.aboutListener(e -> new About().setVisible(true));
    }

    /**
     * Metóda, ktorá volá controller pre {@code ChangePasswordController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void changePassword() {
        ChangePasswordController.createController(database, new ChangePasswordWindow(), user);
    }
    
    /**
     * Metóda, ktorá zobrazuje panel pre domovskú stránku referenta.
     */
    private void viewLoginPage() {
        hideAll();
        clearAll();
        window.removeListeners();
        window.getpLogin().setVisible(true);
    }

    /**
     * Metóda, ktorá volá controller pre {@code LoginController} a vytvára
     * k nemu prislúchajúcu obrazovku. 
     * 
     * Predstavuje odhlásenie používateľa zo systému.
     */
    private void logout() {
        LoginController.createController(database, new LoginWindow());
        window.dispose();
    }

    /**
     * Metóda, ktorá volá controller pre {@code GoodsCostsController}, pričom 
     * zvyšné panely nachádzajúce sa na obrazovke sa nastavia za neviditeľné.
     */
    private void viewCosts() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsCostsController.createController(database, window);
    }

    /**
     * Metóda, ktorá volá controller pre {@code GoodsOverviewController}, pričom 
     * zvyšné panely nachádzajúce sa na obrazovke sa nastavia za neviditeľné.
     */
    private void viewGoodsOverview() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsOverviewController.createController(database, window);
    }

    /**
     * Metóda, ktorá volá controller pre {@code GoodsProfitsControlller}, pričom 
     * zvyšné panely nachádzajúce sa na obrazovke sa nastavia za neviditeľné.
     */
    private void viewProfits() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsProfitsControlller.createController(database, window);
    }
    
    /**
     * Metóda pre skrytie všetkých panelov nachádzajúcich sa na obrazovke
     * referenta.
     */
    private void hideAll() {
        window.getspGoodsCosts().setVisible(false);
        window.getspGoodsOverview().setVisible(false);
        window.getspGoodsProfits().setVisible(false);
        window.getpLogin().setVisible(false);
    }
    
    /**
     * Metóda pre vyprázdnenie všetkých komponentov na jednotlivých paneloch.
     */
    private void clearAll() {
        clearProfits();
        clearCosts();
    }
    
    /**
     * Metóda pre vyprázdnenie všetkých komponentov pre panel s informáciami o
     * ziskoch.
     */
    private void clearProfits() {
        window.setFtfProfitsFrom("");
        window.setFtfProfitsTo("");
        window.getTbGoodsProfits().setRowCount(0);
        window.setLbProfits("");
    }
    
    /**
     * Metóda pre vyprázdnenie všetkých komponentov pre panel s informáciami o
     * nákladoch.
     */
    private void clearCosts() {
        window.setFtfCostsFrom("");
        window.setFtfCostsTo("");
        window.getTbGoodsCosts().setRowCount(0);
        window.setLbCosts("");
    }

}
