import java.io.*;
import java.util.*;

public class FoodFile {
    private String filePath;

    //Constructs a FoodFile tied to a specific file path
    public FoodFile() {}

    //Writing to the file
    public void writeFoodItem(String name, int carbs, int protein, int fat) {
        String line = name + " - Carbs: " + carbs + "g, Protein: " + protein + "g, Fat: " + fat + "g";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    //Reading from the file
    public List<String> readFoodItems() {
        List<String> items = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return items;
    }
}
