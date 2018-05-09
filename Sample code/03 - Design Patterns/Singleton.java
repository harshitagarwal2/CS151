// Singleton class
public class Singleton {
    private static Singleton instance;
    public Singleton() {
        
    }
    public Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();

        return instance;

    }
}