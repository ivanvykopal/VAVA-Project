/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.WarehousemanWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Item;

/**
 *
 * @author Ivan Vykopal
 */
public final class GoodsInfoController implements Controller {

    private final Database database;
    private final WarehousemanWindow window;

    private GoodsInfoController(Database database, WarehousemanWindow window) {
        this.database = database;
        this.window = window;

        initController();
    }

    @Override
    public void initController() {
        window.btnSearchAddListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                fillTable();
            }
        });
    }

    private void fillTable() {
        window.getTbStoragePositionsModel().setRowCount(0);
        String filter = window.getTfCodeFilter();
        int typeIndex = window.getChbStorageOption().getSelectedIndex();

        switch (typeIndex) {
            case 0:
                JOptionPane.showMessageDialog(window, "Nie je zvolená žiadna možnosť!");
                return;
            case 1:
                for (Item item : database.getItemTable()) {
                    if (Pattern.matches("*" + filter + "*", item.getGoods().getCode())) {
                        addRow(item);
                    }
                }
                break;
            case 2:
                for (Item item : database.getItemTable()) {
                    if (Pattern.matches("*" + filter + "*", item.getStorage().getCode())) {
                        addRow(item);
                    }
                }
                break;
        }
    }

    private void addRow(Item item) {
        Object[] row = new Object[7];
        row[0] = item.getStorage().getCode();
        row[1] = item.getStorage().getBuilding();
        row[2] = item.getStorage().getShelf();
        row[3] = item.getGoods().getCode();
        row[4] = item.getGoods().getName();
        row[5] = item.getQuantity();
        row[6] = new SimpleDateFormat("dd.MM.YYYY").format(item.getReceiptDate());
        window.getTbStoragePositionsModel().addRow(row);
    }

}
