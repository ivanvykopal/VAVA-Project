/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Trieda určená pre kontrolu správnosti e-mailu.
 *
 * @author Ivan Vykopal
 */
public final class EmailValidator {
 
    /**
     * Privátny konštruktor triedy {@code EmailValidator}.
     */
    private EmailValidator() {}
    
    /**
     * Metóda pre kontrolu správneho formátu e-mailu.
     * 
     * @param email reťazec predstavujúci e-mail
     * 
     * @return true, ak je e-mail korektný, inak false
     */
    public static boolean checkEmail(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
