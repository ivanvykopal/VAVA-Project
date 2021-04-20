/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Ivan Vykopal
 */
public class InternationalizationClass {
    private static ResourceBundle bundle;
    
    private InternationalizationClass() {}
    
    public static void setBundle(String path, String lang, String country) {
        bundle = ResourceBundle.getBundle(path, new Locale(lang, country));
    }
    
    public static ResourceBundle getBundle() {
        return bundle;
    }
}
