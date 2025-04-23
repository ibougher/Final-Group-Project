import java.io.*;
import java.util.*;

public class FoodFile {

    //Constructs a FoodFile
    public FoodFile() {}

    //Writing to the file
    public void writeFoodItem(String name, String carbs, String protein, String fat) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("foodLog.txt", true))) {
            writer.write(name); writer.newLine();
            writer.write(carbs); writer.newLine();
            writer.write(protein); writer.newLine();
            writer.write(fat); writer.newLine();

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    //Reading from the file
    public ArrayList<String> readFoodItems() throws FileNotFoundException {
        ArrayList<String> items = new ArrayList<>();
        Scanner fileIn = new Scanner(new File("foodLog.txt"));
        while (fileIn.hasNext()){
            items.add(fileIn.next());
        }

        return items;
    }
}