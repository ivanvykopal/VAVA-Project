/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Warehouseman;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
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
 * Trieda reprezentujúca controller pre vývoz tovarov zo skladu.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public final class GoodsExportController implements Controller {

    /** Atribút item predstavuje položku, ktorú používateľ vybral z tabuľky. **/
    private Item item;
    
    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre prihláseného skladníka. **/
    private final WarehousemanWindow window;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code GoodsExportController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * <p>
     * V tomto konštruktore sa zároveň napĺňa tabuľka s aktuálnymi položkami
     * zo skladu.
     * </p>
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného skladníka
     */
    private GoodsExportController(Database database, WarehousemanWindow window) {
        this.database = database;
        this.window = window;

        fillTable();
        window.getSpGoodsExport().setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code GoodsExportController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného skladníka
     */
    public static void createController(Database database, WarehousemanWindow window) {
        new GoodsExportController(database, window);
    }

    /**
     * Metóda pre pridanie listenerov pre tlačidlo a pre tabuľku na výber položky
     * zo skladu. 
     */
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
                int index = window.getTbGoods1Table().rowAtPoint(e.getPoint());
                window.getTbGoods1Table().setRowSelectionInterval(index, index);
                chooseItem(index);
            }
        });
    }

    /**
     * Metóda pre vývoz tovaru zo skladu.
     * 
     * <p>
     * V rámci tejto metódy sa zistí zvolené množstvo. V prípade správnej hodnoty
     * množstva sa buď vytvorí nová položka, ktorá bude zaznačená ako vyvezená
     * alebo sa celá položka označí za vyvezenú. Závisí to od zadaného množstva.
     * </p>
     * <p>
     * V prípade množstva rovnému množstvu položky sa celá položka označí za 
     * vyvezenú.
     * </p>
     * <p>
     * V prípade množstva menšieho ako je množstvo vybranej položky, vtedy sa
     * vtvorí nová položka s požadovaným množstvom a z pôvodnej položky skladu,
     * sa zadané množstvo odstráni.
     * </p>
     */
    private void exportGoods() {
        if (item == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(GoodsExportController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }
        
        Position pos;
        if (window.getChbProduction().isSelected()) {
            pos = Position.PRODUCTION;
        } else {
            pos = Position.OUT_STOCK;
        }

        int quantity = window.getTfQuantity2();
        if (quantity == -1) {
            JOptionPane.showMessageDialog(window, bundle.getString("QUANTITY_ERROR1"));
            return;
        }
        
        Storage storage = item.getStorage();

        Item newItem = new Item();
        newItem.setReceiptDate(item.getReceiptDate());
        newItem.setExportDate(new Date());
        newItem.setGoods(item.getGoods());
        newItem.setPosition(pos);
        newItem.setStorage(null);
        if (quantity == item.getQuantity()) {
            newItem.setId(item.getId());
            newItem.setQuantity(quantity);
            item = database.setItem(newItem);
            if (item == null) {
                JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_ITEM_ERROR"));
                CustomLogger.getLogger(GoodsExportController.class).warn(bundle.getString("CHANGE_ITEM_ERROR"));
                window.setLbChoosedItem1("");
                return;
            }
            storage.setFree(true);
            storage.setItemCount(storage.getItemCount() - 1);
            database.setStorage(storage);
            SerializationClass.serialize(database);
            item = null;
            clear();
        } else if (quantity > item.getQuantity()) {
            JOptionPane.showMessageDialog(window, bundle.getString("QUANTITY_ERROR2"));
            CustomLogger.getLogger(GoodsExportController.class).warn(bundle.getString("QUANTITY_ERROR2"));
        } else if (quantity == 0) {
            JOptionPane.showMessageDialog(window, bundle.getString("QUANTITY_ERROR3"));
            CustomLogger.getLogger(GoodsExportController.class).warn(bundle.getString("QUANTITY_ERROR3"));
        } else {
            item.setQuantity(item.getQuantity() - quantity);
            newItem.setQuantity(quantity);
            item = database.setItem(item);
            if (item == null) {
                JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_ITEM_ERROR"));
                CustomLogger.getLogger(GoodsExportController.class).warn(bundle.getString("CHANGE_ITEM_ERROR"));
                window.setLbChoosedItem1("");
                return;
            }
            storage.setFree(true);
            database.setStorage(storage);
            addNewItem(newItem);
        }
    }

    /**
     * Metóda pre vytvorenie novej položky skladu.
     * 
     * @param it položka skladu
     */
    private void addNewItem(Item it) {
        it = database.addItem(it);
        if (it == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("ADD_ITEM_ERROR"));
            CustomLogger.getLogger(GoodsExportController.class).warn(bundle.getString("ADD_ITEM_ERROR"));
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("EXPORT_ITEM_INFO"));
            CustomLogger.getLogger(GoodsExportController.class).info(it.getGoods().getCode() +": " + bundle.getString("EXPORT_ITEM_INFO"));
            SerializationClass.serialize(database);
            item = null;
            clear();
        }
    }

    /**
     * Metóda pre výber položky skladu z tabuľky.
     * 
     * @param index riadok, ktorý bol používateľom zvolený z tabuľky
     */
    private void chooseItem(int index) {
        if (index == -1) {
            item = null;
            return;
        }

        int id = (int) window.getTbGoods1Model().getValueAt(index, 0);
        item = database.findItem(id);
        window.setLbChoosedItem1(item.getGoods().getName()+ ", " + item.getStorage().getCode() + ", " + item.getQuantity());
    }

    /**
     * Metóda pre naplnenie tabuľky s položkami skladu.
     */
    private void fillTable() {
        window.getTbGoods1Model().setRowCount(0);
        for (Item it : database.getItemTableIn()) {
            Object[] row = new Object[5];
            row[0] = it.getId();
            row[1] = it.getGoods().getCode();
            row[2] = it.getGoods().getName();
            row[3] = it.getQuantity();
            row[4] = it.getStorage().getCode();
            window.getTbGoods1Model().addRow(row);
        }
    }
    
    /**
     * Metóda pre premazanie komponentov.
     */
    private void clear() {
        fillTable();
        window.setTfQuantity2("");
        window.getChbProduction().setSelected(false);
        window.setLbChoosedItem1("");
    }

}
