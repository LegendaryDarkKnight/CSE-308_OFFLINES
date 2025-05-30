import Director.Director;
import Shakes.ShakePlan;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    private final ArrayList<ShakePlan> orderedShakes;
    private final Director director;
    private int choice = 0;
    private boolean lacFree, candy, cookies;
    private final Scanner scanner;
    public Order(Scanner scanner) {
        this.orderedShakes = new ArrayList<>();
        this.director = new Director();
        this.scanner = scanner;
    }

    public void showOrder() {
        double total = 0;
        System.out.println("\n========Your Orders========\n");
        for (ShakePlan sp : orderedShakes) {
            System.out.println(sp);
            total += sp.getPrice();
            System.out.println("---------------------------");
        }
        System.out.println("Total Price: \t" + total);
    }

    public int mainMenu() {
        System.out.println("Press 'o' to open an Order");
        System.out.print("Enter your choice: ");
        char keyPressed = scanner.next().charAt(0);
        switch (keyPressed) {
            case 'o', 'O' -> {
                System.out.println("Order has now opened...");
                return 2;
            }
            case 'e', 'E' -> {
                System.out.println("Shop Closed...");
                return 4;
            }
            default -> System.out.println("Invalid...");
        }
        
        return 1;
    }
    public void prompt(){
        System.out.println("\n\nChoose your Shakes: (press the key to get the estimated shake)\n");
        System.out.println("1. Chocolate Shake");
        System.out.println("2. Coffee Shake");
        System.out.println("3. Strawberry Shake");
        System.out.println("4. Vanilla Shake");
        System.out.println("5. Zero Shake");
        System.out.println("e. Exit Order");
        System.out.print("\nEnter your choice: ");
    }
    public int choiceCase(char keyPressed){
        switch (keyPressed) {
            case '1' -> {
                choice = 1;
                return 3;
            }
            case '2' -> {
                choice = 2;
                return 3;
            }
            case '3' -> {
                choice = 3;
                return 3;
            }
            case '4' -> {
                choice = 4;
                return 3;
            }
            case '5' -> {
                choice = 5;
                return 3;
            }
            case 'e', 'E' -> {
                return closeOrder();
            }
            case 'o', 'O' -> {
                System.out.println("Cannot place new order. Add Shakes to current order");
                return 2;
            }
            default -> {
                System.out.println("Invalid input ...");
                return 2;
            }
        }
    }
    public int orderMenu() {
        lacFree = false;
        candy = false;
        cookies = false;
        prompt(); 
        char keyPressed = scanner.next().charAt(0);  
        return choiceCase(keyPressed);   
    }

    public boolean yesNoChecker(char c){
        switch (c) {
            case 'Y', 'y' -> {
                return true;
            }

        }
        return false;
    }
    public ShakePlan shakeChooser(){
        return switch (choice) {
            case 1 -> director.makeChocolateShake(lacFree, candy, cookies);
            case 2 -> director.makeCoffeeShake(lacFree, candy, cookies);
            case 3 -> director.makeStrawberryShake(lacFree, candy, cookies);
            case 4 -> director.makeVanillaShake(lacFree, candy, cookies);
            default -> director.makeZeroShake(lacFree, candy, cookies);
        };
    }

    public int customizeShake() {
        System.out.println("\n\nCustomize\n");
        System.out.println("Do you want to make your shake lactose-free?(Y/N)");
        char keyPressed = scanner.next().charAt(0);
        if(yesNoChecker(keyPressed)){
            lacFree = true;
            System.out.println("The shake has been made lactose-free");
        }
        System.out.println("Do you want to add Candy?(Y/N)");
        keyPressed = scanner.next().charAt(0);
        if(yesNoChecker(keyPressed)){
            candy = true;
            System.out.println("Candy added");
        }
        System.out.println("Do you want to add Cookies?(Y/N)");
        keyPressed = scanner.next().charAt(0);
        if(yesNoChecker(keyPressed)){
            cookies = true;
            System.out.println("Cookies Added");
        }
        orderedShakes.add(shakeChooser());
        return 2;       
    }

    public int closeOrder() {
        if (orderedShakes.size() < 1) {
            System.out.println("You have to order at least one shake");
            return 2;
        }
        showOrder();
        orderedShakes.clear();
        return 1;
    }
}
