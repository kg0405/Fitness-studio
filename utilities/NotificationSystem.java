package utilities;

import java.util.ArrayList;
import java.util.List;

import gym_management.Gym;
import sessions.*;
import people.*;

public class NotificationSystem implements User{
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message); // Notify each observer
        }
    }
    public static void notifySession(String message, Session session){
        if (session.getRegisteredClients() != null) {
            ArrayList<Client> clientsRegistered = new ArrayList<>(session.getRegisteredClients());
            for (Client client : clientsRegistered)
                client.update(message);
        }
    }
    public static void notifyDate(String message, String date){
        ArrayList<Session> sessions= Gym.getInstance().getSecretary().getSessions();
        for (Session sess: sessions){
            String[] day=sess.getDate().split(" ");
            if (day[0].equals(date)){
                ArrayList<Client> clientsRegistered = new ArrayList<>(sess.getRegisteredClients());
                for (Client client : clientsRegistered){
                    client.update(message);
                }
            }
        }
    }
    public static void notifyGym(String message){
        ArrayList<Client> clients= Gym.getInstance().getSecretary().getClients();
        for (Client client: clients)
            client.update(message);
    }
    public static void notifyClient(String message, Client client){
        client.update(message);
    }
}
