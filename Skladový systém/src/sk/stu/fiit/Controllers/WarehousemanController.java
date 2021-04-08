/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import sk.stu.fiit.GUI.ChangePasswordWindow;
import sk.stu.fiit.GUI.LoginWindow;
import sk.stu.fiit.GUI.WarehousemanWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class WarehousemanController implements Controller {
    private final Database database;
    private final WarehousemanWindow window;
    private final User user;
    
    private WarehousemanController(Database database, WarehousemanWindow window, User user) {
        this.database = database;
        this.window = window;
        this.user = user;
        
        window.setLbName("Meno: " + user.getName());
        window.setLbUsername("Prihlasovacie meno: " + user.getUsername());
        
        initController();
        
    }
    
    public static void createController(Database database, WarehousemanWindow window, User user) {
        new WarehousemanController(database, window, user);
    }

    @Override
    public void initController() {
        window.changePasswordListener(() -> changePassword());
        
        window.goodsExportListener(() -> exportGoods());
        
        window.goodsMoveListener(() -> moveGoods());
        
        window.goodsReceiptListener(() -> accpetGoods());
        
        window.showStorageListener(() -> showStorage());
        
        window.logoutListener(() -> logout());
    }

    private void changePassword() {
        ChangePasswordController.createController(database, new ChangePasswordWindow(), user);
    }

    private void exportGoods() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void moveGoods() {
        hideAll();
        window.removeListeners();
        GoodsMoveController.createController(database, window);
    }

    private void accpetGoods() {
        hideAll();
        window.removeListeners();
        GoodsReceiptController.createController(database, window);
    }

    private void showStorage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void logout() {
        LoginController.createController(database, new LoginWindow());
        window.setVisible(false);
    }
    
    private void hideAll() {
        window.getpLogin().setVisible(false);
        window.getpGoodsReceipt().setVisible(false);
        window.getSpGoodsMove().setVisible(false);
    }
    
    private void clearGoodsReceipt() {
        window.setTfGoodsCode("");
        window.setTfQuantity("");
        window.setTfStorageCode("");
    }
    
    private void clearGoodsMove() {
        window.setTfQuantity1("");
        window.setTfStorageCode1("");
        window.setLbChoosedItem("");
    }
    
}
