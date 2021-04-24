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

/**
 * Trieda reprezentujúca controller pre informácie o sklade a položkách skladu.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public final class GoodsInfoController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre prihláseného skladníka. **/
    private final WarehousemanWindow window;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    
    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code GoodsInfoController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného skladníka
     */
    private GoodsInfoController(Database database, WarehousemanWindow window) {
        this.database = database;
        this.window = window;
        
        window.getSpGoodsInfo().setVisible(true);

        initController();
    }
    
    /**
     * Metóda pre vytvorenie {@code GoodsInfoController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného skladníka
     */
    public static void createController(Database database, WarehousemanWindow window) {
        new GoodsInfoController(database, window);
    }

    /**
     * Metóda pre pridanie listenera pre tlačidlo.
     */
    @Override
    public void initController() {
        window.btnSearchAddListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                fillTable();
            }
        });
    }

    /**
     * Metóda pre naplnenie tabuľky položkami skladu. Tieto položky sú vybrané
     * na základe možností zvolenými používateľom. Medzi možnosti patrí to, či
     * sa budú zobrazovať položky na základe skladovacieho preistoru alebo na
     * základe tovarov.
     * 
     * <p>
     * Používateľ má možnosť filtrovania, pri filtrovaní sa rozlišuje možnosť,
     * ktorú používateľ zvolil.
     * </p>
     */
    private void fillTable() {
        window.getTbStoragePositionsModel().setRowCount(0);
        String filter = window.getTfCodeFilter();
        int typeIndex = window.getCbStorageOption().getSelectedIndex();

        Pattern pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
        switch (typeIndex) {
            case 0:
                JOptionPane.showMessageDialog(window, bundle.getString("OPTION_ERROR"));
                return;
            case 1:
                for (Item item : database.getItemTableIn()) {
                    if (pattern.matcher(item.getGoods().getCode()).find()) {
                        addRow(item);
                    }
                }
                break;
            case 2:
                for (Item item : database.getItemTableIn()) {
                    if (pattern.matcher(item.getStorage().getCode()).find()) {
                        addRow(item);
                    }
                }
                break;
        }
    }

    /**
     * Metóda pre pridanie riadku do tabuľky.
     * 
     * @param item položka, ktorú pridávame do tabuľky
     */
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
