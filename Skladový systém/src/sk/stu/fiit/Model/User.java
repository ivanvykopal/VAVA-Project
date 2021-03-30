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
public class User {
    private int id;
    private String username = "";
    private String email = "";
    private String password = "";
    private String name = "";
    private Type type = null;
    
    public User() {
    }
    
    public User(int id, String username, String email, String name, String typeString) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        setType(typeString);
    }

    public User(int id, String username, String email, String password, String name, Type type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.type = type;
    }
    
    public boolean isAnyAttributeEmpty() {
        return username.equals("") || name.equals("") || type == null;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }
    
    public String getTypeString() {
        switch (type) {
            case ADMINISTRATOR:
                return "administrator";
            case REFERENT:
                return "referent";
            default:
                return "warehouseman";
        }
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public void setType(String typeString) {
        Type userType;
        switch (typeString) {
            case "Administrátor": 
            case "administrator":    
                userType = Type.ADMINISTRATOR;
                break;
            case "Referent": 
            case "referent":    
                userType = Type.REFERENT;
                break;
            default: 
                userType = Type.WAREHOUSEMAN;
                break;
        }
        this.type = userType;
    }
    
}
