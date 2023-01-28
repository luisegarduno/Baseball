/* 
 * BatterUp.java
 * Baseball
 * 
 * Created by Luis G.
 * Updated on 01/27/2023
 * 
 * */

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;

public class BatterUp {
  private int outs;
  private int score;
  private int nextPlayerIndex;
  private Field theField;
  private ArrayList<Player> players = new ArrayList<Player>();

  public BatterUp() {
    outs = 0;
    score = 0;
    nextPlayerIndex = 0;
    theField = new Field();
    createPlayers();
  }

  public void createPlayers() {

    try {
      // Declares players.txt as a file
      File text = new File("players.txt");

      // Scans the file
      Scanner scan = new Scanner(text);

      while(scan.hasNext()){
        // splits the document once "," is found
        scan.useDelimiter(",|\r\n");

        // First thing found is the name
        String name = scan.next();
        // After the comma, the type is found
        String type = scan.next();

        // Creates player and initializes it to dug-out
        Base dugout = theField.getDugout();
        if(type.equalsIgnoreCase(("Dud"))){
          players.add(new Dud(name, dugout));
        }
        if(type.equalsIgnoreCase(("Ringer"))){
          players.add(new Ringer(name, dugout));
        }
        if(type.equalsIgnoreCase(("Average"))){
          players.add(new Player(name, dugout));
        }

      }
      // Closes the file
      scan.close();  

    } catch (Exception e) {
      System.out.println("Problem reading input file");
    }

    // Get Dug-out will enter the Field array-list and return the
    // "dugout" string and Base dug-out will be equal to that string
    Base dugout = theField.getDugout();
    /*        players.add(new Player("Alex"    , dugout)); // Player 1
     *        players.add(new Player("Betty"   , dugout)); // remember, players need to have a name and a location
     *        players.add(new Player("Carlos"  , dugout)); // 3
     *        players.add(new Player("Diana"   , dugout)); // 4
     *        players.add(new Player("Eugene"  , dugout)); // 5
     *        players.add(new Player("Freddy"  , dugout)); // 6
     *        players.add(new Player("Garrett" , dugout)); // 7
     *        players.add(new Player("Henry"   , dugout)); // 8
     *        players.add(new Player("Isabel"  , dugout)); // 9
     */
  }

  // Gets next player in array-list
  public Player getNextPlayer(){
    // This stores the value of numberPlayerIndex once it enters
    int next = nextPlayerIndex;
    // This changes the value of index, so changes the player
    nextPlayerIndex+=1;

    // If the new index is less than or equal to the total size of the team
    if(nextPlayerIndex >= players.size()){
      // Set the value of index back to 0;
      nextPlayerIndex = 0;
    }
    return players.get(next);
  }

  public String play(int numberOfInnings) throws Exception {
    String str = "";
    int inning = 1;
    while(inning <= numberOfInnings) {

      // System.out.printf("\nInning %d\n", inning);
      str += "Inning " + inning + "\n"; 
      while (outs < 3) {
        // System.out.println("\nSCORE: " + score);
        str += "\nScore: " + score +"\n";
        str += displayField();
        Player batter = getNextPlayer();

        // System.out.println(tempo.getName() + " is batting");
        str += batter.getName() + " is batting\n";
        batter.setLocation(theField.getBatterBox());

        // This solidifies the players turn to be stored into a integer value
        int playerTurn = batter.takeTurn();
        str += batter.getOutput();

        // If player gets 3 strikes it adds an out
        if (playerTurn == 0) {
          outs++;
          batter.setLocation(theField.getDugout());
        }else {
          // Otherwise move the player x amount of bases forward.
          str += movePlayers(playerTurn);
        }

        // If there's 3 outs it resets the one players output to ""
        if(outs >= 3){
          batter.resetOutput();
        }
        if(batter.getStrikes() == 3){
          batter.resetOutput();
        }

        // I put this at the end of the while statement so if the player receives
        if(batter.getBalls() == 4){
          // 4 balls it will still move and then later reset the output
          batter.resetOutput(); 
        }
      }
      outs = 0;
      getNextPlayer().resetOutput();
      for (int i = 0;i < players.size();i++) {
        Player resetPlayer = players.get(i);
        resetPlayer.setLocation(theField.getDugout());
      }
      // System.out.println("\nTHREE OUTS!");
      str += "\nTHREE OUTS!\n";

      // System.out.println("INNING " + inning + " OVER WITH A SCORE OF " + score);
      str += "INNING " + inning + " OVER WITH A SCORE OF " + score + "\n\n";
      inning++;
      nextPlayerIndex = 0;
    }
    printStats();
    score = 0;
    return str;
  }

  public String movePlayers(int forward){
    String str = "";
    // Goes through each player
    for(int i = 0;i < players.size();i++){
      // Gets the name of the player and location and stores it as a player
      Player playerZ = players.get(i);

      // If the player is not in the dug-out return true
      if(playerZ.isNotInDugout() == true){
        // Move ahead is called by theField object

        // Remember moveAhead requires a starting point as a parameter as well
        // as an integer value to tell it to move x amount of bases forward
        Base newLocation = theField.moveAhead(playerZ.getLocation(), forward);

        // Sets the players location to the result of Base newLocation
        playerZ.setLocation(newLocation);

        // If the string value equals home then isHome is true
        playerZ.resetOutput();
        if(newLocation.isHome() == true){
          // Adds to the score
          score+=1;
          // System.out.println(playerZ + " SCORED!!");
          str += playerZ + "  SCORED!\n"; 

          // After the player scores he or she is sent back to the dug-out
          playerZ.setLocation(theField.getDugout());
        }
      }
    }
    return str;
  }

  // Displays contents of each base
  public String displayField(){           
    // There are 3 bases so the array-list is from 0 to 2
    String[] theBases = {"empty", "empty", "empty"};
    String str = "";

    for(int i = 0;i < players.size();i++){
      // Remember to call a player you need a name and a location
      // so here you set thePlayer equal to the String name it's currently on
      Player thePlayer = players.get(i);

      // This assigns the player a String depending on the location on field
      Base whichBase = thePlayer.getLocation();

      if(whichBase.getName().equals("First")){
        theBases[0] = thePlayer.getName();
      }
      if(whichBase.getName().equals("Second")){
        theBases[1] = thePlayer.getName();
      }
      if(whichBase.getName().equals("Third")){
        theBases[2] = thePlayer.getName();
      }
    }

    // System.out.printf("\n[ 1 ] %s [ 2 ] %s [ 3 ] %s\n\n ",theBases[0], theBases[1], theBases[2]);//print bases with players
    str += "\n[ 1 ] " + theBases[0] + " [ 2 ] " + theBases[1] + " [ 3 ] " + theBases[2] + "\n\n";
    return str;
  }

  public void printStats() throws Exception{
    // Creates a file
    PrintWriter pw = new PrintWriter("Stats.txt");

    // Writes in the empty file
    pw.println("GAME STATS:");
    pw.println("**************************************");
    pw.println("PLAYER          HITS  AT-BATS  AVERAGE");

    // Run's through the players
    for(int i = 0;i < players.size();i++) {
      // Creates a temporary player
      Player tempo = players.get(i);

      // Gets the average
      double avg = tempo.getBattingAverage();

      // Gets the hits
      int hitz = tempo.getHits();
      int atbats = tempo.getAtBats();

      // Prints the values
      pw.printf("%s\t%d\t%d\t%.3f", tempo, hitz, atbats, avg);
      pw.println();
    }
    pw.println("**************************************");
    pw.close();
  }

}
