package sessions;
import gym_management.*;

public class Pilates extends  Session{
    final private int price=60;
    final private int participants =30;
    private int availableSpots;
    public Pilates(String date, ForumType forumtype, Instructor instructor) {
        super(SessionType.Pilates, date, forumtype, instructor);
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
