package Shakes;

import java.util.ArrayList;

public class Shake implements ShakePlan {
    private final String name;
    private final double price;
    private final ArrayList<String> baseIngredients;
    private final ArrayList<String> addedIngredients;

    public Shake(String name, double price, ArrayList<String> baseIngredients, ArrayList<String> addedIngredients){
        this.name = name;
        this.price = price;
        this.baseIngredients = baseIngredients;
        this.addedIngredients = addedIngredients;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        String output = name + "\nBase Ingredients:\n";
        for (String s : baseIngredients) {
            output += "-- " + s + "\n";
        }
        if (!addedIngredients.isEmpty()) {
            output += "\nAdded Ingredients:\n";
            for (String s : addedIngredients) {
                output += "-- " + s + "\n";
            }
        }
        output += "\nPrice: \t\t" + price;
        return output;
    }

}
