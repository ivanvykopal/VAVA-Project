/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * Trieda predstavujúca databázu všetkých údajov, ktoré sa nachádzajú v systéme.
 *
 * @author Ivan Vykopal
 */
public final class Database implements Serializable {
    
    /** Atribút goodsIdGenerator predstavuje ID ďalšieho pridávaného tovaru. **/
    private int goodsIdGenerator = 1;
    
    /** Atribút itemIdGenerator predstavuje ID ďalšej pridávanej položky skladu. **/
    private int itemIdGenerator = 1;
    
    /** 
     * Atribút storageIdGenerator predstavuje ID ďalšieho pridávaného skladovacieho
     * priestoru.
     **/
    private int storageIdGenerator = 1;
    
    /** Atribút userIdGenerator predstavuje ID ďalšeho pridávaneho používateľa. **/
    private int userIdGenerator = 1;
    
    /** Atribút goodsTable predstavuje zoznam tovarov nachádzajúcich sa v systéme. **/
    private final ArrayList<Goods> goodsTable;
    
    /** 
     * Atribút itemTable predstavuje zoznam položiek skladu nachádzajúcich sa v 
     * systéme. 
     **/
    private final HashMap<Position, ArrayList<Item>> itemTable;
    
    /** 
     * Atribút storageTable predstavuje zoznam skladovacích priestorov
     * nachádzajúcich sa v systéme. 
     **/
    private final ArrayList<Storage> storageTable;
    
    /** 
     * Atribút userTable predstavuje zoznam používateľov nachádzajúcich sa v 
     * systéme. 
     **/
    private final ArrayList<User> userTable;
    
    /**
     * Privátny konštruktor pre inicializáciu atribútov triedy {@code Database}.
     */
    private Database() {
        this.goodsTable = new ArrayList<>();
        this.itemTable = new HashMap<>();
        this.storageTable = new ArrayList<>();
        this.userTable = new ArrayList<>();
        
        addAdministrator();
    }
    
    /**
     * Metóda pre vytvorenie triedy {@code Database}.
     * 
     * @return databáza so všetkými údajmi zo systému 
     */
    public static Database createDatabase() {
        return new Database();
    }

    /**
     * Metóda na získanie všetkých tovarov zo systému.
     * 
     * @return zoznam tovarov zo systému 
     */
    public ArrayList<Goods> getGoodsTable() {
        return new ArrayList<>(goodsTable);
    }

    /**
     * Metóda na získanie všetkých položiek skladu zo systému.
     * 
     * @return zoznam položiek skladu zo systému
     */
    public ArrayList<Item> getItemTableAll() {
        ArrayList<Item> items = new ArrayList<>();
        for (Position pos : itemTable.keySet()) {
            items.addAll(itemTable.get(pos));
        }
        return items;
    }
    
    /**
     * Metóda pre získanie položiek skladu nachádzajúcich sa v sklade.
     * 
     * @return zoznam položiek nachádzajúcich sa v sklade
     */
    public ArrayList<Item> getItemTableIn() {
        ArrayList<Item> items = itemTable.get(Position.IN_STOCK);
        if (items == null) {
            return new ArrayList<>();
        } else {
            return items;
        }
    }
    
    /**
     * Metóda pre získanie položiek vyvezených zo skladu.
     * 
     * @return zoznam položiek vyvezených zo skladu
     */
    public ArrayList<Item> getItemTableOut() {
        ArrayList<Item> items = itemTable.get(Position.OUT_STOCK);
        if (items == null) {
            return new ArrayList<>();
        } else {
            return items;
        }
    }
    
    /**
     * Metóda pre získanie položiek, nachádzajúcich sa vo výrobe.
     * 
     * @return zoznam položiek nachádzajúcich sa vo výrobe 
     */
    public ArrayList<Item> getItemTableProduction() {
        ArrayList<Item> items = itemTable.get(Position.PRODUCTION);
        if (items == null) {
            return new ArrayList<>();
        } else {
            return items;
        }
    }

