package Composite;

public class File extends Directory{
    public File(String name, Directory parent, int size){
        super(name, parent, size);
    }
    public int getSize(){
        return size;
    }
    public Directory changeDirectory(String name){
        System.out.println("Invalid Command");
        return this.getParent();
    }
    public boolean enterIn(){
        System.out.println("Cannot change directory to a file");
        return false;
    }
    public void ownDetails(){
        System.out.println("Name: "+name);
        System.out.println("Type: File");
        System.out.println("Size: "+ getSize() +" kB");
        System.out.println("Directory: "+buildPath(this));
        System.out.println("Component Count: "+childDirectories.size());
        System.out.println("Creation time: "+dateTime);
    }
    public void listing(){
        System.out.println("Invalid Command");
    }
    public boolean selfDelete(){
        return true;
    }
    public void selfRecurDelete(){
        System.out.println("Warning File "+name+" deleted");
    }
    public void makeDir(String name){
        System.out.println("Invalid Command");
    }
    public void touch(String name, int size){
        System.out.println("Invalid Command");

    }
    public void makeDrive(String name){
        System.out.println("Invalid Command");
    }
}
