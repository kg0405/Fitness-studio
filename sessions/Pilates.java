package sessions;
import gym_management.*;

public class Pilates extends  Session{
    final private int price=60;
    final private int participants =30;
    public Pilates(String date, ForumType forumtype, Instructor instructor) {
        super(SessionType.Pilates, date, forumtype, instructor);
    }

    @Override
    public void conductSession(){

    }
}
