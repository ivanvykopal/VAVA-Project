/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.AddGoodsWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Goods;

/**
 *
 * @author Ivan Vykopal
 */
public final class AddGoodsController implements Controller {
    private final Database database;
    private final AddGoodsWindow window;
    
    private AddGoodsController(Database database, AddGoodsWindow window) {
        this.database = database;
        this.window = window;
        window.setVisible(true);
        
        initController();
    }
    
    public static void createController(Database database, AddGoodsWindow window) {
        new AddGoodsController(database, window);
    }

    @Override
    public void initController() {
        window.btnAddGoodsAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                addGoods();
            }
        });
    }
    
    private void addGoods() {
        Goods goods = new Goods();
        goods.setName(window.getTfName());
        goods.setDescription(window.getTaDescription());
        goods.setCode(window.getTfCode());
        
        goods.setIncomePrice(window.getTfIncomePrice());
        goods.setExportPrice(window.getTfExportPrice());
        if (Boolean.logicalOr(goods.getIncomePrice() == -1, goods.getExportPrice() == -1)) {
            JOptionPane.showMessageDialog(window, "Pri cenách sa využíva '.' namiesto ','!");
            return;
        }
        
        if (goods.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, "Je potrebné vyplniť všetky polia!");
            return;
        }
        
        goods = database.addGoods(goods);
        if (goods == null) {
            JOptionPane.showMessageDialog(window, "Zadaný tovar sa už v systéme nachádza!");
        } else {
            JOptionPane.showMessageDialog(window, "Tovar bol pridaný!");
            window.dispose();
        }
        
    }
    
}
