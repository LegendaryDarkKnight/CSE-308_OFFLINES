import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in) // Create Scanner once
        ) {
            Order order = new Order(scanner);          // Pass Scanner to Order
            int page = 1;
            while (page != 4) {
                switch (page) {
                    case 1 -> page = order.mainMenu();
                    case 2 -> page = order.orderMenu();
                    case 3 -> page = order.customizeShake();
                    default -> {}
                }
            }
            // Close at the end
        } // Pass Scanner to Order
    }
}
