/* 
 * Ringer.java
 * Baseball
 *
 * Created by Luis G.
 * Updated on 01/27/2023
 *
 * */

import java.util.Random;

public class Ringer extends Player {
    public Ringer(String ringerString, Base ringerBase){

        // Super class
        super(ringerString, ringerBase);
    }

    public RollResult roll(){
        Random diceRoll = new Random();

        // Decreases the face values to be 3 to increase the chance of getting hit
        int dice1 = diceRoll.nextInt(3) + 1;
        int dice2 = diceRoll.nextInt(3) + 1;

        int[] rolls = new int[2];
        rolls[0] = dice1;
        rolls[1] = dice2;

        String str = "  Rolled  " + rolls[0] + "  " + rolls[1];

        // Returns the array
        return new RollResult(rolls, str);
    }
}
