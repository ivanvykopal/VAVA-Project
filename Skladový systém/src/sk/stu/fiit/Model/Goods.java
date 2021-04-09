/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.io.Serializable;

/**
 *
 * @author Ivan Vykopal
 */
public class Goods implements Serializable {
    private int id = 1;
    private String name = "";
    private String code = "";
    private String description = "";
    private double incomePrice = 0;
    private double exportPrice = 0;
    private boolean deleted = false;
    
    public Goods() {
    }

    public Goods(String name, String code, String description, double incomePrice, double exportPrice) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.incomePrice = incomePrice;
        this.exportPrice = exportPrice;
    }
    
    public boolean isAnyAttributeEmpty() {
        return name.equals("") || code.equals("") || description.equals("") || 
                incomePrice == 0 || exportPrice == 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getIncomePrice() {
        return incomePrice;
    }

    public void setIncomePrice(double incomePrice) {
        this.incomePrice = incomePrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public boolean equals(Goods goods) {
        return code.equals(goods.code);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
}
