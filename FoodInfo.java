import java.util.ArrayList;

/**
 * FoodInfo takes care of all calculations needed for the project
 */
public class FoodInfo {
    private int age;
    private int weight; //lbs //testing
    private int height; //inches //testing
    private String sex; //testing

    public FoodInfo(){
        age = 0;
        weight = 0;
        height = 0;
    }

    /**
     * Constructor takes in biometric info and stores it
     * @param age
     * @param weight
     * @param height
     * @param sex
     */
    public FoodInfo(int age, int weight, int height, String sex) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
    }

    /**
     * Uses the biometric info to find the estimated daily calorie requirements of the user
     * @return dailyCal
     */
    public int getRecommendedCalories(){
       if (sex.equalsIgnoreCase("male")){
           return (int)((10 * (weight * 0.453592)) + (6.25 * (height * 2.54)) - (5 * age) +5);
       } else{
           return (int)((10 * (weight * 0.453592)) + (6.25 * (height * 2.54)) - (5 * age) - 161);
       }
    }//end

    public int getRecommendedProteinGrams(){
        return (int) (weight * 0.8);
    }//end

    public int getRecommendedCarbGrams(){
        return (int) (getRecommendedCalories() * 0.5/4);
    }
    public int getRecommendedFatGrams(){
        return (int) (getRecommendedCalories() * 0.25/9);
    }//end

    /**
     * Calculates the amount of calories each food item is worth using its grams of
     * macronutrients
     * @param items
     * @return total
     */
    public int getTotalCalories(ArrayList<String> items){
        int total = 0;

        for (int i = 0; i + 3 < items.size(); i += 4){
            int carbs = Integer.parseInt(items.get(i + 1));
            int protein = Integer.parseInt(items.get(i + 2));
            int fat = Integer.parseInt(items.get(i + 3));

            total += (carbs * 4) + (protein * 4) + (fat * 9);

        }
        return total;
    }//end

}// end class
