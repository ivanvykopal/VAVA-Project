/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Administrator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.EditGoodsWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Goods;
import sk.stu.fiit.Model.SerializationClass;

/**
 * Trieda reprezentujúca controller pre úpravu tovarov v systéme.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public final class EditGoodsController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre úpravu tovarov. **/
    private final EditGoodsWindow window;
    
    /** Atribút goods predstavuje tovar, ktorý si používateľ zvolil. **/
    private Goods goods = null;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code EditGoodsController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * <p>
     * V metóde sa zároveň aj plní tabuľka s existujúcimi tovarmi.
     * </p>
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre úpravu tovarov
     */
    private EditGoodsController(Database database, EditGoodsWindow window) {
        this.database = database;
        this.window = window;

        fillGoodsTable("");
        window.setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code EditGoodsController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre úpravu tovarov
     */
    public static void createController(Database database, EditGoodsWindow window) {
        new EditGoodsController(database, window);
    }

    /**
     * Metóda pre pridanie listenerov pre jednotlivé tlačidlá. 
     */
    @Override
    public void initController() {
        window.btnChooseGoodsAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseGoods();
            }
        });

        window.btnEditGoodsAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                editGoods();
            }
        });

        window.btnFilterAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                filter();
            }
        });
    }

    /**
     * Metóda pre výber tovaru z tabuľky existujúcich tovarov. Táto metóda zároveň
     * napĺňa údaje do textových polí pre následnú úpravu informácií.
     */
    private void chooseGoods() {
        int index = window.getTbGoodsTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(EditGoodsController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }

        String code = (String) window.getTbGoodsModel().getValueAt(index, 0);

        goods = database.findGoods(code);
        if (goods == null) {
            window.setTfCode("");
            window.setTfName("");
            window.setTaDescription("");
            window.setTfImportPrice("");
            window.setTfExportPrice("");
        } else {
            window.setTfCode(code);
            window.setTfName(goods.getName());
            window.setTaDescription(goods.getDescription());
            window.setTfImportPrice("" + goods.getIncomePrice());
            window.setTfExportPrice("" + goods.getExportPrice());
        }
    }

    /**
     * Metóda pre uloženie upravených informácií o tovare.
     * 
     * <p>
     * V rámci metódy sa kontroluje správnosť cien a to, či sú všetky polia 
     * vyplnené.
     * Pri správne zadaných údajoch sa aktualizujú informácie tovaru.
     * </p>
     */
    private void editGoods() {
        if (goods == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(EditGoodsController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }

        goods.setCode(window.getTfCode());
        goods.setDescription(window.getTaDescription());
        goods.setExportPrice(window.getTfExportPrice());
        goods.setIncomePrice(window.getTfImportPrice());
        goods.setName(window.getTfName());

        if (Boolean.logicalOr(goods.getIncomePrice() == -1, goods.getExportPrice() == -1)) {
            JOptionPane.showMessageDialog(window, bundle.getString("PRICE_ERROR2"));
            return;
        }

        if (goods.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, bundle.getString("EMPTY_ATT_ERROR1"));
            CustomLogger.getLogger(EditGoodsController.class).warn(bundle.getString("EMPTY_ATT_ERROR2"));
            return;
        }

        goods = database.setGoods(goods);
        if (goods == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_GOODS_ERROR"));
            CustomLogger.getLogger(EditGoodsController.class).warn(bundle.getString("CHANGE_GOODS_ERROR"));
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("CHANGE_GOODS_INFO"));
            CustomLogger.getLogger(EditGoodsController.class).info(goods.getCode() + ": " + bundle.getString("CHANGE_GOODS_INFO"));
            SerializationClass.serialize(database);
            window.dispose();
        }
    }

    /**
     * Metóda pre filtrovanie položiek v tabuľke na základe údajov zadaných v 
     * textovom poli.
     */
    private void filter() {
        String filter = window.getTfFilter();
        fillGoodsTable(filter);
    }

    /**
     * Metóda pre naplnenie tabuľky s existujúcimi tovarmi na základe filtra.
     * 
     * @param filter reťazec podľa, ktorého filtrujeme položky tabuľky 
     */
    private void fillGoodsTable(String filter) {
        window.getTbGoodsModel().setRowCount(0);
        Pattern pattern = Pattern.compile("*" + filter + "*", Pattern.CASE_INSENSITIVE);
        for (Goods g : database.getGoodsTable()) {
            if (!g.isDeleted()) {
                if (pattern.matcher(g.getName()).find()) {
                    Object[] row = new Object[4];
                    row[0] = g.getCode();
                    row[1] = g.getName();
                    row[2] = g.getIncomePrice();
                    row[3] = g.getExportPrice();
                    window.getTbGoodsModel().addRow(row);
                }
            }
        }
    }

}
