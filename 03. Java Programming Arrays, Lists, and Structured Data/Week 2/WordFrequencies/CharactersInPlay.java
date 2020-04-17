
/**
 * CharactersInPlay class contains the following methods:
 * * Update() that will add character names and its count to characterNames and characterFreqs.
 * * findAllCharacters that is intended to find all characters from the text.
 * * findIndexOfMax() that will find who is the main character of the play and its speaking parts count.
 * * charactersWithNumParts() that is able to select a character with a specified speaking parts count.
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (October 19, 2019)
 */

import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList <String> characterNames;
    private ArrayList <Integer> characterFreqs;
    private int maxCount;
    private String mainCharacter;
    
    public CharactersInPlay () {
        characterNames = new ArrayList <String> ();
        characterFreqs = new ArrayList <Integer> ();
    }
    
    private void update (String person) {
        int index = characterNames.indexOf(person);
        if (index == -1) {
            characterNames.add(person);
            characterFreqs.add(1);
        }
        else {
            int value = characterFreqs.get(index);
            characterFreqs.set(index, value + 1);
        }
    }
    
    private void findAllCharacters () {
        characterNames.clear();
        characterFreqs.clear();
        FileResource resource = new FileResource();
        
        for (String line : resource.lines()) {
            int index = line.indexOf(".");
            // If it is the first dot of the text get the corresponding substring b/w 0 and "."
            // update() will add those strings and their count to ArrayLists
            // (Shakespeare's plays notation indicates characters in such way)
            if (line.contains(".")) {
                String person = line.substring(0, index).trim();
                update(person);
            }
        }
    }
    
    private void findIndexOfMax () {
        maxCount = 0;
        mainCharacter = null;
        int indexOfMax = 0;
        
        for (int k=0; k < characterFreqs.size(); k++) {
            int currValue = characterFreqs.get(k);
            
            if (currValue > maxCount) {
                maxCount = currValue;                
                indexOfMax = characterFreqs.indexOf(maxCount);
            }
        }
        mainCharacter = characterNames.get(indexOfMax);
    }
     
    private void charactersWithNumParts (int num1, int num2) {
        findAllCharacters();
        System.out.println("List of the unique characters (speaking parts b/w " + num1 + 
                            " and " + num2 + "): ");
        
        for (int k=0; k < characterNames.size(); k++) {
            if (characterFreqs.get(k) >= num1 && characterFreqs.get(k) <= num2) {
               System.out.println(characterNames.get(k) + "\t" + characterFreqs.get(k));
            }
        }        
    }
    
    public void tester () {
        findAllCharacters();
        System.out.println("List of the unique characters: ");
        // Print out all characters and their speaking parts count
        for (int k=0; k < characterNames.size(); k++) {
            if (characterFreqs.get(k) > 1) {
                System.out.println(characterNames.get(k) + "\t" + characterFreqs.get(k));
            }
        }
        // Print out the name of the main character and the count of its speaking parts
        findIndexOfMax();
        System.out.println("The main character and its speaking parts count are: " 
                            + mainCharacter + " " + maxCount);
    }
    
    public void tester2 () {
        // num1 should be less or equal to num2
        int num1 = 10;
        int num2 = 15;
        charactersWithNumParts(num1, num2);
    }
}   
