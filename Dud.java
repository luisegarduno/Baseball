/*
 * Dud.java
 * Baseball
 *
 * Created by Luis G.
 * Updated on 01/27/2023
 *
 * */

import java.util.Random;

// Extends Players means that you can adjust it
public class Dud extends Player {
  public Dud(String dudString, Base dudBase){
    super(dudString, dudBase);
  }

  public RollResult roll(){
    Random diceRoll = new Random();

    // Dice is increased to 10 to increase the chances of it being a ball or strike
    int dice1 = diceRoll.nextInt(10) + 1;
    int dice2 = diceRoll.nextInt(10) + 1;

    int[] rolls = new int[2];
    rolls[0] = dice1;
    rolls[1] = dice2;

    String str = "  Rolled  " + rolls[0] + "  " + rolls[1];

    // Returns the array
    return new RollResult(rolls, str);
  }
}
