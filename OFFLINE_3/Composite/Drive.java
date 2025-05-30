package Composite;

public class Drive extends Directory{
    public Drive(String name, Directory parent){
        super(name+":\\", parent, 0);
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
        System.out.println("Type: Drive");
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
        return childDirectories.isEmpty();
    }
    @Override
    public void selfRecurDelete(){
        for(Directory d:childDirectories){
            d.selfRecurDelete();
        }
        System.out.println("Drive "+name+" deleted");
    }
    @Override
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
    @Override
    public void touch(String name, int size){
        for(Directory d: childDirectories){
            if(name.equals(d.getName()))
            {
                System.out.println("Already Found");
                return;
            }
        }
        childDirectories.add(new File(name, this, size));
    }
    @Override
    public void makeDrive(String name){
        System.out.println("You cannot make drive inside a drive");
    }
}
