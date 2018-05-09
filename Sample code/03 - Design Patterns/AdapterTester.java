import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import junit.framework.TestCase;
import org.junit.Test;



public class AdapterTester extends TestCase {
    @Test
    public void testThis() {
        System.out.println("No results1");
        Class cls = AdapterTestCase.class;
        JUnitCore junit = new JUnitCore();
        //Result result = junit.run(cls);
        junit.run(cls);
        //System.out.println("Another print");

    }
    public static void main(String[] args) {
        AdapterTester at = new AdapterTester();
        at.testThis();
        System.out.println("No results2");
        /*if (result == null) 
            System.out.println("No results");
        for (Failure failure : result.getFailures()) {
            System.out.println("No results3");
         System.out.println(failure.toString());
        }
        System.out.println("No results4");
        System.out.println(result.wasSuccessful());*/
    }
}