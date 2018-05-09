package cs151;
import java.lang.reflect.Method;

public class Student {
    int studentId;

    private void privateFunction() {
        System.out.println("In private function");
    }
    public void publicFunction() {
        System.out.println("In publicFunction");      
    }
    public static void main(String[] args) {
        Student s1 = new Student();
        System.out.println("What's my name");
        System.out.println(s1.getClass().getName());

        System.out.println("Can you be simpler?");
        System.out.println(s1.getClass().getSimpleName());

        Class studentClass = s1.getClass();
        Class classClass = studentClass.getClass();

        // Using reflection on the Class class to inspect the Student class metatdata
        // Notice the use of getMethods to get all public methods.
        System.out.println("Inspecting the Student class metdatata");
        for (Method m : classClass.getMethods()) {
            try {
                System.out.println (" Invoking " + m.getName() + "...");
                System.out.println(m.invoke(studentClass));
            
            } catch (Exception e) {
                System.out.println("Caught");
                System.out.println(e);
            }
        }
        
         // Using reflection on the Class Student to inspect student object
         // Notice the use of getDeclaredMethods to get ALL methods.
        System.out.println("Inspecting the Student object");
        for (Method m : studentClass.getDeclaredMethods()) {
            try {
                m.setAccessible(true);
                System.out.println (" Invoking " + m.getName() + " on Student...");
                System.out.println(m.invoke(s1));
            
            } catch (Exception e) {
                System.out.println("Caught");
                e.printStackTrace();
            }
         }
     }
}
