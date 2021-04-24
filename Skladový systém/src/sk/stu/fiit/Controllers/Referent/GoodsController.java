/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Controllers.Referent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import sk.stu.fiit.Controllers.Controller;
import sk.stu.fiit.CustomLogger;
import sk.stu.fiit.GUI.ReferentWindow;
import sk.stu.fiit.InternationalizationClass;
import sk.stu.fiit.Model.Database;

/**
 * Abstraktná trieda, od ktorej dedia controllery pre funkčnosť obrazoviek 
 * referenta.
 * 
 * @see Controller
 *
 * @author Ivan Vykopal
 */
public abstract class GoodsController implements Controller {
    
    /** Atribút database predstavuje databázu so všetkými údajmi zo systému. **/
    protected Database database;
    
    /** Atribút window predstavuje obrazovku referenta. **/
    protected ReferentWindow window;
    
    /** Atribút bundle predstavuje súbor s aktuálnou jazykovou verziou. **/
    protected final ResourceBundle bundle = InternationalizationClass.getBundle();

    /**
     * Konštruktor pre inicializáciu atribútov triedy {@code GoodsController}.
     * 
     * @param database databáza so všetkými údajmi zo systému
     * 
     * @param window obrazovka pre referenta
     */
    public GoodsController(Database database, ReferentWindow window) {
        this.database = database;
        this.window = window;
    }
    
    /**
     * Metóda pre zistenie, či dátum prijatia je medzi zadanými dátumami.
     * 
     * @param date dátum prijatia položky
     * 
     * @param from dátum začiatku vyhľadávania
     * 
     * @param to dátum konca vyhľadávania
     * 
     * @return true, ak dátum prijatia je v rozmedzí zvolených dátumov, inak 
     * false
     */
    protected boolean matchDates(Date date, Date from, Date to) {
        SimpleDateFormat format = new SimpleDateFormat(bundle.getString("DATE_FORMAT"));
        Date newDate;
        try {
            newDate = format.parse(format.format(date));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(window, bundle.getString("DATE_ERROR"));
            CustomLogger.getLogger(GoodsController.class).warn(bundle.getString("DATE_ERROR"), ex);
            return false;
        }
        return newDate.compareTo(from) == 0 || newDate.compareTo(to) == 0 || (newDate.after(from) && newDate.before(to));
    }
    
    /**
     * Vnorená trieda reprezentujúca prepravku. Slúži najmä na ukladanie
     * informácií o tovaroch, pretovšetkým o celkovom počte, názve a cien
     * tovarov. Využitie závisí od spôsobu využitia v konkrétnych triedach.
     */
    protected static class TableItem {
        
        /** Atribút name predstavuje názov tovaru. **/
        String name;
        
        /** Atribút quantity predstavuje množstvo tovaru. **/
        int quantity;
        
        /** Atribút incomePrice predstavuje nákupnú cenu tovaru. **/
        double incomePrice;
        
        /** Atribút exportPrice predstavuje predajnú cenu tovaru. **/
        double exportPrice;

        /**
         * Konštruktor vnorenej triedy {@code TableItem}.
         * 
         * @param name názov tovaru
         * 
         * @param quantity množstvo tovaru
         * 
         * @param incomePrice nákupná cena
         * 
         * @param exportPrice predajná cena
         */
        public TableItem(String name, int quantity, double incomePrice, double exportPrice) {
            this.name = name;
            this.quantity = quantity;
            this.incomePrice = incomePrice;
            this.exportPrice = exportPrice;
        }
    }
    
}
