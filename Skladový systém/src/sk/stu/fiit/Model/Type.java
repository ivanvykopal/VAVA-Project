/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.io.Serializable;

/**
 * Trieda enumerátor predstavuje typ používateľa.
 *
 * @author Ivan Vykopal
 */
public enum Type implements Serializable {
    
    /** Typ Administrátor. **/
    ADMINISTRATOR,
    
    /** Typ Skladník. **/
    WAREHOUSEMAN,
    
    /** Typ Referent. **/
    REFERENT
}
