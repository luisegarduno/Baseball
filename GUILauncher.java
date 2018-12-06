/* Luis Garduno
   ID #: 47780191
   Lab 8 - Fall 2018
*/
import javax.swing.JFrame;

public class GUILauncher {
    public static void main(String[] args){
        BatterUpGUI theGame = new BatterUpGUI();
        theGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theGame.pack();
        theGame.setLocationRelativeTo(null);
        theGame.setSize(650,450); //set the area for the text box
        theGame.setVisible(true); //makes the jframe visible
    }
}
