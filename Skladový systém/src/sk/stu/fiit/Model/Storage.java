/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.util.ArrayList;

/**
 *
 * @author Ivan Vykopal
 */
public class Storage {
    private int id;
    private String code = "";
    private String building = "";
    private String shelf = "";
    private ArrayList<Item> items;
    
    public Storage() {
        
    }

    public Storage(int id, String code, String building, String shelf, ArrayList<Item> items) {
        this.id = id;
        this.code = code;
        this.building = building;
        this.shelf = shelf;
        this.items = items;
    }
    
    public boolean isAnyAttributeEmpty() {
        return code.equals("") || building.equals("") || shelf.equals("");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    
}
