import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.awt.*;
import java.util.*;

class BioButton implements ActionListener {
    private JFrame frame;
    private JTextField textField;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    public BioButton (JFrame frame, JTextField textField, JTextField textField2,
                      JTextField textField3, JTextField textField4){
        this.frame = frame;
        this.textField = textField;
        this.textField2 = textField2;
        this.textField3 = textField3;
        this.textField4 = textField4;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        int dailyCal;
        FoodInfo info = new FoodInfo(textField.getText(), textField2.getText(),
                textField3.getText(),
                textField4.getText());
        dailyCal = info.getRecommendedCalories();
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("Calorie Tracker");
        int dailyCal = 0;
        boolean surplus;
        String choice = null;


            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel("Age");
            frame.add(label);

            JTextField textField = new JTextField();
            frame.add(textField);

            JLabel label2 = new JLabel("Weight");
            frame.add(label2);

            JTextField textField2 = new JTextField();
            frame.add(textField2);

            JLabel label3 = new JLabel("Height");
            frame.add(label3);

            JTextField textField3 = new JTextField();
            frame.add(textField3);

            JLabel label4 = new JLabel("Sex");
            frame.add(label4);

            JTextField textField4 = new JTextField();
            frame.add(textField4);


            JButton bioButton = new JButton("Calculate");
            bioButton.addActionListener(new BioButton(frame, textField, textField2, textField3,
                    textField4));
            frame.getContentPane().add(bioButton);

            frame.pack();
            frame.setVisible(true);




    }
    public void clear (JFrame frame){
        frame.getContentPane().removeAll();
    }
}
