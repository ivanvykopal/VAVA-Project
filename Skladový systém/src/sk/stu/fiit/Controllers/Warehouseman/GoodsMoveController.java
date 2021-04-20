/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Warehouseman;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.WarehousemanWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Item;
import sk.stu.fiit.Model.Position;
import sk.stu.fiit.Model.SerializationClass;
import sk.stu.fiit.Model.Storage;

/**
 *
 * @author Ivan Vykopal
 */
public final class GoodsMoveController implements Controller {

    private Item item;
    private final Database database;
    private final WarehousemanWindow window;
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

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
                int index = window.getTbGoodsTable().rowAtPoint(e.getPoint());
                window.getTbGoodsTable().setRowSelectionInterval(index, index);
                chooseItem(index);
            }
        });
    }

    private void moveGoods() {
        if (item == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }
        
        int quantity = window.getTfQuantity1();
        if (quantity == -1) {
            JOptionPane.showMessageDialog(window, bundle.getString("QUANTITY_ERROR1"));
            return;
        }
        
        Item newItem = new Item();
        newItem.setReceiptDate(item.getReceiptDate());
        newItem.setGoods(item.getGoods());
        newItem.setPosition(Position.IN_STOCK);
        Storage storage = database.findStorage(window.getTfStorageCode1());
        if (storage == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("LOAD_STORAGE_ERROR"));
            CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("LOAD_STORAGE_ERROR"));
            return;
        }
        
        if (window.getChbStorageStatus1().isSelected()) {
            storage.setFree(false);
            storage = database.setStorage(storage);
            if (storage == null) {
                JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_STORAGE_ERROR"));
                CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("CHANGE_STORAGE_ERROR"));
                return;
            }
            SerializationClass.serialize(database);
        }
        
        newItem.setStorage(storage);
        if (quantity == item.getQuantity()) {
            item = database.removeItem(item);
            if (item == null) {
                JOptionPane.showMessageDialog(window, bundle.getString("REMOVE_ITEM_ERROR"));
                CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("REMOVE_ITEM_ERROR"));
                return;
            }
            newItem.setQuantity(quantity);
            addNewItem(newItem);
        } else if (quantity > item.getQuantity()){
            JOptionPane.showMessageDialog(window, bundle.getString("QUANTITY_ERROR2"));
            CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("QUANTITY_ERROR2"));
        } else if (quantity == 0) {
            JOptionPane.showMessageDialog(window, bundle.getString("QUANTITY_ERROR3"));
            CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("QUANTITY_ERROR3"));
        } else {
            item.setQuantity(item.getQuantity() - quantity);
            newItem.setQuantity(quantity);
            item = database.setItem(item);
            if (item == null) {
                JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_ITEM_ERROR"));
                CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("CHANGE_ITEM_ERROR"));
                return;
            }
            addNewItem(newItem);
        }
    }

    private void chooseStorage(int index) {
        if (index != -1) {
            String code = (String) window.getTbFreeStorage1Model().getValueAt(index, 0);
            window.setTfStorageCode1(code);
        } else {
            window.setTfStorageCode1("");
        }
    }

    private void chooseItem(int index) {
        if (index == -1) {
            item = null;
            return;
        }

        int id = (int) window.getTbGoodsModel().getValueAt(index, 0);
        item = database.findItem(id);
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
    
    private void addNewItem(Item it) {
        it = database.addItem(it);
        if (it == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("ADD_ITEM_ERROR"));
            CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("ADD_ITEM_ERROR"));
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("MOVE_GOODS_INFO"));
            CustomLogger.getLogger(GoodsMoveController.class).warn(it.getGoods().getCode() + ": " + bundle.getString("MOVE_GOODS_INFO"));
            SerializationClass.serialize(database);
            item = null;
            fillGoodsTable();
            window.setTfQuantity1("");
            window.setTfStorageCode1("");
            fillStorageTable();
            window.getChbStorageStatus1().setSelected(false);
        }
    }

    private void fillGoodsTable() {
        window.getTbGoodsModel().setRowCount(0);
        for (Item it : database.getItemTable()) {
            if (it.getPosition() == Position.IN_STOCK) {
                Object[] row = new Object[5];
                row[0] = it.getId();
                row[1] = it.getGoods().getCode();
                row[2] = it.getGoods().getName();
                row[3] = it.getQuantity();
                row[4] = it.getStorage().getCode();
                window.getTbGoodsModel().addRow(row);
            }
        }
    }

}
