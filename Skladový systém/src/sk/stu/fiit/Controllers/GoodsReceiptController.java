/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.WarehousemanWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Item;
import sk.stu.fiit.Model.Position;
import sk.stu.fiit.Model.Storage;

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

        window.getTbFreeStorageTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = window.getTbFreeStorageTable().rowAtPoint(e.getPoint());
                window.getTbFreeStorageTable().setRowSelectionInterval(index, index);
                chooseStorage(index);
            }
        });
    }

    private void acceptGoods() {
        String goodsCode = window.getTfGoodsCode();
        int quantity = window.getTfQuantity();
        String storageCode = window.getTfStorageCode();
        Item item = new Item();
        item.setReceiptDate(new Date());
        item.setGoods(database.findGoods(goodsCode));
        item.setPosition(Position.IN_STOCK);
        item.setQuantity(quantity);

        Storage storage = database.findStorage(storageCode);
        if (storage == null) {
            JOptionPane.showMessageDialog(window, "Chyba pri načítaní skladovacieho priestoru!");
            return;
        } else {
            if (window.getChbStorageStatus().isSelected()) {
                storage.setFree(false);
                storage = database.setStorage(storage);
            }
            item.setStorage(storage);
        }
        database.addItem(item);
        JOptionPane.showMessageDialog(window, "Nový tovar bol prijatý!");
        //pridať clear
    }

    private void chooseStorage(int index) {
        if (index > 0) {
            String code = (String) window.getTbFreeStorageModel().getValueAt(index, 0);
            window.setTfStorageCode(code);
        } else {
            window.setTfStorageCode("");
        }
    }

    private void fillStorageTable() {
        window.getTbFreeStorageModel().setRowCount(0);
        for (Storage storage : database.getStorageTable()) {
            if (storage.isFree()) {
                Object[] row = new Object[1];
                row[0] = storage.getCode();
                window.getTbFreeStorageModel().addRow(row);
            }
        }
    }

}
