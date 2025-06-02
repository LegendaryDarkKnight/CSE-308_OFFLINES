import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class WriteThread implements Runnable {
    private final Thread thread;
    private final Scanner scanner;

    public WriteThread() {
        scanner = new Scanner(System.in);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.print("> ");
                String[] command = scanner.nextLine().trim().split(" ");
                processCommand(command);
            } catch (Exception e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }

    private void processCommand(String[] command) {
        if (command.length == 0 || command[0].isEmpty()) return;

        switch (command[0]) {
            case "exit" -> {
                System.out.println("Exiting client...");
                System.exit(0);
            }
            case "login" -> {
                if (Client.user != null) {
                    System.out.println("Already Logged In");
                    return;
                }
                if (command.length < 2) {
                    System.out.println("Usage: login <username>");
                    return;
                }
                Client.user = new AppUser(command[1]);
                Client.Write(new Wrapper("login", Client.user));
            }
            case "logout" -> {
                if (Client.user == null) {
                    System.out.println("Not Logged In");
                    return;
                }
                Client.Write(new Wrapper("logout", Client.user));
                Client.user.logOut();
                Client.user = null;
            }
            case "S", "U" -> {
                if (Client.user == null) {
                    System.out.println("Not Logged In");
                    return;
                }
                if (command.length < 2) {
                    System.out.println("Usage: " + command[0] + " <argument>");
                    return;
                }
                Client.Write(new Wrapper(command[0], Client.user, command[1]));
            }
            case "V" -> {
                if (Client.user == null) {
                    System.out.println("Not Logged In");
                    return;
                }
                Client.user.view();
            }
            default -> System.out.println("Unknown command.");
        }
    }
}

public class Client implements Serializable {
    public static final String IP_ADDRESS = "127.0.0.1";
    public static final int PORT = 6666;
    public static Socket socket;
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;
    public static User user = null;

    public static void Write(Wrapper wrapper) {
        try {
            oos.writeObject(wrapper);
        } catch (IOException e) {
            System.out.println("Write failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Client Started.");
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
            return;
        }

        new WriteThread();

        while (true) {
            try {
                Object msg = ois.readObject();
                Wrapper wrapper2 = (Wrapper) msg;

                switch (wrapper2.getCommand()) {
                    case "login" -> {
                        if (wrapper2.getUser() == null) {
                            System.out.println("Already logged in on another terminal");
                        } else {
                            Client.user = wrapper2.getUser();
                            Client.user.logIn();
                            System.out.println(wrapper2.getExtra());
                        }
                    }
                    case "notify" -> {
                        Client.user = wrapper2.getUser();
                        Client.user.showNotifications();
                    }
                    default -> {
                        if (wrapper2.getUser() != null) {
                            Client.user = wrapper2.getUser();
                        }
                        System.out.println(wrapper2.getCommand());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Disconnected from server: " + e.getMessage());
                break;
            }
        }
    }
}
