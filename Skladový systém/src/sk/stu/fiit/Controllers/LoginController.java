/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sk.stu.fiit.GUI.AdministratorWindow;
import sk.stu.fiit.GUI.LoginWindow;
import sk.stu.fiit.Model.Database;
import sk.stu.fiit.Model.Type;
import sk.stu.fiit.Model.User;

/**
 *
 * @author Ivan Vykopal
 */
public class LoginController extends Controller {
    private final LoginWindow window;

    public LoginController(LoginWindow window, Database database) {
        super(database);
        this.window = window;
        
        window.setVisible(true);
        initController();
    }
    
    @Override
    void initController() {
        window.btnLoginAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                login();
            }
        });
    }
    
    private void login() {
        String userName = window.getTfLoginField();
        String password;
        try {
            password = SHAtoString(getSHA(window.getPfPasswordField()));
        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(window, "Nastala chyba pri načítaní hesla!\n Opakujte prihlásenie!");
            return;
        }
        
        try {
            String query = "SELECT id, username, password, name, type FROM users WHERE username = '" + userName +"'\n"
                    + "AND password = '" + password + "'";
            PreparedStatement ps = database.connectDatabase().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Type type;
                switch (rs.getString("type")) {
                    case "administrator": 
                        type = Type.ADMINISTRATOR;
                        break;
                    case "warehouseman": 
                        type = Type.WAREHOUSEMAN;
                        break;
                    default: 
                        type = Type.REFERENT;
                }
                User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),  rs.getString("password"), rs.getString("name"), type);
                if (type == Type.ADMINISTRATOR) {
                    AdministratorController.createController(database, new AdministratorWindow(), user);
                    window.setVisible(false);
                } else if (type == Type.WAREHOUSEMAN) {
                    
                }
            } else {
                JOptionPane.showMessageDialog(window, "Nesprávne prihlasovacie údaje!");
                return; 
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(window, "Nastala chyba pri načítaní databázy!\n Opakujte prihlásenie!");
            return;
        } finally {
            database.closeConnection();
        }
        
        
    }
    
    private byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
    
    private String SHAtoString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0,'0');
        }
        return hexString.toString();
    }
    
}