    /**
     * Metóda na získanie všetkých skladovacích priestorov zo systému.
     * 
     * @return zoznam skladovacích priestorov zo systému
     */
    public ArrayList<Storage> getStorageTable() {
        return new ArrayList<>(storageTable);
    }

    /**
     * Metóda na získanie všetkých používateľov zo systému.
     * 
     * @return zoznam používateľov zo systému
     */
    public ArrayList<User> getUserTable() {
        return new ArrayList<>(userTable);
    }
    
    /**
     * Metóda pre nájdenie používateľa v systéme na základe prihlasovacieho mena
     * a hesla.
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
                return new User(user);
            }
        }
        return null;
    }
    
    /**
     * Metóda pre nájdenie používateľa v systéme na základe prihlasovacieho mena.
     * 
     * @param username prihlasovacie meno
     * 
     * @return nájdeného používateľa, inak null
     */
    public User findUser(String username) {
        for (User user : userTable) {
            if (user.getUsername().equals(username)) {
                return new User(user);
            }
        }
        return null;
    }
    
    /**
     * Metóda pre nájdenie používateľa v systéme na základe ID.
     * 
     * @param id ID používateľa
     * 
     * @return nájdeného používateľa, inak null
     */
    public User findUser(int id) {
        for (User user : userTable) {
            if (user.getId() == id) {
                return new User(user);
            }
        }
        return null;
    }
    
