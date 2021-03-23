/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import sk.stu.fiit.GUI.About;
import sk.stu.fiit.GUI.AdministratorWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class AdministratorController extends Controller {
    private final AdministratorWindow window;

    public AdministratorController(Database database, AdministratorWindow window, User user) {
        super(database);
        this.window = window;
        
        window.setVisible(true);
        
        window.setLbName("Meno: " + user.getName());
        window.setLbUsername("Prihlasovacie meno: " + user.getUsername());
        
        initController();
    }

    @Override
    void initController() {
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
    }
    
    private void addStorage() {
        
    }
    
    private void addUser() {
        
    }
    
    private void changePassword() {
        
    }
    
    private void editGoods() {
        
    }
    
    private void editUser() {
        
    }
    
    private void exit() {
        
    }
    
    private void logout() {
        
    }
    
    private void removeGoods() {
        
    }
    
    private void removeStorage() {
        
    }
    
    private void removeUser() {
        
    }
}
