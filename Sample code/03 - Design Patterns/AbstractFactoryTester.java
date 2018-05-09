import junit.framework.TestCase;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;
import java.lang.reflect.*;
import java.io.*;

public class AbstractFactoryTester {
    AbstractFactory af;
    
    @Test
    public void testProductA1() {
        af = new ConcreteFactory1();
        AbstractProductA apa = af.createProductA();
        assertTrue("ProductA".equals(apa.getName()));
        assertTrue("Platform1".equals(apa.getPlatform()));
    }

    @Test
    public void testProductA2() {
        af = new ConcreteFactory2();
        AbstractProductA apa = af.createProductA();
        assertTrue("ProductA".equals(apa.getName()));
        assertTrue("Platform2".equals(apa.getPlatform()));
    }

    @Test
    public void testProductB1() {
        af = new ConcreteFactory1();
        AbstractProductB apb = af.createProductB();
        assertTrue("ProductB".equals(apb.getTitle()));
        assertTrue("Platform1".equals(apb.getPlatform()));
    }

    @Test
    public void testProductB2() {
        af = new ConcreteFactory2();
        AbstractProductB apb = af.createProductB();
        assertTrue("ProductB".equals(apb.getTitle()));
        assertTrue("Platform2".equals(apb.getPlatform()));
    }
}