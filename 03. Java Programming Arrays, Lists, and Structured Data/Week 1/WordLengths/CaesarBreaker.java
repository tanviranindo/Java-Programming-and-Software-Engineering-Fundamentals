
/**
 * CaesarBreaker class contains methods that can be used used to decrypted Caesar cipher
 * entrypted messages. decrypt() method for decrypting messages that used 1 key for encryption
 * and decryptTwoKeys() for messages that used 2 encryption keys.
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0.0 (September 26, 2019)
 */

import edu.duke.*;

public class CaesarBreaker {
    public String decrypt (String encrypted) {
        CaesarCipher CeasarCipher = new CaesarCipher();
        int [] freqs = countLetters(encrypted);
        int maxIndex = maxIndex(freqs);
        int distanceKey = maxIndex - 4;
        if (maxIndex < 4) {
            distanceKey = 26 - (4 - maxIndex);
        }
        String message = CeasarCipher.encrypt(encrypted, 26 - distanceKey);
        return message;
    }
    
    public int [] countLetters (String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int [26];
        for (int i = 0; i < message.length(); i++) {
            char currChar = Character.toLowerCase(message.charAt(i));
            int index = alphabet.indexOf(currChar);
            if (index != -1) {
                counts[index] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex (int [] values) {
        int maxIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void testDecrypt () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decrypt(encrypted);
        System.out.println("The decrypted message is " + decrypted);
    }
    
    public String halfOfString (String message, int start) {
        StringBuilder half1 = new StringBuilder();
        StringBuilder half2 = new StringBuilder();
        
        for (int i = 0; i < message.length(); i++) {
            char currChar = message.charAt(i);
            
            if (Character.isLetter(currChar)) {
                if (i % 2 == 0) {
                    half1.append(currChar);
                }
                else {
                    half2.append(currChar);
                }  
            }
            
            // Otherwise do nothing
        }
        
        String half = "";
        
        if (start == 0) {
            half = half1.toString();
        }
        else if (start == 1) {
            half = half2.toString();
        }
        
        return half;
    }
    
    public void testHalfOfString () {
        String message = "Qbkm Zgis";
        // Choose 0 or 1
        int start = 0;
        
        String halfOfString = halfOfString(message, start);
        System.out.println("The half string is " + halfOfString + " if a start is at " + start);
    }
    
    public int getKey (String s) {
        int [] freqs = countLetters(s);
        int maxIndex = maxIndex(freqs);
        
        int distanceKey = maxIndex - 4;
        if (maxIndex < 4) {
            distanceKey = 26 - (4 - maxIndex);
        }
        
        return distanceKey;
    }
    
    public void testGetKey () {
        FileResource fr = new FileResource();
        String string = fr.asString();
        int stringKey = getKey(string);
        System.out.println("The key is " + stringKey);
    }
    
    public String decryptTwoKeys (String encrypted) {
        CaesarCipher CeasarCipher = new CaesarCipher();
        
        String half1 = halfOfString(encrypted, 0);
        String half2 = halfOfString(encrypted, 1);
        
        int keyHalf1 = getKey(half1);
        int keyHalf2 = getKey(half2);
        
        System.out.println("Key 1: " + keyHalf1 + ", key 2: " + keyHalf2);
        
        String message = CeasarCipher.encryptTwoKeys(encrypted, 26 - keyHalf1, 26 - keyHalf2);
        return message;
    }
    
    public void testDecryptTwoKeys () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println("The decrypted message is " + decrypted);
    }
}
