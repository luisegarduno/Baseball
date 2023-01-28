/* 
 * Field.java
 * Baseball
 *
 * Created by Luis G.
 * Updated on 01/27/2023
 *
 * */

import java.util.ArrayList;

public class Field{

    // Creates bases object array-list
    ArrayList<Base> bases;

    public Field(){
        // Instance variable
        bases = new ArrayList<Base>();

        // Add 6 locations
        bases.add(new Base("Dugout"));
        bases.add(new Base("BatterBox"));
        bases.add(new Base("First"));
        bases.add(new Base("Second"));
        bases.add(new Base("Third"));
        bases.add(new Base("Home"));
    }

    public Base getDugout(){
        // If Base getDugout is called, it will return dug-out
        return bases.get(0);
    }

    // This gets the String BatterBox
    public Base getBatterBox(){
        return bases.get(1);
    }

    public Base moveAhead(Base startingBase, int numberOfBases){
        int startingBaseIndexNumber = bases.indexOf(startingBase);

        // Move ahead the number of bases, but don't exceed index location 5
        int newBaseIndexNumber = Math.min(startingBaseIndexNumber + numberOfBases ,5);
        Base newBase = bases.get(newBaseIndexNumber);
        return newBase;
    }
}
