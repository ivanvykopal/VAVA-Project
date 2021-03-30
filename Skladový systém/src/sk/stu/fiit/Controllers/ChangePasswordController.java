/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static sk.stu.fiit.Controllers.Controller.database;
import sk.stu.fiit.GUI.ChangePasswordWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public final class ChangePasswordController extends Controller {
    private final ChangePasswordWindow window;
    private final User user;

    private ChangePasswordController(Database database, ChangePasswordWindow window, User user) {
        super(database);
        this.window = window;
        this.user = user;
        window.setVisible(true);
        
        initController();
    }
    
    public static void createController(Database database, ChangePasswordWindow window, User user) {
        new ChangePasswordController(database, window, user);
    }

    @Override
    void initController() {
        window.btnChangePasswordAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                changePassword();
            }
        });
    }
    
    private void changePassword() {
        String oldPassword = window.getPfOldPassword();
        String newPassword = window.getPfNewPassword();
        String confirmPassword = window.getPfConfirmPassword();
        
        if (oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")) {
            JOptionPane.showMessageDialog(window, "Je potrebné vyplniť všetky polia.");
            return;
        }
        
        if (oldPassword.equals(newPassword)) {
            JOptionPane.showMessageDialog(window, "Dané heslo sa aktuálne používa.");
            return;
        }
        
        //Pridať do iného listenera
        if (!newPassword.equals(confirmPassword)) {
            window.setLbInfoMessage("Heslá nie sú rovnaké.", Color.RED);
        } else {
            window.setLbInfoMessage("Heslá sú rovnaké.", Color.GREEN);
        }
        
        try {
            String query = "UPDATE users SET password = ? WHERE id = ?;";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setInt(2, user.getId());
            
            ps.executeUpdate();
            
            ps.close();
            JOptionPane.showMessageDialog(window, "Heslo bolo zmenené!");
            window.setVisible(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(window, "Nastala chyba pri načítaní databázy!");
        } finally {
            database.closeConnection();
        }
    }
    
}
