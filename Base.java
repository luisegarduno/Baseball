/* Luis Garduno
   ID #: 47780191
   Lab 8 - Fall 2018
*/
public class Base{
    private String name;                            //private null attribute name

    public Base(String namE){                       //Base constructor with String paramater used to declare the attribute value
        name = namE;
    }

    public String getName(){                        //gets name
        return name;
    }

    public void setName(String newName){            //initializes name with String parameter newName
        name = newName;
    }

    public boolean isDugout(){
        if(name.equals("Dugout")){                  //if the player is in the dugout it returns true
            return true;
        }
        else{
            return false;                           //if the player is not in the dugout it returns false
        }
    }

    public boolean isHome(){
        if(name.equals("Home")){                     //if the player at home plate return true
            return true;
        }
        else{
            return false;                             //otherwise return false
        }

    }

    public String toString(){
        return name;
    }

}