    /**
     * Metóda na zistenie, či daný používateľ sa už v systéme nachádza.
     * 
     * @param user používateľ, ktorého vyhľadávame v systéme
     * 
     * @return true, ak sa používateľ v systéme nachádza, inak false
     */
    public boolean existUser(User user) {
        for (User u : userTable) {
            if (u.equals(user)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metóda pre pridanie používateľa do systému. Pred pridaním sa kontroluje, 
     * či daný používateľ sa už v systéme nenachádza.
     * 
     * @param user používateľ, ktorého pridávame
     * 
     * @return používateľ, ktorý bol pridaný, inak null
     */
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
    
    /**
     * Metóda pre nastavenie používateľa. Zmena sa vykonáva na základe ID 
     * používateľa. Vykonáva sa aj kontrola, či zadaný používateľ už v systéme 
     * neexistuje.
     * 
     * @param user používateľ, ktorého chceme upraviť
     * 
     * @return upraveného používateľa, inak null, ak nebol používateľ upravený
     */
    public User setUser(User user) {
        User u = findUser(user.getId());
        if (!u.getUsername().equals(user.getUsername())) {
            if (existUser(user)) {
                return null;
            }
        }
        for (int i = 0; i < userTable.size(); i++) {
            if (userTable.get(i).getId() == user.getId()) {
                userTable.set(i, user);
                return user;
            }
        }
        return null;
    }
    
    /**
     * Metóda pre zistenie počtu administrátorov v systéme.
     * 
     * @return počet administrátorov 
     */
    private int checkAdministrators() {
        int count = 0;
        for (User user : userTable) {
            if (user.getType() == Type.ADMINISTRATOR) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Metóda pre odstránenie používateľa zo systému. Pred samotným odstránením
     * sa kontroluje, či nejde o posledného administrátora. V prípade posledného
     * administrátora odstránenie sa nevykoná. Je potrebné, aby v systéme bol
     * aspoň jeden administrátor.
     * 
     * @param user používateľ, ktorého chceme odstrániť
     * 
     * @return používateľ, ktorý bol odstránený, null ak nebol nik odstránený
     */
    public User removeUser(User user) {
        if (user.getType() == Type.ADMINISTRATOR && checkAdministrators() == 1) {
            JOptionPane.showMessageDialog(null, "Nie je možné odstrániť posledného administrátora!");
            return null;
        }
        for (User u : userTable) {
            if (u.equals(user)) {
                userTable.remove(u);
                return user;
            }
        }
        return null;
    }
    
    /**
     * Metóda na skontrolovanie, či zadaný tovar sa už v systéme nachádza.
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
     * Metóda pre pridanie tovaru do databázy. Najskôr sa kontroluje, či už daný
     * tovar v systéme nie je.
     * 
     * @param goods tovar, ktorý sa pridáva
     * 
     * @return tovar, ktorý bol pridaný, inak null, ak nebol tovar pridaný
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
    
    /**
     * Metóda pre nájdenie tovaru na základe kódu.
     * 
     * @param code kód tovaru, ktorý vyhľadávame
     * 
     * @return nájdený tovar, inak null
     */
    public Goods findGoods(String code) {
        for (Goods goods : goodsTable) {
            if (goods.getCode().equals(code)) {
                return new Goods(goods);
            }
        }
        return null;
    }
    
    /**
     * Metóda pre nájdenie tovaru na základe ID.
     * 
     * @param id ID, tovaru, ktorý hľadáme
     * 
     * @return nájdený tovar, inak null
     */
    public Goods findGoods(int id) {
        for (Goods goods : goodsTable) {
            if (goods.getId() == id) {
                return new Goods(goods);
            }
        }
        return null;
    }
    
    /**
     * Metóda pre nastavenie príznaku tovaru o tom, že tovar je vymazaný.
     * 
     * @param goods tovar, ktorý chceme označiť ako vymazaný
     * 
     * @return tovar, ktorý bol označený ako vymazaný, inak null
     */
    public Goods removeGoods(Goods goods) {
        goods.setDeleted(true);
        return setGoods(goods);
    }
    
    /**
     * Metóda pre nastavenie tovaru. Zmena sa vykonáva na základe ID tovaru. 
     * Vykonáva sa aj kontrola, či zadaný tovar už v systéme neexistuje.
     * 
     * @param goods tovar, ktorý upravujeme
     * 
     * @return tovar, ktorý bol upravený, inak null
     */
    public Goods setGoods(Goods goods) {
        Goods g = findGoods(goods.getId());
        if (!g.getCode().equals(goods.getCode())) {
            if (existGoods(goods)) {
                return null;
            }
        }  
        for (int i = 0; i < goodsTable.size(); i++) {
            if (goodsTable.get(i).getId() == goods.getId()) {
                goodsTable.set(i, goods);
                return goods;
            }
        }
        return null;
    }
    
    /**
     * Metóda pre zistenie, či sa daný skladovací priestor už v systéme nachádza.
     * 
     * @param storage skladovací systém, ktorého výskyt kontrolujeme
     * 
     * @return true, ak sa už v systéme nachádza, inak false
     */
    public boolean existStorage(Storage storage) {
        for (Storage s : storageTable) {
            if (s.equals(storage)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metóda pre pridanie skladovacieho priestoru do systému. Najskôr sa 
     * kontroluje, či už priestor v systéme nie je.
     * 
     * @param storage skladovací priestor, ktorý do systému pridávame
     * 
     * @return skladovací priestor, ktorý bol pridaný, inak null
     */
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
    
    /**
     * Metóda pre vyhľadanie skladovacieho priestoru na základe kódu.
     * 
     * @param code kód skladovacieho priestoru
     * 
     * @return skladovací priestor, inak null, ak nebol žiaden nájdený
     */
    public Storage findStorage(String code) {
        for (Storage storage : storageTable) {
            if (storage.getCode().equals(code)) {
                return new Storage(storage);
            }
        }
        return null;
    }
    
    /**
     * Metóda pre vyhľadanie skladovacieho priestoru na základe ID.
     * 
     * @param id ID skladovacieho priestoru
     * 
     * @return skladovací priestor, inak null, ak nebol žiaden nájdený
     */
    public Storage findStorage(int id) {
        for (Storage storage : storageTable) {
            if (storage.getId() == id) {
                return new Storage(storage);
            }
        }
        return null;
    }
    
    /**
     * Metóda pre odstránenie skladovacieho priestoru.
     * 
     * @param storage skladovací priestor na odstránenie
     * 
     * @return odstránený skladovací priestor, inak null
     */
    public Storage removeStorage(Storage storage) {
        for (Storage s : storageTable) {
            if (s.equals(storage)) {
                storageTable.remove(s);
                return storage;
            }
        }
        return null;
    }
    
    /**
     * Metóda pre nastavenie skladovacieho priestoru. Zmena sa vykonáva na 
     * základe ID sklad. priestoru. Vykonáva sa aj kontrola, či zadaný 
     * sklad. priestor už v systéme neexistuje.
     * 
     * @param storage skladovací tovar, ktorý upravujeme
     * 
     * @return skladovací tovar, ktorý bol upravený, inak null
     */
    public Storage setStorage(Storage storage) {
        Storage s = findStorage(storage.getId());
        if (!s.getCode().equals(storage.getCode())) {
            if (existStorage(storage)) {
                return null;
            }
        }

        for (int i = 0; i < storageTable.size(); i++) {
            if (storageTable.get(i).getId() == storage.getId()) {
                storageTable.set(i, storage);
                return storage;
            }
        }
        return null;
    }
    
    /**
     * Metóda pre pridanie položky skladu.
     * 
     * @param item položka skladu
     * 
     * @return pridaná položka skladu, inak null
     */
    public Item addItem(Item item) {
        if (item.getStorage() != null) {
            Storage storage = findStorage(item.getStorage().getCode());
            if (!storage.isFree()) {
                return null;
            }
            storage.setItemCount(storage.getItemCount() + 1);
            setStorage(storage);
        }
        item.setId(itemIdGenerator);
        itemIdGenerator++;
        ArrayList<Item> items = itemTable.get(item.getPosition());
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        this.itemTable.put(item.getPosition(), items);
        return item;
    }
    
    /**
     * Metóda pre nájdenie položky skladu na základe ID.
     * 
     * @param id ID položky skladu
     * 
     * @return nájdená položka skladu, inak null
     */
    public Item findItem(int id) {
        for (Item item : getItemTableAll()) {
            if (item.getId() == id) {
                return new Item(item);
            }
        }
        return null;
    }
    
    /**
     * Metóda pre nastavenie položky skladu. Položka sa nastavuje na základe ID.
     * 
     * @param item položka skladu, ktorú upravujeme
     * 
     * @return upravené položka skladu, inak null
     */
    public Item setItem(Item item) {
        Item it = findItem(item.getId());
        ArrayList<Item> items = itemTable.get(it.getPosition());
        if (it.getStorage() != null && item.getStorage() == null) {
            Storage storage = it.getStorage();
            storage.setItemCount(storage.getItemCount() - 1);
            setStorage(storage);
        }
        
        if (item.getStorage()!= null && !it.getStorage().getCode().equals(item.getStorage().getCode())) {
            Storage storage = findStorage(item.getStorage().getCode());
            if (!storage.isFree()) {
                return null;
            }
        }

        if (item.getPosition() == it.getPosition()) {
            boolean set = false;
            for(int i = 0; i < items.size(); i++) {
                if (items.get(i).getId() == it.getId()) {
                    items.set(i, item);
                    set = true;
                    break;
                }
            }
            if (!set) {
                return null;
            }
            itemTable.replace(it.getPosition(), items);
            return item;
        } else {
            boolean removed = false;
            for(int i = 0; i < items.size(); i++) {
                if (items.get(i).getId() == it.getId()) {
                    items.remove(i);
                    removed = true;
                    break;
                }
            }
            if (!removed) {
                return null;
            }
            itemTable.replace(it.getPosition(), items);
            items = itemTable.get(item.getPosition());
            if (items == null) {
                items = new ArrayList<>();
            }
            items.add(item);
            return item;
        }
    }
    
    /**
     * Metóda pre pridanie administrátora do systému, v prípade, ak sa v ňom
     * žiaden administrátor nenachádza.
     */
    private void addAdministrator() {
        if (checkAdministrators() == 0) {
            User user = new User(itemIdGenerator, "admin", "admin@gmail.com", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Administrátor", Type.ADMINISTRATOR);
            addUser(user);
        }
    }
    
}
