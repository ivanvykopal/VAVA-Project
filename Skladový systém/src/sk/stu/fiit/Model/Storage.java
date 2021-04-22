/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.io.Serializable;

/**
 * Trieda predstavujúca Skladovací priestor.
 *
 * @author Ivan Vykopal
 */
public class Storage implements Serializable {
    
    /** Atribút id predstavuje ID skladovacieho priestoru. **/
    private int id = 1;
    
    /** Atribút code predstavuje kód skladovacieho priestoru. **/
    private String code = "";
    
    /** 
     * Atribút building predstavuje budovu, v ktorom sa skladovací priestor
     * nachádza. 
     **/
    private String building = "";
    
    /** 
     * Atribút shelf predstavuje regál, na ktorom sa skladovací priestor
     * nachádza. 
     **/
    private String shelf = "";
    
    /** 
     * Atribút itemCount predstavuje počet položiek, ktoré obsahuje.
     **/
    private int itemCount = 0;
    
    /** Atribút free predstavuje informácio o tom, či daná pozícia je voľná. **/
    private boolean free = true;
    
    /**
     * Konštruktor triedy {@code Storage}.
     */
    public Storage() {
    }

    /**
     * Konštruktor pre inicializáciu atribútov triedy {@code Storage}.
     * 
     * @param code kód skladovacieho priestoru
     * 
     * @param building budova, v ktorom sa skladovací priestor nachádza
     * 
     * @param shelf regál, na ktorom sa skladovací priestor nachádza
     * 
     * @param itemCount počet položiek v danom priestore
     * 
     * @param free informácia o tom, či priestor je ešte voľný
     */
    public Storage(String code, String building, String shelf, int itemCount, boolean free) {
        this.code = code;
        this.building = building;
        this.shelf = shelf;
        this.free = free;
        this.itemCount = itemCount;
    }
    
    /**
     * Metóda pre zistenie, či niektorý z atribútov je prázdny.
     * 
     * @return true, ak niektorý atribút je prázdny, inak false
     */
    public boolean isAnyAttributeEmpty() {
        return code.equals("") || building.equals("") || shelf.equals("");
    }

    /**
     * Metóda pre získanie kódu skladovacieho priestoru.
     * 
     * @return kód skladovacieho priestoru
     */
    public String getCode() {
        return code;
    }

    /**
     * Metóda pre nastavenie kódu skladovacieho priestoru.
     * 
     * @param code kód skladovacieho priestoru.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Metóda pre získanie budovy, v ktorom sa skladovací priestor nachádza.
     * 
     * @return budova, v ktorom sa skladovací priestor nachádza
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Metóda pre nastavenie budovy, v ktorom sa skladovací priestor nachádza.
     * 
     * @param building budova, v ktorom sa skladovací priestor nachádza
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * Metóda pre získanie regálu, na ktorom sa skladovací priestor nachádza.
     * 
     * @return regál, na ktorom sa skladovací priestor nachádza
     */
    public String getShelf() {
        return shelf;
    }

    /**
     * Metóda pre nastavenie regálu, na ktorom sa skladovací priestor nachádza.
     * 
     * @param shelf regál, na ktorom sa skladovací priestor nachádza
     */
    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    /**
     * Metóda pre získanie informácie o tom, či je daný priestor ešte voľný.
     * 
     * @return informácia o tom, či je priestor ešte voľný
     */
    public boolean isFree() {
        return free;
    }

    /**
     * Metóda pre nastavenie informácie o tom, či je daný priestor ešte voľný.
     * 
     * @param free informácia o tom, či je priestor ešte voľný
     */
    public void setFree(boolean free) {
        this.free = free;
    }

    /**
     * Metóda pre porovanie daného skladovacieho priestoru s iným priestorom. 
     * Porovnávanie prebieha na základe kódov skladovacích priestorov.
     * 
     * @param storage skladovací priestor, s ktorým porovnávame
     * 
     * @return true, ak sú priestory zhodné, inak false 
     */
    public boolean equals(Storage storage) {
        return code.equals(storage.getCode());
    }

    /**
     * Metóda pre získanie ID skladovacieho priestoru.
     * 
     * @return ID skladovacieho priestoru
     */
    public int getId() {
        return id;
    }

    /**
     * Metóda pre nastavenie ID skladovacieho priestoru.
     * 
     * @param id ID skladovacieho priestoru
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metóda pre získanie informácie o počte položiek v danom priestore.
     * 
     * @return počet položiek v danom priestore
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Metóda pre nastavenie informácie o počte položiek v danom priestore.
     * 
     * @param itemCount počet položiek v danom priestore
     */
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    
}
