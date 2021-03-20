/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import sk.stu.fiit.GUI.IWindow;
import sk.stu.fiit.Model.Database;

/**
 *
 * @author Ivan Vykopal
 */
public abstract class Controller {
    protected Database database;

    public Controller(Database database) {
        this.database = database;
    }
    
    abstract void initController();
}
