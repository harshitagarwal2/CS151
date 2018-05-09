//SingletonTester.java

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;

public class SingletonTester {
 @Test
 public void testSingleton() {
    try {
        String className = System.getProperty("className");
        assertNotNull("Please provide a class name to check for Singleton", className);
        Class <?> classObj = Class.forName(className);
        assertTrue("Found at least 1 public constructor", 
            classObj.getConstructors().length == 0);
    } catch (Exception e) {
        e.printStackTrace();
    }
 }

}