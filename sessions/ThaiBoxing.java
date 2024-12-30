package sessions;
import people.*;
import gym_management.*;
import exceptions.*;

public class ThaiBoxing extends Session{
    final private int price=100;
    final private int participants =20;

    @Override
    public int getPrice() {
        return price;
    }

    public ThaiBoxing(String date, ForumType forumtype, Instructor instructor) {
        super(SessionType.ThaiBoxing, date, forumtype, instructor);
    }

    @Override
    public void conductSession(){

    }
}
