/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Ivan Vykopal
 */
public final class Database implements Serializable {
    private int goodsIdGenerator = 1;
    private int itemIdGenerator = 1;
    private int storageIdGenerator = 1;
    private int userIdGenerator = 1;
    private final ArrayList<Goods> goodsTable;
    private final ArrayList<Item> itemTable;
    private final ArrayList<Storage> storageTable;
    private final ArrayList<User> userTable;
    
    private Database() {
        this.goodsTable = new ArrayList<>();
        this.itemTable = new ArrayList<>();
        this.storageTable = new ArrayList<>();
        this.userTable = new ArrayList<>();
    }
    
    public static Database createDatabase() {
        return new Database();
    }

    public ArrayList<Goods> getGoodsTable() {
        return new ArrayList<>(goodsTable);
    }

    public ArrayList<Item> getItemTable() {
        return new ArrayList<>(itemTable);
    }

    public ArrayList<Storage> getStorageTable() {
        return new ArrayList<>(storageTable);
    }

    public ArrayList<User> getUserTable() {
        return new ArrayList<>(userTable);
    }
    
    /**
     * Metóda pre nájdenie používateľa v systéme na základe prihlasovacieho mena a hesla.
     * 
     * @param username prihlasovacie meno
     * 
     * @param password prihlasovacie heslo
     * 
     * @return nájdeného používateľa, inak null
     */
    public User findUser(String username, String password) {
        for (User user : userTable) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    public User findUser(String username) {
        for (User user : userTable) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
    public boolean existUser(User user) {
        for (User u : userTable) {
            if (u.equals(user)) {
                return true;
            }
        }
        return false;
    }
    
    public User addUser(User user) {
        if (existUser(user)) {
            return null;
        } else {
            user.setId(userIdGenerator);
            userIdGenerator++;
            this.userTable.add(user);
            return user;
        }
    }
    
    public User setUser(User user) {
        for (int i = 0; i < userTable.size(); i++) {
            if (userTable.get(i).getId() == user.getId()) {
                userTable.set(i, user);
                return user;
            }
        }
        return null;
    }
    
    public User removeUser(User user) {
        for (User u : userTable) {
            if (u.equals(user)) {
                userTable.remove(u);
                return user;
            }
        }
        return null;
    }
    
    /**
     * Metóda na skontrolvoanie, či zadaný tovar sa už v systéme nachádza.
     * 
     * @param goods tovar, ktorého výskyt kontrolujeme
     * 
     * @return true, ak sa daný tovar už v systéme nachádza, inak false
     */
    public boolean existGoods(Goods goods) {
        for (Goods g : goodsTable) {
            if (g.equals(goods)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metóda pre pridanie tovaru do databázy. 
     * Najskôr sa kontroluje, či už daný tovar v systéme nie je.
     * 
     * @param goods tovar, ktorý sa pridáva
     * 
     * @return tovar, ktorý bol pridaný, inak null
     */
    public Goods addGoods(Goods goods) {
        if (existGoods(goods)) {
            return null;
        } else {
            goods.setId(goodsIdGenerator);
            goodsIdGenerator++;
            this.goodsTable.add(goods);
            return goods;
        }
    }
    
    public Goods findGoods(String code) {
        for (Goods goods : goodsTable) {
            if (goods.getCode().equals(code)) {
                return goods;
            }
        }
        return null;
    }
    
    public Goods removeGoods(Goods goods) {
        goods.setDeleted(true);
        return setGoods(goods);
    }
    
    public Goods setGoods(Goods goods) {
        for (int i = 0; i < goodsTable.size(); i++) {
            if (goodsTable.get(i).getId() == goods.getId()) {
                goodsTable.set(i, goods);
                return goods;
            }
        }
        return null;
    }
    
    public boolean existStorage(Storage storage) {
        for (Storage s : storageTable) {
            if (s.equals(storage)) {
                return true;
            }
        }
        return false;
    }
    
    public Storage addStorage(Storage storage) {
        if (existStorage(storage)) {
            return null;
        }
        else {
            storage.setId(storageIdGenerator);
            storageIdGenerator++;
            this.storageTable.add(storage);
            return storage;
        }
    }
    
    public Storage findStorage(String code) {
        for (Storage storage : storageTable) {
            if (storage.getCode().equals(code)) {
                return storage;
            }
        }
        return null;
    }
    
    public Storage removeStorage(Storage storage) {
        for (Storage s : storageTable) {
            if (s.equals(storage)) {
                storageTable.remove(s);
                return storage;
            }
        }
        return null;
    }
    
    public Storage setStorage(Storage storage) {
        for (int i = 0; i < storageTable.size(); i++) {
            if (storageTable.get(i).getId() == storage.getId()) {
                storageTable.set(i, storage);
                return storage;
            }
        }
        return null;
    }
    
    public Item addItem(Item item) {
        item.setId(itemIdGenerator);
        itemIdGenerator++;
        this.itemTable.add(item);
        return item;
    }
    
    public Item findItem(int id) {
        for (Item item : itemTable) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    
    public Item removeItem(Item item) {
        for(Item i : itemTable) {
            if (i.getId() == item.getId()) {
                itemTable.remove(i);
                return item;
            }
        }
        return null;
    }
    
    public Item setItem(Item item) {
        for (int i = 0; i < itemTable.size(); i++) {
            if (itemTable.get(i).getId() == item.getId()) {
                itemTable.set(i, item);
                return item;
            }
        }
        return null;
    }
    
}
