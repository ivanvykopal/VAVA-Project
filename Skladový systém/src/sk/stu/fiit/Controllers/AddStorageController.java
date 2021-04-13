/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.AddStorageWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Storage;

/**
 *
 * @author Ivan Vykopal
 */
public final class AddStorageController implements Controller {
    private final Database database;
    private final AddStorageWindow window;

    private AddStorageController(Database database, AddStorageWindow window) {
        this.database = database;
        this.window = window;
        window.setVisible(true);
        
        initController();
    }
    
    public static void createController(Database database, AddStorageWindow window) {
        new AddStorageController(database, window);
    }

    @Override
    public void initController() {
        window.btnAddStorageAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                addStorage();
            }
        });
    }
    
    private void addStorage() {
        Storage storage = new Storage();
        storage.setBuilding(window.getTfBuilding());
        storage.setCode(window.getTfCode());
        storage.setShelf(window.getTfShelf());
        
        if (storage.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, "Je potrebné vyplniť všetky polia!");
            return;
        }
        
        storage = database.addStorage(storage);
        if (storage == null) {
            JOptionPane.showMessageDialog(window, "Zadaný skladovací pristor sa už v systéme nachádza!");
        } else {
            JOptionPane.showMessageDialog(window, "Skladovací priestor bol pridaný!");
            window.dispose();
        }
    }
    
}
