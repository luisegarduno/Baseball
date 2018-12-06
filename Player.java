/* Luis Garduno
   ID #: 47780191
   Lab 8 - Fall 2018
*/
import java.util.Random;

public class Player {
    private String name;
    private String output;
    private int strikes;
    private int balls;
    private int hits;
    private int atBats;
    private Base location;

    public Player(String namE, Base basE) { //This constructs a player. Every player will have a name and a assigned base
        name = namE;
        location = basE;
        hits = 0;
        atBats = 0;
        output = "";
    }

    public String getOutput(){
        return output;
    }

    public void resetOutput(){
        output = "";
    }

    public String getName() {             //gets the name
        return name;
    }

    public int getAtBats() {             //gets the atbat value so it can be later used
        return atBats;
    }

    public int getHits() {               //gets the hits values so it can be used later
        return hits;
    }

    public RollResult roll() {           //this method  rolls the dice and stores the value in a array
        Random diceRoll = new Random();
        int dice1 = diceRoll.nextInt(6) + 1; //dice is increased to 10 to increase the chances of it being a ball or strike
        int dice2 = diceRoll.nextInt(6) + 1;

        int[] rolls = new int[2];
        rolls[0] = dice1;
        rolls[1] = dice2;

        String str = "   Rolled  " + rolls[0] + "  " + rolls[1];
        return new RollResult(rolls, str);
    }

    public void setName(String newName) {                        //sets the name of player
        name = newName;
    }

    public Base getLocation() {                                  //gets location of player
        return location;
    }

    public void setLocation(Base newLocation) {                  //sets the location of the player
        location = newLocation;
    }

    public boolean isNotInDugout() {
        if (location.isDugout() == false) {                      //if the players location is at the dugout, return true;
            return true;
        } else {
            return false;                                       //if the player's location is not at dugout, return false
        }
    }

    public int takeTurn(){
        strikes = 0;                                           //initializes the value of strikes and balls to start at 0;
        balls = 0;
        atBats += 1;
        while (true) {                                         //continue looping until false
            int result = bat();
            //the bat method will only return values between 0-4, and I declared it so it wouldn't get new bat values
            if (result < 1) { //this mean that the player either got ball or a strike
                if (balls == 4) { //until the counter of balls or strikes reach their max value, that's when the loop will end
                    output += "  Walk\n";//System.out.println("Walk");
                    return 1;            //4 balls means the player moves 1 base
                }
                if (strikes == 3) {
                    output += "  Strike out!!\n";//System.out.println("Strike out!!");
                    return 0;                    //if the player gets 3 strikes, he or she does not move bases
                }
            } else {
                hits += 1;
                return result;                  //returns the number of bases to move from the bat method
            }
        }
    }

    public int getStrikes(){
        return strikes;
    }

    public int getBalls(){
        return balls;
    }

    public int bat() {
        RollResult dice = roll(); //stores the roll array into a new temp array called swing
        int even = (dice.getVals()[0] + dice.getVals()[1]) % 2; //checks to see if it's even
        int move = 0; //tells the value of the base
        String represent = "";

        if (dice.getVals()[0] == dice.getVals()[1] && dice.getVals()[0] <= 4) { //this basically means that the player hit
            if (dice.getVals()[0] == 1) {
                move = 1;  //gives a num value , we'll use this to compare later
                output += dice.getOutput() + "  Single!\n";//represent = "Single!"; //gives a String value
            }
            if (dice.getVals()[0] == 2) {
                move = 2;
                output += dice.getOutput() + "  Double!\n";//represent = "Double!";
            }
            if (dice.getVals()[0] == 3) {
                move = 3;
                output += dice.getOutput() + "  Triple!\n";//represent = "Triple!";
            }
            if (dice.getVals()[0] == 4) {
                move = 4;
                output += dice.getOutput() + "  Home Run!\n";//represent = "Home Run!";
            }
        } else {
            if (even == 0) { //if player rolls a strike
                move = 0;
                strikes++; // adds a strike to strike counter
                output += dice.getOutput() + "  STRIKE\n";//represent = "Strike!";
            }
            if (even != 0) { //if player rolls a ball
                move = 0;
                balls++; //adds a ball to balls counter
                output += dice.getOutput() + "  BALL!\n";//represent = "Ball!";
            }
        }
        return move; //returns the value of the dice, which will be used to tell the player how many bases to move.
    }

    public double getBattingAverage() {
        return ((double) hits) / ((double) atBats); //casts the int to turn into double and returns the avg
    }

    public String toString() {
        return name;
    }

//Casting -     ((double)atBats)
}
