package controller;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class PasswordHasher {
    
    // Basic SHA-256 hashing (as in your original code)
    public static String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        
        return hexString.toString();
    }
    
    // Simple verification (compare hashes)
    public static boolean verifyPassword(String inputPassword, String storedHash) 
            throws Exception {
        String hashedInput = hashPassword(inputPassword);
        return hashedInput.equals(storedHash);
    }
}