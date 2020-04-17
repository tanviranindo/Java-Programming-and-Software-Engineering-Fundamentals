
/**
 * VigenereBreaker is a class that can decipher a text that was encypted using Vigenere Cipher
 * with up to 100 keys (can be changed in the breakForLanguage() method if checking for more
 * keys is needed). This program can be used to decrypt a message with either known or unknown
 * key length that in encrypted in the known language (Danish, Dutch, English, French, German,
 * Italian, Portuguese, or Spanish).
 * 
 * Please select the encrypted text which you wish to decipher after running breakVigenere()
 * method and select all language libraries from the 'dictionaries' folder when prompted.
 * 
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (November 3, 2019)
 */

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedStr = new StringBuilder();
        char [] charSplitMessage = message.toCharArray();
        
        for (int k=whichSlice; k < message.length(); k+=totalSlices) {
            slicedStr.append(charSplitMessage[k]);
        }
        
        return slicedStr.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        CaesarCracker caesarCracker = new CaesarCracker(mostCommon);
        int[] key = new int[klength];
        
        for (int k=0; k < klength; k++) {
            String slicedStr = sliceString(encrypted, k, klength);
            int currKey = caesarCracker.getKey(slicedStr);
            key[k] = currKey;
        }
        
        return key;
    }
    
    public HashSet<String> readDictionary (FileResource fr) {
        HashSet<String> hashSet = new HashSet<String> ();
        
        for (String line : fr.lines()) {
            line = line.toLowerCase();
            hashSet.add(line);
        }
        
        return hashSet;
    }
    
    public int countWords (String message, HashSet<String> dictionary) {
        int count = 0;
        
        for (String word : message.split("\\W+")) {
            word = word.toLowerCase();
            if (dictionary.contains(word)) {
                count +=1;
            }
        }
        
        return count;
    }
    
    public String breakForLanguage (String encrypted, HashSet<String> dictionary) {
        String decrypted = "";
        int maxCount = 0;
        
        // We assume that max key length possible is 100 and most common letter in English is 'e'
        for (int k=1; k <= 100; k++) {
            char mostCommonLetter = 'e';
            int [] keys = tryKeyLength(encrypted, k, mostCommonLetter);
            
            VigenereCipher vigenereCipher = new VigenereCipher(keys);
            String currDecrypted = vigenereCipher.decrypt(encrypted);
            
            int currCount = countWords(currDecrypted, dictionary);
            if (currCount > maxCount) {
                maxCount = currCount;
                decrypted = currDecrypted;
                                
            }
        }
        
        return decrypted;
    }
    
    public void tester () {
        String decrypted = "";
        int maxCount = 0;
        int correctKeyLength = 0;
        
        FileResource resource = new FileResource ();
        String encrypted = resource.asString();
        
        FileResource rDict = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(rDict);
        
        // We assume that max key length possible is 100 and most common letter in English is 'e'
        for (int k=1; k <= 100; k++) {
            char mostCommonLetter = 'e';
            int [] keys = tryKeyLength(encrypted, k, mostCommonLetter);
            
            VigenereCipher vigenereCipher = new VigenereCipher(keys);
            String currDecrypted = vigenereCipher.decrypt(encrypted);
            
            int currCount = countWords(currDecrypted, dictionary);
            if (currCount > maxCount) {
                maxCount = currCount;
                decrypted = currDecrypted;
                                
            }
            //
            if (currCount == maxCount) {
                correctKeyLength = keys.length;
            }
        }
        
        System.out.println("Correct key length: " + correctKeyLength);
        System.out.println("Number of valid words: " + maxCount);
    }

    public void breakVigenereKnownKeyLength () {
        FileResource resource = new FileResource();
        String resourceStr = resource.asString();
        
        int keyLength = 4;
        char mostCommonLetter = 'e';
        int [] keys = tryKeyLength(resourceStr, keyLength, mostCommonLetter);
        
        VigenereCipher vigenereCipher = new VigenereCipher(keys);
        String decrypted = vigenereCipher.decrypt(resourceStr);
        
        System.out.println(decrypted);
        //System.out.println("Keys used to encrypt: " + Arrays.toString(keys));
    }
    
    public void tester2 () {
        int count = 0;
        FileResource resource = new FileResource();
        String resourceStr = resource.asString();
        
        FileResource rDict = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(rDict);
        
        int keyLength = 38;
        char mostCommonLetter = 'e';
        int [] keys = tryKeyLength(resourceStr, keyLength, mostCommonLetter);
        
        VigenereCipher vigenereCipher = new VigenereCipher(keys);
        String decrypted = vigenereCipher.decrypt(resourceStr);
        
        for (String word : decrypted.split("\\W+")) {
            word = word.toLowerCase();
            if (dictionary.contains(word)) {
                count +=1;
            }
        }
        
        System.out.println("Valid count: " + count);
    }
    
    public void breakVigenere () {
        FileResource resource = new FileResource ();
        String resourceStr = resource.asString();
        
        FileResource rDict = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(rDict);
        
        String decrypted = breakForLanguage(resourceStr, dictionary);
        System.out.println(decrypted);
    }
}
