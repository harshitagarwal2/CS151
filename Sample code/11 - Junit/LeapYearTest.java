import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;

public class LeapYearTest extends TestCase {
    @Test
    public void testNormalLeap() {
        LeapYear ly = new LeapYear(2016);
        assertTrue(ly.isLeap());
    }

    @Test
    public void testNonLeap() {
        LeapYear ly = new LeapYear(2015);
        assertFalse("2015 is not a leap year", ly.isLeap());
    }

    @Test
    public void testCentury() {
        LeapYear ly = new LeapYear(1900);
        assertFalse("1900 is not a leap year", ly.isLeap());
    }

    @Test
    public void test4Century() {
        LeapYear ly = new LeapYear(2000);
        assertTrue(ly.isLeap());
    }
}