/* Luis Garduno
   ID #: 47780191
   Lab 8 - Fall 2018
*/
import java.util.Random;

public class Ringer extends Player {
    public Ringer(String ringerString, Base ringerBase){
        super(ringerString, ringerBase);           //super class
    }

    public RollResult roll(){
        Random diceRoll = new Random();
        int dice1 = diceRoll.nextInt(3) + 1; //decreases the face values to be 3 to increase the chance of getting hit
        int dice2 = diceRoll.nextInt(3) + 1;

        int[] rolls = new int[2];
        rolls[0] = dice1;
        rolls[1] = dice2;

        String str = "  Rolled  " + rolls[0] + "  " + rolls[1];

        return new RollResult(rolls, str);          //returns the array
    }
}
