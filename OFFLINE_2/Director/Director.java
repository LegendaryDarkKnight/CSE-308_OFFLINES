package Director;

import Builder.*;
import Shakes.*;

public class Director {
    public ShakePlan makeChocolateShake(boolean lacFree, boolean candy, boolean cookies){
        ShakeBuilder builder = new ShakeBuilder();
        builder.setName("Chocolate Shake");
        builder.setPrice(230);
        builder.addMilk();
        builder.addSugar();
        builder.addChocolateSyrup();
        builder.addChocolateIceCream();
        if(lacFree) builder.makeLactoseFree();
        if(candy) builder.addCandy();
        if(cookies) builder.addCookies();
        return builder.getShake();
    }
    public ShakePlan makeCoffeeShake(boolean lacFree, boolean candy, boolean cookies){
        ShakeBuilder builder = new ShakeBuilder();
        builder.setName("Coffee Shake");
        builder.setPrice(250);
        builder.addMilk();
        builder.addSugar();
        builder.addCoffee();
        builder.addJello();
        if(lacFree) builder.makeLactoseFree();
        if(candy) builder.addCandy();
        if(cookies) builder.addCookies();
        return builder.getShake();
    }
    public ShakePlan makeStrawberryShake(boolean lacFree, boolean candy, boolean cookies){
        ShakeBuilder builder = new ShakeBuilder();
        builder.setName("Strawberry Shake");
        builder.setPrice(200);
        builder.addMilk();
        builder.addSugar();
        builder.addStrawberrySyrup();
        builder.addStrawberryIceCream();
        if(lacFree) builder.makeLactoseFree();
        if(candy) builder.addCandy();
        if(cookies) builder.addCookies();
        return builder.getShake();
    }
    public ShakePlan makeVanillaShake(boolean lacFree, boolean candy, boolean cookies){
        ShakeBuilder builder = new ShakeBuilder();
        builder.setName("Vanilla Shake");
        builder.setPrice(190);
        builder.addMilk();
        builder.addSugar();
        builder.addVanillaFlavoring();
        builder.addJello();
        if(lacFree) builder.makeLactoseFree();
        if(candy) builder.addCandy();
        if(cookies) builder.addCookies();
        return builder.getShake();
    }
    public ShakePlan makeZeroShake(boolean lacFree, boolean candy, boolean cookies){
        ShakeBuilder builder = new ShakeBuilder();
        builder.setName("Zero Shake");
        builder.setPrice(240);
        builder.addMilk();
        builder.addSweetener();
        builder.addVanillaFlavoring();
        builder.addSugarFreeJello();
        if(lacFree) builder.makeLactoseFree();
        if(candy) builder.addCandy();
        if(cookies) builder.addCookies();
        return builder.getShake();
    } 
}
