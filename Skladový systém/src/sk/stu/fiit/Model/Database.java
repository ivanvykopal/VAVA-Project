/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ivan Vykopal
 */
public final class Database {
    private Connection connection = null;
    private final String url = "jdbc:mysql://localhost:3306/project";
    private final String user = "root";
    private final String password = "root";
    
    public Connection connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getClass().getName() + ": " +ex.getMessage());
            System.exit(0);
        }
        
        return this.connection; 
    }
    
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getClass().getName() + ": " +ex.getMessage());
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
}
