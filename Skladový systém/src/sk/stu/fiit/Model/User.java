/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.io.Serializable;
import sk.stu.fiit.InternationalizationClass;

/**
 * Trieda predstavujúca používateľa v systéme.
 * 
 * @author Ivan Vykopal
 */
public final class User implements Serializable {
    
    /** Atribút id predstavuje ID používateľa. **/
    private int id = 1;
    
    /** Atribút username predstavuje používateľské meno používateľa. **/
    private String username = "";
    
    /** Atribút email predstavuje e-mail používateľa. **/
    private String email = "";
    
    /** Atribút password predstavuje heslo používateľa. **/
    private String password = "";
    
    /** Atribút name predstavuje meno používateľa. **/
    private String name = "";
    
    /** Atribút type predstavuje typ používateľa. **/
    private Type type = null;
    
    /**
     * Konštruktor triedy {@code User}.
     */
    public User() {
    }
    
    /**
     * Konštruktor pre inicializáciu atribútov triedy {@code User}.
     * 
     * @param username používateľské meno
     * 
     * @param email e-mail používateľa
     * 
     * @param name meno používateľa
     * 
     * @param typeString typ používateľa
     */
    public User(String username, String email, String name, String typeString) {
        this.username = username;
        this.email = email;
        this.name = name;
        setType(typeString);
    }
    
    /**
     * Konštruktor pre inicializáciu atribútov triedy {@code User}.
     * 
     * @param user používateľ na základe, ktorého inicializujeme
     */
    public User(User user) {
        this.email = user.getEmail();
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.type = user.getType();
        this.username = user.getUsername();
    }

    /**
     * Konštruktor pre inicializáciu atribútov triedy {@code User}.
     * 
     * @param id ID používateľa
     * 
     * @param username používateľské meno
     * 
     * @param email e-mail používateľa
     * 
     * @param password heslo používateľa
     * 
     * @param name meno používateľa
     * 
     * @param type typ používateľa
     */
    public User(int id, String username, String email, String password, String name, Type type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.type = type;
    }
    
    /**
     * Metóda pre zistenie, či niektorý z atribútov je prázdny.
     * 
     * @return true, ak niektorý atribút je prázdny, inak false
     */
    public boolean isAnyAttributeEmpty() {
        return username.equals("") || name.equals("") || type == null;
    }

    /**
     * Metóda pre získanie ID používateľa.
     * 
     * @return ID používateľa
     */
    public int getId() {
        return id;
    }

    /**
     * Metóda pre nastavenie ID používateľa.
     * 
     * @param id ID používateľa
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metóda pre získanie e-mailu používateľa.
     * 
     * @return e-mail používateľa 
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metóda pre nastavenie e-mailu používateľa.
     * 
     * @param email e-mail používateľa
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Metóda pre získanie používateľkého mena.
     * 
     * @return používateľské meno
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metóda pre nastavenie používateľkého mena.
     * 
     * @param username používateľské meno
     */    
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metóda pre získanie hesla používateľa.
     * 
     * @return heslo používateľa
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Metóda pre nastavenie hesla používateľa.
     * 
     * @param password heslo používateľa
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metóda pre získanie mena používateľa.
     * 
     * @return meno používateľa
     */
    public String getName() {
        return name;
    }

    /**
     * Metóda pre nastavenie mena používateľa.
     * 
     * @param name meno používateľa
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metóda pre získanie typu používateľa.
     * 
     * @return typ používateľa
     */
    public Type getType() {
        return type;
    }
    
    /**
     * Metóda pre získanie typu používateľa.
     * 
     * @return typ používateľa (reťazec)
     */
    public String getTypeString() {
        switch (type) {
            case ADMINISTRATOR:
                return InternationalizationClass.getBundle().getString("ADMINISTRATOR");
            case REFERENT:
                return InternationalizationClass.getBundle().getString("REFERENT");
            default:
                return InternationalizationClass.getBundle().getString("WAREHOUSEMAN");
        }
    }

    /**
     * Metóda pre nastavenie typu používateľa.
     * 
     * @param type typ používateľa
     */
    public void setType(Type type) {
        this.type = type;
    }
    
    /**
     * Metóda pre nastavenie typu používateľa.
     * 
     * @param typeString typ používateľa (reťazec)
     */
    public void setType(String typeString) {
        Type userType;
        if(typeString.equals(InternationalizationClass.getBundle().getString("ADMINISTRATOR")) || typeString.equals("administrator")) {
            userType = Type.ADMINISTRATOR;
        } else if (typeString.equals(InternationalizationClass.getBundle().getString("REFERENT")) || typeString.equals("referent")) {
            userType = Type.REFERENT;
        } else {
            userType = Type.WAREHOUSEMAN;
        }
        this.type = userType;
    }

    /**
     * Metóda pre porovanie daného používateľa s iným používateľom. 
     * Porovnávanie prebieha na základe používateľského mena.
     * 
     * @param user tovar, s ktorým porovnávame
     * 
     * @return true, ak sú používatelia zhodné, inak false 
     */
    public boolean equals(User user) {
        return username.equals(user.getUsername());
    }
    
}
