import java.io.Serializable;

public class Wrapper implements Serializable{
    private final User user;
    private final String command;
    private final String extra;
    public Wrapper(String command, User user){
        this.command = command;
        this.user = user;
        this.extra = null;
    }
    public Wrapper(String command, User user, String extra){
        this.command = command;
        this.user = user;
        this.extra = extra;
    }
    public User getUser(){ return user;}
    public String getCommand(){ return command;}
    public String getExtra(){ return extra;}
}
