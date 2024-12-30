package people;

import java.util.ArrayList;

public class Client extends Person{
    private boolean hasAccess;
    private ArrayList<String> messages;
    final private Person originalPerson;

    public Client(Person p){
        super(p);
        this.hasAccess = true;
        this.messages = new ArrayList<>();
        this.originalPerson = p;
    }
    public void unregister(){
        this.hasAccess=false;
    }
    public void message(String str){
        this.messages.add(str);
    }
    public boolean isClient(){
        return hasAccess;
    }

    public ArrayList<String> getNotifications() {
        return messages;
    }
    public void setClientBalance(int i){
        this.originalPerson.setBalance(i);
        this.setBalance(i);
    }
    public int getClientBalance(){
        return this.originalPerson.getBalance();
    }
}
