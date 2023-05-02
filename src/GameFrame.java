import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

public class GameFrame {

    private String openingLine = "Welcome to 'Ghostly and Ghastly Coasters'!\nWe hope you enjoy the rides at our fine establishment!";

    private ImageIcon imageIcon;

    private Timer timer = new Timer(0, this::checkTime);
    private JFrame frame;
    private Border softBevelBorderOf4 = BorderFactory.createSoftBevelBorder(4);

    private JButton option1;
    private JButton option2;
    private JButton option3;
    private JButton option4;
    private JButton option5;
    private JButton option6;
    private JButton option7;
    private JButton option8;

    private JTextField txtField;
    private JTextArea txtArea;
    private JPanel panel;
    private JPanel inventoryPanel;

    private KeyListener listener = new KeyListener() {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (txtField.getText().equals("")) {
                    txtArea.setText("Sorry, that name isn't valid.\nPlease try again!");
                } else {
                    timer.start();
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stu
        }

    };

    public GameFrame(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLayout(null);

        imageIcon = new ImageIcon();

        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        frame.setContentPane(panel);
        panel.setBackground(new Color(0x424949));
        panel.setSize(350, height);

        option1 = new JButton();
        option1.setSize(150, 25);
        option1.setLocation(15, 240);
        option1.setBorder(softBevelBorderOf4);

        option2 = new JButton();
        option2.setSize(150, 25);
        option2.setLocation(170, 240);
        option2.setBorder(softBevelBorderOf4);

        option3 = new JButton();
        option3.setSize(150, 25);
        option3.setLocation(15, 280);
        option3.setBorder(softBevelBorderOf4);

        option4 = new JButton();
        option4.setSize(150, 25);
        option4.setLocation(170, 280);
        option4.setBorder(softBevelBorderOf4);

        option5 = new JButton();
        option5.setSize(150, 25);
        option5.setLocation(15, 320);
        option5.setBorder(softBevelBorderOf4);

        option6 = new JButton();
        option6.setSize(150, 25);
        option6.setLocation(170, 320);
        option6.setBorder(softBevelBorderOf4);

        option7 = new JButton();
        option7.setSize(150, 25);
        option7.setLocation(15, 360);
        option7.setBorder(softBevelBorderOf4);

        option8 = new JButton();
        option8.setSize(150, 25);
        option8.setLocation(170, 360);
        option8.setBorder(softBevelBorderOf4);

        txtField = new JTextField();
        txtField.setSize(305, 25);
        txtField.setLocation(15, 200);
        txtField.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0x20481c)));
        txtField.setBackground(Color.BLACK);
        txtField.setForeground(Color.GREEN);
        txtField.addKeyListener(listener);

        txtArea = new JTextArea();
        txtArea.setSize(305, 160);
        txtArea.setLocation(15, 20);
        txtArea.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0x20431c)));
        txtArea.setEditable(false);
        txtArea.setBackground(Color.BLACK);
        txtArea.setForeground(Color.GREEN);
        txtArea.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 12));
        txtArea.setLineWrap(false);

        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(option5);
        panel.add(option6);
        panel.add(option7);
        panel.add(option8);
        panel.add(txtField);
        panel.add(txtArea);
        frame.setVisible(true);
    }

    private String getString(String string) {
        return string;
    }

    private void checkTime(ActionEvent evt) {
        int length = getString(openingLine).length();
        for (int x = 0; x < length - 1; x++) {
            txtArea.append(openingLine);
        }
    }

    private void buttonHandler(ActionEvent event) {

    }
}
