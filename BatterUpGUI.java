/* Luis Garduno
   ID #: 47780191
   Lab 8 - Fall 2018
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BatterUpGUI extends JFrame {
    private BatterUp playBall = new BatterUp();
    JTextArea output;
    JScrollPane scrollPane;                //scroll bar
    JButton playButton, resetButton;       //play and reset button
    JLabel numOfInnings, teamName;         // labels for innings and team
    JTextField inputInnings, inputTeam;    // text box for the jlabels
    FlowLayout flow;                       //layout

    public BatterUpGUI(){
        super("BatterUp!"); //or super("tile"); to include a title in the JFrame's Top border
        flow = new FlowLayout(); //creates new flowlayout
        setLayout(flow);         //sets layout

        numOfInnings = new JLabel("Number of innings"); //create Jlabels
        inputInnings = new JTextField(20); //defines the length of the Number of Innings textbox
        add(numOfInnings);                         //adds it to the JFrame
        add(inputInnings);

        teamName = new JLabel("Team Name");
        inputTeam = new JTextField(20);
        add(teamName);
        add(inputTeam);

        playButton = new JButton("Play");    // creates play button
        resetButton = new JButton("Reset");  // creates reset button
        add(playButton);                          //add the play button the frame
        add(resetButton);                         //add the reset button to the frame
            pressButton button = new pressButton(); //creates an instance of pressButton
            playButton.addActionListener(button);
            resetButton.addActionListener(button);
            resetButton.setEnabled(false);          //the reset button is initially turned off


        output = new JTextArea(20, 50);
        scrollPane = new JScrollPane(output);
        add(scrollPane);

        output.setEditable(false); //Disable editing

        output.setText("");       // Erase contents and replace with test
        output.append("");        //Append contents with additional text

    }

    private class pressButton implements ActionListener { //invoke whenever either the play or reset button is pressed
        public boolean checker() {  // this just makes sure that the innings textbox and team name textbox actually constains a string/int
            String innings = inputInnings.getText();
            String team = inputTeam.getText();
            if (innings.length() >= 1 && team.length() >= 1) { //if the string length is longer than or equal to 1 return true
                return true;
            } else {
                return false;
            }
        }

        public void actionPerformed(ActionEvent e) {
            if(inputTeam.getText().length() >= 1){ //checks to see if team name text box is filled in
                try{
                    int innings = Integer.parseInt(inputInnings.getText());
                    String teamString = "Team " + inputTeam.getText() + " is playing!";
                    if (e.getSource() == playButton && checker() == true) {
                        if (innings > 0) {
                            resetButton.setEnabled(true); //if textboxes are filled in the reset button is enabled
                            output.append(teamString + "\n");
                            output.append(playBall.play(innings));
                        }

                        resetButton.setEnabled(true);
                        playButton.setEnabled(false);    //when the reset button is enabled, the play button is not
                        inputInnings.setEditable(false); //when the play button is pressed, you cannot edit the innings or
                        inputTeam.setEditable(false);    // the team name until the user presses reset
                    }
                    if (e.getSource() == resetButton ) { //this is self-explanatory
                        inputInnings.setText("");
                        inputTeam.setText("");
                        output.setText("");
                        resetButton.setEnabled(false);
                        playButton.setEnabled(true);
                        inputInnings.setEditable(true);
                        inputTeam.setEditable(true);
                    }
                } catch(Exception error){ // if a team is typed in but the Innings box is left empty it will open this message
                    if(inputInnings.getText().length() < 1){
                        JOptionPane.showMessageDialog(
                                null, //specifies where on the screen to place the dialog
                                "Please enter the number of innings", //message to display
                                "Missing Data", //title bar message
                                0 //0 refers to the JOptionPane.ERROR_MESSAGE icon
                        );
                    }
                }
            }
                else{ //if the textbox for team name is not filled in
                    if(inputInnings.getText().length() < 1){ //if innings text box is empty this message will popup
                        JOptionPane.showMessageDialog(
                        null,
                        "Please enter the number of innings",
                        "Missing Data",
                        0
                        );
                    }
                    if(inputTeam.getText().length() < 1){ //if team name is missing this message will appear
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
