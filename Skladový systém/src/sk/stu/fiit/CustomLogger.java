/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


/**
 *
 * @author Acer
 */
public class CustomLogger {
    //private static Logger logger;
    
    private CustomLogger() {}
    
    public static Logger getLogger(final Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz);
        DOMConfigurator.configure("log4j.xml");
        System.out.println(logger.isWarnEnabled());
        System.out.println(logger.isDebugEnabled());
        System.out.println(logger.isFatalEnabled());
        System.out.println(logger.isInfoEnabled());
        logger.fatal("Ahoj!");
        return logger;
    }
}
