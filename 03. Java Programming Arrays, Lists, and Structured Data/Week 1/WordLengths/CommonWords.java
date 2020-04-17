
/**
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0.0 (September 25, 2019)
 */

import edu.duke.*;

public class CommonWords {
    public String[] getCommon () {
        FileResource resource = new FileResource("data/common.txt");
        String [] common = new String [20];
        int index = 0;
        for (String s : resource.words()) {
            common[index] = s;
            index += 1;
        }
        return common;
    }
    
    public int indexOf (String [] list, String word) {
        for (int i = 0; i < list.length; i++) {
            if (list [i].equals(word)) {
                return i;
            }
        }
        return -1;
    }
    
    public void countWords (FileResource resource, String [] common, int [] counts) {
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int index = indexOf(common, word);
            if (index != -1) {
                counts[index] += 1;
            }
        }
    }
    
    void countShakespeare () {
        String [] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt",
                           "romeo.txt"};
        String [] common = getCommon();
        int [] counts = new int [common.length];
        for (int i = 0; i < plays.length; i++) {
            FileResource resource = new FileResource ("data/" + plays[i]);
            countWords (resource, common, counts);
            System.out.println("Done with " + plays[i]);
        }
        for (int i = 0; i < common.length; i++) {
            System.out.println(common[i] + "\t" + counts[i]);
        }
    }
}
