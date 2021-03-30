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
public final class LoginController extends Controller {
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
            String query = "SELECT id, username, password, name, type, email FROM users WHERE username = '" + userName +"'\n"
                    + "AND password = '" + password + "';";
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
                switch (type) {
                    case ADMINISTRATOR:
                        AdministratorController.createController(database, new AdministratorWindow(), user);
                        window.setVisible(false);
                        break;
                    case WAREHOUSEMAN:
                        break;
                    case REFERENT:
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(window, "Nesprávne prihlasovacie údaje!");
                return; 
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(window, "Nastala chyba pri načítaní databázy!\n Opakujte prihlásenie!");
            return;
        } finally {
            database.closeConnection();
        }
        
        
    }
    //https://www.baeldung.com/sha-256-hashing-java
    
    private byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
    
    private String SHAtoString(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
}
