
/**
 * CaesarCipherTwo class is used to encrypt any message using Caesar cipher with specified
 * 2 keys. It also has a decrypt() method that can be used to decrypt the encrypted message.
 * 
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0. (October 17, 2019)
 */

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo (int key1, int key2) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt (String input) {
        StringBuilder sb = new StringBuilder(input);
        
        for (int i = 0; i < sb.length(); i++) {
            char currChar = Character.toLowerCase(sb.charAt(i));
            
            int index = alphabet.indexOf(currChar);
            
            if (index != -1) {
                if (i % 2 == 0) {
                    char newChar1 = shiftedAlphabet1.charAt(index);
                    sb.setCharAt(i, newChar1);
                }
                else {
                    char newChar2 = shiftedAlphabet2.charAt(index);
                    sb.setCharAt(i, newChar2);
                }
            }
            
            // Otherwise do nothing
        }
        return sb.toString();
    }
    
    public String decrypt (String input) {
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        String decrypted = cc2.encrypt(input);
        
        return decrypted;
    }
}
