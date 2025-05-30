package Builder;

import Shakes.*;
import java.util.ArrayList;

public class ShakeBuilder {
    private String name;
    private double price;
    private final ArrayList<String> baseIngredients;
    private final ArrayList<String> addedIngredients;

    public ShakeBuilder(){
        baseIngredients = new ArrayList<>();
        addedIngredients = new ArrayList<>();
        price = 0;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void addMilk(){
        baseIngredients.add("Milk");
    }
    public void addSugar(){
        baseIngredients.add("Sugar");
    }
    public void addSweetener(){
        baseIngredients.add("Sweetener");
    }    
    public void addChocolateSyrup(){
        baseIngredients.add("Chocolate Syrup");
    }
    public void addStrawberrySyrup(){
        baseIngredients.add("Strawberry Syrup");
    }
    public void addChocolateIceCream(){
        baseIngredients.add("Chocolate ice cream");
    }
    public void addStrawberryIceCream(){
        baseIngredients.add("Strawberry ice cream");
    }
    public void addCoffee(){
        baseIngredients.add("Coffee");
    }
    public void addJello(){
        baseIngredients.add("Jello");
    }
    public void addSugarFreeJello(){
        baseIngredients.add("Sugar-free jello");
    }
    public void addVanillaFlavoring(){
        baseIngredients.add("Vanilla flavoring");
    }
    public void makeLactoseFree() {
        baseIngredients.remove("Milk");
        addedIngredients.add("Almond Milk");
        price += 60;
    }
    public void addCandy() {
        addedIngredients.add("Candy");
        price += 50;
    }
    public void addCookies() {
        addedIngredients.add("Cookies");
        price += 40;
    }
    public ShakePlan getShake(){
        return new Shake(name, price, baseIngredients, addedIngredients);
    }
}