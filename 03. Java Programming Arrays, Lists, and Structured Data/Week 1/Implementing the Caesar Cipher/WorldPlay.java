
/**
 * WorldPlay class contains multiple methods such as:
 * * isVowel() method that checks whether a passed character is a vowel or not.
 * * replaceVowels() method that replaces all vowels in a passed string with a selected character.
 * * emphasize() method replaces characters in a string that are equal to ch and even to '*'
 * * * and odd to '+'. Other characters in the string remain the same.
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (September 23, 2019)
 */

import edu.duke.*;

public class WorldPlay { 
    public boolean isVowel (Character ch) {
        boolean isVowel;
        // Check whether the character is a vowel (lower or upper case)
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
            ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
            isVowel = true;
        }
        else {
            isVowel = false;
        }
        return isVowel;
    }
    
    public void testIsVowel () {
        Character ch1 = 'F';
        boolean test1 = isVowel(ch1);
        System.out.println("First test scenario returns: " + test1);
        
        Character ch2 = 'a';
        boolean test2 = isVowel(ch2);
        System.out.println("First test scenario returns: " + test2);
    }
     
    public String replaceVowels (String phrase, Character ch) {
        StringBuilder stringBuilder = new StringBuilder(phrase);
        
        for (int i = 0; i < stringBuilder.length(); i++) {
            Character currChar = stringBuilder.charAt(i);
            if (isVowel(currChar) == true) {
                stringBuilder.setCharAt(i, ch);
            }
            
            //Otherwise do nothing
        }
        return stringBuilder.toString();
    }
    
    public void testReplaceVowels () {
        String str1 = "Hello World";
        String strreplaced1 = replaceVowels(str1, '*');
        System.out.println("Phrase \"" + str1 + "\" was replaced with \"" + strreplaced1 + "\"");
        
        String str2 = "All roads lead to Rome";
        String strreplaced2 = replaceVowels(str2, '/');
        System.out.println("Phrase \"" + str2 + "\" was replaced with \"" + strreplaced2 + "\"");        
    }
    
    public String emphasize (String phrase, Character ch) {
        StringBuilder stringBuilder = new StringBuilder(phrase);
        
        for (int i = 0; i < stringBuilder.length(); i++) {
            Character currChar = stringBuilder.charAt(i);
            Character currCharLower = Character.toLowerCase(currChar);
            
            if (currCharLower.equals(ch) && i % 2 == 0) {
                stringBuilder.setCharAt(i, '*');
            }
            else if (currCharLower.equals(ch) && i % 2 != 0) {
                stringBuilder.setCharAt(i, '+');
            }
            
            //Otherwise do nothing
        }
        return stringBuilder.toString();
    }
    
    public void testEmphasize () {
        String str1 = "dna ctgaaactga";
        String strreplaced1 = emphasize(str1, 'a');
        System.out.println("String \"" + str1 + "\" is replaced with \"" + strreplaced1 + "\"");
        
        String str2 = "Mary Bella Abracadabra";
        String strreplaced2 = emphasize(str2, 'a');
        System.out.println("String \"" + str2 + "\" is replaced with \"" + strreplaced2 + "\"");
    }
}
