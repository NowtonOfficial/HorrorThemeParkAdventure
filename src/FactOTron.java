import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.Timer;

public class FactOTron extends JFrameVariables {

    private JFrame factOTronFrame = new JFrame("Fact-O-Tron");
    private JWindow temp;
    private JPanel buttonHolder = new JPanel();
    private JButton optionA = new JButton("");
    private JButton optionB = new JButton("");
    private JButton optionC = new JButton("");
    private JButton optionD = new JButton("");

    private JLabel questionLabel = new JLabel("", JLabel.CENTER);
    private JLabel timeLeftIndicator = new JLabel("", JLabel.CENTER);
    private JLabel timesGuessed;
    private int totalTimeRemainingInMinutes = 4;
    private int totalTimeRemainingInSeconds = 60;
    private Timer timer = new Timer(1000, this::checkTime);

    private String[] questions;
    private String[] answers;
    private int numOfQuestions = 23;
    private String[] falseAnswers;
    private String[] usedQuestions = new String[20];
    private int numberOfTimesAnswered = 0;
    private int numberOfCorrectAnswers = 0;
    private String properAnswer;

    public FactOTron() {
        temp = new JWindow(factOTronFrame);
        temp.setSize(400, 220);
        temp.setLayout(null);
        temp.setLocation(300, 400);

        temp.getContentPane().setBackground(Color.lightGray);

        buttonHolder.setLayout(new GridLayout(2, 2));
        buttonHolder.setSize(400, 110);
        buttonHolder.setLocation(0, 110);

        questionLabel.setSize(400, 110);
        questionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        questionLabel.setLocation(0, 0);

        timeLeftIndicator.setSize(50, 25);
        timeLeftIndicator.setFont(new Font("Monospaced", Font.PLAIN, 12));
        timeLeftIndicator.setLocation(0, 0);
        timeLeftIndicator.setForeground(Color.RED);

        timesGuessed = new JLabel("00/20", JLabel.CENTER);
        timesGuessed.setSize(50, 25);
        timesGuessed.setFont(new Font("Monospaced", Font.PLAIN, 12));
        timesGuessed.setLocation(350, 0);
        timesGuessed.setForeground(Color.BLACK);

        optionA.addActionListener(event -> this.buttonHandler(event, optionA.getText()));
        optionB.addActionListener(event -> this.buttonHandler(event, optionB.getText()));
        optionC.addActionListener(event -> this.buttonHandler(event, optionC.getText()));
        optionD.addActionListener(event -> this.buttonHandler(event, optionD.getText()));

        buttonHolder.add(optionA);
        buttonHolder.add(optionB);
        buttonHolder.add(optionC);
        buttonHolder.add(optionD);

        temp.add(buttonHolder);
        temp.add(questionLabel);
        temp.add(timeLeftIndicator);
        temp.add(timesGuessed);
        temp.setVisible(true);

        getQuestions();
        getAnswers();
        getFalseAnswers();

        // Generates the first question
        int rand = randonNumberGenerator(numOfQuestions, 0);
        properAnswer = answers[rand];
        questionLabel.setText(questions[rand]);
        optionA.setText(answers[rand]);
        optionB.setText(falseAnswers[rand * 3]);
        optionC.setText(falseAnswers[rand * 3 + 1]);
        optionD.setText(falseAnswers[rand * 3 + 2]);
        usedQuestions[0] = questionLabel.getText();

        timer.start();
    }

    private int randonNumberGenerator(int number, int minimum) {
        return (int) (Math.random() * number) + minimum;
    }

