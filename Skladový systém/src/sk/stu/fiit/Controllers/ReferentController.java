/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import sk.stu.fiit.GUI.ChangePasswordWindow;
import sk.stu.fiit.GUI.LoginWindow;
import sk.stu.fiit.GUI.ReferentWindow;
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

    private ReferentController(Database database, ReferentWindow window, User user) {
        this.database = database;
        this.window = window;
        this.user = user;
        
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
    }

    private void changePassword() {
        ChangePasswordController.createController(database, new ChangePasswordWindow(), user);
    }

    private void logout() {
        LoginController.createController(database, new LoginWindow());
        window.setVisible(false);
    }

    private void viewCosts() {
        GoodsCostsController.createController(database, window);
    }

    private void viewGoodsOverview() {
        GoodsOverviewController.createController(database, window);
    }

    private void viewProfits() {

    }

}
