/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.io.Serializable;

/**
 * Trieda predstavujúca tovar v systéme.
 *
 * @author Ivan Vykopal
 */
public class Goods implements Serializable {
    
    /** Atribút id predstavuje ID tovaru. **/
    private int id = 1;
    
    /** Atribút name predstavuje názov tovaru. **/
    private String name = "";
    
    /** Atribút code predstavuje kód tovaru. **/
    private String code = "";
    
    /** Atribút description predstavuje opis tovaru. **/
    private String description = "";
    
    /** Atribút incomePrice predstavuje nákupnú cenu tovaru. **/
    private double incomePrice = 0;
    
    /** Atribút exportPrice predstavuje predajnú cenu tovaru. **/
    private double exportPrice = 0;
    
    /** Atribút deleted predstavuje informáciu o vymazaní tovaru. **/
    private boolean deleted = false;
    
    /**
     * Konštruktor triedy {@code Goods}.
     */
    public Goods() {
    }

    /**
     * 
     * Konštruktor pre inicializáciu atribútov triedy {@code Goods}.
     *
     * @param name názov tovaru
     * 
     * @param code kód tovaru
     * 
     * @param description opis tovaru
     * 
     * @param incomePrice nákupná cena
     * 
     * @param exportPrice predajná cena 
     */
    public Goods(String name, String code, String description, double incomePrice, double exportPrice) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.incomePrice = incomePrice;
        this.exportPrice = exportPrice;
    }
    
    /**
     * Konštruktor pre inicializáciu atribútov triedy {@code Goods}.
     * 
     * @param goods tovar na základe, ktorého inicializujeme
     */
    public Goods(Goods goods) {
        this.id = goods.getId();
        this.name = goods.getName();
        this.code = goods.getCode();
        this.description = goods.getDescription();
        this.incomePrice = goods.getIncomePrice();
        this.exportPrice = goods.getExportPrice();
        this.deleted = goods.isDeleted();
    }
    
    /**
     * Metóda pre zistenie, či niektorý z atribútov je prázdny.
     * 
     * @return true, ak niektorý atribút je prázdny, inak false
     */
    public boolean isAnyAttributeEmpty() {
        return name.equals("") || code.equals("") || description.equals("") || 
                incomePrice == 0 || exportPrice == 0;
    }

    /**
     * Metóda pre získanie názvu tovaru.
     * 
     * @return názov tovaru
     */
    public String getName() {
        return name;
    }

    /**
     * Metóda pre nastavenie názvu tovaru.
     * 
     * @param name názov tovaru 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metóda pre získanie kódu tovaru.
     * 
     * @return kód tovaru
     */
    public String getCode() {
        return code;
    }

    /**
     * Metóda pre nastavenie kódu tovaru.
     * 
     * @param code kód tovaru 
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Metóda pre získanie opisu tovaru.
     * 
     * @return opis tovaru
     */
    public String getDescription() {
        return description;
    }

    /**
     * Metóda pre nastavenie opisu tovaru.
     * 
     * @param description opis tovaru
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Metóda pre získanie nákupnej ceny tovaru.
     * 
     * @return nákupná cena tovaru
     */
    public double getIncomePrice() {
        return incomePrice;
    }

    /**
     * Metóda pre nastavenie nákupnej ceny tovaru.
     * 
     * @param incomePrice nákupná cena tovaru
     */
    public void setIncomePrice(double incomePrice) {
        this.incomePrice = incomePrice;
    }

    /**
     * Metóda pre získanie predajnej ceny tovaru.
     * 
     * @return predajná cena tovaru
     */
    public double getExportPrice() {
        return exportPrice;
    }

    /**
     * Metóda pre nastavenie predajnej ceny tovaru.
     * 
     * @param exportPrice predajná cena tovaru
     */
    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    /**
     * Metóda pre porovanie daného tovaru s iným tovarom. Porovnávanie prebieha
     * na základe kódov tovarov.
     * 
     * @param goods tovar, s ktorým porovnávame
     * 
     * @return true, ak sú tovary zhodné, inak false 
     */
    public boolean equals(Goods goods) {
        return code.equals(goods.code);
    }

    /**
     * Metóda pre získanie ID tovaru.
     * 
     * @return ID tovaru 
     */
    public int getId() {
        return id;
    }

    /**
     * Metóda pre nastavenie ID tovaru.
     * 
     * @param id ID tovaru 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metóda na zistenie, či daný tovar má príznak, že bol vymazaný.
     * 
     * @return true, ak bol vymazaný, inak false
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Metóda pre nastavenie príznaku o zmazaní tovaru.
     * 
     * @param deleted príznak o zmazaní tovaru 
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
}
