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
 * Trieda reprezentujúca controller pre zobrazovanie informácie o ziskoch za
 * zvolené obdobie.
 * 
 * @see GoodsController
 *
 * @author Ivan Vykopal
 */
public final class GoodsProfitsControlller extends GoodsController {

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code GoodsProfitsControlller}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre zobrazovanie nákladov
     */
    private GoodsProfitsControlller(Database database, ReferentWindow window) {
        super(database, window);

        window.getspGoodsProfits().setVisible(true);

        initController();
    }
    
    /**
     * Metóda pre vytvorenie {@code GoodsProfitsControlller}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre zobrazovanie nákladov
     */
    public static void createController(Database database, ReferentWindow window) {
        new GoodsProfitsControlller(database, window);
    }

    /**
     * Metóda pre pridanie listenera pre tlačidlo. 
     */
    @Override
    public void initController() {
        window.btnViewProfitsAddListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                viewProfits();
            }
        });
    }
    
    /**
     * Metóda pre zobrazenie ziskov firmy za zvolené obdobie.
     * 
     * <p>
     * V rámci tejto metódy sa zistia všetky položky, ktoré boli vyvezené za
     * obdobie zadané používateľom. Na základe získaných položiek sa vypočítajú
     * zisky. Položky vyvezené za dané obdobie sa zobrazia v tabuľke a výsledná
     * cena pod tabuľkou.
     * </p>
     */
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
        
        for (Item item : database.getItemTableOut()) {
            if (matchDates(item.getExportDate(), from, to)) {
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
        double price = 0;
        for(String key : table.keySet()) {
            TableItem item = table.get(key);
            Object[] row = new Object[5];
            row[0] = key;
            row[1] = item.name;
            row[2] = item.quantity;
            row[3] = item.incomePrice;
            row[4] = item.exportPrice;
            price += item.quantity * (item.exportPrice - item.incomePrice);
            window.getTbGoodsProfits().addRow(row);
        }
        
        window.setLbProfits(new BigDecimal(price).setScale(2, RoundingMode.HALF_UP) + " " + bundle.getString("CURRENCY"));
    }
    
}
