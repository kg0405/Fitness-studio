package gym_management;

import java.util.ArrayList;
import people.*;
import sessions.*;
import exceptions.*;
import utilities.*;

public class Gym {
    private String name;
    private Secretary currentsecretary;
    private int gymBalance=0;
    private static Gym instance;

    public Gym(){

    }

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym(); // Create the instance only if it doesn't exist
        }
        return instance;    }

    public String getName(){
        return name;
    }
    public void setName(String n){
        this.name = n;
    }
    public void setSecretary(Person p, int n){
        if (currentsecretary!=null){
            currentsecretary.revokeAccess();
        }
        this.currentsecretary= new Secretary(p,n);
    }
    public Secretary getSecretary(){
        return currentsecretary;
    }
    public void addToBalance(int i){
        this.gymBalance = gymBalance+1;
    }

    public int getGymBalance() {
        return this.gymBalance;
    }

    public String getClientData() {
        return getClientData();
    }

    public void addToGymBalance(int amount) {
        gymBalance += amount;
    }
    public void subtractFromGymBalance(int amount) {
        gymBalance -= amount;
    }

    public String getClientsData(){
        StringBuilder infoBuilder = new StringBuilder();
        ArrayList<Client> clients = Secretary.getClients();
        for (Client client : clients) {
            infoBuilder.append(getClientData(client)).append("\n");
        }
        return infoBuilder.toString();
    }

    public static String getClientData(Client client) {
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %s | Balance: %s",client.getID(), client.getName(), client.getGender(), client.getBirthdate(), client.getAge(), client.getClientBalance());
    }

    public String getEmployeesData(){
        StringBuilder infoBuilder = new StringBuilder();
        ArrayList<Instructor> instructors = Secretary.getInstructors();
        for (Instructor instructor : instructors) {
            infoBuilder.append(getEmployeeData(instructor)).append(getAllowedClasses(instructor)).append("\n");
        }
        return infoBuilder.toString();
    }

    public String getAllowedClasses(Instructor instructor){
        StringBuilder infoBuilder = new StringBuilder();
        ArrayList<SessionType> sessionTypes = instructor.getAllowedSessions();
        for (SessionType sessionType : sessionTypes) {
            infoBuilder.append(sessionType).append(", ");
        }
        infoBuilder.delete(infoBuilder.length() - 2, infoBuilder.length());
        return infoBuilder.toString();
    }

    public static String getEmployeeData(Instructor instructor) {
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d | Role: Instructor | Salary per Hour: %s | Certified Classes: ",instructor.getID(), instructor.getName(), instructor.getGender(), instructor.getBirthdate(), instructor.getAge(), instructor.getBalance(), instructor.getIncome());
    }

    public String getSessionsData(){
        StringBuilder infoBuilder = new StringBuilder();
        ArrayList<Session> sessions = Secretary.getSessions();
        for (Session session : sessions) {
            infoBuilder.append(getSessionData(session)).append("\n");
        }
        return infoBuilder.delete(infoBuilder.length()-1,infoBuilder.length()).toString();
    }

    public static String getSessionData(Session session) {
        return String.format("Session Type: %s | Date: %s | Forum: %s | Instructor: %s | Participants: %d/%d", session.getSessionType(), session.getDate(), session.getForumType(), session.getInstructor().getName(), session.getTakenSpots(), session.getParticipants());
    }

    @Override
    public String toString() {
        return String.format("Gym Name: %s\nGym Secretary: %s\nGym Balance: %d\n\nClients Data:\n%s\nEmployees Data:\n%s%s\n\nSessions Data:\n%s", getName(), getSecretary(), getGymBalance(), getClientsData(), getEmployeesData(), getSecretary(), getSessionsData());
    }

}
