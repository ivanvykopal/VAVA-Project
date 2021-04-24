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
import sk.stu.fiit.GUI.RemoveGoodsWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Goods;
import sk.stu.fiit.Model.SerializationClass;

/**
 * Trieda reprezentujúca controller pre odstraňovanie tovarov zo systému.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public final class RemoveGoodsController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre odstraňovanie tovarov. **/
    private final RemoveGoodsWindow window;
    
    /** Atribút goods predstavuje tovar, ktorý si používateľ zvolil. **/
    private Goods goods = null;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code RemoveGoodsController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * <p>
     * V metóde sa zároveň aj plní tabuľka s existujúcimi tovarmi.
     * </p>
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre odstraňovanie tovarov
     */
    private RemoveGoodsController(Database database, RemoveGoodsWindow window) {
        this.database = database;
        this.window = window;

        fillGoodsTable("");
        window.setVisible(true);

        initController();

    }

    /**
     * Metóda pre vytvorenie {@code RemoveGoodsController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre odstraňovanie tovarov
     */
    public static void createController(Database database, RemoveGoodsWindow window) {
        new RemoveGoodsController(database, window);
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

        window.btnRemoveGoodsAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeGoods();
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
     * napĺňa údaje do textových polí pre zobrazenie informácií o tovare.
     */
    private void chooseGoods() {
        int index = window.getTbGoodsTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(RemoveGoodsController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }

        String code = (String) window.getTbGoodsModel().getValueAt(index, 0);

        goods = database.findGoods(code);
        if (goods == null) {
            clear();
        } else {
            window.setTfCode(code);
            window.setTfName(goods.getName());
            window.setTaDescription(goods.getDescription());
            window.setTfImportPrice("" + goods.getIncomePrice());
            window.setTfExportPrice("" + goods.getExportPrice());
        }
    }

    /**
     * Metóda pre odstránenie tovaru zo systému. Tovar sa zo systému skutočne
     * nevymazáva, ale nastavuje sa mu príznak, že je vymazaný.
     */
    private void removeGoods() {
        if (goods == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(RemoveGoodsController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }

        goods = database.removeGoods(goods);
        if (goods == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("REMOVE_GOODS_ERROR"));
            CustomLogger.getLogger(RemoveGoodsController.class).warn(bundle.getString("REMOVE_GOODS_ERROR"));
            clear();
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("REMOVE_GOODS_INFO"));
            CustomLogger.getLogger(RemoveGoodsController.class).info(goods.getCode() + ": " + bundle.getString("REMOVE_GOODS_INFO"));
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
        Pattern pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
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
    
    /**
     * Metóda pre premazanie komponentov.
     */
    private void clear() {
        window.setTfCode("");
        window.setTfName("");
        window.setTaDescription("");
        window.setTfImportPrice("");
        window.setTfExportPrice("");
    }

}
