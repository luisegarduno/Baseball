/* Luis Garduno
   ID #: 47780191
   Lab 8 - Fall 2018
*/
import java.util.ArrayList;

public class Field{

    ArrayList<Base> bases;                             //creates bases object arraylist

    public Field(){
        bases = new ArrayList<Base>();                 //instance variable
        bases.add(new Base("Dugout"));           //add 6  locations
        bases.add(new Base("BatterBox"));
        bases.add(new Base("First"));
        bases.add(new Base("Second"));
        bases.add(new Base("Third"));
        bases.add(new Base("Home"));
    }

    public Base getDugout(){
        return bases.get(0);                          //if Base getDugout is called, it will return dugout
    }

    public Base getBatterBox(){                        //this gets the String BatterBox
        return bases.get(1);
    }

    public Base moveAhead(Base startingBase, int numberOfBases){
        int startingBaseIndexNumber = bases.indexOf(startingBase);
        int newBaseIndexNumber = Math.min(startingBaseIndexNumber + numberOfBases ,5); //move ahead the number of bases, but don't exceed index location 5
        Base newBase = bases.get(newBaseIndexNumber);
        return newBase;
    }
}