package sessions;
import people.*;
import gym_management.*;
import exceptions.*;

public class Ninja extends Session{
    final private int price=150;
    final private int participants =5;
    private int availableSpots;
    public Ninja(String date, ForumType forumtype, Instructor instructor) {
        super(SessionType.Ninja, date, forumtype, instructor);
        this.availableSpots = participants;
    }

    @Override
    public void conductSession(){

    }
    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public int getParticipants() {
        return participants;
    }
    @Override
    public int getAvailableSpots() {
        return availableSpots;
    }
    @Override
    public void decreaseAvailableSpots() {
        this.availableSpots--;
    }

}
