
/**
 * VigenereBreaker is a class that can decipher a text that was encypted using Vigenere Cipher
 * with up to 100 keys (can be changed in the breakForLanguage() method if checking for more
 * keys is needed). The program will determine how many keys were used to encrypt the message
 * and in which language it was originally written (Danish, Dutch, English, French, German,
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
import java.io.*;
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
    
    public char mostCommonCharIn (HashSet<String> dictionary) {
        char mostCommonCharIn = Character.MIN_VALUE;
        
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        
        for (String word : dictionary) {
            for(int k=0; k < word.length(); k++){
                int dex = alph.indexOf(Character.toLowerCase(word.charAt(k)));
                if (dex != -1){
                    counts[dex] += 1;
                }
            }
        }
        
        int maxDex = 0;
        
        for(int k=0; k < counts.length; k++){
            if (counts[k] > counts[maxDex]){
                maxDex = k;
            }
        }
        
        mostCommonCharIn = alph.charAt(maxDex);
        
        return mostCommonCharIn;
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
        
        // We assume that max key length possible is 100
        for (int k=1; k <= 100; k++) {
            char mostCommonLetter = mostCommonCharIn(dictionary);
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
    
    public void breakForAllLangs (String encrypted, HashMap<String,HashSet<String>> languages) {
        int maxCount = 0;
        String decrypted = "";
        String language = "";
        
        for (String currLanguage : languages.keySet()) {
            System.out.println("Comparing to " + currLanguage);
            HashSet<String> currDict = languages.get(currLanguage);
            String currDecrypted = breakForLanguage(encrypted, currDict);
            
            int currCount = countWords(currDecrypted, currDict);
            if (currCount > maxCount) {
                maxCount = currCount;
                decrypted = currDecrypted;
                language = currLanguage;
            }
        }
        //
        System.out.println(decrypted);
        System.out.println("Language used: " + language);
    }
    
    public void breakVigenere () {
        System.out.println("Select the encrypted file");
        FileResource resource = new FileResource();
        String encrypted = resource.asString();
        
        HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>> ();
        
        System.out.println("Select the dictionary folder");
        DirectoryResource dirResource = new DirectoryResource();
        for (File currFile : dirResource.selectedFiles()) {
            String currName = currFile.getName();
            FileResource currDict = new FileResource(currFile);
            HashSet<String> currDictContents = readDictionary(currDict);
            
            languages.put(currName, currDictContents);
        }
        
        breakForAllLangs(encrypted,languages);
    }
}
