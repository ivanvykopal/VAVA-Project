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
public class Storage implements Serializable {
    private int id = 1;
    private String code = "";
    private String building = "";
    private String shelf = "";
    private boolean containsItem = false;
    private boolean free = true;
    
    public Storage() {
    }

    public Storage(String code, String building, String shelf, boolean containsItem, boolean free) {
        this.code = code;
        this.building = building;
        this.shelf = shelf;
        this.free = free;
    }
    
    public boolean isAnyAttributeEmpty() {
        return code.equals("") || building.equals("") || shelf.equals("");
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

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean equals(Storage storage) {
        return code.equals(storage.getCode());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getContainsItem() {
        return containsItem;
    }

    public void setContainsItem(boolean containsItem) {
        this.containsItem = containsItem;
    }
    
}
