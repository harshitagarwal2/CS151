package cs151;
import java.lang.reflect.*;

public class Reflection {
    
    public static void main(String[] args) {
        
        Student s1 = new Student();

        /*
        System.out.println("What's my name");
        System.out.println(studentClass.getName());
        System.out.println("Can you be simpler?");
        System.out.println(s1.getClass().getSimpleName());
        System.out.println(studentClass.getPackage());*/
        

        
        Object s2 = null;
        try {
            s2 = Class.forName("cs151.Student").newInstance();
            System.out.println("What's my name");
            System.out.println(s2.getClass().getName());

            System.out.println("Can you be simpler?");
            System.out.println(s2.getClass().getSimpleName());
        } catch (Exception e) {
            System.out.println(e);
        }


        Class studentClass = s2.getClass();
         // Using reflection on the Class Student to inspect student object
         // Notice the use of getDeclaredMethods to get ALL methods.
        /*System.out.println("Inspecting the Student object");
        for (Method m : studentClass.getDeclaredMethods()) {
            try {
                m.setAccessible(true);
                System.out.println (" Invoking " + m.getName() + " on Student...");
                System.out.println(m.invoke(s2));
            
            } catch (Exception e) {
                System.out.println("Caught");
                System.out.println(e);
            }
         }
         for (Field f : studentClass.getDeclaredFields()) {
            try {
                f.setAccessible(true);
                System.out.println(" Invoking " + f.getName() + " on Student...");
                System.out.println(" Setting the value");
                f.setInt(s2, 10);
                //Verify that it's set.
                System.out.println(((Student)s2).getStudentId());
                
            
            } catch (Exception e) {
                System.out.println("Caught");
            }
         }
         try {
             Field f = studentClass.getDeclaredField("studentId");
             f.setAccessible(true);
             f.setInt(s2, 15);
         } catch (Exception e) {
            System.out.println(e);
         }*/

        /*Uncomment this section to understand how to use reflection on reflection.
         // Using reflection on the Class class to inspect the Student class metatdata
         // Notice the use of getMethods to get all public methods.*/
      System.out.println("Inspecting the Student class metdatata");

        Class classClass = studentClass.getClass();
         for (Method m : classClass.getMethods()) {
            try {
                System.out.println (" Invoking " + m.getName() + "...");
                System.out.println(m.invoke(studentClass));
            
            } catch (Exception e) {
                System.out.println("Caught");
                System.out.println(e);
            }
         } 
     }














}
