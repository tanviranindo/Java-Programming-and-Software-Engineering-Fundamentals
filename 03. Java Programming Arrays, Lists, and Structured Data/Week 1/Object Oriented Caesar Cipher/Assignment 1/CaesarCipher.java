
/**
 * CaesarCipher class is used to encrypt any message using Caesar cipher with a specified key.
 * It also has a decrypt() method that can be used to decrypt the encrypted message.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (October 17, 2019)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher (int key) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt (String input) {
        StringBuilder sb = new StringBuilder(input);
        
        for (int i = 0; i < sb.length(); i++) {
            char currChar = Character.toLowerCase(sb.charAt(i));
            
            int index = alphabet.indexOf(currChar);
            
            if (index != -1) {
                // Get a character from the shiftedAlphabet that corresponds to the index
                char newChar = shiftedAlphabet.charAt(index);
                // Replace the ith character of the encrypted string with newCharUpper
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }
    
    public String decrypt (String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String decrypted = cc.encrypt(input);
        
        return decrypted;
    }
}