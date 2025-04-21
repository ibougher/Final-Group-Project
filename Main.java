import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.awt.*;
import java.util.*;

class ButtonListener implements ActionListener {
    private JFrame frame;
    private JTextField textField;

    public ButtonListener (JFrame frame, JTextField textField){
        this.frame = frame;
        this.textField = textField;
    }

    public int fact(int num) {
        int total = 1;
        for (int i = 1; i <= num; i++){
            total *= i;
        }
        return total;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = textField.getText();
        int answer = fact(Integer.parseInt(input));
        JOptionPane.showMessageDialog(null, "The factorial of " + input + " is " +
                Integer.toString(answer));
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("Calorie Tracker");

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Age");
        frame.add(label);

        JTextField textField = new JTextField();
        frame.add(textField);

        JLabel label2 = new JLabel("Height");
        frame.add(label2);

        JTextField textField2 = new JTextField();
        frame.add(textField2);


        JButton button = new JButton("Calculate");
        button.addActionListener(new ButtonListener(frame, textField));
        frame.getContentPane().add(button);


        frame.pack();
        frame.setVisible(true);
    }
}
