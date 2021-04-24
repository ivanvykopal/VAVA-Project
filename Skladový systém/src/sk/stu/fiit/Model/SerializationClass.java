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
import java.util.ResourceBundle;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.InternationalizationClass;


/**
 * Trieda určená pre serializáciu databázy.
 *
 * @author Ivan Vykopal
 */
public final class SerializationClass {
    
    /**
     * Metóda pre serializáciu databázy. Vytvorí sa súbor database.ser.
     * 
     * @param database databáza so všetkými údajmi zo systému
     */
    public static void serialize(Database database) {
        try {
            FileOutputStream file = new FileOutputStream("database.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(database);
            out.close();
            file.close();
        } catch(IOException ex) {
            CustomLogger.getLogger(SerializationClass.class).warn(InternationalizationClass.getBundle().getString("SERIALIZATION_ERROR"), ex);
        }
    }
    
    /**
     * Metóda pre deserializáciu databázy. Databáza sa načítava z database.ser.
     * 
     * @return načítaná databáza, inak prázdna databáza
     */
    public static Database deserialize() {
        Database database;
        try {
            FileInputStream file = new FileInputStream("database.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            database = (Database) in.readObject();
            in.close();
            file.close();
            return database;
        } catch (IOException | ClassNotFoundException ex) {
            CustomLogger.getLogger(SerializationClass.class).warn(InternationalizationClass.getBundle().getString("SERIALIZATION_ERROR"), ex);
            return Database.createDatabase();
        }
    }
    
}
