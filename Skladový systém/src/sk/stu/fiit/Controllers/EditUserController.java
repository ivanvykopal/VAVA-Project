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
import sk.stu.fiit.GUI.EditUserWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class EditUserController extends Controller {
    private final EditUserWindow window;
    private static ArrayList<User> usersList = new ArrayList<>();
    private User user = null;

    static {
        try {
            String query = "SELECT id, username, name, type, email FROM users;";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String type = rs.getString("type");
                String email = rs.getString("email");
                usersList.add(new User(id, username, email, name, type));
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
    
    private EditUserController(Database database, EditUserWindow window) {
        super(database);
        this.window = window;
        
        fillUsersTable();
        window.setVisible(true);
    
        initController();
    }
    
    public static void createController(Database database, EditUserWindow window) {
        new EditUserController(database, window);
    }

    @Override
    void initController() {
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
    
    private void fillUsersTable() {
        for (User u : usersList) {
            Object[] row = new Object[4];
            row[0] = u.getUsername();
            row[1] = u.getName();
            row[2] = u.getEmail();
            row[3] = u.getTypeString();
            window.getTbUsersModel().addRow(row);
        }
    }
    
}
