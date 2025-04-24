import java.io.*;
import java.util.*;

/**
 * The FoodFile class handles reading from and writing to a file
 * that stores food item info, including their name, carbs, protein, and fats
 */
public class FoodFile {

    /**
     * Constructs a new FoodFile object.
     */
    public FoodFile() {}

    /**
     * Clears the contents of the "foodLog.txt" file.
     */
    public void clearFile() {
        //clears the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("foodLog.txt"))) {
            writer.write("");
        } catch (IOException e) {
            System.err.println("Error clearing file: " + e.getMessage());
        }
    }

    /**
     * Writes a food item and its macros to the "foodLog.txt" file.
     * Each value is written on a new line.
     * @param name the name of the food item
     * @param carbs the grams of carbs
     * @param protein the grams of proteins
     * @param fat the grams of fat
     */
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

    /**
     * Reads all food items and their macros from the "foodLog.txt" file.
     * Each item is assumed to take up four lines of the file.
     * @return an ArrayList of strings containing all the food item data
     * @throws FileNotFoundException if the file cannot be found
     */
    public ArrayList<String> readFoodItems() throws FileNotFoundException {
        ArrayList<String> items = new ArrayList<>();
        Scanner fileIn = new Scanner(new File("foodLog.txt"));
        while (fileIn.hasNext()){
            items.add(fileIn.next());
        }

        return items;
    }
}