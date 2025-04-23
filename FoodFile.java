import java.io.*;
import java.util.*;

public class FoodFile {
    private String filePath;

    //Constructs a FoodFile tied to a specific file path
    public FoodFile() {}

    //Writing to the file
    public void writeFoodItem(String name, int carbs, int protein, int fat) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("foodLog.txt"))) {
            writer.write(name);
            writer.newLine();

            writer.write(carbs);
            writer.newLine();

            writer.write(protein);
            writer.newLine();

            writer.write(fat);
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    //Reading from the file
    public List<String> readFoodItems() throws FileNotFoundException {
        List<String> items = new ArrayList<>();
        Scanner fileIn = new Scanner(new File("foodLog.txt"));
        while (fileIn.hasNext()){
            items.add(fileIn.next());
        }

        return items;
    }
}