package Decorator;
public class CrewMate implements Crew{
    private final String RESET = "\u001B[0m";
    private final String GREEN = "\u001B[32m";

    @Override
    public void logIn(){
       System.out.println(GREEN+"Welcome Crewmate!"+RESET);
    }
    @Override
    public void repair(){
        System.out.println(GREEN+"Repairing the spaceship."+RESET);
    }
    @Override
    public void work(){
        System.out.println(GREEN+"Doing research."+RESET);
    }
    @Override
    public void logOut(){
        System.out.println(GREEN+"Bye Bye crewmate."+RESET);
    }
}
