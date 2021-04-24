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
import sk.stu.fiit.GUI.RemoveStorageWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.SerializationClass;
import sk.stu.fiit.Model.Storage;

/**
 * Trieda reprezentujúca controller pre odstraňovanie skladovacích priestorov zo
 * systému.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public final class RemoveStorageController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** 
     * Atribút window predstavuje obrazovku pre odstraňovanie skladovacích 
     * priestorov. 
     **/
    private final RemoveStorageWindow window;
    
    /** 
     * Atribút storage predstavuje skladovací priestor, ktorý si používateľ 
     * zvolil. 
     **/
    private Storage storage = null;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code RemoveStorageController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * <p>
     * V metóde sa zároveň aj plní tabuľka s existujúcimi skladovacími priestormi.
     * </p>
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre odstraňovanie skladovacích priestorov
     */
    private RemoveStorageController(Database database, RemoveStorageWindow window) {
        this.database = database;
        this.window = window;

        fillStorageTable("");
        window.setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code RemoveStorageController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre odstraňovanie skladovacích priestorov
     */
    public static void createController(Database database, RemoveStorageWindow window) {
        new RemoveStorageController(database, window);
    }

    /**
     * Metóda pre pridanie listenerov pre jednotlivé tlačidlá. 
     */
    @Override
    public void initController() {
        window.btnChooseStorageAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseStorage();
            }
        });

        window.btnRemoveStorageAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeStorage();
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
     * Metóda pre výber skladovacieho priestoru z tabuľky existujúcich priestorov.
     * Táto metóda zároveň napĺňa údaje do textových polí pre zobrazenie informácií.
     */
    private void chooseStorage() {
        int index = window.getTbStoragesTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(RemoveStorageController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }

        String code = (String) window.getTbStoragesModel().getValueAt(index, 0);

        storage = database.findStorage(code);
        if (storage == null) {
            clear();
        } else {
            window.setTfBuilding(storage.getBuilding());
            window.setTfCode(storage.getCode());
            window.setTfShelf(storage.getShelf());
            if (storage.getItemCount() > 0) {
                window.setTfContainItem(bundle.getString("YES"));
            } else {
                window.setTfContainItem(bundle.getString("NO"));
            }
        }
    }

    /**
     * Metóda pre odstránenie zvoleného skladovacieho priestoru zo sstému.
     */
    private void removeStorage() {
        if (storage == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("RECORD_ERROR"));
            CustomLogger.getLogger(RemoveStorageController.class).warn(bundle.getString("RECORD_ERROR"));
            return;
        }
        
        if (storage.getItemCount() > 0) {
            JOptionPane.showMessageDialog(window, bundle.getString("FULL_STORAGE_ERROR"));
            CustomLogger.getLogger(RemoveStorageController.class).warn(bundle.getString("FULL_STORAGE_ERROR"));
            return;
        }
        
        storage = database.removeStorage(storage);
        if (storage == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("REMOVE_STORAGE_ERROR"));
            CustomLogger.getLogger(RemoveStorageController.class).warn(bundle.getString("REMOVE_STORAGE_ERROR"));
            clear();
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("REMOVE_STORAGE_INFO"));
            CustomLogger.getLogger(RemoveStorageController.class).info(storage.getCode() + ": " + bundle.getString("REMOVE_STORAGE_INFO"));
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
        fillStorageTable(filter);
    }

    /**
     * Metóda pre naplnenie tabuľky s existujúcimi skladovacími priestormi na
     * základe filtra.
     * 
     * @param filter reťazec podľa, ktorého filtrujeme položky tabuľky 
     */
    private void fillStorageTable(String filter) {
        window.getTbStoragesModel().setRowCount(0);
        Pattern pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
        for (Storage s : database.getStorageTable()) {
            if (pattern.matcher(s.getBuilding()).find()) {
                Object[] row = new Object[4];
                row[0] = s.getCode();
                row[1] = s.getBuilding();
                row[2] = s.getShelf();
                if (s.getItemCount() > 0) {
                    row[3] = bundle.getString("YES");
                } else {
                    row[3] = bundle.getString("NO");
                }
                window.getTbStoragesModel().addRow(row);
            }
        }
    }
    
    /**
     * Metóda pre premazanie komponentov.
     */
    private void clear() {
        window.setTfBuilding("");
        window.setTfCode("");
        window.setTfShelf("");
        window.setTfContainItem("");
    }

}
