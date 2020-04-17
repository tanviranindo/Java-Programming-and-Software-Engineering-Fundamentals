
/**
 * TestCaesarCipherTwo class contains testing scenarios for CaesarCipherTwo methods.
 * 
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0. (October 17, 2019)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    private int [] countLetters (String message) {
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
    
    private int maxIndex (int [] values) {
        int maxIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    private String halfOfString (String message, int start) {
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
    
    private int getKey (String s) {
        int [] freqs = countLetters(s);
        int maxIndex = maxIndex(freqs);
        
        int distanceKey = maxIndex - 4;
        if (maxIndex < 4) {
            distanceKey = 26 - (4 - maxIndex);
        }
        
        return distanceKey;
    }
    
    public String breakCaesarCipher (String input) {
        String half1 = halfOfString(input, 0);
        String half2 = halfOfString(input, 1);
        
        int keyHalf1 = getKey(half1);
        int keyHalf2 = getKey(half2);
        
        System.out.println("First key: " + keyHalf1);
        System.out.println("Second key: " + keyHalf2);
        
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26 - keyHalf1, 26 - keyHalf2);
        String message = cc2.encrypt(input);
        
        return message;
    }
    
    public void simpleTests () {
        int key1 = 14;
        int key2 = 24;
        CaesarCipherTwo cc2 = new CaesarCipherTwo(key1, key2);
        
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        String encrypted = cc2.encrypt(message);
        System.out.println("The encrypted message: " + encrypted);
        
        String decrypted = cc2.decrypt(message);
        System.out.println("The decrypted message: " + decrypted + " (when key is known)");
        
        String decryptedBreakCC2 = breakCaesarCipher(message);
        System.out.println("The decrypted message: " + decryptedBreakCC2 + " (when key is unknown)");
    }
}
