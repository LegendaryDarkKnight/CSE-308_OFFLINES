package Decorator;
public class ImposterDecorator extends CrewDecorator{
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    public ImposterDecorator(Crew crewMate){
        super(crewMate);
    }
    @Override
    public void logIn(){
        crewMate.logIn();
        System.out.println(RED + "We won't tell anyone; you are an imposter" + RESET);
     }
     @Override
     public void repair(){
        crewMate.repair();
        System.out.println(RED + "Damaging the spaceship.\r" + RESET);

     }
     @Override
     public void work(){
        crewMate.work();
        System.out.println(RED + "Trying to kill a crewmate.\nSuccessfully killed a crewmate.\n" + RESET);
     }
     @Override
     public void logOut(){
        crewMate.logOut();
        System.out.println(RED + "See you again Comrade Imposter." + RESET);
     }
}
