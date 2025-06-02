import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class ThreadingMain implements Runnable {
    Thread t;
    Socket socket;
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;

    ThreadingMain(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        this.socket = socket;
        this.ois = ois;
        this.oos = oos;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object msg = ois.readObject();
                Wrapper wrapper = (Wrapper) msg;
                switch (wrapper.getCommand()) {
                    case "login" -> {
                        if (Server.userMap.containsKey(wrapper.getUser().getName())) {
                            User user = Server.userMap.get(wrapper.getUser().getName());
                            if (Server.inMap.containsKey(user)) {
                                oos.writeObject(new Wrapper("login", null, Server.getList()));
                            } else {
                                oos.writeObject(new Wrapper("login",
                                        new AppUser(user.getName(), user.getNotifications(), user.getStocks()),
                                        Server.getList()));
                                Server.inMap.put(user, ois);
                                Server.outMap.put(user, oos);
                            }
                        } else {
                            User newUser = new AppUser(wrapper.getUser().getName());
                            Server.userMap.put(wrapper.getUser().getName(), newUser);
                            oos.writeObject(new Wrapper("login", newUser, Server.getList()));
                            Server.inMap.put(newUser, ois);
                            Server.outMap.put(newUser, oos);
                        }
                    }
                    case "logout" -> {
                        User user = Server.userMap.get(wrapper.getUser().getName());
                        Server.inMap.remove(user);
                        Server.outMap.remove(user);
                    }
                    case "S" -> {
                        User user;
                        if (Server.stockMap.containsKey(wrapper.getExtra())) {
                            user = Server.userMap.get(wrapper.getUser().getName());
                            user.subscribe(Server.stockMap.get(wrapper.getExtra()));
                            oos.writeObject(new Wrapper("Subsciption Added",
                                    new AppUser(user.getName(), user.getNotifications(), user.getStocks())));
                        } else {
                            user = Server.userMap.get(wrapper.getUser().getName());
                            user.subscribe(Server.stockMap.get(wrapper.getExtra()));
                            oos.writeObject(new Wrapper("Invalid Stock Name",
                                    new AppUser(user.getName(), user.getNotifications(), user.getStocks())));
                        }
                    }
                    case "U" -> {
                        User user;
                        if (Server.stockMap.containsKey(wrapper.getExtra())) {
                            user = Server.userMap.get(wrapper.getUser().getName());
                            user.unsubscribe(Server.stockMap.get(wrapper.getExtra()));
                            user.view();
                            oos.writeObject(new Wrapper("Subsciption Removed",
                                    new AppUser(user.getName(), user.getNotifications(), user.getStocks())));
                        } else {
                            user = Server.userMap.get(wrapper.getUser().getName());
                            user.subscribe(Server.stockMap.get(wrapper.getExtra()));
                            user.view();
                            oos.writeObject(new Wrapper("Invalid Stock Name",
                                    new AppUser(user.getName(), user.getNotifications(), user.getStocks())));
                        }
                    }
                    default -> {
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
                break;
            }
        }
    }
}

class AdminThread implements Runnable {
    Thread t;
    Administrator admin;
    Scanner scanner; // Moved scanner as instance variable

    public AdminThread() {
        admin = new Administrator(Server.stockMap);
        scanner = new Scanner(System.in); // Initialize scanner once
        t = new Thread(this);
        t.start();
    }

    public void sendNotification(List<User> users) {
        for (User u : users) {
            System.out.println(u.getName() + " has been notified");
            u.view();
            if (Server.outMap.containsKey(u)) {
                try {
                    Server.outMap.get(u).writeObject(
                            new Wrapper("notify", new AppUser(u.getName(), u.getNotifications(), u.getStocks())));
                    u.clearNotifications();
                } catch (IOException e) {
                   System.out.println(e);
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String[] input = scanner.nextLine().split(" ", 6);
                if(input[0].equals("exit")){
                    scanner.close(); // Close scanner before exit
                    System.exit(0);
                }

                if (input.length < 3) {
                    System.out.println("Invalid");
                } else {
                    switch (input[0]) {
                        case "I" -> {
                            admin.increasePrice(input[1], Double.parseDouble(input[2]));
                            if (Server.stockMap.containsKey(input[1])) {
                                sendNotification(Server.stockMap.get(input[1]).getUsers());
                            }
                        }
                        case "D" -> {
                            admin.decreasePrice(input[1], Double.parseDouble(input[2]));
                            if (Server.stockMap.containsKey(input[1])) {
                                sendNotification(Server.stockMap.get(input[1]).getUsers());
                            }
                        }
                        case "C" -> {
                            admin.changeInCount(input[1], Integer.parseInt(input[2]));
                            if (Server.stockMap.containsKey(input[1])) {
                                sendNotification(Server.stockMap.get(input[1]).getUsers());
                            }
                        }
                        case "show" -> {
                            for (Map.Entry<String, Stock> entry : Server.stockMap.entrySet())
                                entry.getValue().showDetails();
                        }
                        default -> System.out.println("Invalid Command");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
    }
}

public class Server {
    static Map<String, Stock> stockMap = new HashMap<>();
    static Map<String, User> userMap = new HashMap<>();
    static Map<User, ObjectInputStream> inMap = new HashMap<>();
    static Map<User, ObjectOutputStream> outMap = new HashMap<>();

    public static String getList() {
        String s = "";
        for (Map.Entry<String, Stock> entry : Server.stockMap.entrySet())
            s += entry.getValue().getDetails();
        return s;
    }

    private static void loadStocksFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String name = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    double price = Double.parseDouble(parts[2]);
                    stockMap.put(name, new Stock(name, count, price));
                    stockMap.get(name).showDetails();

                }
            }
        } catch (IOException | NumberFormatException e) {
        }
    }

    public static void main(String[] args) throws IOException {
        loadStocksFromFile("init_stocks.txt");
        new AdminThread();
        try (ServerSocket serverSocket = new ServerSocket(6666)) {
            System.out.println("Server Started");
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                new ThreadingMain(socket, new ObjectInputStream(socket.getInputStream()),
                        new ObjectOutputStream(socket.getOutputStream()));
            }
        }
    }
}