import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NarratorFightFrame {

    // THIS IS AN UNFINISHED BOSS FIGHT! IT WILL NOT APPEAR IN THE GAME SO PLEASE DISREGARD IT!!!

    private JTextArea fightScreen;
    private JFrame fightFrame;
    private JButton attack;
    private JButton items;
    private JButton talk;
    private JLabel healthBar;
    private JTextField dialogueBar;
    private JPanel backgroundPanel;

    private Color narratorFightColor = new Color(205, 78, 78);

    public NarratorFightFrame() {
        backgroundPanel = new JPanel();
        fightFrame = new JFrame("Now You've Done It!");
        fightFrame.setSize(505, 450);
        fightFrame.setLayout(null);
        backgroundPanel.setLayout(null);
        fightFrame.setContentPane(backgroundPanel);
        backgroundPanel.setBackground(Color.BLACK);

        fightScreen = new JTextArea();
        fightScreen.setBackground(Color.BLACK);
        fightScreen.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, narratorFightColor));
        fightScreen.setForeground(narratorFightColor);
        fightScreen.setSize(460, 290);
        fightScreen.setLocation(15, 15);
        fightScreen.setEditable(false);
        fightScreen.setLineWrap(false);
        // Fight Screen can support 980 characters before going over!
        // Fight Screen will support 20 lines in height, ~80 lines in width.
        fightScreen.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 9));
        // I WORKED SO HARD ON THIS STUPID THING!!! 
        fightScreen.setText("     +==============================================================================+"
                + "\n     |                                                                              |"
                + "\n     |                                                                              |"
                + "\n     |        .-==========-.                                  .-==========-.        |"
                + "\n     |          __________                                      __________          |"
                + "\n     |        //          \\\\                                  //          \\\\        |"
                + "\n     |        |_____(o)____|                                  |_____(o)____|        |"
                + "\n     |                                                                              |"
                + "\n     |                                                                              |"
                + "\n     |                          ________________________                            |"
                + "\n     |                     ___// |   |   |   |   |   |  \\\\___                       |"
                + "\n     |                  __//-----'---'---'---'---'---'------\\\\__                    |"
                + "\n     |                 //-.----.----.-----.----.-----.----.----.\\\\                  |"
                + "\n     |                 |__|____|____|_____|____|_____|____|____|_|                  |"
                + "\n     |                                                                              |"
                + "\n     |                                                                              |"
                + "\n     |                                                                              |"
                + "\n     |                                                                              |"
                + "\n     |                                                                              |"
                + "\n     +==============================================================================+");

        attack = new JButton("Attack");
        attack.setSize(125, 25);
        attack.setLocation(15, 360);

        items = new JButton("Items");
        items.setSize(125, 25);
        items.setLocation(155, 360);

        talk = new JButton("Talk");
        talk.setSize(125, 25);
        talk.setLocation(295, 360);

        dialogueBar = new JTextField();
        dialogueBar.setEditable(false);

        backgroundPanel.add(attack);
        backgroundPanel.add(items);
        backgroundPanel.add(talk);
        backgroundPanel.add(fightScreen);
        fightFrame.setVisible(true);
    }
}
