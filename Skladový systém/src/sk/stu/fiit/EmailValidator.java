/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ivan Vykopal
 */
public final class EmailValidator {
 
    private EmailValidator() {}
    
    public static boolean checkEmail(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
