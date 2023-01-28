/* 
 * Base.java
 * Baseball
 * 
 * Created by Luis G.
 * Updated on 01/27/2023
 * 
 * */

public class Base {
  // Private null attribute name
  private String name; 

  // Base constructor with String parameter used to declare the attribute value
  public Base(String namE){
    name = namE;
  }

  // getter (returns name)
  public String getName(){
    return name;
  }

  // Initializes name with String parameter newName
  public void setName(String newName){
    name = newName;
  }

  public boolean isDugout(){
    // If the player is in the dug-out it returns true
    if(name.equals("Dugout")){
      return true;
    }
    // If the player is not in the dug-out it returns false
    else {
      return false;
    }
  }

  public boolean isHome(){
    // If the player at home plate return true
    if(name.equals("Home")){
      return true;
    }
    // Otherwise return false
    else{
      return false;
    }
  }

  public String toString(){
    return name;
  }

}
