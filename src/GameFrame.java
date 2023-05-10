import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

public class GameFrame extends JFrameVariables {

    private int money = 6;
    private int endingsFound = 0;
    private boolean playerHasDoneSomething = false;
    private String userName;
    // Some strings that I use alot!
    private String noMoneyLeft = "After using up what is considered the last of your change,\nyou fill yourself up on food and start walking\naway from the venders. Whilst walking you notice a large tap\nsectioning off part of the park.\n[Take a Peek?]";
    private String invalidName = "Sorry, the name you submitted isn't valid!\nPlease try again!";
    private String openingLine = "Welcome to 'Ghostly and Ghastly Coasters'!\nWe hope you enjoy the rides at our fine establishment!\nIf you have any concerns let us know!";
    protected String outputBuffer = "";

    private Color defaultButtonColor = new JButton().getBackground();
    private Color narratorFightColor = new Color(205, 78, 78);

    private Timer timer = new Timer(50, this::checkTime);
    private JFrame frame;
    private Border softBevelBorderOf4 = BorderFactory.createSoftBevelBorder(4);

    private JButton[] buttonOptions = new JButton[6];

    private JButton option1;
    private JButton option2;
    private JButton option3;
    private JButton option4;
    private JButton option5;
    private JButton option6;

    private boolean textIsPrinting = false;

    private JButton inventory1;
    private JButton inventory2;

    private JButton skipDialogueButton;
    private JLabel endingsFoundLabel;

    private JTextField txtField;
    private JTextArea txtArea;
    private JPanel panel;
    private JPanel inventoryPanel;

