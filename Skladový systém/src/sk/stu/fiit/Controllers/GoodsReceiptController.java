/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import sk.stu.fiit.GUI.WarehousemanWindow;
import sk.stu.fiit.Model.Database;

/**
 *
 * @author Ivan Vykopal
 */
public final class GoodsReceiptController implements Controller {
    private final Database database;
    private final WarehousemanWindow window;
    
    private GoodsReceiptController(Database database, WarehousemanWindow window) {
        this.database = database;
        this.window = window;
        
        fillStorageTable();
        window.getpGoodsReceipt().setVisible(true);
        
        initController();
    }
    
    public static void createController(Database database, WarehousemanWindow window) {
        new GoodsReceiptController(database, window);
    }

    @Override
    public void initController() {
        window.btnAcceptGoodsAddListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                acceptGoods();
            }
        });
    }
    
    private void acceptGoods() {
        
    }
    
    private void fillStorageTable() {
        
    }
    
}
