/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static sk.stu.fiit.Controllers.Controller.database;
import sk.stu.fiit.GUI.RemoveStorageWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Storage;

/**
 *
 * @author Ivan Vykopal
 */
public final class RemoveStorageController extends Controller {
    private final RemoveStorageWindow window;
    private static ArrayList<Storage> storageList = new ArrayList<>();
    private Storage storage = null;
    
    static {
        try {
            String query = "SELECT (id, code, building, shelf, containsItem) FROM storage";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String building = rs.getString("building");
                String shelf = rs.getString("shelf");
                boolean containsItem = rs.getBoolean("containsItem");
                storageList.add(new Storage(id, code, building, shelf, containsItem));
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("chyba!");
        } finally {
            database.closeConnection();
        }
    }
    
    public RemoveStorageController(Database database, RemoveStorageWindow window) {
        super(database);
        this.window = window;
        
        fillStorageTable();
        
        initController();
    }

    @Override
    void initController() {
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
    }
    
    private void chooseStorage() {
        int index = window.getTbStoragesTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }
        
        String code = (String) window.getTbStoragesModel().getValueAt(index, 0);
        
        try {
            String query = "SELECT (id, code, building, shelf, containsItem) FROM storage WHERE code = ?";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                storage = new Storage();
                storage.setId(rs.getInt("id"));
                storage.setBuilding(rs.getString("building"));
                storage.setContainsItem(rs.getBoolean("containsItem"));
                storage.setShelf(rs.getString("shelf"));
            }
            
            window.setTfBuilding(storage.getBuilding());
            window.setTfCode(storage.getCode());
            window.setTfShelf(storage.getShelf());
            if (storage.isContainsItem()) {
                window.setTfContainItem("Áno");
            } else {
                window.setTfContainItem("Nie");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("chyba!");
        } finally {
            database.closeConnection();
        }
    }
    
    private void removeStorage() {
        if (storage == null) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }
        
        try {
            String query = "DELETE FROM storage WHERE id = ?";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setInt(1, storage.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(window, "Vybraný skladovací priestor bol vymazaný!");
            ps.close();
            window.setVisible(false);
        } catch (SQLException ex) {
            System.out.println("Chyba!");
        } finally {
            database.closeConnection();
        }
    }
    
    private void fillStorageTable() {
        for (Storage s : storageList) {
            Object[] row = new Object[4];
            row[0] = s.getCode();
            row[1] = s.getBuilding();
            row[2] = s.getShelf();
            if (s.isContainsItem()) {
                row[3] = "Áno";
            } else {
                row[3] = "Nie";
            }
            window.getTbStoragesModel().addRow(row);
        }
    }
    
}
