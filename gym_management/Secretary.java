package gym_management;

import java.util.ArrayList;
import people.*;
import sessions.*;
import exceptions.*;
import utilities.*;

public class Secretary extends Person{
    private boolean hasAccess;
    private static ArrayList<Client> clients=new ArrayList<>();
    private static ArrayList<Instructor> instructors=new ArrayList<>();
    private static ArrayList<Session> sessions=new ArrayList<>();
    private static ArrayList<String> actionHistory=new ArrayList<>();
    private int salary;
    public Time currentTime = new Time();
    public Secretary(Person person, int salary){
        super(person);
        this.hasAccess = true;
        this.salary=salary;
        actionHistory.add("A new secretary has started working at the gym: "+person.getName());
    }
    public void revokeAccess(){
        this.hasAccess=false;
    }

    public boolean isClientRegistered(Person p2) {
        for (Client client : clients) {
            if (client.getID() == (p2.getID())) {
                return true;
            }
        }
        return false;
    }

    public Client registerClient(Person p2) throws InvalidAgeException, DuplicateClientException {
        if(!hasAccess)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        if (p2.getAge()<18)
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");

        if (isClientRegistered(p2))
            throw new DuplicateClientException("Error: The client is already registered");

        Client newClient = new Client(p2);
        clients.add(newClient);
        actionHistory.add("Registered new client: "+p2.getName());
        return newClient;
    }

    public void unregisterClient(Client c2) throws ClientNotRegisteredException {
        if(!hasAccess)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        if (!isClientRegistered(c2))
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        c2.unregister();
        clients.remove(c2);
        actionHistory.add("Unregistered client: "+c2.getName());
    }

    public Instructor hireInstructor(Person p4, int i, ArrayList<SessionType> objects) {
        if(!hasAccess)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        Instructor instructor=new Instructor(p4,i,objects);
        instructors.add(instructor);
        actionHistory.add("Hired new instructor: "+p4.getName()+" with salary per hour: "+i);
        return(instructor);
    }
    public Session addSession(SessionType type, String date, ForumType gender, Instructor instructor) throws InstructorNotQualifiedException {
        if(!hasAccess)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        ArrayList<SessionType> instructorType=instructor.getAllowedSessions();
        if (!instructorType.contains(type))
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        Session sess = FactorySession.createSession(type,date,gender,instructor);
        sessions.add(sess);
        String[] splitDate = sess.getSplitDate();
        actionHistory.add(String.format("Created new session: %s on %s-%s-%sT%s with instructor: %s", type, splitDate[2], splitDate[1], splitDate[0], splitDate[3], instructor.getName()));
        return (sess);
    }

    public void registerClientToLesson(Client c1, Session s1) throws ClientNotRegisteredException, DuplicateClientException {
        if(!hasAccess)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        if (!c1.isClient())
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");

        if (s1.getAvailableSpots() > 0) {
            s1.register(c1);
        }
        else {
            actionHistory.add("Failed registration: No available spots for session");
        }
    }

    public void notify(Session sess, String str) {
        if(!hasAccess)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        if (sess.getRegisteredClients() != null) {
            ArrayList<Client> clientsRegistered = new ArrayList<>(sess.getRegisteredClients());
            for (Client client : clientsRegistered)
                client.message(str);
        }
        String[] splitDate = sess.getSplitDate();
        actionHistory.add(String.format("A message was sent to everyone registered for session %s on %s-%s-%sT%s : %s", sess.getSessionType(), splitDate[2], splitDate[1], splitDate[0], splitDate[3], str));
    }
    public void notify(String date,String str){
        if(!hasAccess)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        for (Session sess: sessions){
            String[] day=sess.getDate().split(" ");
            if (day[0].equals(date)){
                ArrayList<Client> clientsRegistered = new ArrayList<>(sess.getRegisteredClients());
                for (Client client : clientsRegistered){
                    client.message(str);
                }
            }
        }
        String[] splitDate = getSplitDate(date);
        actionHistory.add(String.format("A message was sent to everyone registered for a session on %s-%s-%s : %s", splitDate[2], splitDate[1], splitDate[0], str));
    }
    public void notify(String str){
        if(!hasAccess)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        for (Client client: clients)
            client.message(str);
        actionHistory.add("A message was sent to all gym clients: "+str);
    }
    public void notify(Client c, String str){
        if(!hasAccess)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        c.message(str);
        actionHistory.add("A message was sent to client "+c.getName()+" : "+str);
    }
    public void printActions() {
        if(!hasAccess)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        for (String action: actionHistory){
            System.out.println(action);
        }
    }

    public void paySalaries() {
        if(!hasAccess)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        for (Instructor inst: instructors){
            int i=inst.getInstructorBalance()+inst.getPay();
            inst.setInstructorBalance(i);
            Gym.getInstance().addToGymBalance(-inst.getPay());
            inst.setPay(0);
        }
        pay();
        Gym.getInstance().addToGymBalance(-salary);
        actionHistory.add("Salaries have been paid to all employees");
    }
    public void addAction(String str){
        actionHistory.add(str);
    }

    public static ArrayList<Client> getClients(){
        return clients;
    }

    public static ArrayList<Instructor> getInstructors(){
        return instructors;
    }

    public static ArrayList<Session> getSessions(){
        return sessions;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %s | Balance: %s | Role: Secretary | Salary per Month: %s",getID(), getName(), getGender(), getBirthdate(), getAge(), getBalance(), this.salary);
    }

    public String[] getSplitDate(String date){
        return date.split("[- ]");
    }
    public void pay(){
        super.setBalance(this.getBalance()+salary);
    }

}
