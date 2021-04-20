/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Warehouseman;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.GUI.WarehousemanWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Item;
import sk.stu.fiit.Model.Position;

/**
 *
 * @author Ivan Vykopal
 */
public final class GoodsInfoController implements Controller {

    private final Database database;
    private final WarehousemanWindow window;
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    private GoodsInfoController(Database database, WarehousemanWindow window) {
        this.database = database;
        this.window = window;

        initController();
    }
    
    public static void createController(Database database, WarehousemanWindow window) {
        new GoodsInfoController(database, window);
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

        Pattern pattern = Pattern.compile("*" + filter + "*", Pattern.CASE_INSENSITIVE);
        switch (typeIndex) {
            case 0:
                JOptionPane.showMessageDialog(window, bundle.getString("OPTION_ERROR"));
                return;
            case 1:
                for (Item item : database.getItemTable()) {
                    if (pattern.matcher(item.getGoods().getCode()).find() && item.getPosition() == Position.IN_STOCK) {
                        addRow(item);
                    }
                }
                break;
            case 2:
                for (Item item : database.getItemTable()) {
                    if (pattern.matcher(item.getStorage().getCode()).find() && item.getPosition() == Position.IN_STOCK) {
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
        row[6] = new SimpleDateFormat(bundle.getString("DATE_FORMAT")).format(item.getReceiptDate());
        window.getTbStoragePositionsModel().addRow(row);
    }

}
