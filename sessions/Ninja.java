package sessions;
import people.*;
import gym_management.*;
import exceptions.*;

public class Ninja extends Session{
    final private int price=150;
    final private int participants =5;
    public Ninja(String date, ForumType forumtype, Instructor instructor) {
        super(SessionType.Ninja, date, forumtype, instructor);
    }

    @Override
    public void conductSession(){

    }
}
