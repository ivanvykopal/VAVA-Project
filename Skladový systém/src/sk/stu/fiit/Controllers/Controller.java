/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

/**
 * Interface pre jednotlivé controllere.
 *
 * @author Ivan Vykopal
 */
public interface Controller {
    
    /**
     * Abstraktná trieda pre inicializáciu controllera pre pridávanie listenerov
     * pre jednotlivé komponenty.
     */
    void initController();
}
