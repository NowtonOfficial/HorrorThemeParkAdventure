import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MerryGoRound {

    private KeyListener keyListener;
    private JFrame merryGoRoundFrame;
    private JPanel backgroundPanel;
    private JLabel indicatorLabel;
    private JLabel pointsIndicator;
    private Timer timer = new Timer(1000, e -> {
        try {
            checkTime(e);
        } catch (InterruptedException evt) {
        }
    });

    private boolean spaceIsPressed = false;
    private int points = 0;

    public MerryGoRound() {
        merryGoRoundFrame = new JFrame("Merry Go Round");
        merryGoRoundFrame.setSize(300, 250);
        keyListener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spaceIsPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spaceIsPressed = false;
                }
            }
            
        };
        merryGoRoundFrame.addKeyListener(keyListener);
        backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(140, 140, 140));
        merryGoRoundFrame.setContentPane(backgroundPanel);

        indicatorLabel = new JLabel();
        indicatorLabel.setText("Press [Space] when prompted!");
        pointsIndicator = new JLabel();
        pointsIndicator.setText("Current Score: ");

        backgroundPanel.add(indicatorLabel);
        backgroundPanel.add(pointsIndicator);

        merryGoRoundFrame.setVisible(true);
        timer.start();
    }

    private int timePassedInSeconds = 0;
    private int randomTimeDelay = (int) (Math.random() * 5 + 3);

    private void checkTime(ActionEvent evt) throws InterruptedException {
        timePassedInSeconds++;
        if (timePassedInSeconds >= randomTimeDelay) {
            indicatorLabel.setText("NOW!");
            Thread.sleep(250);
            randomTimeDelay = (int) (Math.random() * 5 + 3);
            timePassedInSeconds = 0;
        } else {
            indicatorLabel.setText("");
        }
        if (indicatorLabel.getText().equals("NOW!") && spaceIsPressed) {
            points++;
        }
        if ((indicatorLabel.getText().equals("NOW!") && !spaceIsPressed)||(!indicatorLabel.getText().equals("NOW!") && spaceIsPressed)) {
            points--;      
        }
        pointsIndicator.setText("Current Score: "+points);
    }

}
