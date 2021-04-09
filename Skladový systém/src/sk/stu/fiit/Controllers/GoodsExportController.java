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
public final class GoodsExportController implements Controller {

    private final Database database;
    private final WarehousemanWindow window;

    private GoodsExportController(Database database, WarehousemanWindow window) {
        this.database = database;
        this.window = window;

        fillTable();
        window.getSpGoodsExport().setVisible(true);

        initController();
    }

    @Override
    public void initController() {
        window.btnExportGoodsAddListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                exportGoods();
            }
        });

        window.getTbGoods1Table().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseItem();
            }
        });
    }

    private void exportGoods() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void chooseItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void fillTable() {
        
    }

}
