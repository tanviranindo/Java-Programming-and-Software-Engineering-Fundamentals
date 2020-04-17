
/**
 * WordFrequencies class contains the following methods:
 * * findUnique () that will find each unique word in the text and count how many times it occurred.
 * * findIndexOfMax() that will find most frequent word in the text file and its count.
 * 
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (October 18, 2019)
 */

import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList <String> myWords;
    private ArrayList <Integer> myFreqs;
    private int maxCount;
    private String maxCountWord;
    
    public WordFrequencies () {
        myWords = new ArrayList <String> ();
        myFreqs = new ArrayList <Integer> ();
    }
    
    private void findUnique () {
        myWords.clear();
        myFreqs.clear();
        maxCount = 0;
        maxCountWord = null;
        
        FileResource resource = new FileResource();
        
        for (String string : resource.words()) {
            string = string.toLowerCase();
            int index = myWords.indexOf(string);
            
            if (index == -1) {
                myWords.add(string);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }
    
    private void findIndexOfMax () {
        int indexOfMax = 0;
        
        for (int k=0; k < myFreqs.size(); k++) {
            int currValue = myFreqs.get(k);
            
            if (currValue > maxCount) {
                maxCount = currValue;                
                indexOfMax = myFreqs.indexOf(maxCount);
            }
        }
        maxCountWord = myWords.get(indexOfMax);
    }
    
    public void tester () {
        findUnique();
        
        Integer uniqueWords = myWords.size();
        System.out.println("Number of unique words: " + uniqueWords);
        // Print out each unique word and its count from the text
        for (int k=0; k < myWords.size(); k++) {
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
        // Print out the most frequent word and its count
        // If there are multiple words that have maxCount value, only print the first one
        findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " 
                            + maxCountWord + " " + maxCount);
    }
}
