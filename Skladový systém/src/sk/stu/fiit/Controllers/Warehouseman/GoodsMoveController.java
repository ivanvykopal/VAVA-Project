/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Warehouseman;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
 * Trieda reprezentujúca controller pre presun tovarov v sklade.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public final class GoodsMoveController implements Controller {

    /** Atribút item predstavuje položku, ktorú používateľ vybral z tabuľky. **/
    private Item item;
    
    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre prihláseného skladníka. **/
    private final WarehousemanWindow window;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    
    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code GoodsMoveController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * <p>
     * V tomto konštruktore sa zároveň napĺňa tabuľka s aktuálnymi položkami
     * zo skladu a tabuľka s voľnými skladovacími priestorami.
     * </p>
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného skladníka
     */
    private GoodsMoveController(Database database, WarehousemanWindow window) {
        this.database = database;
        this.window = window;

        fillStorageTable();
        fillGoodsTable();
        window.getSpGoodsMove().setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code GoodsMoveController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného skladníka
     */
    public static void createController(Database database, WarehousemanWindow window) {
        new GoodsMoveController(database, window);
    }

    /**
     * Metóda pre pridanie listenerov pre tlačidlo a pre tabuľky na výber položky
     * zo skladu a voľnú skladovaciu pozíciu.
     */
    @Override
    public void initController() {
        window.btnMoveGoodsAddListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                moveGoods();
            }
        });

        window.getTbFreeStorage1Table().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int index = window.getTbFreeStorage1Table().rowAtPoint(e.getPoint());
                window.getTbFreeStorage1Table().setRowSelectionInterval(index, index);
                chooseStorage(index);
            }
        });

        window.getTbGoodsTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int index = window.getTbGoodsTable().rowAtPoint(e.getPoint());
                window.getTbGoodsTable().setRowSelectionInterval(index, index);
                chooseItem(index);
            }
        });
    }

    /**
     * Metóda pre presun tovaru v sklade.
     * 
     * <p>
     * V rámci tejto metódy sa zistí zvolené množstvo. V prípade správnej hodnoty
     * množstva sa buď vytvorí nová položka, ktorej bude pridelená nová pozícia
     * alebo sa danej položky pridelí nová pozícia. Závisí to od zadaného množstva.
     * </p>
     * <p>
     * V prípade množstva rovnému množstvu položky sa danej položke zmení pozícia
     * v sklade.
     * </p>
     * <p>
     * V prípade množstva menšieho ako je množstvo vybranej položky, vtedy sa
     * vtvorí nová položka s požadovaným množstvom a z pôvodnej položky skladu,
     * sa zadané množstvo odstráni.
     * </p>
     */
    private void moveGoods() {
        if (item == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }
        
        int quantity = window.getTfQuantity1();
        if (quantity == -1) {
            JOptionPane.showMessageDialog(window, bundle.getString("QUANTITY_ERROR1"));
            return;
        }
        
        Item newItem = new Item();
        newItem.setReceiptDate(item.getReceiptDate());
        newItem.setGoods(item.getGoods());
        newItem.setPosition(Position.IN_STOCK);
        Storage storage = database.findStorage(window.getTfStorageCode1());
        if (storage == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("LOAD_STORAGE_ERROR"));
            CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("LOAD_STORAGE_ERROR"));
            return;
        }
        
        if (window.getChbStorageStatus1().isSelected()) {
            storage.setFree(false);
        }
        
        newItem.setStorage(storage);
        Storage oldStorage = item.getStorage();
        if (quantity == item.getQuantity()) {
            newItem.setId(item.getId());
            newItem.setQuantity(quantity);
            storage.setItemCount(storage.getItemCount() + 1);
            newItem.setStorage(storage);
            item = database.setItem(newItem);
            if (item == null) {
                JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_ITEM_ERROR"));
                CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("CHANGE_ITEM_ERROR"));
                window.setLbChoosedItem("");
                return;
            }
            storage = database.setStorage(item.getStorage());
            if (storage == null) {
                JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_STORAGE_ERROR"));
                CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("CHANGE_STORAGE_ERROR"));
                return;
            }
            oldStorage.setFree(true);
            oldStorage.setItemCount(storage.getItemCount() - 1);
            database.setStorage(oldStorage);
            item = null;
            clear();
            SerializationClass.serialize(database);
        } else if (quantity > item.getQuantity()){
            JOptionPane.showMessageDialog(window, bundle.getString("QUANTITY_ERROR2"));
            CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("QUANTITY_ERROR2"));
        } else if (quantity == 0) {
            JOptionPane.showMessageDialog(window, bundle.getString("QUANTITY_ERROR3"));
            CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("QUANTITY_ERROR3"));
        } else {
            item.setQuantity(item.getQuantity() - quantity);
            newItem.setQuantity(quantity);
            oldStorage.setFree(true);
            item.setStorage(oldStorage);
            item = database.setItem(item);
            if (item == null) {
                JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_ITEM_ERROR"));
                CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("CHANGE_ITEM_ERROR"));
                window.setLbChoosedItem("");
                return;
            }
            storage = database.setStorage(storage);
            if (storage == null) {
                JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_STORAGE_ERROR"));
                CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("CHANGE_STORAGE_ERROR"));
                return;
            }
            database.setStorage(oldStorage);
            addNewItem(newItem);
        }
    }

    /**
     * Metóda pre výber skladovacej pozície z tabuľky voľných pozícií.
     * 
     * @param index riadok, ktorý bol používateľom zvolený z tabuľky
     */
    private void chooseStorage(int index) {
        if (index != -1) {
            String code = (String) window.getTbFreeStorage1Model().getValueAt(index, 0);
            window.setTfStorageCode1(code);
        } else {
            window.setTfStorageCode1("");
        }
    }

    /**
     * Metóda pre výber položky skladu z tabuľky položiek.
     * 
     * @param index riadok, ktorý bol používateľom zvolený z tabuľky
     */
    private void chooseItem(int index) {
        if (index == -1) {
            item = null;
            return;
        }

        int id = (int) window.getTbGoodsModel().getValueAt(index, 0);
        item = database.findItem(id);
        window.setLbChoosedItem(item.getGoods().getName()+ ", " + item.getStorage().getCode() + ", " + item.getQuantity());
    }

    /**
     * Metóda pre naplnenie tabuľky s voľnými pozíciami v sklade.
     */
    private void fillStorageTable() {
        window.getTbFreeStorage1Model().setRowCount(0);
        for (Storage storage : database.getStorageTable()) {
            if (storage.isFree()) {
                Object[] row = new Object[1];
                row[0] = storage.getCode();
                window.getTbFreeStorage1Model().addRow(row);
            }
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
            CustomLogger.getLogger(GoodsMoveController.class).warn(bundle.getString("ADD_ITEM_ERROR"));
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("MOVE_GOODS_INFO"));
            CustomLogger.getLogger(GoodsMoveController.class).warn(it.getGoods().getCode() + ": " + bundle.getString("MOVE_GOODS_INFO"));
            SerializationClass.serialize(database);
            item = null;
            clear();
        }
    }

    /**
     * Metóda pre naplnenie tabuľky s položkami skladu.
     */
    private void fillGoodsTable() {
        window.getTbGoodsModel().setRowCount(0);
        for (Item it : database.getItemTableIn()) {
            Object[] row = new Object[5];
            row[0] = it.getId();
            row[1] = it.getGoods().getCode();
            row[2] = it.getGoods().getName();
            row[3] = it.getQuantity();
            row[4] = it.getStorage().getCode();
            window.getTbGoodsModel().addRow(row);
        }
    }
    
    /**
     * Metóda pre premazanie komponentov.
     */
    private void clear() {
        fillGoodsTable();
        window.setTfQuantity1("");
        window.setTfStorageCode1("");
        fillStorageTable();
        window.getChbStorageStatus1().setSelected(false);
        window.setLbChoosedItem("");
    }

}
