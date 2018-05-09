import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;

public class FizzBuzzTest extends TestCase {
    @Test
    public void testFizzBuzz() {
        assertTrue("Wrong output", "FizzBuzz".equals(FizzBuzz.foo(16)));
        assertTrue("Fizz".equals(FizzBuzz.foo(9)));
        assertTrue("Buzz".equals(FizzBuzz.foo(10)));
        assertTrue("".equals(FizzBuzz.foo(8)));
    }
}