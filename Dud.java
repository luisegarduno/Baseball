import java.util.Random;

public class Dud extends Player{                     //extends Players means that you can adjust it
    public Dud(String dudString, Base dudBase){
        super(dudString, dudBase);
    }

    public RollResult roll(){
        Random diceRoll = new Random();
        int dice1 = diceRoll.nextInt(10) + 1; //dice is increased to 10 to increase the chances of it being a ball or strike
        int dice2 = diceRoll.nextInt(10) + 1;

        int[] rolls = new int[2];
        rolls[0] = dice1;
        rolls[1] = dice2;

        String str = "  Rolled  " + rolls[0] + "  " + rolls[1];

        return new RollResult(rolls, str);           //returns the array
    }
}