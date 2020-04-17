
/**
 * CaesarCipher class contains multiple methods such as:
 * * encrypt() method uses Caesar cipher to encrypt input string using a chosen key value.
 * * encryptTwoKeys() method uses Caesar cipher to encrypt input string using chosen 2 key
 * * values.
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (September 24, 2019)
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt (String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = alphabetUpper.toLowerCase();
        // Shift alphabet strings at the indicated key value
        String shiftedAlphabetUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0, key);
        String shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            // Get an index of currChar in the alphabet string (Look in both upper- and lowercases)
            int indexUpper = alphabetUpper.indexOf(currChar);
            int indexLower = alphabetLower.indexOf(currChar);
            
            if (indexUpper != -1) {
                char newCharUpper = shiftedAlphabetUpper.charAt(indexUpper);
                encrypted.setCharAt(i, newCharUpper);
            }
            
            if (indexLower != -1) {
                char newCharLower = shiftedAlphabetLower.charAt(indexLower);
                encrypted.setCharAt(i, newCharLower);
            }
            
            // Otherwise do nothing
        }
        
        return encrypted.toString();
    }
    
    public void testCaesar () {
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("Message : " + message); 
        System.out.println("Key : " + key);
        System.out.println("Encrypted message : " + encrypted);
    }
    
    public String encryptTwoKeys (String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = alphabetUpper.toLowerCase();
        // Shift alphabet strings at the indicated key1 value
        String shiftedAlphabetUpperKey1 = alphabetUpper.substring(key1) + alphabetUpper.substring(0, key1);
        String shiftedAlphabetLowerKey1 = alphabetLower.substring(key1) + alphabetLower.substring(0, key1);
        // Shift alphabet strings at the indicated key2 value
        String shiftedAlphabetUpperKey2 = alphabetUpper.substring(key2) + alphabetUpper.substring(0, key2);
        String shiftedAlphabetLowerKey2 = alphabetLower.substring(key2) + alphabetLower.substring(0, key2);
        
        for (int i = 0; i < encrypted.length(); i++) {
          char currChar = encrypted.charAt(i);
          // Get an index of currChar in the alphabet string (Look in both upper- and lowercases)
          int indexUpper = alphabetUpper.indexOf(currChar);
          int indexLower = alphabetLower.indexOf(currChar);
          
          if (indexUpper != -1) {
              if (i % 2 == 0) {
                  char newCharUpperKey1 = shiftedAlphabetUpperKey1.charAt(indexUpper);
                  encrypted.setCharAt(i, newCharUpperKey1);
              }
              else {
                  char newCharUpperKey2 = shiftedAlphabetUpperKey2.charAt(indexUpper);
                  encrypted.setCharAt(i, newCharUpperKey2);
              }
          }
          
          if (indexLower != -1) {
              if (i % 2 == 0) {
                  char newCharLowerKey1 = shiftedAlphabetLowerKey1.charAt(indexLower);
                  encrypted.setCharAt(i, newCharLowerKey1);
              }
              else {
                  char newCharLowerKey2 = shiftedAlphabetLowerKey2.charAt(indexLower);
                  encrypted.setCharAt(i, newCharLowerKey2);
              }
          }
            
          // Otherwise do nothing
        }
        
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys () {
        int key1 = 8;
        int key2 = 21;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("Message : " + message); 
        System.out.println("Keys used : " + key1 + " and " + key2);
        System.out.println("Encrypted message : " + encrypted);
    }
}
