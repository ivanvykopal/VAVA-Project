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
import sk.stu.fiit.Model.Item;
import sk.stu.fiit.Model.Storage;

/**
 *
 * @author Ivan Vykopal
 */
public final class GoodsMoveController implements Controller {

    private final Database database;
    private final WarehousemanWindow window;
    private final Storage storage = null;
    private final Item item = null;

    private GoodsMoveController(Database database, WarehousemanWindow window) {
        this.database = database;
        this.window = window;

        fillStorageTable();
        window.getSpGoodsMove().setVisible(true);

        initController();
    }

    public static void createController(Database database, WarehousemanWindow window) {
        new GoodsMoveController(database, window);
    }

    @Override
    public void initController() {
        window.btnMoveGoodsAddListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                moveGoods();
            }
        });

        window.getTbFreeStorage1Table().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseFreeStorage();
            }
        });

        window.getTbGoodsTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseGoods();
            }
        });
    }

    private void moveGoods() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void chooseFreeStorage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void chooseGoods() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void fillStorageTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
