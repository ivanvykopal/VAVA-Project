/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

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
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class AdministratorController implements Controller {
    private final Database database;
    private final AdministratorWindow window;
    private final User user;

    private AdministratorController(Database database, AdministratorWindow window, User user) {
        this.database = database;
        this.window = window;
        this.user = user;
        
        window.setVisible(true);
        
        window.setLbName("Meno: " + user.getName());
        window.setLbUsername("Prihlasovacie meno: " + user.getUsername());
        
        initController();
    }
    
    public static void createController(Database database, AdministratorWindow window, User user) {
        new AdministratorController(database, window, user);
    }

    @Override
    public void initController() {
        window.addGoodsListener(() -> addGoods());
        
        window.addStorageListener(() -> addStorage());
        
        window.addUserListener(() ->  addUser());
        
        window.aboutListener(e -> new About().setVisible(true));
        
        window.changePasswordListener(() -> changePassword());
        
        window.editGoodsListener(() -> editGoods());
        
        window.editUserListener(() -> editUser());
        
        window.exitListener(e -> exit());
        
        window.logoutListener(() -> logout());
        
        window.removeGoodsListener(() -> removeGoods());
        
        window.removeStorageListener(() -> removeStorage());
        
        window.removeUserListener(() -> removeUser());
        
    }
    
    private void addGoods() {
        AddGoodsController.createController(database, new AddGoodsWindow());
    }
    
    private void addStorage() {
        AddStorageController.createController(database, new AddStorageWindow());
    }
    
    private void addUser() {
        AddUserController.createController(database, new AddUserWindow());
    }
    
    private void changePassword() {
        ChangePasswordController.createController(database, new ChangePasswordWindow(), user);
    }
    
    private void editGoods() {
        EditGoodsController.createController(database, new EditGoodsWindow());
    }
    
    private void editUser() {
        EditUserController.createController(database, new EditUserWindow());
    }
    
    private void exit() {
        System.exit(0);
    }
    
    private void logout() {
        new LoginController(new LoginWindow(), database);
        window.setVisible(false);
    }
    
    private void removeGoods() {
        RemoveGoodsController.createController(database, new RemoveGoodsWindow());
    }
    
    private void removeStorage() {
        RemoveStorageController.createController(database, new RemoveStorageWindow());
        
    }
    
    private void removeUser() {
        RemoveUserController.createController(database, new RemoveUserWindow());
    }
}
