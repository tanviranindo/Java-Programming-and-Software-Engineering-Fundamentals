
/**
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (October 22, 2019)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap <String, ArrayList <String>> wordMap;
    
    public WordsInFiles () {
        wordMap = new HashMap <String, ArrayList <String>> ();
    }
    
    private void addWordsFromFile (File file) {
        FileResource fr = new FileResource(file);
        String filename = file.getName();
        
        for (String word : fr.words()) {
            
            if (wordMap.keySet().contains(word)) { 
                ArrayList <String> values = wordMap.get(word);
                if (! values.contains(filename)) {  
                    values.add(filename);
                }
                
                // Otherwise do nothing
            }
            else {
                ArrayList <String> values = new ArrayList <String> ();
                values.add(filename);
                wordMap.put(word, values);
            }
        }
    }
    
    private void buildWordFileMap () {
        wordMap.clear();
        
        DirectoryResource dr = new DirectoryResource ();
        
        for (File file : dr.selectedFiles()) {
            addWordsFromFile(file);
        }
    }
    // maxNumber() method returns the maximum number of files any word appears in
    private int maxNumber () {
        int maxNumber = 0;
        
        for (String key : wordMap.keySet()) {
            int count = 0;
            for (String value : wordMap.get(key)) {
                count +=1;
            }
            if (count > maxNumber) {
                maxNumber = count;
            }
        }
        
        return maxNumber;
    }
    
    private ArrayList wordsInNumFiles (int number) {
        ArrayList <String> wordsArrayList = new ArrayList <String> ();
        
        for (String key : wordMap.keySet()) {
            int count = 0;
            for (String value : wordMap.get(key)) {
                count +=1;
            }
            if (count == number) {
                wordsArrayList.add(key);
            }
        }
        
        return wordsArrayList;
    }
    
    private void printFilesIn (String word) {
        ArrayList <String> filenames = new ArrayList <String> ();
        
        for (String key : wordMap.keySet()) {
            if (key.equals(word)) {
                for (String value : wordMap.get(key)) {
                    filenames.add(value);
                }
            }
        }
        System.out.println("The word \"" + word + "\" appears in the following file(s): ");
        for (int k=0; k < filenames.size(); k++) {
            System.out.println(filenames.get(k));
        }
    }
    
    public void tester () {
        buildWordFileMap();
        
        for (String key : wordMap.keySet()) {
            System.out.println("The word \"" + key + "\" appears in file(s): ");
            for (String value : wordMap.get(key)) {
                System.out.println(value);
            }
            System.out.println("");
        }
        
        int maxNumber = maxNumber();
        System.out.println("The greatest number of files a word appears in is " + maxNumber);
        
        ArrayList <String> wordsInNumFiles = wordsInNumFiles(4);
        System.out.println("There are " + wordsInNumFiles.size() + " such words : " + 
                           wordsInNumFiles + "\n");
        
        for (int k=0; k < wordsInNumFiles.size(); k++) {
            printFilesIn(wordsInNumFiles.get(k));
            System.out.println("");
        }
    }
}
