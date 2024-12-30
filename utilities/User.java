package utilities;

public interface User {
    void attach(Observer observer); // Add an observer
    void detach(Observer observer); // Remove an observer
    void notifyObservers(String message);
}
