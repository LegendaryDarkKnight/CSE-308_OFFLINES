import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface User {
    public void logIn();
    public String getName();
    public void notify(String msg);
    public void showNotifications();
    public void subscribe(Stock s);
    public void unsubscribe(Stock s);
    public void view();
    public void logOut();
    public List<String> getNotifications();
    public List<Stock> getStocks();
    public void clearNotifications();
}

class AppUser implements User, Serializable{
    private final String name;
    private final List<String> notifications;
    private final List<Stock> stocks;
    public AppUser(String name){
        this.name = name;
        notifications = new ArrayList<>();
        stocks = new ArrayList<>();
    }
    public AppUser(String name,List<String> notifications,List<Stock> stocks){
        this.name = name;
        this.notifications = new ArrayList<>(notifications);
        this.stocks = new ArrayList<>();
        for(Stock s: stocks){
            this.stocks.add(new Stock(s.getName(), s.getCount(), s.getPrice()));
        }
    }
    @Override
    public List<String> getNotifications(){
        return notifications;
    }
    @Override
    public List<Stock> getStocks(){
        return stocks;
    }
    @Override
    public void clearNotifications(){
        notifications.clear();
    }
    @Override
    public void logIn(){
        System.out.println("Welcome "+name);
        showNotifications();
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public void notify(String msg){
       notifications.add(msg);
    }
    @Override
    public void showNotifications(){
        if(notifications.isEmpty()){
            return;
        }
        System.out.println("Notification List: ");
        for(String n:notifications){
            System.out.println(n);
        }
        clearNotifications();

    }
    @Override
    public void subscribe(Stock s){
        stocks.add(s);
        s.addSubcription(this);
    }
    @Override
    public void unsubscribe(Stock s){
        stocks.remove(s);
        s.removeSubcription(this);
    }
    @Override
    public void view(){
        for(Stock s: stocks){
            s.showDetails();
        }
    }
    @Override
    public void logOut(){
        System.out.println("GoodBye "+name);
    }
}