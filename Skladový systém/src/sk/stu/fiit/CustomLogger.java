/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import org.apache.log4j.Logger;


/**
 * Trieda predstavujúca Logger. Využíva sa Log4j spolu s konfigurátorom, v ktorom
 * sa určuje kde sa logy zapisujú. Logy sa zapisujú aj do konzoly aj do súboru.
 * 
 * @author Ivan Vykopal
 */
public final class CustomLogger {
    
    /**
     * Privátny konštruktor triedy {@code CustomLogger}.
     */
    private CustomLogger() {}
    
    /**
     * Metóda pre vytvorenie loggera na základe triedy {@code clazz} spolu s
     * kofigurátorom.
     * 
     * @param clazz trieda, pre ktorú sa logger vytvára
     * 
     * @return logger, prostredníctvom, ktorého sa loguje do súboru a konzoly
     */
    public static Logger getLogger(final Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz);
        return logger;
    }
}
