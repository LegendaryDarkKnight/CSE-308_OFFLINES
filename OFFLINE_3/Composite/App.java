package Composite;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int page = 0;
        Directory current = new Root();
        String inpuString;
        String[] commands;

        try (Scanner scanner = new Scanner(System.in) // <-- move Scanner out of loop
        ) {
            while (page == 0) {
                System.out.print(current.buildPath(current) + ">>");
                inpuString = scanner.nextLine();
                commands = inpuString.split(" ");
                switch (commands[0]) {
                    case "cd" -> {
                        if (commands.length != 2) {
                            System.out.println("Invalid number of arguments");
                        } else if (commands[1].equals("~")) {
                            current = current.getToRoot();
                        } else {
                            current = current.changeDirectory(commands[1]);
                        }
                    }
                    case "ls" -> {
                        if (commands.length != 2) {
                            System.out.println("Invalid number of arguments");
                        } else {
                            current.details(commands[1]);
                        }
                    }
                    case "list" -> {
                        if (commands.length != 1) {
                            System.out.println("Invalid number of arguments");
                        } else {
                            current.listing();
                        }
                    }
                    case "delete" -> {
                        switch (commands.length) {
                            case 3 -> {
                                if (commands[1].equals("-r")) {
                                    current.recursiveDelete(commands[2]);
                                } else {
                                    System.out.println("Invalid flag");
                                }
                            }
                            case 2 -> current.delete(commands[1]);
                            default -> System.out.println("Invalid number of arguments");
                        }
                    }
                    case "mkdir" -> {
                        if (commands.length != 2) {
                            System.out.println("Invalid number of arguments");
                        } else {
                            current.makeDir(commands[1]);
                        }
                    }
                    case "touch" -> {
                        if (commands.length != 3) {
                            System.out.println("Invalid number of arguments");
                        } else {
                            current.touch(commands[1], Integer.parseInt(commands[2]));
                        }
                    }
                    case "mkdrive" -> {
                        if (commands.length != 2) {
                            System.out.println("Invalid number of arguments");
                        } else {
                            current.makeDrive(commands[1]);
                        }
                    }
                    case "exit" -> {
                        System.out.println("Good Bye!");
                        page = 1;
                    }
                    default -> {
                        System.out.println("Unknown command: " + commands[0]);
                    }
                }
            }
            // Close it only once at the end
        }
    }

}
