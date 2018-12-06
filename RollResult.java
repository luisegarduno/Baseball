/* Luis Garduno
   ID #: 47780191
   Lab 8 - Fall 2018
*/
public class RollResult {
    private int[] vals;
    private String output;

    public RollResult(int[] nullVals, String nullOutput){
        vals = nullVals;                        //values of dice are stored into an value array
        output = nullOutput;                    //the string is the overall batting result
    }

    public void setOutput(String newOutput){    //setter
        output = newOutput;
    }

    public String getOutput(){                  //getter
        return output;
    }

    public void setVals(int[] newVals){         //setter
        vals = newVals;
    }

    public int[] getVals(){                     //getter
        return vals;
    }
}
