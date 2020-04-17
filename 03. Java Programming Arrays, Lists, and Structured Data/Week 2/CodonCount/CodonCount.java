
/**
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (October 21, 2019)
 */

import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap <String, Integer> codonMap;
    private int count;
    private int maxCount;
    
    public CodonCount() {
        codonMap = new HashMap <String, Integer> ();
        count = 0;
        maxCount = 0;
    }
    
    public void buildCodonMap (int start, String dna) {
        codonMap.clear();
        dna = dna.toUpperCase();
        
        for (int k=start; k+3 < dna.length(); k+=3) {
            String codon = dna.substring(k, k+3);
            
            if (codonMap.keySet().contains(codon)) {
                codonMap.put(codon, codonMap.get(codon) + 1);
            }
            else {
                codonMap.put(codon, 1);
                count += 1;
            }
        }        
    }
    
    public String getMostCommonCodon () {
        String maxCountCodon = "";
        for (String codon : codonMap.keySet()) {
            int occurrencies = codonMap.get(codon);
            if (occurrencies > maxCount) {
                maxCount = occurrencies;
                maxCountCodon = codon;
            }
        }
        return maxCountCodon;
    }
    
    public void printCodonCounts (int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are: ");
        for (String codon : codonMap.keySet()) {
            int occurrencies = codonMap.get(codon);
            if (occurrencies >= start && occurrencies <= end) {
                System.out.println(codon + "\t" + occurrencies);
            }
        }
    }
    
    public void tester () {
        FileResource resource = new FileResource();
        String dna = resource.asString();
        
        int startString = 0;
        buildCodonMap(startString, dna);
        System.out.println("Reading frame starting with " + startString + 
                           " results in " + count + " unique codons");
         
        String maxCountCodon = getMostCommonCodon();
        System.out.println("and most common codon is " + maxCountCodon + " with count " + maxCount);
        
        int startCount = 1;
        int endCount = 5;
        printCodonCounts(startCount, endCount);
    }
}
