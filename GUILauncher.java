/* 
 * GUILauncher.java
 * Baseball
 *
 * Created by Luis G.
 * Updated on 01/27/2023
 *
 * */

import javax.swing.JFrame;

public class GUILauncher {
    public static void main(String[] args){
        BatterUpGUI theGame = new BatterUpGUI();
        theGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theGame.pack();
        theGame.setLocationRelativeTo(null);
        
        // Set the area for the text box
        theGame.setSize(650,450);

        // Makes the jframe visible
        theGame.setVisible(true);
    }
}
