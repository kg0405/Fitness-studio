package people;

import java.util.ArrayList;
import utilities.*;

public class Person {
    private static int globalID = 1111;
    private int ID;
    private String name;
    private int balance;
    private String birthdate;
    private Gender gender;
    private int age;
    private static ArrayList<Person> people = new ArrayList<>();

    public Person(String name, int balance,Gender gender,String birthdate){
        this.name=name;
        this.balance=balance;
        this.gender=gender;
        this.birthdate=birthdate;
        this.ID = globalID++;
    }

    public Person(Person person){
        this.age = person.age;
        this.name=person.name;
        this.balance=person.balance;
        this.gender=person.gender;
        this.birthdate=person.birthdate;
        this.ID = person.ID;
        people.add(person);
    }

    public int getAge(){
        Time currenttime=new Time();
        String[] parts=birthdate.split("-");
        this.age=currenttime.getYear()-(Integer.parseInt(parts[2]));
        if (currenttime.getMonth()<(Integer.parseInt(parts[1])))
            this.age--;
        if (currenttime.getMonth()==(Integer.parseInt(parts[1]))){
            if (currenttime.getDay()<(Integer.parseInt(parts[0])))
                this.age--;
        }
        return this.age;
    }
    public String getName() {
        return this.name;
    }
    public int getBalance(){
        return this.balance;
    }
    public Gender getGender(){
        return this.gender;
    }
    public String getBirthdate(){
        return this.birthdate;
    }
    public int getID(){
        return this.ID;
    }
    public void setBalance(int i){
        this.balance=i;
    }
    public Person getPersonOfID(int id) {
        for (Person person : people) {
            if (person.ID == id)
                return person;
        }
        return null;
    }
}
