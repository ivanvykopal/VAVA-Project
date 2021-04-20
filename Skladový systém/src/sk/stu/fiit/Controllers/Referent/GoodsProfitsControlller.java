/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Referent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.ReferentWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Item;

/**
 *
 * @author Ivan Vykopal
 */
public final class GoodsProfitsControlller extends GoodsController {
    
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    private GoodsProfitsControlller(Database database, ReferentWindow window) {
        super(database, window);

        window.getpGoodsProfits().setVisible(true);

        initController();
    }
    
    public static void createController(Database database, ReferentWindow window) {
        new GoodsProfitsControlller(database, window);
    }

    @Override
    public void initController() {
        window.btnViewProfitsAddListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                viewProfits();
            }
        });
    }
    
    private void viewProfits() {
        Date from;
        Date to;
        try {
            from = new SimpleDateFormat(bundle.getString("DATE_FORMAT")).parse(window.getFtfProfitsFrom());
            to = new SimpleDateFormat(bundle.getString("DATE_FORMAT")).parse(window.getFtfProfitsTo());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(window, bundle.getString("DATE_ERROR"));
            CustomLogger.getLogger(GoodsProfitsControlller.class).warn(bundle.getString("DATE_ERROR"), ex);
            return;
        }
        
        HashMap<String, TableItem> table = new HashMap<>();
        
        for (Item item : database.getItemTable()) {
            if (item.getExportDate() != null && matchDates(item.getExportDate(), from, to)) {
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
        
        window.getTbGoodsProfits().setRowCount(0);
        BigDecimal price = new BigDecimal(0);
        for(String key : table.keySet()) {
            TableItem item = table.get(key);
            Object[] row = new Object[5];
            row[0] = key;
            row[1] = item.name;
            row[2] = item.quantity;
            row[3] = item.incomePrice;
            row[4] = item.exportPrice;
            price.add(new BigDecimal(item.quantity * (item.exportPrice - item.incomePrice)));
            window.getTbGoodsProfits().addRow(row);
        }
        
        window.setLbProfits(price.setScale(2, RoundingMode.HALF_UP) + " " + bundle.getString("CURRENCY"));
        
    }
    
    private boolean matchDates(Date date, Date from, Date to) {
        return date.equals(from) || date.equals(to) || (date.after(from) && date.before(to));
    }
    
}
