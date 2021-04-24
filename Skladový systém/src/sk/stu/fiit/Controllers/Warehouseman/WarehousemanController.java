/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Warehouseman;

import java.util.ResourceBundle;
import sk.stu.fiit.Controllers.ChangePasswordController;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.Controllers.LoginController;
import sk.stu.fiit.GUI.About;
import sk.stu.fiit.GUI.ChangePasswordWindow;
import sk.stu.fiit.GUI.LoginWindow;
import sk.stu.fiit.GUI.WarehousemanWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 * Trieda reprezentujúca controller pre prihláseného skladníka.
 *
 * @see Controller
 * 
 * @author Ivan Vykopal
 */
public final class WarehousemanController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre prihláseného skladníka. **/
    private final WarehousemanWindow window;
    
    /** Atribút user predstavujúci prihláseného skladníka. **/
    private final User user;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code WarehousemanController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * <p>
     * V tomto konštruktore sa zároveň vypĺňajú informácie o skladníkovi viditeľné
     * na obrazovke ako sú prihlasovacie meno a meno skladníka.
     * </p>
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného skladníka
     * 
     * @param user prihlásený skladník
     */
    private WarehousemanController(Database database, WarehousemanWindow window, User user) {
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
     * Metóda pre vytvorenie {@code WarehousemanController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného skladníka
     * 
     * @param user prihlásený skladník
     */
    public static void createController(Database database, WarehousemanWindow window, User user) {
        new WarehousemanController(database, window, user);
    }

    /**
     * Metóda pre pridanie listenerov pre tlačidlá a menu itemy nachádzajúce sa
     * na obrazovke referenta po prihlásení. 
     */
    @Override
    public void initController() {
        window.changePasswordListener(() -> changePassword());

        window.goodsExportListener(() -> exportGoods());

        window.goodsMoveListener(() -> moveGoods());

        window.goodsReceiptListener(() -> accpetGoods());

        window.showStorageListener(() -> showStorage());

        window.logoutListener(() -> logout());

        window.aboutListener(e -> new About().setVisible(true));

        window.exitListener(e -> System.exit(0));

        window.loginListener(e -> viewLoginPage());
    }

    /**
     * Metóda, ktorá volá controller pre {@code ChangePasswordController} a vytvára
     * k nemu prislúchajúcu obrazovku.
     */
    private void changePassword() {
        ChangePasswordController.createController(database, new ChangePasswordWindow(), user);
    }

    /**
     * Metóda, ktorá zobrazuje panel pre domovskú stránku skladníka.
     */
    private void viewLoginPage() {
        hideAll();
        clearAll();
        window.removeListeners();
        window.getpLogin().setVisible(true);
    }

    /**
     * Metóda, ktorá volá controller pre {@code GoodsExportController}, pričom 
     * zvyšné panely nachádzajúce sa na obrazovke sa nastavia za neviditeľné.
     */
    private void exportGoods() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsExportController.createController(database, window);
    }

    /**
     * Metóda, ktorá volá controller pre {@code GoodsMoveController}, pričom 
     * zvyšné panely nachádzajúce sa na obrazovke sa nastavia za neviditeľné.
     */
    private void moveGoods() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsMoveController.createController(database, window);
    }

    /**
     * Metóda, ktorá volá controller pre {@code GoodsReceiptController}, pričom 
     * zvyšné panely nachádzajúce sa na obrazovke sa nastavia za neviditeľné.
     */
    private void accpetGoods() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsReceiptController.createController(database, window);
    }

    /**
     * Metóda, ktorá volá controller pre {@code GoodsInfoController}, pričom 
     * zvyšné panely nachádzajúce sa na obrazovke sa nastavia za neviditeľné.
     */
    private void showStorage() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsInfoController.createController(database, window);
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
     * Metóda pre skrytie všetkých panelov nachádzajúcich sa na obrazovke
     * skladníka.
     */
    private void hideAll() {
        window.getpLogin().setVisible(false);
        window.getpGoodsReceipt().setVisible(false);
        window.getSpGoodsMove().setVisible(false);
        window.getSpGoodsExport().setVisible(false);
        window.getSpGoodsInfo().setVisible(false);
    }

    /**
     * Metóda pre vyprázdnenie všetkých komponentov na jednotlivých paneloch.
     */
    private void clearAll() {
        clearGoodsExport();
        clearGoodsMove();
        clearGoodsReceipt();
        clearGoodsInfo();
    }

    /**
     * Metóda pre vyprázdnenie všetkých komponentov pre panel príjmu tovarov.
     */
    private void clearGoodsReceipt() {
        window.setTfGoodsCode("");
        window.setTfQuantity("");
        window.setTfStorageCode("");
        window.getCbStorageStatus().setSelected(false);
    }

    /**
     * Metóda pre vyprázdnenie všetkých komponentov pre panel presunu tovarov.
     */
    private void clearGoodsMove() {
        window.setTfQuantity1("");
        window.setTfStorageCode1("");
        window.setLbChoosedItem("");
        window.getChbStorageStatus1().setSelected(false);
    }

    /**
     * Metóda pre vyprázdnenie všetkých komponentov pre panel vývozu tovarov.
     */
    private void clearGoodsExport() {
        window.setTfQuantity2("");
        window.setLbChoosedItem1("");
        window.getChbProduction().setSelected(false);
    }

    /**
     * Metóda pre vyprázdnenie všetkých komponentov pre panel informácií o
     * položkách skladu.
     */
    private void clearGoodsInfo() {
        window.getTbStoragePositionsModel().setRowCount(0);
        window.setTfCodeFilter("");
        window.getCbStorageOption().setSelectedIndex(0);
    }

}
