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
import sk.stu.fiit.Controllers.Administrator.AddUserController;
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
 * Trieda reprezentujúca controller pre príjem tovarov do skladu.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public final class GoodsReceiptController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre prihláseného skladníka. **/
    private final WarehousemanWindow window;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code GoodsReceiptController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * <p>
     * V tomto konštruktore sa zároveň napĺňa tabuľka s voľnými skladovacími
     * priestorami.
     * </p>
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného skladníka
     */
    private GoodsReceiptController(Database database, WarehousemanWindow window) {
        this.database = database;
        this.window = window;

        fillStorageTable();
        window.getpGoodsReceipt().setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code GoodsReceiptController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre prihláseného skladníka
     */
    public static void createController(Database database, WarehousemanWindow window) {
        new GoodsReceiptController(database, window);
    }

    /**
     * Metóda pre pridanie listenerov pre tlačidlo a pre výber voľnej skladovacej
     * pozície.
     */
    @Override
    public void initController() {
        window.btnAcceptGoodsAddListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                acceptGoods();
            }
        });

        window.getTbFreeStorageTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = window.getTbFreeStorageTable().rowAtPoint(e.getPoint());
                window.getTbFreeStorageTable().setRowSelectionInterval(index, index);
                chooseStorage(index);
            }
        });
    }

    /**
     * Metóda pre vykonanie príjmu tovaru. Na základe kódu tovaru sa vyhľadá daný
     * tovar v databáze a vytvorí sa nová položka skladu s daným tovarom, 
     * vybraným skladovacím priestorom a zadaným množstvom.
     * 
     * Zároveň skladník má možnosť označiť danú pozíciu za obsadenú v prípade,
     * ak sa do danej pozície, žiadna iná položka nezmestí.
     */
    private void acceptGoods() {
        String goodsCode = window.getTfGoodsCode();
        int quantity = window.getTfQuantity();
        String storageCode = window.getTfStorageCode();
        Item item = new Item();
        item.setReceiptDate(new Date());
        item.setGoods(database.findGoods(goodsCode));
        item.setPosition(Position.IN_STOCK);
        item.setQuantity(quantity);

        Storage storage = database.findStorage(storageCode);
        if (storage == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("LOAD_STORAGE_ERROR"));
            CustomLogger.getLogger(GoodsReceiptController.class).warn(bundle.getString("LOAD_STORAGE_ERROR"));
            return;
        } else {
            if (window.getCbStorageStatus().isSelected()) {
                storage.setFree(false);
            }
            item.setStorage(storage);
        }
        
        if (item.getGoods() == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("GOODS_ERROR1"));
            CustomLogger.getLogger(GoodsReceiptController.class).warn(bundle.getString("GOODS_ERROR1"));
            return;
        }
        
        if (item.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, bundle.getString("EMPTY_ATT_ERROR1"));
            CustomLogger.getLogger(GoodsReceiptController.class).warn(bundle.getString("EMPTY_ATT_ERROR2"));
            return;
        }
        
        item = database.addItem(item);
        if (item == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("ADD_ITEM_ERROR"));
            CustomLogger.getLogger(GoodsReceiptController.class).warn(bundle.getString("ADD_ITEM_ERROR"));
            return;
        }
        storage = database.setStorage(storage);
        if (storage == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_STORAGE_ERROR"));
            CustomLogger.getLogger(GoodsReceiptController.class).warn(bundle.getString("CHANGE_STORAGE_ERROR"));
            return;
        }
        SerializationClass.serialize(database);
        JOptionPane.showMessageDialog(window, bundle.getString("RECEIPT_GOODS_INFO"));
        CustomLogger.getLogger(GoodsReceiptController.class).info(item.getGoods().getCode() + ": " + bundle.getString("RECEIPT_GOODS_INFO"));
        
        window.setTfGoodsCode("");
        window.setTfQuantity("");
        window.setTfStorageCode("");
        window.getCbStorageStatus().setSelected(false);
        fillStorageTable();
    }

    /**
     * Metóda pre výber skladovacej pozície z tabuľky voľných pozícií.
     * 
     * @param index riadok, ktorý bol používateľom zvolený z tabuľky
     */
    private void chooseStorage(int index) {
        if (index != -1) {
            String code = (String) window.getTbFreeStorageModel().getValueAt(index, 0);
            window.setTfStorageCode(code);
        } else {
            window.setTfStorageCode("");
        }
    }

    /**
     * Metóda pre naplnenie tabuľky s voľnými pozíciami v sklade.
     */
    private void fillStorageTable() {
        window.getTbFreeStorageModel().setRowCount(0);
        for (Storage storage : database.getStorageTable()) {
            if (storage.isFree()) {
                Object[] row = new Object[1];
                row[0] = storage.getCode();
                window.getTbFreeStorageModel().addRow(row);
            }
        }
    }

}
