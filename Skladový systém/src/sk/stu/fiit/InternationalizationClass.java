/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Trieda určená pre Internacionalizáciu.
 *
 * @author Ivan Vykopal
 */
public class InternationalizationClass {
    
    /** Atribút bundle predsatvuje súbor, v ktorom sa nachádzajú texty, pre zvolený jazyk. **/
    private static ResourceBundle bundle;
    
    /** Atribút locale predstavuje aktuálnu lokalizáciu programu. **/
    private static Locale locale;
    
    /**
     * Privátny konštruktor triedy {@code InternationalizationClass}.
     */
    private InternationalizationClass() {}
    
    /**
     * Metóda pre nastavenie súboru, z ktorého sa budú načítavať texty pre grafické rozhranie
     * 
     * @param path cesta k súboru s jazykovou verziou
     * 
     * @param lang jazyk
     * 
     * @param country krajina
     */
    public static void setBundle(String path, String lang, String country) {
        locale = new Locale(lang, country);
        bundle = ResourceBundle.getBundle(path, locale);
    }
    
    /**
     * Metóda pre získanie aktuálnej jazykovej verzie.
     * 
     * @return súbor s aktuálnou jazykovou verziou 
     */
    public static ResourceBundle getBundle() {
        return bundle;
    }
    
    /**
     * Metóda pre získanie aktuálnej lokalizácie.
     * 
     * @return aktuálna lokalizácia
     */
    public static Locale getLocale() {
        return locale;
    }
}
