package MysteryArray;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class MysteryArrayTest {

@Test
public void TestEvenArray() {
int [] arr = {0 , 1, 2 , 3 , 4, 5};
MysteryArray m = new MysteryArray(arr);
Iterator it = m.iterator();
assertEquals(0, it.next()) ;
assertEquals(2, it.next()) ;
assertEquals(4, it.next()) ;
assertEquals(5, it.next()) ;
assertEquals(3, it.next()) ;
assertEquals(1, it.next()) ;
}

@Test
public void TestOddArray() {
int [] arr = {0 , 1, 2 , 3 , 4, 5, 6};
MysteryArray m = new MysteryArray(arr);
Iterator it = m.iterator();
assertEquals(0, it.next()) ;
assertEquals(2, it.next()) ;
assertEquals(4, it.next()) ;
assertEquals(6, it.next()) ;
assertEquals(5, it.next()) ;
assertEquals(3, it.next()) ;
assertEquals(1, it.next()) ;

}
}

