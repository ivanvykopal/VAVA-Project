/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.AddStorageWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Storage;

/**
 *
 * @author Ivan Vykopal
 */
public final class AddStorageController extends Controller {
    private final AddStorageWindow window;

    private AddStorageController(Database database, AddStorageWindow window) {
        super(database);
        this.window = window;
        
        initController();
    }
    
    public static void createController(Database database, AddStorageWindow window) {
        new AddStorageController(database, window);
    }

    @Override
    void initController() {
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
        
        try {
            String query = "INSERT INTO storage (code, building, shelf) VALUES (?, ?, ?);";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setString(1,storage.getCode());
            ps.setString(2,storage.getBuilding());
            ps.setString(3,storage.getShelf());
            
            ps.executeUpdate();

            ps.close();
            JOptionPane.showMessageDialog(window, "Skladovací priestor bol pridaný!");
            window.setVisible(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(window, "Nastala chyba pri načítaní databázy!\n Opakujte prihlásenie!");
        } finally {
            database.closeConnection();
        }
    }
    
}
