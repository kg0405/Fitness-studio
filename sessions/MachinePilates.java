package sessions;
import people.*;
import gym_management.*;
import exceptions.*;
public class MachinePilates extends Session{
    final private int price=80;
    final private int participants =10;

    public MachinePilates(String date, ForumType forumtype, Instructor instructor) {
        super(SessionType.MachinePilates, date, forumtype, instructor);
    }

    @Override
    public void conductSession(){

    }
}
