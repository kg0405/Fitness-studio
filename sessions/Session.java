package sessions;

import java.util.ArrayList;
import people.*;
import gym_management.*;
import exceptions.*;
import utilities.*;

public abstract class Session {
    private ForumType forumType;
    final String date;
    private int participants;
    private int availableSpots;
    private int price;
    private ArrayList<Client> clients;
    private SessionType sessionType;
    private Instructor instructor;

    public Session(SessionType sessiontype, String date, ForumType forumtype,Instructor instructor){
        this.forumType=forumtype;
        this.date=date;
        this.clients = new ArrayList<>();
        this.sessionType=sessiontype;
        this.instructor=instructor;
        instructor.addPay();
    }

    void conductSession(){}

    public boolean isClientRegistered(Person p2) {
        for (Client client : clients) {
            if (client.equals(p2)) {
                return true;
            }
        }
        return false;
    }

    public void register(Client c) throws DuplicateClientException {
        boolean ableToRegister = true;
        if (isClientRegistered(c))
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        if (this.sessionPassed()){
            Gym.getInstance().getSecretary().addAction("Failed registration: Session is not in the future");
            ableToRegister = false;
        }

        if ((this.forumType==ForumType.Female&&c.getGender()!=Gender.Female)||(this.forumType==ForumType.Male&&c.getGender()!=Gender.Male)){
            Gym.getInstance().getSecretary().addAction("Failed registration: Client's gender doesn't match the session's gender requirements");
            ableToRegister = false;
        }
        if (this.forumType==ForumType.Seniors&&c.getAge()<65){
            Gym.getInstance().getSecretary().addAction("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
            ableToRegister = false;
        }
        if (c.getBalance()<this.price){
            Gym.getInstance().getSecretary().addAction("Failed registration: Client doesn't have enough balance");
            ableToRegister = false;
        }
        if (availableSpots<=0){
            Gym.getInstance().getSecretary().addAction("Failed registration: No available spots for session");
            ableToRegister = false;
        }
        if (ableToRegister){
            clients.add(c);
            availableSpots--;
            c.setClientBalance(c.getClientBalance()-this.getPrice());
            Gym.getInstance().addToGymBalance(this.getPrice());
            String[] splitDate = this.getSplitDate();
            Gym.getInstance().getSecretary().addAction(String.format("Registered client: %s to session: %s on %s-%s-%sT%s for price: %s", c.getName(), this.getSessionType(), splitDate[2], splitDate[1], splitDate[0], splitDate[3], this.getPrice()));
        }
    }
    public SessionType getSessionType(){return sessionType;}
    public int getPrice(){return price;}
    public int getAvailableSpots(){
        return availableSpots;
    }
    public int getTakenSpots(){
        return clients.size();
    }
    public int getParticipants(){
        return participants;
    }
    public ArrayList<Client> getRegisteredClients(){
        return clients;
    }
    public ForumType getForumType(){
        return forumType;
    }
    public String getDate(){
        return date;
    }
    public String[] getSplitDate(){
        return date.split("[- ]");
    }

    public Instructor getInstructor(){
        return instructor;
    }

    public boolean sessionPassed(){
        Time sessionTime = new Time(this.date);
        Time currentTime = new Time();
        return currentTime.isAfter(sessionTime);
    }
}
