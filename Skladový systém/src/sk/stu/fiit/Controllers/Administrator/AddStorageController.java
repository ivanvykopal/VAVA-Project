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
import sk.stu.fiit.GUI.AddStorageWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.SerializationClass;
import sk.stu.fiit.Model.Storage;

/**
 * Trieda reprezentujúca controller pre pridávanie skladovacích priestorov do systému.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public final class AddStorageController implements Controller {

    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    private final Database database;
    
    /** Atribút window predstavuje obrazovku pre pridávanie skladovacích priestorov. **/
    private final AddStorageWindow window;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    private final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code AddStorageController}, 
     * nastavenie aktuálneho panelu a pridanie listenerov pre jednotlivé komponenty
     * pre podporu interakcie.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre pridávanie skladovacích priestorov
     */
    private AddStorageController(Database database, AddStorageWindow window) {
        this.database = database;
        this.window = window;
        window.setVisible(true);

        initController();
    }

    /**
     * Metóda pre vytvorenie {@code AddStorageController}.
     *
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre pridávanie skladovacích priestorov
     */
    public static void createController(Database database, AddStorageWindow window) {
        new AddStorageController(database, window);
    }

    /**
     * Metóda pre pridanie listenera pre tlačidlo. 
     */
    @Override
    public void initController() {
        window.btnAddStorageAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                addStorage();
            }
        });
    }

    /**
     * Metóda pre pridanie nového skladovacieho priestoru do systému.
     * 
     * <p>
     * V rámci tejto triedy sa kontroluje to, či sú vyplnené všetky polia.
     * Pri správne zadaných údajoch je následne daný skladovací priestor pridaný
     * do systému v prípade, ak sa v ňom ešte nenachádza.
     * </p>
     */
    private void addStorage() {
        Storage storage = new Storage();
        storage.setBuilding(window.getTfBuilding());
        storage.setCode(window.getTfCode());
        storage.setShelf(window.getTfShelf());

        if (storage.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, bundle.getString("EMPTY_ATT_ERROR1"));
            CustomLogger.getLogger(AddStorageController.class).warn(bundle.getString("EMPTY_ATT_ERROR2"));
            return;
        }

        storage = database.addStorage(storage);
        if (storage == null) {
            JOptionPane.showMessageDialog(window, bundle.getString("STORAGE_ERROR"));
            CustomLogger.getLogger(AddStorageController.class).warn(bundle.getString("STORAGE_ERROR"));
        } else {
            JOptionPane.showMessageDialog(window, bundle.getString("STORAGE_INFO"));
            CustomLogger.getLogger(AddStorageController.class).info(storage.getCode() + ": " + bundle.getString("STORAGE_INFO"));
            SerializationClass.serialize(database);
            window.dispose();
        }
    }

}
