import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Stock implements Serializable{
    private String name;
    private int count;
    private double price;
    private static Map<Stock,List<User>> userMap;

    public Stock(String name, int count, double price){
        if(userMap == null){
            userMap = new HashMap<>();
        }
        this.name = name;
        this.count = count;
        this.price = price;
        userMap.put(this, new ArrayList<>());
    }
    
    public String getName(){return name;}
    public int getCount(){return count;}
    public double getPrice(){return price;}
    
    public void setName(String name){
        this.name = name;
    }
    public void setCount(int count){
        this.count = count;
        for(User u:userMap.get(this)){
            u.notify("The stock named "+name+" count has changed to "+ count);
        }
    }
    public void setPrice(double price){
        this.price = price;
        for(User u:userMap.get(this)){
            System.out.println(u.getName()+" has been notified");
            u.notify("The stock named "+name+" price has changed to "+ price);
        }
    }

    public void addSubcription(User user){
        userMap.get(this).add(user);
    }
    public void removeSubcription(User user){
        userMap.get(this).remove(user);
    }

    public void showDetails(){
        System.out.println("Stock Name: "+name+" ,count: "+count+" ,price: "+price);
    }

    public String getDetails(){
        return "Stock Name: "+name+" ,count: "+count+" ,price: "+price +"\n";
    }
    public List<User> getUsers(){
        return userMap.get(this);
    }
}
