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
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.AddUserWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class AddUserController extends Controller {
    private final AddUserWindow window;

    private AddUserController(Database database, AddUserWindow window) {
        super(database);
        this.window = window;
        
        initController();
    }

    public static void createController(Database database, AddUserWindow window) {
        new AddUserController(database, window);
    }

    @Override
    void initController() {
        window.btnAddUserAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                addUser();
            }
        });
    }
    
    private void addUser() {
        User user = new User();
        user.setUsername(window.getTfUsername());
        user.setName(window.getTfName());
        user.setEmail(window.getTfEmail());
        user.setType((String) window.getCbType().getSelectedItem());
        
        if (user.isAnyAttributeEmpty()) {
            JOptionPane.showMessageDialog(window, "Je potrebné vyplniť všetky polia!");
            return;
        }
        
        try {
            String query = "INSERT INTO user (username, name, type, email) VALUES (?, ?, ?, ?);";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setString(1, "'" + user.getUsername() + "'");
            ps.setString(2, "'" + user.getName() + "'");
            ps.setString(3, "'" + user.getTypeString() + "'");
            ps.setString(4, "'" + user.getEmail() + "'");
            
            ResultSet rs = ps.executeQuery();
            
            //TODO: zistiť či tovar bol skutočne pridaný
            rs.close();
            ps.close();
            JOptionPane.showMessageDialog(window, "Používateľ bol pridaný!");
            window.setVisible(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(window, "Nastala chyba pri načítaní databázy!\n Opakujte prihlásenie!");
        } finally {
            database.closeConnection();
        }    
    }
    
}
