package Composite;

public class Root extends Directory{
    public Root(){
        super("Root", null, 0);
    }
    @Override
    public int getSize(){
        int temp = 0;
        for(Directory d:childDirectories){
            temp += d.getSize();
        }
        size = temp;
        return temp;
    }
    @Override
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
    @Override
    public boolean enterIn(){
        return true;
    }
    @Override
    public void ownDetails(){
        System.out.println("Name: "+name);
        System.out.println("Type: Root");
        System.out.println("Size: "+ getSize() +" kB");
        System.out.println("Directory: "+buildPath(this));
        System.out.println("Component Count: "+childDirectories.size());
        System.out.println("Creation time: "+dateTime);
    }
    @Override
    public void listing(){
        for(Directory d: childDirectories){
            System.out.println(d.getName()+"\t"+d.getSize()+" kB\t"+d.getCreationTime());
        }
    }
    @Override
    public boolean selfDelete(){
        System.out.println("Cannot delete root");
        return false;
    }
    @Override
    public void selfRecurDelete(){
        System.out.println("Cannot delete root");
    }
    @Override
    public void makeDir(String name){
        System.out.println("Cannot create a folder in root");
    }
    @Override
    public void touch(String name, int size){
        System.out.println("Cannot create a file in root");
    }
    @Override
    public void makeDrive(String name){
        for(Directory d: childDirectories){
            if(name.equals(d.getName()))
            {
                System.out.println("Already Found");
                return;
            }
        }
        childDirectories.add(new Drive(name, this));
    }

}
