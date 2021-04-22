/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Model;

import java.io.Serializable;

/**
 * Trieda enumerátor predstavujúca informáciu o tom, či položka je na sklade,
 * alebo mimo skladu.
 *
 * @author Ivan Vykopal
 */
public enum Position implements Serializable {
    /** Na sklade. **/
    IN_STOCK,
    
    /** Výroba. **/
    PRODUCTION,
    
    /** Mimo skladu. **/
    OUT_STOCK
}
