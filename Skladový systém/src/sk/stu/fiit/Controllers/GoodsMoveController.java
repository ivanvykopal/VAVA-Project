/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public final class GoodsMoveController implements Controller {

    private final Database database;
    private final WarehousemanWindow window;

    private GoodsMoveController(Database database, WarehousemanWindow window) {
        this.database = database;
        this.window = window;

        fillStorageTable();
        fillGoodsTable();
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
                int index = window.getTbFreeStorage1Table().rowAtPoint(e.getPoint());
                window.getTbFreeStorage1Table().setRowSelectionInterval(index, index);
                chooseStorage(index);
            }
        });

        window.getTbGoodsTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseItem();
            }
        });
    }

    private void moveGoods() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void chooseStorage(int index) {
        if (index > 0) {
            String code = (String) window.getTbFreeStorage1Model().getValueAt(index, 0);
            window.setTfStorageCode1(code);
        } else {
            window.setTfStorageCode1("");
        }
    }

    private void chooseItem() {
        int index = window.getTbGoodsTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, "Nie je vybraný žiaden tovar!");
            return;
        }

        int id = (int) window.getTbGoodsModel().getValueAt(index, 0);
        Item item = database.findItem(id);
        if (item == null) {
            JOptionPane.showMessageDialog(window, "Chyba pri načítaní položky skladu!");
            return;  
        }
        
        int quantity = window.getTfQuantity1();
        if (quantity == -1) {
            JOptionPane.showMessageDialog(window, "Zlý formát množstva!");
            return;
        }
        
        Item newItem = new Item();
        newItem.setReceiptDate(item.getReceiptDate());
        newItem.setGoods(item.getGoods());
        newItem.setPosition(Position.IN_STOCK);
        Storage storage = database.findStorage(window.getTfStorageCode1());
        if (storage == null) {
            JOptionPane.showMessageDialog(window, "Chyba pri načítaní skladovacieho priestoru!");
            return;
        }
        
        newItem.setStorage(storage);
        if (quantity == item.getQuantity()) {
            item = database.removeItem(item);
            if (item == null) {
                JOptionPane.showMessageDialog(window, "Chyba pri mazaní poloky skladu!");
                return;
            }
            newItem.setQuantity(quantity);
        } else if (quantity > item.getQuantity()){
            JOptionPane.showMessageDialog(window, "Zadané množstvo je väčšie ako množstvo vybranej položky!");
        } else if (quantity == 0){
            JOptionPane.showMessageDialog(window, "Množstvo je nulové!");
        } else {
            item.setQuantity(item.getQuantity() - quantity);
            newItem.setQuantity(quantity);
            item = database.setItem(item);
            if (item == null) {
                JOptionPane.showMessageDialog(window, "Chyba pri zmene položky skladu!");
            }
            newItem = database.addItem(newItem);
            if (newItem == null) {
                JOptionPane.showMessageDialog(window, "Chyba pri pridanávaní novej položky skladu!");
            } else {
                JOptionPane.showMessageDialog(window, "Tovar bol premiestnený!");
                //pridať clear
            }
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

    private void fillGoodsTable() {
        window.getTbGoodsModel().setRowCount(0);
        for (Item item : database.getItemTable()) {
            if (item.getPosition() == Position.IN_STOCK) {
                Object[] row = new Object[5];
                row[0] = item.getId();
                row[1] = item.getGoods().getCode();
                row[2] = item.getGoods().getName();
                row[3] = item.getQuantity();
                row[4] = item.getStorage().getCode();
                window.getTbGoodsModel().addRow(row);
            }
        }
    }

}
