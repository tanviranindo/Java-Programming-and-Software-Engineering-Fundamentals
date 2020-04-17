
/**
 * Dice Rolling allows to simulate over a required number of rolls and finds out which 
 * * combination is the most common (between 2 and 12)
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0.0 (September 25, 2019) 
 */

import java.util.Random;

public class DiceRolling {
    public void simulate (int rolls) {
        Random rand = new Random();
        int [] counts = new int [13];
        
        for (int i = 0; i < rolls; i++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            counts [d1 + d2] += 1;
        }
       
        for (int i = 2; i <= 12; i++) {
            // Print out the count for each combination and the % of its occurrence in all simulations
            System.out.println(i + "'s=\t" + counts[i] + "\t" + 100.0 * counts[i] / rolls);
        }
    }
}
