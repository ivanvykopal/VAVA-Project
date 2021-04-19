/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


/**
 *
 * @author Acer
 */
public class CustomLogger {
    
    private CustomLogger() {}
    
    public static Logger getLogger(final Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz);
        DOMConfigurator.configure(new File("src/sk/stu/fiit/log4j.xml").getAbsolutePath());
        return logger;
    }
}
