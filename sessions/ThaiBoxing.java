package sessions;
import gym_management.*;

public class ThaiBoxing extends Session{
    final private int price=100;
    final private int participants =20;
    private int availableSpots;

    public ThaiBoxing(String date, ForumType forumtype, Instructor instructor) {
        super(SessionType.ThaiBoxing, date, forumtype, instructor);
        this.availableSpots = participants;
    }

    @Override
    public void conductSession(){

    }
    @Override
    public int getPrice() {
        return this.price;
    }
    @Override
    public int getParticipants() {
        return this.participants;
    }
    @Override
    public int getAvailableSpots() {
        return this.availableSpots;
    }
    @Override
    public void decreaseAvailableSpots() {
        this.availableSpots--;
    }

}
