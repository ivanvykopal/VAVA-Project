/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Referent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.ReferentWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Item;

/**
 * Trieda reprezentujúca controller pre zobrazovanie informácií o tovaroch, 
 * množstvách a celkovej cene skladovaného tovaru.
 * 
 * @see GoodsController
 *
 * @author Ivan Vykopal
 */
public final class GoodsOverviewController extends GoodsController {
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();
    
    private HashMap<String, TableItem> table = new HashMap<>();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code GoodsOverviewController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre zobrazovanie nákladov
     */
    private GoodsOverviewController(Database database, ReferentWindow window) {
        super(database, window);
        
        window.getpGoodsOverview().setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code GoodsOverviewController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre zobrazovanie nákladov
     */
    public static void createController(Database database, ReferentWindow window) {
        new GoodsOverviewController(database, window);
    }

    /**
     * Metóda pre naplnenie informácií o tovaroch, množstvách a celkovej cene
     * skladovaného tovaru.
     */
    @Override
    public void initController() {
        fillInformation();
        
        window.btnExportDataAddListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                exportData();
            }
        });
    }

    /**
     * Metóda pre zistenie informácií o tovaroch, množstvách a celkovej cene
     * skladovaného tovaru.
     * 
     * <p>
     * V rámci tejto metódy sa prechádzajú položky, ktoré sa aktuálne nachádzajú 
     * na sklade a pre jednotlivé tovary sa vypočítavajú množstvá. Po výpočte
     * a zistení tovarov nachádzajúcich sa v sklade sú tieto informácie zobrazené
     * v tabuľke a pod tabuľkou.
     * </p>
     */
    private void fillInformation() { 
        for (Item item : database.getItemTableIn()) {
            TableItem i = table.get(item.getGoods().getCode());
            if (i == null) {
                i = new TableItem(item.getGoods().getName(), item.getQuantity(), item.getGoods().getIncomePrice(), item.getGoods().getExportPrice());
                table.put(item.getGoods().getCode(), i);
            } else {
                i.quantity += item.getQuantity();
                table.replace(item.getGoods().getCode(), i);
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

        window.setLbTotalPrice(price.setScale(2, RoundingMode.HALF_UP) + " " + bundle.getString("CURRENCY"));
    }
    
    /**
     * Metoda pre export dát do csv formátu.
     */
    private void exportData() {
        File file = new File("goods_overview.csv");
        file.delete();
        try {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            writer.write(bundle.getString("GOODS_CODE") + "," + bundle.getString("GOODS_NAME")
                    + "," + bundle.getString("QUANTITY") + "," + bundle.getString("GOODS_INCOME") 
                    + "," + bundle.getString("GOODS_EXPORT") + "\n");
            
            for(String key : table.keySet()) {
                TableItem item = table.get(key);
                writer.write(key + "," + item.name + "," + item.quantity + "," + item.incomePrice + "," + item.exportPrice + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(window, bundle.getString("CSV_ERROR"));
            CustomLogger.getLogger(GoodsOverviewController.class).warn(bundle.getString("CSV_ERROR"), ex);
        }
    }

}
