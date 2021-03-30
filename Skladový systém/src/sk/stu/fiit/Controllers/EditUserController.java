/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.EditUserWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class EditUserController implements Controller {
    private final Database database;
    private final EditUserWindow window;
    private User user = null;
    private int offset = 0;
    
    private EditUserController(Database database, EditUserWindow window) {
        this.database = database;
        this.window = window;
        new Thread(() -> fillUsersTable("", offset, "administrator")).start();
        
        window.setVisible(true);
    
        initController();
    }
    
    public static void createController(Database database, EditUserWindow window) {
        new EditUserController(database, window);
    }

    @Override
    public void initController() {
        window.btnChooseUserAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseUser();
            }
        });
        
        window.btnEditUserAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                editUser();
            }
        });
        
        window.btnFilterAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                filter();
            }
        });
        
        window.btnNextAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
               next();
            }
        });
        
        window.btnPreviousAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                previous();
            }
        });
        
        window.getCbTypeFilter().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String filter = window.getTfFilter();
                    fillUsersTable(filter, offset, convertType((String) window.getCbTypeFilter().getSelectedItem()));
                }
            }
        });
    }
    
    private void chooseUser() {
        int index = window.getTbUsersTable().getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }
        
        String username = (String) window.getTbUsersModel().getValueAt(index, 0);
        
        try {
            String query = "SELECT id, username, name, type, email FROM users WHERE username = ?;";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setType(rs.getString("type"));
                user.setUsername(rs.getString("username"));
            }
            
            window.setTfEmail(user.getEmail());
            window.setTfName(user.getName());
            window.setCbType(user.getType());
            window.setTfUsername(user.getUsername());
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("chyba!");
        } finally {
            database.closeConnection();
        }
    }
    
    private void editUser() {
        if (user == null) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }
        
        user.setEmail(window.getTfEmail());
        user.setName(window.getTfName());
        user.setType((String) window.getCbType().getSelectedItem());
        user.setUsername(window.getTfUsername());
        
        if (user.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, "Je potrebné vyplniť všetky polia!");
            return;
        }
        
        try {
            String query = "UPDATE users SET username = ?, name = ?, type = ?, email = ? WHERE id = ?;";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getTypeString());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getId());
            
            ps.executeUpdate();
            
            ps.close();
            JOptionPane.showMessageDialog(window, "Používateľ bol upravený!");
            window.setVisible(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(window, "Nastala chyba pri načítaní databázy!\n Opakujte prihlásenie!");
        } finally {
            database.closeConnection();
        }  
    }
    
    private void next() {
        offset += 100;
        String filter = window.getTfFilter();
        fillUsersTable(filter, offset, convertType((String) window.getCbTypeFilter().getSelectedItem()));
    }
    
    private void previous() {
        if (offset == 0) {
            JOptionPane.showMessageDialog(window, "Ste na začiatku zoznamu.");
            return;
        }
        offset -= 100;
        String filter = window.getTfFilter();
        fillUsersTable(filter, offset, convertType((String) window.getCbTypeFilter().getSelectedItem()));
    }
    
    private void filter() {
        offset = 0;
        String filter = window.getTfFilter();
        fillUsersTable(filter, offset, convertType((String) window.getCbTypeFilter().getSelectedItem()));
    }
    
    private void fillUsersTable(String filter, int offset, String type) {
        window.getTbUsersModel().setRowCount(0);
        try {
            String query = "SELECT id, username, name, type, email FROM users "
                    + " WHERE UPPER(name) LIKE UPPER('%" + filter + "%')"
                    + " AND type = '" + type + "'"
                    + " ORDER BY name LIMIT 100 OFFSET " + offset +";";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            int pocet = 0;
            Object[] row = new Object[4];
            while(rs.next()) {
                row[0] = rs.getString("username");
                row[1] = rs.getString("name");
                row[2] = rs.getString("email");
                row[3] = rs.getString("type");
                window.getTbUsersModel().addRow(row);
                pocet++;
            }
            
            rs.close();
            ps.close();
            if (pocet == 0) {
                JOptionPane.showMessageDialog(window, "Ste na konci zoznamu.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("chyba!");
        } finally {
            database.closeConnection();
        }
    }
    
    private String convertType(String type) {
        switch(type) {
            case "Administrátor": return "administrator";
            case "Referent": return "referent";
            default: return "warehouseman";
        }
    }
    
}
