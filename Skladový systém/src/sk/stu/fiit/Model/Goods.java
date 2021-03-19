/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

/**
 *
 * @author Ivan Vykopal
 */
public class Goods {
    private int id;
    private String name;
    private String code;
    private String description;
    private double incomePrice;
    private double exportPrice;

    public Goods(int id, String name, String code, String description, double incomePrice, double exportPrice) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.incomePrice = incomePrice;
        this.exportPrice = exportPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
}
