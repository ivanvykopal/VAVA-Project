/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Trieda predstavujúca hasher pre heslá. 
 *
 * @author Ivan Vykopal
 */
public final class PasswordHasher {
    
    /**
     * Privátny konštruktor triedy {@code PasswordHasher}.
     */
    private PasswordHasher() {}
    
    /**
     * Metóda určená pre konverziu zadaného hesla na pole znakov pomocou SHA-256.
     * 
     * @param input heslo
     * 
     * @return pole znakov konvertovaného hesla
     * 
     * @throws NoSuchAlgorithmException výnimka pri nenájdení algoritmu pre
     * kryptografiu
     */
    //https://www.baeldung.com/sha-256-hashing-java
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Metóda pre skonvertovanie poľa znakov na reťazec.
     * 
     * @param hash pole znakov konvertovaného hesla
     * 
     * @return reťazcová reprezentácia zahashovaného hesla
     */
    public static String SHAtoString(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
}
