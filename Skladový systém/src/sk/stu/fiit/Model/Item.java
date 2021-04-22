/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Trieda predstavujúca položku skladu v systéme.
 *
 * @author Ivan Vykopal
 */
public class Item implements Serializable {
    
    /** Atribút id predstavuje ID položky skladu. **/
    private int id;
    
    /** Atribút goods predstavuje tovar, predstavujúci položku. **/
    private Goods goods = null;
    
    /** 
     * Atribút storage predstavuje skladovací priestor, v ktorom sa položka 
     * nachádza. 
     **/
    private Storage storage = null;
    
    /** Atribút quantity predstavuje množstvo tovaru v položke. **/
    private int quantity = 0;
    
    /** Atribút receiptDate predstavuje dátum príjmu položky. **/
    private Date receiptDate = null;
    
    /** Atribút exportDate predstavuje dátum vývozu položky. **/
    private Date exportDate = null;
    
    /** Atribút position predstavuje pozíciu položky. **/
    private Position position = null;
    
    /**
     * Konštruktor triedy {@code Item}.
     */
    public Item() {
    }

    /**
     * Konštruktor pre inicializáciu atribútov triedy {@code Item}.
     * 
     * @param id ID položky
     * 
     * @param goods tovar, predstavujúci položku
     * 
     * @param quantity množstvo tovaru v položke
     * 
     * @param receiptDate dátum príjmu položky
     * 
     * @param exportDate dátum vývozu položky
     * 
     * @param position pozíciu položky
     */
    public Item(int id, Goods goods, int quantity, Date receiptDate,
            Date exportDate, Position position) {
        this.id = id;
        this.goods = goods;
        this.quantity = quantity;
        this.receiptDate = receiptDate;
        this.exportDate = exportDate;
        this.position = position;
    }
    
    /**
     * Metóda pre zistenie, či niektorý z atribútov je prázdny.
     * 
     * @return true, ak niektorý atribút je prázdny, inak false
     */
    public boolean isAnyAttributeEmpty() {
        return goods == null || quantity == 0 || receiptDate == null;
    }

    /**
     * Metóda pre získanie ID položky.
     * 
     * @return ID položky 
     */
    public int getId() {
        return id;
    }

    /**
     * Metóda pre nastavenie ID položky.
     * 
     * @param id ID položky
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metóda pre získanie tovaru, predstavujúceho položku.
     * 
     * @return tovar, predstavujúci položku
     */
    public Goods getGoods() {
        return goods;
    }

    /**
     * Metóda pre nastavenie tovaru, predstavujúceho položku.
     * 
     * @param goods tovar, predstavujúci položku
     */
    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    /**
     * Metóda pre získanie množstva.
     * 
     * @return množstvo tovaru v položke 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Metóda pre nastavenie množstva tovaru v položke.
     * 
     * @param quantity množstvo tovaru v položke 
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Metóda pre získanie dátumu príjmu položky.
     * 
     * @return dátum príjmu položky
     */
    public Date getReceiptDate() {
        return receiptDate;
    }

    /**
     * Metóda pre nastavenie dátumu príjmu položky.
     * 
     * @param receiptDate dátum príjmu položky
     */
    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    /**
     * Metóda pre získanie dátumu vývozu položky.
     * 
     * @return dátum vývozu položky 
     */
    public Date getExportDate() {
        return exportDate;
    }

    /**
     * Metóda pre nastavenie dátumu vývozu položky.
     * 
     * @param exportDate dátum vývozu položky 
     */
    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    /**
     * Metóda pre získanie pozície položky.
     * 
     * @return pozícia položky 
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Metóda pre nastavenie pozície položky.
     * 
     * @param position pozícia položky
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Metóda pre získanie skladovacieho priestoru.
     * 
     * @return skladovací priestor 
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Metóda pre nastavenie skladovacieho priestoru.
     * 
     * @param storage skladovací priestor 
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }
    
}
