
/**
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0.0 (September 25, 2019)
 */

import edu.duke.*;

public class WordLengths {
    public void countWordLengths (FileResource resource, int counts []) {
        for (String word : resource.words()) {
            int wordLength = word.length();
            
            if (!Character.isLetter(word.charAt(0))) {
                wordLength = wordLength - 1;
            }
            if (!Character.isLetter(word.charAt(wordLength - 1)) && wordLength != 1) {
                wordLength = wordLength - 1;
            }
            
            if (wordLength >= 30) {
                counts[30] += 1;
            }
            else {
                counts[wordLength] += 1;
            }
        }
        
        int maxCount = 0;
        int indexOfMaxCount = 0;
        
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > maxCount) {
                maxCount = counts[i];
                indexOfMaxCount = i;
            }
        }
        System.out.println("The most common word length is " + indexOfMaxCount);
        System.out.println("It has occurred " + maxCount + " times");
    }
    
    public int indexOfMax (int values []) {
        int maxCount = 0;
        int indexOfMaxCount = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxCount) {
                maxCount = values[i];
                indexOfMaxCount = i;
            }
        }
        
        return indexOfMaxCount;
    }
    
    public void testCountWordLenghts () {
        int [] counts = new int [31];
        FileResource resource = new FileResource();
        countWordLengths(resource, counts);
    }
}
