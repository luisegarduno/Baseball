/* Luis Garduno
   ID #: 47780191
   Lab 8 - Fall 2018
*/
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;

public class BatterUp{
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
            File text = new File("players.txt"); //declares players.txt as a file
            Scanner scan = new Scanner(text);             //scans the file

            while(scan.hasNext()){
                scan.useDelimiter(",|\r\n");             //splits the document once "," is ofund

                String name = scan.next();               // first thing found is the name
                String type = scan.next();               //after the comma, the type is found

                Base dugout = theField.getDugout();      //creates player and initializes it to dugout
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
            scan.close();                                //closes the file

        } catch (Exception e) {
            System.out.println("Problem reading input file");
        }

        Base dugout = theField.getDugout(); //get Dugout will enter the Field arraylist and return the "dugout" string and Base dugout will be equal to that string
//        players.add(new Player("Alex"    , dugout));  //Player 1
//        players.add(new Player("Betty"   , dugout));  // remember, players need to have a name and a location
//        players.add(new Player("Carlos"  , dugout));  //3
//        players.add(new Player("Diana"   , dugout));  //4
//        players.add(new Player("Eugene"  , dugout));  //5
//        players.add(new Player("Freddy"  , dugout));  //6
//        players.add(new Player("Garrett" , dugout));  //7
//        players.add(new Player("Henry"   , dugout));  //8
//        players.add(new Player("Isabel"  , dugout));  //9
    }

    public Player getNextPlayer(){             //gets next player in arraylist
        int next = nextPlayerIndex;            //this stores the value of numberPlayerIndex once it enters
        nextPlayerIndex+=1;                    //this changes the value of index, so changes the player
        if(nextPlayerIndex >= players.size()){ //if the new index is less than or equal to the total size of the team
            nextPlayerIndex = 0;               //set the value of index back to 0;
        }
        return players.get(next);
    }

    public String play(int numberOfInnings) throws Exception {
        String str = "";
        int inning = 1;
        while(inning <= numberOfInnings) {

            str += "Inning " + inning + "\n";             //System.out.printf("\nInning %d\n", inning);
            while (outs < 3) {
                str += "\nScore: " + score +"\n";         //System.out.println("\nSCORE: " + score);
                str += displayField();
                Player batter = getNextPlayer();
                str += batter.getName() + " is batting\n";//System.out.println(tempo.getName() + " is batting");
                batter.setLocation(theField.getBatterBox());
                int playerTurn = batter.takeTurn();       //this solidifies the players turn to be stored into a int value
                str += batter.getOutput();
                if (playerTurn == 0) {                    //if player gets 3 strikes it adds an out
                    outs++;
                    batter.setLocation(theField.getDugout());
                }else {
                    str += movePlayers(playerTurn);       //otherwise move the player x amount of bases forward.
                }
                if(outs >= 3){            //if there's 3 outs it resets the one players output to ""
                    batter.resetOutput();
                }
                if(batter.getStrikes() == 3){
                    batter.resetOutput();
                }
                if(batter.getBalls() == 4){ //I put this at the end of the while statement so if the player recieves
                    batter.resetOutput();  //4 balls it will still move and then later reset the output
                }
            }
            outs = 0;
            getNextPlayer().resetOutput();
            for (int i = 0; i < players.size(); i++) {
                Player resetPlayer = players.get(i);
                resetPlayer.setLocation(theField.getDugout());
            }
            str += "\nTHREE OUTS!\n";                                             //System.out.println("\nTHREE OUTS!");
            str += "INNING " + inning + " OVER WITH A SCORE OF " + score + "\n\n";//System.out.println("INNING " + inning + " OVER WITH A SCORE OF " + score);
            inning++;
            nextPlayerIndex = 0;
        }
        printStats();
        score = 0;
        return str;
    }

    public String movePlayers(int forward){
        String str = "";
        for(int i = 0; i < players.size(); i++){             //goes through each player
            Player playerZ = players.get(i);                 //gets the name of the player and location and stores it as a player
            if(playerZ.isNotInDugout() == true){             //if the player is not in the dugout return true
                Base newLocation = theField.moveAhead(playerZ.getLocation(), forward); //move ahead is called by theField object
                //remember moveAhead requires a starting point as a parameter as well as an int value to tell it to move x amount of bases forward
                playerZ.setLocation(newLocation);            //sets the players location to the result of Base newLocation
                playerZ.resetOutput();
                if(newLocation.isHome() == true){             //if the string value equals home then isHome is true
                    score+=1;//adds to the score
                    str += playerZ + "  SCORED!\n";           //System.out.println(playerZ + " SCORED!!");
                    playerZ.setLocation(theField.getDugout());//after the player scores he or she is sent back to the dugout
                }
            }
        }
    return str;
    }

    public String displayField(){                        //displays contents of each base
        String[] theBases = {"empty", "empty", "empty"}; //there are 3 bases so the arraylist is from 0 to 2
        String str = "";

        for(int i = 0; i < players.size();i++){
            Player thePlayer = players.get(i);           //remember to call a player you need a name and a location
            //so here you set thePlayer equal to the String name it's currently on
            Base whichBase = thePlayer.getLocation();    //this assigns the player a String depending on the location on field

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
        str += "\n[ 1 ] " + theBases[0] + " [ 2 ] " + theBases[1] + " [ 3 ] " + theBases[2] + "\n\n";//System.out.printf("\n[ 1 ] %s [ 2 ] %s [ 3 ] %s\n\n ",theBases[0], theBases[1], theBases[2]);//print bases with players
        return str;
    }

    public void printStats() throws Exception{
        PrintWriter pw = new PrintWriter("Stats.txt"); //creates a file
        pw.println("GAME STATS:");                             //writes in the empty file
        pw.println("**************************************");
        pw.println("PLAYER          HITS  AT-BATS  AVERAGE");

        for(int i = 0; i < players.size(); i++) {              //run's through the players
            Player tempo = players.get(i);                     //creates a temp player
            double avg = tempo.getBattingAverage();            //gets the average
            int hitz = tempo.getHits();                        //gets the hits
            int atbats = tempo.getAtBats();

            pw.printf("%s\t%d\t%d\t%.3f", tempo, hitz, atbats, avg); //prints the values
            pw.println();
        }
        pw.println("**************************************");
        pw.close();
    }

}