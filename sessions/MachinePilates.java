package sessions;
import gym_management.*;

public class MachinePilates extends Session{
    final private int price=80;
    final private int participants =10;
    private int availableSpots;

    public MachinePilates(String date, ForumType forumtype, Instructor instructor) {
        super(SessionType.MachinePilates, date, forumtype, instructor);
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
