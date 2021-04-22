/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Administrator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.AddGoodsWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Goods;
import sk.stu.fiit.Model.SerializationClass;

/**
 * Trieda reprezentujúca controller pre pridávanie tovarov do systému.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public final class AddGoodsController implements Controller {
    
    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre pridávanie tovarov. **/
    private final AddGoodsWindow window;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code AddGoodsController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre pridávanie tovarov
     */
    private AddGoodsController(Database database, AddGoodsWindow window) {
        this.database = database;
        this.window = window;
        window.setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code AddGoodsController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre pridávanie tovarov
     */
    public static void createController(Database database, AddGoodsWindow window) {
        new AddGoodsController(database, window);
    }

    /**
     * Metóda pre pridanie listenera pre tlačidlo. 
     */
    @Override
    public void initController() {
        window.btnAddGoodsAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                addGoods();
            }
        });
    }

    /**
     * Metóda pre pridanie nového tovaru do systému.
     * 
     * <p> 
     * V rámci tejto triedy sa kontroluje to, či sú vyplnené všetky polia, správnosť
     * zadaných cien. Pri správne zadaných údajoch je následne daný tovar pridaný
     * do systému v prípade, ak sa v ňom ešte nenachádza.
     * </p>
     */
    private void addGoods() {
        Goods goods = new Goods();
        goods.setName(window.getTfName());
        goods.setDescription(window.getTaDescription());
        goods.setCode(window.getTfCode());

        goods.setIncomePrice(window.getTfIncomePrice());
        goods.setExportPrice(window.getTfExportPrice());
        if (Boolean.logicalOr(goods.getIncomePrice() == -1, goods.getExportPrice() == -1)) {
            JOptionPane.showMessageDialog(window, bundle.getString("PRICE_ERROR2"));
            return;
        }

        if (goods.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, bundle.getString("EMPTY_ATT_ERROR1"));
            CustomLogger.getLogger(AddGoodsController.class).warn(bundle.getString("EMPTY_ATT_ERROR2"));
            return;
        }

        goods = database.addGoods(goods);
        if (goods == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("GOODS_ERROR"));
            CustomLogger.getLogger(AddGoodsController.class).warn(bundle.getString("GOODS_ERROR"));
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("GOODS_INFO"));
            CustomLogger.getLogger(AddGoodsController.class).info(goods.getCode() + ": " + bundle.getString("GOODS_INFO"));
            SerializationClass.serialize(database);
            window.dispose();
        }
    }

}
