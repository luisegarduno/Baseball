/* 
 * Player.java
 * Baseball
 *
 * Created by Luis G.
 * Updated on 01/27/2023
 *
 * */

import java.util.Random;

public class Player {
    private String name;
    private String output;
    private int strikes;
    private int balls;
    private int hits;
    private int atBats;
    private Base location;

    // This constructs a player. Every player will have a name and a assigned base
    public Player(String namE, Base basE) {
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

    // Gets the name
    public String getName() {
        return name;
    }

    // Gets the atbat value so it can be later used
    public int getAtBats() {
        return atBats;
    }

    // Gets the hits values so it can be used later
    public int getHits() {
        return hits;
    }

    // This method  rolls the dice and stores the value in a array
    public RollResult roll() {
        Random diceRoll = new Random();

        // Dice is increased to 10 to increase the chances of it being a ball or strike
        int dice1 = diceRoll.nextInt(6) + 1;
        int dice2 = diceRoll.nextInt(6) + 1;

        int[] rolls = new int[2];
        rolls[0] = dice1;
        rolls[1] = dice2;

        String str = "   Rolled  " + rolls[0] + "  " + rolls[1];
        return new RollResult(rolls, str);
    }

    // Sets the name of player
    public void setName(String newName) {
        name = newName;
    }

    // Gets location of player
    public Base getLocation() {
        return location;
    }

    // Sets the location of the player
    public void setLocation(Base newLocation) {
        location = newLocation;
    }

    // If the players location is at the dug-out, return true;
    public boolean isNotInDugout() {
        if (location.isDugout() == false) {
            return true;
        } else {

            // If the player's location is not at dug-out, return false
            return false;
        }
    }

    public int takeTurn(){

        // Initializes the value of strikes and balls to start at 0;
        strikes = 0;
        balls = 0;
        atBats += 1;

        // Continue looping until false
        while (true) {
            int result = bat();
            // The bat method will only return values between 0-4, and I declared it so it wouldn't get new bat values

            // This mean that the player either got ball or a strike
            if (result < 1) {

                // Until the counter of balls or strikes reach their max value, that's when the loop will end
                if (balls == 4) {

                    // System.out.println("Walk");
                    output += "  Walk\n";

                    // 4 balls means the player moves 1 base
                    return 1;
                }
                if (strikes == 3) {

                    // System.out.println("Strike out!!");
                    output += "  Strike out!!\n";

                    // If the player gets 3 strikes, he or she does not move bases
                    return 0;
                }
            } else {
                hits += 1;

                // Returns the number of bases to move from the bat method
                return result;
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

        // Stores the roll array into a new temporary array called swing
        RollResult dice = roll();

        // Checks to see if it's even
        int even = (dice.getVals()[0] + dice.getVals()[1]) % 2;

        // Tells the value of the base
        int move = 0;
        String represent = "";


        // This basically means that the player hit
        if (dice.getVals()[0] == dice.getVals()[1] && dice.getVals()[0] <= 4) {
            if (dice.getVals()[0] == 1) {

                // Gives a number value , we'll use this to compare later
                move = 1;

                // Represent = "Single!";// Gives a String value
                output += dice.getOutput() + "  Single!\n";
            }
            if (dice.getVals()[0] == 2) {
                move = 2;

                // Represent = "Double!";
                output += dice.getOutput() + "  Double!\n";
            }
            if (dice.getVals()[0] == 3) {
                move = 3;

                // Represent = "Triple!";
                output += dice.getOutput() + "  Triple!\n";
            }
            if (dice.getVals()[0] == 4) {
                move = 4;

                // Represent = "Home Run!";
                output += dice.getOutput() + "  Home Run!\n";
            }
        } else {

            // If player rolls a strike
            if (even == 0) {
                move = 0;

                // Adds a strike to strike counter
                strikes++;

                // Represent = "Strike!";
                output += dice.getOutput() + "  STRIKE\n";
            }

            // If player rolls a ball
            if (even != 0) {
                move = 0;

                // Adds a ball to balls counter
                balls++;

                // Represent = "Ball!";
                output += dice.getOutput() + "  BALL!\n";
            }
        }

        // Returns the value of the dice, which will be used to tell the player how many bases to move.
        return move;
    }

    public double getBattingAverage() {

    // Casts the integer to turn into double and returns the average
        return ((double) hits) / ((double) atBats);
    }

    public String toString() {
        return name;
    }

// Casting -     ((double)atBats)
}
