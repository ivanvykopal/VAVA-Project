/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.WarehousemanWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Item;
import sk.stu.fiit.Model.Position;

/**
 *
 * @author Ivan Vykopal
 */
public final class GoodsExportController implements Controller {

    private Item item;
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

    
    //TODO: pridať checkbox pre nastavenie, že sklad. priestor je voľný
    private void exportGoods() {
        if (item == null) {
            JOptionPane.showMessageDialog(window, "Nie je vybraný žiaden tovar!");
            return;
        }

        int quantity = window.getTfQuantity2();
        if (quantity == -1) {
            JOptionPane.showMessageDialog(window, "Zlý formát množstva!");
            return;
        }

        Item newItem = new Item();
        newItem.setReceiptDate(item.getReceiptDate());
        newItem.setExportDate(new Date());
        newItem.setGoods(item.getGoods());
        newItem.setPosition(Position.OUT_STOCK);
        newItem.setStorage(null);
        if (quantity == item.getQuantity()) {
            item = database.removeItem(item);
            if (item == null) {
                JOptionPane.showMessageDialog(window, "Chyba pri mazaní položky skladu!");
                return;
            }
            item = null;
            newItem.setQuantity(quantity);
            addNewItem(newItem);
        } else if (quantity > item.getQuantity()) {
            JOptionPane.showMessageDialog(window, "Zadané množstvo je väčšie ako množstvo vybranej položky!");
        } else if (quantity == 0) {
            JOptionPane.showMessageDialog(window, "Množstvo je nulové!");
        } else {
            item.setQuantity(item.getQuantity() - quantity);
            newItem.setQuantity(quantity);
            item = database.setItem(item);
            if (item == null) {
                JOptionPane.showMessageDialog(window, "Chyba pri zmene položky skladu!");
                return;
            }
            addNewItem(newItem);
        }
    }

    private void addNewItem(Item it) {
        it = database.addItem(it);
        if (it == null) {
            JOptionPane.showMessageDialog(window, "Chyba pri pridanávaní novej položky skladu!");
        } else {
            JOptionPane.showMessageDialog(window, "Tovar bol vyvezený!");
            item = null;
            fillTable();
            window.setTfQuantity2("");
        }
    }

    private void chooseItem() {
        int index = window.getTbGoods1Table().getSelectedRow();
        if (index == -1) {
            item = null;
            return;
        }

        int id = (int) window.getTbGoods1Model().getValueAt(index, 0);
        item = database.findItem(id);
    }

    private void fillTable() {
        window.getTbGoods1Model().setRowCount(0);
        for (Item it : database.getItemTable()) {
            if (it.getPosition() == Position.IN_STOCK) {
                Object[] row = new Object[5];
                row[0] = it.getId();
                row[1] = it.getGoods().getCode();
                row[2] = it.getGoods().getName();
                row[3] = it.getQuantity();
                row[4] = it.getStorage().getCode();
                window.getTbGoods1Model().addRow(row);
            }
        }
    }

}
