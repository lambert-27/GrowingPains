package controller;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains controller package - PasswordHasher - Performs a hash function an a users password before inserting into the database
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

/**
 * PasswordHasher is used throughout the system when the customer alters or creates a new Password. 
 * Before inserting the password into the Customers table, first the SHA-256 hashing algorithm is performed
 */
public class PasswordHasher {
    
    /**
     * Performs thee SHA-256 hashing algorithm on the users input password
     * @param password The password input by the user
     * @return The hexadecimal of the function, converted to a string 
     * @throws Exception thrown if an error occurs when attempting to perform the SHA-256 algorithm
     */
    public static String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        
        return hexString.toString();
    }
    
   /**
    * Method which compares hashed passwords
    * @param inputPassword The new password input by the Customer
    * @param storedHash The old password, stored in the Customer table
    * @return True if and only if the passwords are the same
    * @throws Exception thrown if an error occurs when attempting to perform the SHA-256 algorithm
    */
    public static boolean verifyPassword(String inputPassword, String storedHash) 
            throws Exception {
        String hashedInput = hashPassword(inputPassword);
        if(hashedInput.equals(storedHash)) {
        	return true;
        }
        else
        	return false;
    }
}