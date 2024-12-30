package gym_management;

import java.util.ArrayList;
import people.*;
import sessions.*;
import exceptions.*;
import utilities.*;

public class Instructor extends Person {
    private boolean hasaccess;
    private ArrayList<SessionType> allowedSessions;
    final int income;
    private int pay = 0;
    final private Person originalPerson;
    public Instructor(Person p,int i, ArrayList<SessionType> arr){
        super(p);
        hasaccess=true;
        this.allowedSessions = new ArrayList<>(arr);
        this.income=i;
        this.originalPerson = p;
    }
    public ArrayList<SessionType> getAllowedSessions(){
        return this.allowedSessions;
    }
    public void addPay(){
        this.pay=pay+income;
    }
    public int getPay(){
        return pay;
    }
    public void setPay(int i){
        this.pay=i;
    }
    public int getIncome(){
        return income;
    }
    public void setInstructorBalance(int i){
        this.originalPerson.setBalance(i);
        this.setBalance(i);
    }
    public int getInstructorBalance(){
        return this.originalPerson.getBalance();
    }
}