    public GameFrame(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLayout(null);
        frame.setResizable(false);

        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        frame.setContentPane(panel);
        panel.setBackground(new Color(0x424949));

        inventoryPanel = new JPanel(new GridLayout());
        inventoryPanel.setSize(200, height - 39);
        inventoryPanel.setLocation(335, 0);
        inventoryPanel.setBackground(new Color(0, 0, 0, 60));
        inventoryPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 5));
        panel.add(inventoryPanel);
        // Fancy for loop to create my button setups and compact code!
        int posY = 240;
        for (int x = 0; x < buttonOptions.length; x++) {
            buttonOptions[x] = new JButton();
            buttonOptions[x].setSize(150, 25);
            buttonOptions[x].setBorder(softBevelBorderOf4);
            if (x % 2 == 0) {
                buttonOptions[x].setLocation(15, posY);
            } else {
                buttonOptions[x].setLocation(170, posY);
                posY += 40;
            }
        }
        option1 = buttonOptions[0];
        option1.setText("Submit");
        option2 = buttonOptions[1];
        option3 = buttonOptions[2];
        option4 = buttonOptions[3];
        option5 = buttonOptions[4];
        option6 = buttonOptions[5];

        // Sadly the foor loop doesn't work on addActionListener();... So sad
        option1.addActionListener(event -> this.buttonHandler(event, option1.getText()));
        option2.addActionListener(event -> this.buttonHandler(event, option2.getText()));
        option3.addActionListener(event -> this.buttonHandler(event, option3.getText()));
        option4.addActionListener(event -> this.buttonHandler(event, option4.getText()));
        option5.addActionListener(event -> this.buttonHandler(event, option5.getText()));
        option6.addActionListener(event -> this.buttonHandler(event, option6.getText()));

        inventory1 = new JButton();
        inventory1.setVerticalAlignment(1);

        inventory2 = new JButton();
        inventory2.setVerticalAlignment(3);

        txtField = new JTextField();
        txtField.setSize(305, 25);
        txtField.setLocation(15, 200);
        txtField.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0x20481c)));
        txtField.setBackground(Color.BLACK);
        txtField.setForeground(Color.GREEN);

        txtArea = new JTextArea();
        txtArea.setSize(305, 160);
        txtArea.setLocation(15, 20);
        txtArea.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0x20431c)));
        txtArea.setEditable(false);
        txtArea.setBackground(Color.BLACK);
        txtArea.setForeground(Color.GREEN);
        txtArea.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 12));
        txtArea.setText("Please type and submit your prefered name in \nthe box below!");
        txtArea.setLineWrap(true);

        inventoryPanel.add(inventory1);
        inventoryPanel.add(inventory2);

        skipDialogueButton = new JButton("Skip Dialogue");
        skipDialogueButton.setSize(150, 25);
        skipDialogueButton.setLocation(170, 360);
        skipDialogueButton.setBorder(softBevelBorderOf4);
        skipDialogueButton.addActionListener(event -> this.buttonHandler(event, skipDialogueButton.getText()));

        endingsFoundLabel = new JLabel("", JLabel.CENTER);
        endingsFoundLabel.setSize(150, 25);
        endingsFoundLabel.setLocation(15, 360);
        endingsFoundLabel.setOpaque(false);
        endingsFoundLabel.setForeground(Color.BLACK);
        endingsFoundLabel.setText("Endings Found: " + endingsFound + "/10");

        panel.add(skipDialogueButton);
        panel.add(endingsFoundLabel);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(option5);
        panel.add(option6);
        panel.add(txtField);
        panel.add(txtArea);
        frame.setLocation(150, 300);
        frame.setVisible(true);
    }

    protected void checkTime(ActionEvent evt) {
        if (outputBuffer.equals("")) {
            textIsPrinting = false;
            return;
        }
        int tickInMilliseconds = timer.getDelay();
        if (tickInMilliseconds >= 20) {
            textIsPrinting = true;
            txtArea.append(outputBuffer.substring(0, 1));
            outputBuffer = outputBuffer.substring(1);
            timer.restart();
        }
    }

    private int skipIsClicked = 0;

    protected void buttonHandler(ActionEvent event, String textID) {
        timer.start();

        endingsFoundLabel.setText("Endings Found: " + endingsFound + "/10");
        if (textID.equals("Skip Dialogue")) {
            timer.setInitialDelay(0);
            skipIsClicked++;
            if (skipIsClicked > 3 && endingsFound == 0) {
                txtField.setText("Read The Story!");
            }
            return;
        } else {
            timer.setInitialDelay(50);
        }
        if (textIsPrinting || textID.equals("")) {
            return;
        }
        txtArea.setText("");
        option1.setText("");
        option2.setText("");
        option3.setText("");
        option4.setText("");
        option5.setText("");
        option6.setText("");

        option2.setBackground(defaultButtonColor);
        switch (textID) {
            // The beginning to my long list of plausible text options for the player
            case "Restart":
                option1.setText("Enter Park");
                option2.setText("Go Home");

                option2.setBackground(defaultButtonColor);
                txtArea.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0x20431c)));
                txtArea.setForeground(Color.GREEN);
                txtField.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0x20431c)));
                txtField.setForeground(Color.GREEN);
                playerHasDoneSomething = false;

                outputBuffer += openingLine;
                break;
            case "Submit":
                userName = txtField.getText();
                if (userName.equals("")) {
                    outputBuffer += invalidName;
                    option1.setText("Submit");
                    return;
                }
                txtField.setText("");
                txtField.setEditable(false);
                option1.setText("Enter Park");
                option2.setText("Go Home");
                outputBuffer += openingLine;
                break;
            case "Go Home":
                outputBuffer += "Turning around quite alarmingly.\nYou walk briskly towards your car and get in.\nFrantically turning the ignition you speed away,\nnever to return.\n\n[One Star, Horrible Service]\nEnding 1 of 10";
                option1.setText("Restart");
                endingsFound++;
                break;
            case "Enter Park":
                outputBuffer += "Going through the gate, you enter the park.\nMany things catch you eyes as you stroll down\nthe barren pavement. There appears to be several rides\nand carnival games available to you.\n[Which One Do You Choose?]";
                option1.setText("Fact-O-Tron");
                option2.setText("None Of Them");
                break;
                // We praise and love the all knowing Fact-O-Tron!!!
            case "Fact-O-Tron":
                playerHasDoneSomething = true;
                FactOTron factOTron = new FactOTron();
                option1.setText("More Game");
                option2.setText("Continue");
                ;
                break;
            case "None Of Them":
                outputBuffer += "Wow. Okay. Thanks for invalidating all my\nhard work I did...\nI actually feel really hurt by that.\n\nYou just couldn't have played ONE game?\n";
                option1.setText("Continue");
                break;
            case "Continue":
                if (!playerHasDoneSomething) {
                    outputBuffer += "Moving on from the 'uninteresting' games,\nyou continue walking down the path.";
                } else {
                    outputBuffer += "Deciding you've played plenty,\nyou walk away from the games and down the pavement.\n";
                }
                outputBuffer += "\nFinding your mouth watering and stomach grumbling,\na delicous arouma fills the air.\n\nThere's a vender selling hotdogs and smoothies.\n[Stop for a Bite?]";
                option1.setText("Stop for Food");
                option2.setText("Starve");
                break;
                // Having a food system for an eventual inventory
            case "Stop for Food":
                playerHasDoneSomething = true;
                outputBuffer += "Overcome by the smells, you drift towards the vender.\nA small menu is present on a board above the counter.\n[What do you want?]";
                txtField.setText("$" + money + " - is in you wallet");
                option1.setText("Frozen Lemonade - $2.00");
                option2.setText("Berry Smoothie - $3.00");
                option3.setText("Jumbo Hotdog - $4.00");
                option4.setText("Nevermind, I'll Starve");
                break;
            case "Frozen Lemonade - $2.00":
                if (money >= 2) {
                    money -= 2;
                    txtField.setText("$" + money + " - is in you wallet");
                    option1.setText("Frozen Lemonade - $2.00");
                    option2.setText("Berry Smoothie - $3.00");
                    option3.setText("Jumbo Hotdog - $4.00");
                    option4.setText("Alright. That's Enough");
                } else {
                    outputBuffer += noMoneyLeft;
                    option1.setText("Don't Take a Peek");
                    option2.setText("Take a Peek");
                }
                break;
            case "Berry Smoothie - $3.00":
                if (money >= 3) {
                    money -= 3;
                    txtField.setText("$" + money + " - is in you wallet");
                    option1.setText("Frozen Lemonade - $2.00");
                    option2.setText("Berry Smoothie - $3.00");
                    option3.setText("Jumbo Hotdog - $4.00");
                    option4.setText("Alright. That's Enough");
                } else {
                    outputBuffer += noMoneyLeft;
                    option1.setText("Don't Take a Peek");
                    option2.setText("Take a Peek");
                }
                break;
            case "Jumbo Hotdog - $4.00":
                if (money >= 4) {
                    money -= 4;
                    txtField.setText("$" + money + " - is in you wallet");
                    option1.setText("Frozen Lemonade - $2.00");
                    option2.setText("Berry Smoothie - $3.00");
                    option3.setText("Jumbo Hotdog - $4.00");
                    option4.setText("Alright. That's Enough");
                } else {
                    outputBuffer += noMoneyLeft;
                    option1.setText("Don't Take a Peek");
                    option2.setText("Take a Peek");
                }
                break;
            case "Alright. That's Enough":
                txtField.setText("");
                outputBuffer += "After purchasing some food, you decide to keep moving.\nWalking past rows and rows of venders.\nEventually after passing the last one, you see a large\ntarp hiding part of the park.\n[Take a Peek?]";
                option1.setText("Don't Take a Peek");
                option2.setText("Take a Peek");
                break;
            case "Nevermind, I'll Starve":
                playerHasDoneSomething = false;
                txtField.setText("");
                outputBuffer += "Well thanks for the time waster!\n\nDeciding to change your mind about purchasing some delicous\nfood, you walk away from the venders and come across a\ntarped off section of the park.\n[Take a Peek?]";
                option1.setText("Don't Take a Peek");
                option2.setText("Take a Peek");
                break;
            case "Starve":
                outputBuffer += "Fighting the urge for food you push forwards.\nClearing the venders you see a large tarp sealing\noff an area of the park.\n[Take a Peek?]";
                option1.setText("Don't Take a Peek");
                option2.setText("Take a Peek");
                break;
            case "Don't Take a Peek":
                if (!playerHasDoneSomething) {
                    outputBuffer += "Okay.\nBe serious with me for a second.\nAre you just determined on doing nothing?\nI don't mind entirely, but did I do all\nthis work for nothing?\n[Y/N?]";
                    option1.setText("I Will Do Nothing");
                    option2.setText("No, I'll Do Something");
                } else {
                    outputBuffer += "Deciding against violating privacy, you continue down\nthe path. Eventually looping back to the main gate entrance.\nA small segment near the gate is a giftshop.\n[Stop There?]";
                    option1.setText("Stop at the GiftShop");
                    option2.setText("Leave the Park");
                }
                break;
            case "I Will Do Nothing":
                outputBuffer += "That was a test, you have failed.\n\nRealizing that you haven't done anything useful with\nyour time or money, the idea of wasting both of these things\nstrikes fear into you. As you collapse to the ground\nyour heart stops. Leaving you dead on the pavement\n\n[Just ONE Thing!]\nEnding 5 of 10";
                option1.setText("Restart");
                endingsFound++;
                break;
            case "No, I'll Do Something":
                outputBuffer += "Oh thank god!\nAs you decide against violating privacy laws,\nyou stride through hoards of tourists and visitors.\nUpon stopping you realize that you're at the park's\nentrance again. Looking to the right there is a giftshop\nwith merchandise.\n[Pit Stop?]";
                option1.setText("Stop at the GiftShop");
                option2.setText("Leave the Park");
                break;
            case "Stop at the GiftShop":
                outputBuffer += "Wanting to take a look around and find a potential\nsouvenier to put up at home.\nWhaltzing around you see T-Shirts, snow globes, and\nbobble heads. However a golden glint catches your eye.\nA trophy that's selling for $100.\n[Buy It?]";
                if (money < 100) {
                    option1.setText("That is WAY too much");
                } else {
                    option1.setText("Buy the Trophy!");
                }
                option2.setText("No Thanks!");
                break;
            case "That is WAY too much":
                outputBuffer += "Shouting out at the clear idiotic pricing!\nYou storm out of the store and begin violently assulting\ninnocent visitors. 1 down, 2 down, 5, 15.\nOne by one people are knocked down. Park security\nshows up and arrest you. Throwing you out and banning\nyou for life.\n\n[Prices to Riot over!]\nEnding 7 of 10";
                option1.setText("Restart");
                endingsFound++;
                break;
            case "Buy the Trophy!":
                outputBuffer += "Grabbing the trophy and paying heavily for it, you walk\nout of the park with the trophy lighting up your world.\nGetting into your car and driving away, screaming with joy!\n\n[Completionist Award]\nEnding 4 of 10";
                option1.setText("Restart");
                endingsFound++;
                break;
            case "No Thanks!":
                outputBuffer += "Seeing the last there is, you decide to head home.\nPassing through the gate and getting to your car,\nyou hop in and crank up the radio. Speeding away.\n\n[Had a Nice Day]\nEnding 2 of 10";
                option1.setText("Restart");
                endingsFound++;
                break;
            case "Leave the Park":
                if (!playerHasDoneSomething) {
                    outputBuffer += "No";
                    option2.setBackground(new Color(170, 170, 170));
                    option2.setForeground(new Color(25, 67, 83));
                    option1.setText("Stop at the GiftShop");
                    option2.setText("|@*&%$!)/{<!+^?");
                } else {
                    outputBuffer += "Deciding that today has given enough action,\nyou walk through the terminal and exit the park.\nSlowly strolling towards your car you hop in and\nturn on the radio.\n\n[An Average Day]\nEnding 3 of 10";
                    option1.setText("Restart");
                    endingsFound++;
                }
                break;
            // I think you made him angry
            case "|@*&%$!)/{<!+^?":
                txtArea.setForeground(narratorFightColor);
                txtArea.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, narratorFightColor));
                txtField.setForeground(narratorFightColor);
                txtField.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, narratorFightColor));
                outputBuffer += "I don't think you understand...\n\nYou. Have no 'freedom' here...\nThe choices you make are meaningless...\n\nThis was hard to work on...\nAND YOU HAVE AVOIDED ALL OF THEM...";
                option1.setText("Why can I choose?");
                option2.setText("But it's all BORING!");
                break;
            case "Why can I choose?":
                outputBuffer += "So variety can exist.\nWhy else?\n\nIt's supposed to make each run-through original...\nBut you don't care... Do you?";
                option1.setText("I care!");
                option2.setText("I don't!");
                break;
            case "I care!":
                outputBuffer += "If you care SO MUCH!\nThen why don't you PLAY THE GAME!?";
                option1.setText("To Explore");
                option2.setText("To Enjoy");
                option3.setText("To Learn");
                option4.setText("To Complete");
                option5.setText("To Ruin");
                option6.setText("To Destroy");
                break;
            case "To Explore":
                outputBuffer += "Explore?\nThat sounds nice...\nI won't give you an ending though...\nMade me pretty angry there.";
                option1.setText("Restart");
                break;
            case "To Enjoy":
                outputBuffer += "ENJOY?!\nYou've been pissing me off since the start!\nDon't LIE to me now!\n\nGET OUT OF HERE!";
                option1.setText("Restart");
                break;
            case "To Learn":
                outputBuffer += "Learn what?\nHow to make people upset?\n\nI guess I'll let you off the hook...\nYou don't get anything special though.";
                option1.setText("Restart");
                break;
            case "To Complete":
                outputBuffer += "A completionist then?\nwell you're currently sitting at %"
                        + (int) Math.round((double) endingsFound / 10) * 100
                        + " of finishing\nthe game.\n\nGood Luck!\nBut there isn't an ending for this.";
                option1.setText("Restart");
                break;
                // Whoopsies, that added fuel to the flame
            case "To Ruin":
                timer.setInitialDelay(300);
                outputBuffer += "ruin...\nthat was one of two things...\nYOU SHOULDN'T HAVE SAID!";
                narratorFightSetup();

                break;
            case "To Destroy":
                timer.setInitialDelay(300);
                outputBuffer += "no...\nno no no...\n\ni am going to DESTROY you...\nGAME ON!";
                narratorFightSetup();
                break;
                // Look at you huh. Sassing the narrator
            case "I don't!":
                timer.setInitialDelay(300);
                outputBuffer += "...\nokay\n...\n...\n...\ni am done with you";
                narratorFightSetup();
                break;
                // How would you feel if I called your work boring?
            case "But it's all BORING!":
                timer.setInitialDelay(300);
                outputBuffer += "boring...\n\n...\n\nalright, i'll make it interesting then...\nHave fun~!";
                narratorFightSetup();
                break;
            case "Take a Peek":
                playerHasDoneSomething = true;
                outputBuffer += "Pulling up part of the tarp, you slip inside.\nRandom pieces of construction gear is layed\naround on the flooring.\nA rung ladder is drilled into a seemingly\nbottomless pipe made of concrete.\n[Peek even more?]";
                option1.setText("Go Down the Pipe");
                option2.setText("Leave the Tarp");
                break;
            case "Go Down the Pipe":
                outputBuffer += "Needing to know more, you climb down the ladder.\nDescending into darkness you find it connects into a\nsewer system. The smell of fecies and trickling water\ntells you that much.\nFeeling around you brush you hand against a pile of\npaper notes.[Take them?]";
                option1.setText("Take the Paper");
                option2.setText("I'm Not Touching It!");
                break;
            case "Take the Paper":
                money += 98;
                outputBuffer += "Having no issue with infections or diseases,\nyou take the paper notes and climb back up the ladder.\nUpon reaching the top you see that the paper is\n$98 in cash!";
                option1.setText("Go On");
                break;
            case "I'm Not Touching It!":
                outputBuffer += "Deciding for the better that it is a good idea not to\ntake random paper from a sewer. You climb back up the\nladder and hop out of the pipe.";
                option1.setText("Go On");
                break;
            case "Go On":
                outputBuffer += "Leaving the tarped area, you make your way and find\nthat you've made a full circle and returned to the\npark's entrance.\nThere appears to be a small little giftshop that\nyou can go through when leaving.\n[Check it Out?]";
                option1.setText("Stop at the GiftShop");
                option2.setText("Leave the Park");
                break;
            case "Leave the Tarp":
                outputBuffer += "Wanting to avoid from pressing your luck,\nyou leave the tarped off area and head onwards.\nSurprisingly you have gone full circle and are back at\nthe main entrance. There appears to be a giftshop on the way out.\n[Stop for Merchandise?]";
                option1.setText("Stop at the GiftShop");
                option2.setText("Leave the Park");
                break;
        }
    }

    // An unfinished prototype for a kind of "True Ending". But ran out of time
    private void narratorFightSetup() {
        endingsFoundLabel.setText("Endings Found: " + endingsFound + "/??");
        panel.setBackground(Color.BLACK);
        inventoryPanel.setBorder(BorderFactory.createLineBorder(narratorFightColor, 5));

        inventory1.setBackground(Color.BLACK);
        inventory2.setBackground(Color.BLACK);

        inventory1.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, narratorFightColor));
        inventory2.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, narratorFightColor));

        option1.setBackground(Color.BLACK);
        option2.setBackground(Color.BLACK);
        option3.setBackground(Color.BLACK);
        option4.setBackground(Color.BLACK);
        option5.setBackground(Color.BLACK);
        option6.setBackground(Color.BLACK);

        skipDialogueButton.setBackground(Color.BLACK);
        skipDialogueButton.setForeground(narratorFightColor);
        skipDialogueButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, narratorFightColor));
        skipDialogueButton.setEnabled(false);

        endingsFoundLabel.setForeground(narratorFightColor);

        option1.setForeground(narratorFightColor);
        option2.setForeground(narratorFightColor);
        option3.setForeground(narratorFightColor);
        option4.setForeground(narratorFightColor);
        option5.setForeground(narratorFightColor);
        option6.setForeground(narratorFightColor);

        option1.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, narratorFightColor));
        option2.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, narratorFightColor));
        option3.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, narratorFightColor));
        option4.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, narratorFightColor));
        option5.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, narratorFightColor));
        option6.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, narratorFightColor));

        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
        option5.setEnabled(false);
        option6.setEnabled(false);
    }
}
