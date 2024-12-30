package sessions;
import people.*;
import gym_management.*;
import exceptions.*;

public class FactorySession {
    public static Session createSession(SessionType sessionType, String date, ForumType forumtype,Instructor instructor){
        switch (sessionType) {
            case MachinePilates:
                return new MachinePilates(date, forumtype, instructor);
            case Pilates:
                return new Pilates(date, forumtype, instructor);
            case Ninja:
                return new Ninja(date, forumtype, instructor);
            case ThaiBoxing:
                return new ThaiBoxing(date, forumtype, instructor);
            default:
                throw new IllegalArgumentException("error: session type does not exist");
        }
    }
}
