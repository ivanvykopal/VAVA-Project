/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.util.Date;

/**
 *
 * @author Ivan Vykopal
 */
public class Item {
    private int id;
    private Goods goods = null;
    private int quantity = 0;
    private double price = 0;
    private Date receiptDate = null;
    private Date exportDate = null;
    private Position position = null;
    private boolean consumed = false;
    
    public Item() {
        
    }

    public Item(int id, Goods goods, int quantity, double price, Date receiptDate,
            Date exportDate, Position position, boolean consumed) {
        this.id = id;
        this.goods = goods;
        this.quantity = quantity;
        this.price = price;
        this.receiptDate = receiptDate;
        this.exportDate = exportDate;
        this.position = position;
        this.consumed = consumed;
    }
    
    public boolean isAnyAttributeEmpty() {
        return goods == null || quantity == 0 || price == 0 || receiptDate == null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }
    
}
