package Composite;

public class Folder extends Directory{
    public Folder(String name, Directory parent){
        super(name, parent, 0); 
    }
    public int getSize(){
        int temp = 0;
        for(Directory d:childDirectories){
            temp += d.getSize();
        }
        size = temp;
        return temp;
    }
    public Directory changeDirectory(String name){
        for(Directory directory: childDirectories){
            if(directory.getName().equals(name)){
                if(directory.enterIn())
                    return directory;
                else
                    return this;
            }
        }
        return this;
    }
    public boolean enterIn(){
        return true;
    }
    public void ownDetails(){
        System.out.println("Name: "+name);
        System.out.println("Type: Folder");
        System.out.println("Size: "+ getSize() +" kB");
        System.out.println("Directory: "+buildPath(this));
        System.out.println("Component Count: "+childDirectories.size());
        System.out.println("Creation time: "+dateTime);
    }
    public void listing(){
        for(Directory d: childDirectories){
            System.out.println(d.getName()+"\t"+d.getSize()+" kB\t"+d.getCreationTime());
        }
    }
    public boolean selfDelete(){
        return childDirectories.size() == 0;
    }
    public void selfRecurDelete(){
        for(Directory d:childDirectories){
            d.selfRecurDelete();
        }
        childDirectories = null;
        System.out.println("Folder "+name+" deleted");
    }
    public void makeDir(String name){
        for(Directory d: childDirectories){
            if(name.equals(d.getName()))
            {
                System.out.println("Already Found");
                return;
            }
        }
        childDirectories.add(new Folder(name, this));
    }
    public void touch(String name, int size){
        for(Directory d: childDirectories){
            if(name.equals(d.getName()))
            {
                System.out.println("Already Found");
                return;
            }
        }
        childDirectories.add(new File(name, this, size));
        System.out.println("A file has been created of "+size+" kB");
    }
    public void makeDrive(String name){
        System.out.println("You cannot make drive inside a folder");
    }

}
