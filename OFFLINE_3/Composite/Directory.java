package Composite;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class Directory {
    protected String name;
    protected int size;
    protected ArrayList<Directory> childDirectories;
    protected Directory parent;
    protected LocalDateTime creationTime;

    protected String dateTime ;

    public Directory(String name, Directory parent, int size){
        this.name = name;
        this.parent = parent;
        this.size = size;
        childDirectories = new ArrayList<>();
        creationTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy h:mm a", Locale.ENGLISH);
        dateTime = creationTime.format(formatter);
    }
    
    public Directory getToRoot(){
        Directory temp = this;
        while (temp.getParent()!=null) {
            temp = temp.getParent();
        }
        return temp;
    }
    public String getName(){
        return name;
    }
    public String getCreationTime(){
        return dateTime;
    }
    public Directory getParent(){
        return parent;
    }
    public String buildPath(Directory d){
        if(d.getParent()==null){
            return "";
        }
        return buildPath(d.getParent())+"\\"+d.getName();
    }
    public void delete(String name){
        ArrayList<Directory> temp = new ArrayList<>();
        for(Directory d:childDirectories){
            if(d.getName().equals(name) && d.selfDelete()){
                continue;               
            }
            temp.add(d);
        }
        childDirectories = temp;
    }
    public void recursiveDelete(String name){
        ArrayList<Directory> temp = new ArrayList<>();
        for(Directory d:childDirectories){
            if(d.getName().equals(name)){
                d.selfRecurDelete();
                continue;               
            }
            temp.add(d);
        }
        childDirectories = temp;
    }
    public void details(String name){
        for(Directory d: childDirectories){
            if(d.getName().equals(name)){
                d.ownDetails();
                break;
            }
        }
    }
    
    public abstract int getSize();
    public abstract Directory changeDirectory(String name);
    public abstract boolean enterIn();
    public abstract void ownDetails();
    public abstract void listing(); 
    public abstract boolean selfDelete();
    public abstract void selfRecurDelete();
    public abstract void makeDir(String name);
    public abstract void touch(String name, int size);
    public abstract void makeDrive(String name);

}
