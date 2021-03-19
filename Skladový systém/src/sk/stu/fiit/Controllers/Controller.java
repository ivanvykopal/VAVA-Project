/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import sk.stu.fiit.GUI.IWindow;

/**
 *
 * @author Ivan Vykopal
 */
public abstract class Controller {
    protected IWindow window;

    public Controller(IWindow window) {
        this.window = window;
    }
    
    abstract void initController();
}
