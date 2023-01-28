/* 
 * RollResult.java
 * Baseball
 *
 * Created by Luis G.
 * Updated on 01/27/2023
 *
 * */

public class RollResult {
    private int[] vals;
    private String output;

    public RollResult(int[] nullVals, String nullOutput){
        // Values of dice are stored into an value array
        vals = nullVals;
        
        // The string is the overall batting result
        output = nullOutput;
    }

    // Setter
    public void setOutput(String newOutput){
        output = newOutput;
    }

    // Getter
    public String getOutput(){
        return output;
    }

    // Setter
    public void setVals(int[] newVals){
        vals = newVals;
    }

    // Getter
    public int[] getVals(){
        return vals;
    }
}
