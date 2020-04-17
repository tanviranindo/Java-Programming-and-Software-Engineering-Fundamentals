
/**
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (October 24, 2019)
 */

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap <String, ArrayList <String>> myMap;
    private ArrayList <String> usedWordsList;
    private Random myRandom;
    private int countReplacedWords = 0;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap <String, ArrayList <String>> ();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap <String, ArrayList <String>> ();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String [] labels = {"adjective", "noun", "color", "country", "name", "animal", 
                            "timeframe", "verb", "fruit"};                    
        for (String string : labels) {
            ArrayList <String> labelsList = readIt(source + "/" + string + ".txt");
            myMap.put(string, labelsList);
        }
        
        usedWordsList = new ArrayList <String> ();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        for (String key : myMap.keySet()) {
            if (key.equals(label)) {
                return randomFrom(myMap.get(key));
            }
        }
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        
        while (true) {
            if (!usedWordsList.contains(sub)) {
                usedWordsList.add(sub);
                countReplacedWords += 1;
                break;
            }
            sub = getSubstitute(w.substring(first+1,last));
        }
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        String uniqueWord = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap () {
        int totalWordsInMap = 0;
        
        for (String key : myMap.keySet()) {
            for (String value : myMap.get(key)) {
                totalWordsInMap +=1;
            }
        }
        
        return totalWordsInMap;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        
        System.out.println("");
        System.out.println("Number of words replaced: " + countReplacedWords);
        
        int totalWordsInMap = totalWordsInMap();
        System.out.println("Total number of words that were possible to pick from: "
                           + totalWordsInMap);
        
        usedWordsList.clear();
    }
}
