import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This is where all the gui elements are added, removed, and calculates
 */
class ButtonListener implements ActionListener {
    private JFrame frame;
    private JTextField ageField;
    private JTextField weightField;
    private JTextField heightField;
    private JTextField sexField;

    /**
     * Constructor that takes in and stores the biometric info of the user
     * @param frame
     * @param ageField
     * @param weightField
     * @param heightField
     * @param sexField
     */
    public ButtonListener (JFrame frame, JTextField ageField, JTextField weightField,
                      JTextField heightField, JTextField sexField) {
        this.frame = frame;
        this.ageField = ageField;
        this.weightField = weightField;
        this.heightField = heightField;
        this.sexField = sexField;
    }

    /**
     * Clears the frame
     */
    public void clearFrame() {
        frame.getContentPane().removeAll();
        frame.repaint();
    }

    /**
     * When the biometrics are entered, this method calls getRecommendedCalories
     * to calculate estimated daily caloric intake. It also clears the GUI and moves on
     * to asking about surplus.
     * @param e the event to be processed
     */
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
            foodEntry(surplus, dailyCal);
        });

        deficitButton.addActionListener(r -> {
            boolean surplus = false;
            foodEntry(surplus, dailyCal);
        });

        frame.add(prompt);
        frame.add(surplusButton);
        frame.add(deficitButton);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Displays information and areas for the user to enter all the food they ate for the day,
     * then moving on to the goal screen
     * @param surplus
     * @param dailyCal
     */
    public void foodEntry(boolean surplus, int dailyCal) {
        String name;
        int carbs;
        int protein;
        int fat;
        clearFrame();

        JLabel prompt = new JLabel("Please enter the names and macros (in grams)" + "\n "+
                "of the food items you have eaten today" );
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel carbsLabel = new JLabel("Carbs:");
        JTextField carbField = new JTextField();

        JLabel proteinLabel = new JLabel("Protein:");
        JTextField proteinField = new JTextField();

        JLabel fatLabel = new JLabel("Fat:");
        JTextField fatField = new JTextField();

        FoodFile file = new FoodFile();

        JButton addButton = new JButton("Add");
        JButton doneButton = new JButton("Finish");

        addButton.addActionListener(e -> {
            file.writeFoodItem(
                    nameField.getText(),
                    carbField.getText(),
                    proteinField.getText(),
                    fatField.getText()
             );
            nameField.setText("");
            carbField.setText("");
            proteinField.setText("");
            fatField.setText("");
            }
        );

        doneButton.addActionListener(e -> {
            try {
                goalView(surplus, dailyCal);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        frame.add(prompt);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(carbsLabel);
        frame.add(carbField);
        frame.add(proteinLabel);
        frame.add(proteinField);
        frame.add(fatLabel);
        frame.add(fatField);
        frame.add(addButton);
        frame.add(doneButton);
        frame.revalidate();
        frame.repaint();


    }

    /**
     * Presents the users estimated daily caloric intake, how many calories the user actually ate,
     * if the user met their surplus or deficit goal, and a list of everything the user ate.
     * @param surplus
     * @param dailyCal
     * @throws FileNotFoundException
     */
    public void goalView(boolean surplus, int dailyCal) throws FileNotFoundException {
        clearFrame();
        ArrayList<String> list = new ArrayList<>();
        FoodFile file = new FoodFile();
        FoodInfo info = new FoodInfo();
        list = file.readFoodItems();
        int totalCal = info.getTotalCalories(list);

        JLabel calLabel = new JLabel("Your estimated daily caloric intake is: " + dailyCal);
        JLabel totalCalLabel = new JLabel("You ate " + totalCal + " total calories");
        JLabel goalLabel = new JLabel();
        JLabel listText = new JLabel("Here is a list of everything you ate for the day: ");

        if (surplus){
            if (totalCal > dailyCal){
                goalLabel.setText("Congratulations! You hit your calorie surplus!");
            }
            else {
                goalLabel.setText("Unfortunately, you failed to meet your calorie surplus.");
            }
        }
        else {
            if (totalCal < dailyCal){
                goalLabel.setText("Congratulations! You hit your calorie deficit!");
            }
            else {
                goalLabel.setText("Unfortunately, you failed to meet your calorie deficit.");
            }
        }

        JLabel listView = new JLabel();

        StringBuilder labelText = new StringBuilder("<html>");
        for (int i = 0; i + 3 < list.size(); i += 4) {
            labelText.append(list.get(i))
                    .append(", ")
                    .append(list.get(i + 1)).append("g of carbs, ")
                    .append(list.get(i + 2)).append("g of protein, ")
                    .append(list.get(i + 3)).append("g of fat.<br>");
        }
        labelText.append("</html>");
        listView.setText(labelText.toString());

        frame.add(calLabel);
        frame.add(totalCalLabel);
        frame.add(goalLabel);
        frame.add(listText);
        frame.add(listView);
        frame.revalidate();
        frame.repaint();

    }
}

/**
 * Initializes the gui and runs the code
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new FoodFile().clearFile();
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
            bioButton.addActionListener(new ButtonListener(frame, textField, textField2, textField3,
                    textField4));
            frame.getContentPane().add(bioButton);

            frame.pack();
            frame.setVisible(true);




    }

}
