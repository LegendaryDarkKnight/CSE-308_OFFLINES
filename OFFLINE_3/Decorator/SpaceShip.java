package Decorator;
import java.util.Scanner;

public class SpaceShip {
    private Crew crew;
    private String inputString;
    private String []commands;
    private int page = 0;
    public void Menu(){
        while(page == 0){
            try (Scanner scanner = new Scanner(System.in)) {
                inputString = scanner.nextLine();
            }
            commands = inputString.split(" ");
            switch (commands[0]) {
                case "login" -> {
                    if(commands.length<2){
                        System.out.println("Invalid Command");
                        break;
                    }
                    if(crew!=null){
                        System.out.println("Already Logged In");
                        break;
                    }
                    crew = new CrewMate();
                    System.out.println(commands[1].substring(0, 4));
                    if("crew".equalsIgnoreCase(commands[1].substring(0, 4))){        
                        crew.logIn();
                    }
                    else{
                        crew = new ImposterDecorator(crew);
                        crew.logIn();
                    }
                }
                case "repair" -> {
                    if(crew==null){
                        System.out.println("Not Logged In");
                    }
                    else{
                        crew.repair();
                    }
                }
                case "work" -> {
                    if(crew==null){
                        System.out.println("Not Logged In");
                    }
                    else{
                        crew.work();
                    }
                }
                case "logout" -> {
                    if(crew==null){
                        System.out.println("Not Logged In");
                    }
                    else{
                        crew.logOut();
                        crew = null;
                    }
                }
                case "exit" -> {
                    System.out.println("Good Bye!");
                    page = 1;
                }
                default -> {
                }
            }
        }
    }
}
