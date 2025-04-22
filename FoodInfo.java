public class FoodInfo {
    private int age;
    private int weight = 0; //lbs //testing
    private int height = 0; //inches //testing
    private String sex = "female"; //testing

    public FoodInfo(int age, int weight, int height, String sex) {
        this.age = age;
    }
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


}// end class