    private void getQuestions() {
        try (Scanner in = new Scanner(new FileReader("src/factOTronData/questions.txt"))) {
            this.questions = new String[numOfQuestions];
            for (int x = 0; x < numOfQuestions; x++) {
                this.questions[x] = in.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.print("Error. File Not Found");
        }
    }

    private void getAnswers() {
        try (Scanner in = new Scanner(new FileReader("src/factOTronData/answers.txt"))) {
            this.answers = new String[numOfQuestions];
            for (int x = 0; x < numOfQuestions; x++) {
                this.answers[x] = in.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.print("Error. File Not Found");
        }
    }

    private void getFalseAnswers() {
        try (Scanner in = new Scanner(new FileReader("src/factOTronData/false_answers.txt"))) {
            this.falseAnswers = new String[numOfQuestions * 3];
            for (int x = 0; x < numOfQuestions * 3; x++) {
                this.falseAnswers[x] = in.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.print("Error. File Not Found");
        }
    }

    @Override
    protected void buttonHandler(ActionEvent evt, String buttonText) {
        // Increase final score if the correct answer is chosen
        if (buttonText.equals(properAnswer)) {
            numberOfCorrectAnswers++;
        }
        // Restart and Quit options once the game is finished
        if (buttonText.equals("Restart")) {
            totalTimeRemainingInMinutes = 4;
            totalTimeRemainingInSeconds = 60;
            numberOfTimesAnswered = 0;
            numberOfCorrectAnswers = 0;
            timer.restart();
        }
        if (buttonText.equals("Quit")) {
            factOTronFrame.dispose();
        }
        // All the randomized button positioning and chosen answers -- I know, it's a
        // lot
        numberOfTimesAnswered++;
        int randQuestions = randonNumberGenerator(numOfQuestions, 0);
        int randButtonPosition = randonNumberGenerator(4, 0);
        questionLabel.setText(questions[randQuestions]);
        properAnswer = answers[randQuestions];
        usedQuestions[numberOfTimesAnswered - 1] = properAnswer;
        // OKAY! I don't know what is wrong with this code. I have been trying to
        // implement a way of sorting out already used questions. However no matter how
        // I use the ".equals();" command, the value is null and causes a bunch of
        // problems

        // for (int x = 0; x < usedQuestions.length; x++) {
        // String tempString = usedQuestions[x];
        // if (tempString.equals(properAnswer)) {
        // randQuestions = randonNumberGenerator(numOfQuestions, 0);
        // }
        // }

        // Generates the false questions
        String falseAnswerOne = falseAnswers[randQuestions * 3];
        String falseAnswerTwo = falseAnswers[randQuestions * 3 + 1];
        String falseAnswerThree = falseAnswers[randQuestions * 3 + 2];
        // Based off a randum number between 0 and 3, the button placement is randomized
        if (randButtonPosition == 0) {
            optionA.setText(properAnswer);
            optionB.setText(falseAnswerOne);
            optionC.setText(falseAnswerTwo);
            optionD.setText(falseAnswerThree);
        } else if (randButtonPosition == 1) {
            optionA.setText(falseAnswerThree);
            optionB.setText(properAnswer);
            optionC.setText(falseAnswerOne);
            optionD.setText(falseAnswerTwo);
        } else if (randButtonPosition == 2) {
            optionA.setText(falseAnswerThree);
            optionB.setText(falseAnswerTwo);
            optionC.setText(properAnswer);
            optionD.setText(falseAnswerOne);
        } else if (randButtonPosition == 3) {
            optionA.setText(falseAnswerThree);
            optionB.setText(falseAnswerOne);
            optionC.setText(falseAnswerTwo);
            optionD.setText(properAnswer);
        }
        // Fancy formatting!
        if (numberOfTimesAnswered >= 10) {
            timesGuessed.setText(numberOfTimesAnswered + "/20");
        } else {
            timesGuessed.setText("0" + numberOfTimesAnswered + "/20");
        }
        // Stops the game if the player has hit 20 answered questions
        if (numberOfTimesAnswered >= 20) {
            questionLabel.setText("That is the limit! You got "
                    + (int) (Math.round((double) numberOfCorrectAnswers / 20 * 100)) + "%");
            timer.stop();
            optionA.setText("Restart");
            optionB.setText("Quit");
            optionC.setText("");
            optionD.setText("");
        }
    }

    @Override
    protected void checkTime(ActionEvent evt) {
        totalTimeRemainingInSeconds--;
        // Stops the game when the timer hits 0:00.
        if (totalTimeRemainingInMinutes <= 0 && totalTimeRemainingInSeconds <= 0) {
            questionLabel.setText(
                    "Time is up! You got " + (int) (Math.round((double) numberOfCorrectAnswers / 20 * 100)) + "%");
            optionA.setText("Restart");
            optionB.setText("Quit");
            optionC.setText("");
            optionD.setText("");
            timer.stop();
        }
        if (totalTimeRemainingInSeconds < 10) {
            timeLeftIndicator.setText(totalTimeRemainingInMinutes + ":0" + totalTimeRemainingInSeconds);
        } else {
            timeLeftIndicator.setText(totalTimeRemainingInMinutes + ":" + totalTimeRemainingInSeconds);
        }
        if (totalTimeRemainingInSeconds == 0) {
            totalTimeRemainingInMinutes--;
            totalTimeRemainingInSeconds = 60;
        }

    }
}
