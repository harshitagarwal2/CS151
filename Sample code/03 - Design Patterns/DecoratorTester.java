import org.junit.Test;
import static org.junit.Assert.*;

public class DecoratorTester {
    @Test
    public void testDecorator() {
    try {
        Decorator dec = new LabelDecorator(new Circle());
        assertTrue(dec.draw().contains("label"));
        assertTrue(dec.draw().contains("Circle"));

        Circle circle = new Circle();
        assertFalse(circle.draw().contains("label"));
        assertTrue(circle.draw().contains("Circle"));

    } catch (Exception e) {
        e.printStackTrace();
    }
 }
}