/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Warehouseman;

import sk.stu.fiit.Controllers.ChangePasswordController;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.Controllers.LoginController;
import sk.stu.fiit.GUI.About;
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
        
        window.miAboutAddListener(e -> new About().setVisible(true));
        
        window.miExitAddListener(e -> System.exit(0));
        
        window.miLoginPageAddListener(e -> viewLoginPage());
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

    private void exportGoods() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsExportController.createController(database, window);
    }

    private void moveGoods() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsMoveController.createController(database, window);
    }

    private void accpetGoods() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsReceiptController.createController(database, window);
    }

    private void showStorage() {
        hideAll();
        clearAll();
        window.removeListeners();
        GoodsInfoController.createController(database, window);
    }

    private void logout() {
        LoginController.createController(database, new LoginWindow());
        window.setVisible(false);
    }
    
    private void hideAll() {
        window.getpLogin().setVisible(false);
        window.getpGoodsReceipt().setVisible(false);
        window.getSpGoodsMove().setVisible(false);
        window.getSpGoodsExport().setVisible(false);
        window.getSpGoodsInfo().setVisible(false);
    }
    
    private void clearAll() {
        clearGoodsExport();
        clearGoodsMove();
        clearGoodsReceipt();
        clearGoodsInfo();
    }
    
    private void clearGoodsReceipt() {
        window.setTfGoodsCode("");
        window.setTfQuantity("");
        window.setTfStorageCode("");
        window.getChbStorageStatus().setSelected(false);
    }
    
    private void clearGoodsMove() {
        window.setTfQuantity1("");
        window.setTfStorageCode1("");
        window.setLbChoosedItem("");
        window.getChbStorageStatus1().setSelected(false);
    }
    
    private void clearGoodsExport() {
        window.setTfQuantity2("");
        window.setLbChoosedItem1("");
    }
    
    private void clearGoodsInfo() {
        window.getTbStoragePositionsModel().setRowCount(0);
        window.setTfCodeFilter("");
        window.getChbStorageOption().setSelectedIndex(0);
    }
    
}
