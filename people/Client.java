package people;

import utilities.Observer;

import java.util.ArrayList;

public class Client extends Person implements Observer{
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

    public void setClientBalance(int i){
        this.originalPerson.setBalance(i);
        this.setBalance(i);
    }
    public int getClientBalance(){
        return this.originalPerson.getBalance();
    }

    @Override
    public void update(String message) {
        messages.add(message);
    }
    @Override
    public String getNotifications(){
         return messages.toString();
    }
}
