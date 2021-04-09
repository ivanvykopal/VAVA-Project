/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 *
 * @author Ivan Vykopal
 */
public final class SerializationClass {
    
    public static void serialize(Database database) {
        try {
            FileOutputStream file = new FileOutputStream("database.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(database);
            out.close();
            file.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Database deserialize() {
        Database database = null;
        try {
            FileInputStream file = new FileInputStream("database.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            database = (Database) in.readObject();
            in.close();
            file.close();
            return database;
        } catch (IOException ex) {
            return Database.createDatabase();
        } catch (ClassNotFoundException ex) {
            return Database.createDatabase();
        }
    }
    
}
