/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import sk.stu.fiit.GUI.ReferentWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Item;
import sk.stu.fiit.Model.Position;

/**
 *
 * @author Ivan Vykopal
 */
public final class GoodsOverviewController implements Controller {

    private final Database database;
    private final ReferentWindow window;

    private GoodsOverviewController(Database database, ReferentWindow window) {
        this.database = database;
        this.window = window;
        
        window.getpGoodsOverview().setVisible(true);

        initController();
    }

    public static void createController(Database database, ReferentWindow window) {
        new GoodsOverviewController(database, window);
    }

    @Override
    public void initController() {
        fillInformation();
    }

    private void fillInformation() {
        HashMap<String, TableItem> table = new HashMap<>();
        
        for (Item item : database.getItemTable()) {
            if (item.getPosition() == Position.IN_STOCK) {
                TableItem i = table.get(item.getGoods().getCode());
                if (i == null) {
                    i = new TableItem(item.getGoods().getName(), item.getQuantity(), item.getGoods().getIncomePrice(), item.getGoods().getExportPrice());
                    table.put(item.getGoods().getCode(), i);
                } else {
                    i.quantity += item.getQuantity();
                    table.replace(item.getGoods().getCode(), i);
                }
            }
        }
        
        window.getTbOverviewGoodsModel().setRowCount(0);
        BigDecimal price = new BigDecimal(0);
        for(String key : table.keySet()) {
            TableItem item = table.get(key);
            Object[] row = new Object[5];
            row[0] = key;
            row[1] = item.name;
            row[2] = item.quantity;
            row[3] = item.incomePrice;
            row[4] = item.exportPrice;
            price.add(new BigDecimal(item.quantity * item.incomePrice));
            window.getTbOverviewGoodsModel().addRow(row);
        }

        window.setLbTotalPrice("Celková cena skladovaného tovaru: " + price.setScale(2, RoundingMode.HALF_UP) + " €");

    }

    //Prepravka
    private static class TableItem {

        String name;
        int quantity;
        double incomePrice;
        double exportPrice;

        public TableItem(String name, int quantity, double incomePrice, double exportPrice) {
            this.name = name;
            this.quantity = quantity;
            this.incomePrice = incomePrice;
            this.exportPrice = exportPrice;
        }
    }

}
