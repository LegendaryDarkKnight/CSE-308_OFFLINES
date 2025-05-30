package Decorator;
public abstract class CrewDecorator implements Crew{
    protected Crew crewMate;
    public CrewDecorator(Crew crewMate){
        this.crewMate = crewMate;
    }
}
