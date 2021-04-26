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
import javax.swing.JOptionPane;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.ReferentWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Item;

/**
 * Trieda reprezentujúca controller pre zobrazovanie informácie o nákladoch za
 * zvolené obdobie.
 * 
 * @see GoodsController
 *
 * @author Ivan Vykopal
 */
public final class GoodsCostsController extends GoodsController {

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code GoodsCostsController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre zobrazovanie nákladov
     */
    private GoodsCostsController(Database database, ReferentWindow window) {
        super(database, window);
        
        window.getspGoodsCosts().setVisible(true);
        
        initController();
    }
    
    /**
     * Metóda pre vytvorenie {@code GoodsCostsController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre zobrazovanie nákladov
     */
    public static void createController(Database database, ReferentWindow window) {
        new GoodsCostsController(database, window);
    }

    /**
     * Metóda pre pridanie listenera pre tlačidlo. 
     */
    @Override
    public void initController() {
        window.btnViewCostsAddListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                viewCosts();
            }
        });
    }
    
    /**
     * Metóda pre zobrazenie nákladov firmy za zvolené obdobie.
     * 
     * <p>
     * V rámci tejto metódy sa zistia všetky položky, ktoré boli prijaté za
     * obdobie zadané používateľom. Na základe získaných položiek sa vypočítajú
     * náklady. Položky prijaté za dané obdobie sa zobrazia v tabuľke a výsledná
     * cena pod tabuľkou.
     * </p>
     */
    private void viewCosts() {
        Date from;
        Date to;
        try {
            from = new SimpleDateFormat(bundle.getString("DATE_FORMAT")).parse(window.getFtfCostsFrom());
            to = new SimpleDateFormat(bundle.getString("DATE_FORMAT")).parse(window.getFtfCostsTo());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(window, bundle.getString("DATE_ERROR"));
            CustomLogger.getLogger(GoodsCostsController.class).warn(bundle.getString("DATE_ERROR"), ex);
            return;
        }
        
        if (from.after(to)) {
            JOptionPane.showMessageDialog(window, bundle.getString("DATE_ERROR2"));
            CustomLogger.getLogger(GoodsCostsController.class).warn(bundle.getString("DATE_ERROR2"));
            return;
        }
        
        HashMap<String, TableItem> table = new HashMap<>();
        
        for (Item item : database.getItemTableAll()) {
            if (matchDates(item.getReceiptDate(), from, to)) {
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
        
        window.getTbGoodsCosts().setRowCount(0);
        double price = 0;
        for(String key : table.keySet()) {
            TableItem item = table.get(key);
            Object[] row = new Object[5];
            row[0] = key;
            row[1] = item.name;
            row[2] = item.quantity;
            row[3] = item.incomePrice;
            row[4] = item.exportPrice;
            price += item.quantity * item.incomePrice;
            window.getTbGoodsCosts().addRow(row);
        }
        
        window.setLbCosts(new BigDecimal(price).setScale(2, RoundingMode.HALF_UP) + " "+ bundle.getString("CURRENCY"));
    }
    
}
