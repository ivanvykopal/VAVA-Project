/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Referent;

import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.GUI.ReferentWindow;
import sk.stu.fiit.Model.Database;

/**
 *
 * @author Ivan Vykopal
 */
public abstract class GoodsController implements Controller {
    protected Database database;
    protected ReferentWindow window;

    public GoodsController(Database database, ReferentWindow window) {
        this.database = database;
        this.window = window;
    }
    
    //Prepravka
    protected static class TableItem {

        String name;
        int quantity;
        double incomePrice;
        double exportPrice;

        public TableItem(String name, int quantity, double incomePrice, double exportPrice) {
            this.name = name;
            this.quantity = quantity;
            this.incomePrice = incomePrice;
            this.exportPrice = exportPrice;
        }
    }
    
}
