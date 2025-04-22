import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.awt.*;
import java.util.*;

class BioButton implements ActionListener {
    private JFrame frame;
    private JTextField ageField;
    private JTextField weightField;
    private JTextField heightField;
    private JTextField sexField;

    public BioButton (JFrame frame, JTextField ageField, JTextField weightField,
                      JTextField heightField, JTextField sexField) {
        this.frame = frame;
        this.ageField = ageField;
        this.weightField = weightField;
        this.heightField = heightField;
        this.sexField = sexField;
    }

    private void clearFrame() {
        frame.getContentPane().removeAll();
        frame.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        int age = Integer.parseInt(ageField.getText());
        int weight = Integer.parseInt(weightField.getText());
        int height = Integer.parseInt(heightField.getText());
        String sex = sexField.getText();

        FoodInfo info = new FoodInfo(age, weight, height, sex);
        int dailyCal = info.getRecommendedCalories();

        clearFrame();

        JLabel prompt = new JLabel("Do you want a calorie surplus or deficit?");
        JButton surplusButton = new JButton("Surplus");
        JButton deficitButton = new JButton("Deficit");

        surplusButton.addActionListener(r -> {
            boolean surplus = true;
            foodEntry(surplus);
        });

        deficitButton.addActionListener(r -> {
            boolean surplus = false;
            foodEntry(surplus);
        });

        frame.add(prompt);
        frame.add(surplusButton);
        frame.add(deficitButton);
        frame.revalidate();
        frame.repaint();
    }
    private void foodEntry(boolean surplus) {
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

}
