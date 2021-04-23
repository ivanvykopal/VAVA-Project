/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import java.util.ArrayList;
import java.util.Random;

/**
 * Trieda predstavujúca generátor pre heslo pre nového používateľa.
 * 
 * Inšpirácia: https://stackoverflow.com/questions/19743124/java-password-generator
 * 
 * @author Ivan Vykopal
 */
public final class PasswordGenerator {
    
    /** Atribút LOWER predstavuje zoznam malých písmen abecedy. **/
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    
    /** Atribút UPPER predstavuje zoznam veľkých písmen abecedy. **/
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /** Atribút DIGITS predstavuje zoznam čísel. **/
    private static final String DIGITS = "0123456789";
    
    /** Atribút PUNCTUATION predstavuje zoznam povelených znakov. **/
    private static final String PUNCTUATION = ".,";
    
    /** Atribút LENGTH predstavuje dĺžku hesla. **/
    private static final int LENGTH = 12;
    
    /**
     * Privátny konštruktor triedy {@code PasswordGenerator}.
     */
    private PasswordGenerator() {}
    
    /**
     * Metóda pre vygenerovanie hesla pre nového používateľa.
     * 
     * @return vygenerované heslo 
     */
    public static String generatePassword() {
        StringBuilder password = new StringBuilder(LENGTH);
        
        Random random = new Random(System.nanoTime());
        
        ArrayList<String> characters = new ArrayList<>();
        characters.add(LOWER);
        characters.add(UPPER);
        characters.add(DIGITS);
        characters.add(PUNCTUATION);
        
        for (int i = 0; i < LENGTH; i++) {
            String character = characters.get(random.nextInt(characters.size()));
            int position = random.nextInt(character.length());
            password.append(character.charAt(position));
        }
        
        return new String(password);
    }

}
