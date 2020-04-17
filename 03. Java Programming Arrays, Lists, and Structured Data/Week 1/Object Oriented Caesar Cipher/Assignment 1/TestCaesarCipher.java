
/**
 * TestCaesarCipher class contains testing scenarios for CaesarCipher methods.
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (October 17, 2019)
 */

import edu.duke.*;

public class TestCaesarCipher {
    private int [] countLetters (String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int [26];
        
        for (int i = 0; i < message.length(); i++) {
            char currChar = message.charAt(i);
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
    
    public String breakCaesarCipher (String input) {
        int [] freqs = countLetters(input);
        int maxIndex = maxIndex(freqs);
        int distanceKey = maxIndex - 4;
        if (maxIndex < 4) {
            distanceKey = 26 - (4 - maxIndex);
        }
        
        CaesarCipher cc = new CaesarCipher(26 - distanceKey);
        String decrypted = cc.encrypt(input);
        
        return decrypted;
    }
    
    public void simpleTests () {
        int key = 15;
        CaesarCipher cc = new CaesarCipher(key);
        
        FileResource fr = new FileResource();        
        String message = fr.asString();
        
        String encrypted = cc.encrypt(message);
        System.out.println("The encrypted message: " + encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println("The decrypted message: " + decrypted + " (when key is known)");
        
        String decryptedBreakCC = breakCaesarCipher(encrypted);
        System.out.println("The decrypted message: " + decryptedBreakCC + " (when key is unknown)");
    }
}
