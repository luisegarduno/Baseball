/* 
 * BatterUpGUI.java
 * Baseball
 * 
 * Created by Luis G.
 * Updated on 01/27/2023
 * 
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BatterUpGUI extends JFrame {
  private BatterUp playBall = new BatterUp();
  JTextArea output;

  // Scroll bar
  JScrollPane scrollPane;

  // Play and reset button
  JButton playButton, resetButton;

  // Labels for innings and team
  JLabel numOfInnings, teamName;

  // Text box for the jlabels
  JTextField inputInnings, inputTeam;

  // Layout
  FlowLayout flow;

  public BatterUpGUI(){
    // Or super("tile");to include a title in the JFrame's Top border
    super("BatterUp!");

    // Creates new flow-layout
    flow = new FlowLayout();

    // Sets layout
    setLayout(flow);

    // Create Jlabels
    numOfInnings = new JLabel("Number of innings");

    // Defines the length of the Number of Innings text-box
    inputInnings = new JTextField(20);

    // Adds it to the JFrame
    add(numOfInnings);
    add(inputInnings);

    teamName = new JLabel("Team Name");
    inputTeam = new JTextField(20);
    add(teamName);
    add(inputTeam);

    // Creates play button
    playButton = new JButton("Play");   

    // Creates reset button
    resetButton = new JButton("Reset"); 

    // Add the play button the frame
    add(playButton);

    // Add the reset button to the frame
    add(resetButton);

    // Creates an instance of pressButton
    pressButton button = new pressButton();
    playButton.addActionListener(button);
    resetButton.addActionListener(button);

    // The reset button is initially turned off
    resetButton.setEnabled(false);


    output = new JTextArea(20, 50);
    scrollPane = new JScrollPane(output);
    add(scrollPane);

    // Disable editing
    output.setEditable(false);

    // Erase contents and replace with test
    output.setText("");

    // Append contents with additional text
    output.append("");

  }

  // Invoke whenever either the play or reset button is pressed
  private class pressButton implements ActionListener { 

    // This just makes sure that the innings text-box and team name text-box actually contains a string/integer
    public boolean checker() {  
      String innings = inputInnings.getText();
      String team = inputTeam.getText();

      // If the string length is longer than or equal to 1 return true
      if (innings.length() >= 1 && team.length() >= 1) { 
        return true;
      } else {
        return false;
      }
    }

    public void actionPerformed(ActionEvent e) {

      // Checks to see if team name text box is filled in
      if(inputTeam.getText().length() >= 1){ 
        try{
          int innings = Integer.parseInt(inputInnings.getText());
          String teamString = "Team " + inputTeam.getText() + " is playing!";
          if (e.getSource() == playButton && checker() == true) {
            if (innings > 0) {

              // If text-boxes are filled in the reset button is enabled
              resetButton.setEnabled(true);
              output.append(teamString + "\n");
              output.append(playBall.play(innings));
            }

            resetButton.setEnabled(true);

            // When the reset button is enabled, the play button is not
            playButton.setEnabled(false);   

            // When the play button is pressed, you cannot edit the innings or
            inputInnings.setEditable(false);

            // The team name until the user presses reset
            inputTeam.setEditable(false);   
          }

          // This is self-explanatory
          if (e.getSource() == resetButton ) { 
            inputInnings.setText("");
            inputTeam.setText("");
            output.setText("");
            resetButton.setEnabled(false);
            playButton.setEnabled(true);
            inputInnings.setEditable(true);
            inputTeam.setEditable(true);
          }
          // If a team is typed in but the Innings box is left empty it will open this message
        } catch(Exception error){ 
          if(inputInnings.getText().length() < 1){
            JOptionPane.showMessageDialog(

                // Specifies where on the screen to place the dialog
                null, 

                // Message to display
                "Please enter the number of innings", 
                // Title bar message
                "Missing Data", 
                // 0 refers to the JOptionPane.ERROR_MESSAGE icon
                0 
                );
          }
        }
      }
      // Else, the text-box for team name is not filled in
      else{ 
        // If innings text box is empty this message will pop-up
        if(inputInnings.getText().length() < 1){ 
          JOptionPane.showMessageDialog(
              null,
              "Please enter the number of innings",
              "Missing Data",
              0
              );
        }

        // If team name is missing this message will appear
        if(inputTeam.getText().length() < 1){ 
          JOptionPane.showMessageDialog(
            null,
            "Please enter a team name",
            "Missing Data",
            0
          );
        }
      }
    }
  }
}
