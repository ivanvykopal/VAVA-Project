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
 *
 * @author Ivan Vykopal
 */
public final class ReferentController implements Controller {

    private final Database database;
    private final ReferentWindow window;
    private final User user;
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    private ReferentController(Database database, ReferentWindow window, User user) {
        this.database = database;
        this.window = window;
        this.user = user;
        
        window.setLbName(bundle.getString("NAME_LB") + ": " + user.getName());
        window.setLbUsername(bundle.getString("USER_USERNAME") + ": " + user.getUsername());
        
        window.getpLogin().setVisible(true);

        initController();
    }

    public static void createController(Database database, ReferentWindow window, User user) {
        new ReferentController(database, window, user);
    }

    @Override
    public void initController() {
        window.changePasswordListener(() -> changePassword());

        window.costsListener(() -> viewCosts());

        window.logoutListener(() -> logout());

        window.goodsOverviewListener(() -> viewGoodsOverview());

        window.profitsListener(() -> viewProfits());
        
        window.miExitAddListener(e -> System.exit(0));
        
        window.miLoginPageAddListener(e -> viewLoginPage());
        
        window.miAboutAddListener(e -> new About().setVisible(true));
    }

    private void changePassword() {
        ChangePasswordController.createController(database, new ChangePasswordWindow(), user);
    }
    
    private void viewLoginPage() {
        hideAll();
        clearAll();
        window.removeListeners();
        window.getpLogin().setVisible(true);
    }

    private void logout() {
        LoginController.createController(database, new LoginWindow());
        window.setVisible(false);
    }

    private void viewCosts() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsCostsController.createController(database, window);
    }

    private void viewGoodsOverview() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsOverviewController.createController(database, window);
    }

    private void viewProfits() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsProfitsControlller.createController(database, window);
    }
    
    private void hideAll() {
        window.getpGoodsCosts().setVisible(false);
        window.getpGoodsOverview().setVisible(false);
        window.getpGoodsProfits().setVisible(false);
        window.getpLogin().setVisible(false);
    }
    
    private void clearAll() {
        clearProfits();
        clearCosts();
    }
    
    private void clearProfits() {
        window.setFtfProfitsFrom("");
        window.setFtfProfitsTo("");
        window.getTbGoodsProfits().setRowCount(0);
        window.setLbProfits("");
    }
    
    private void clearCosts() {
        window.setFtfCostsFrom("");
        window.setFtfCostsTo("");
        window.getTbGoodsCosts().setRowCount(0);
        window.setLbCosts("");
    }

}
