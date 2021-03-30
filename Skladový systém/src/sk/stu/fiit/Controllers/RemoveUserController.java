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
import sk.stu.fiit.GUI.RemoveUserWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class RemoveUserController extends Controller {
    private final RemoveUserWindow window;
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

    private RemoveUserController(Database database, RemoveUserWindow window) {
        super(database);
        this.window = window;
        
        fillUserTable();
        window.setVisible(true);
        
        initController();
    }
    
    public static void createController(Database database, RemoveUserWindow window) {
        new RemoveUserController(database, window);
    }

    @Override
    void initController() {
        window.btnChooseUserAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                chooseUser();
            }
        });
        
        window.btnRemoveUserAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeUser();
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
            switch(user.getType()) {
                case ADMINISTRATOR:
                    window.setTfType("Administrátor");
                    break;
                case WAREHOUSEMAN:
                    window.setTfType("Skladník");
                    break;
                default:
                    window.setTfType("Referent");
                    break;
            }
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
    
    private void removeUser() {
        if (user == null) {
            JOptionPane.showMessageDialog(window, "Nebol vybraný žiaden záznam.");
            return;
        }
        
        try {
            String query = "DELETE FROM users WHERE id = ?;";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(window, "Vybraný používateľ bol vymazaný!");
            ps.close();
            window.setVisible(false);
        } catch (SQLException ex) {
            System.out.println("Chyba!");
        } finally {
            database.closeConnection();
        }
    }
    
    private void fillUserTable() {
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
