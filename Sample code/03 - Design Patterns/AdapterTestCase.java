import junit.framework.TestCase;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;
import java.lang.reflect.*;
import java.io.*;

public class AdapterTestCase extends TestCase {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testAdapter() {
        Line l = new Line();
        Rectangle r = new Rectangle();

        // A begin and end point from a graphical editor
        int x1 = 10, y1 = 20;
        int x2 = 30, y2 = 60;
        
        l.draw(x1, y1, x2, y2);
        assertThat(outContent.toString(), containsString("(10,20)"));
        assertThat(outContent.toString(), containsString("(30,60)"));

        r.draw(x1,y1,x2,y2);
        assertThat(outContent.toString(), containsString("width 20"));
        assertThat(outContent.toString(), containsString("height 40"));


    }
}