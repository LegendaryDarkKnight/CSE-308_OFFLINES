import java.util.Map;

public class Administrator {
    Map<String, Stock> stockMap;
    public Administrator(Map<String, Stock> stockMap){
        this.stockMap = stockMap;
    }
    public void increasePrice(String name, double price){
        Stock s = stockMap.get(name);
        if(s == null){
            System.out.println("Stock doesn't exist");
            return;
        }
        s.setPrice(s.getPrice()+price);
        System.out.println(s.getName()+"'s price increased to "+s.getPrice());
    }
    public void decreasePrice(String name, double price){
        Stock s = stockMap.get(name);
        if(s == null){
            System.out.println("Stock doesn't exist");
            return;
        }
        s.setPrice(s.getPrice() - price);
        System.out.println(s.getName()+"'s price decreased to "+s.getPrice());
    }
    public void changeInCount(String name, int count){
        Stock s = stockMap.get(name);
        if(s == null){
            System.out.println("Stock doesn't exist");
            return;
        }
        s.setCount(count);
        System.out.println(s.getName()+"'s count changed to "+s.getCount());
    }
}
