import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.AssertionFailedError;

public class NumberSequenceTest {

	   @Test public void squareSequenceTest()
	   {
	      // Create a square sequence and make sure it returns the values in sequence.
		   SquareSequence sq = new SquareSequence();
		  assertEquals(0, sq.next(), .000001);		 
		  assertEquals(1, sq.next(), .000001);	
		  assertEquals(4, sq.next(), .000001);	
		  assertEquals(9, sq.next(), .000001);	
//		  assertEquals(1, sq.next(), .000001);	//Displaying a fail check.
		  assertEquals(16, sq.next(), .000001);	
		  assertEquals(25, sq.next(), .000001);	
	   }
	   
	   @Test public void ofTestFinite()
	   {
	      // Test creating a FiniteSequence. Make sure the sequence is returned in order.     
		  FiniteSequence fq = (FiniteSequence) FiniteSequence.of(1,2,3,4,5,6,7,8,9,10);
		  assertEquals(1, fq.next(), .000001);	
		  assertEquals(2, fq.next(), .000001);	
		  assertEquals(3, fq.next(), .000001);	
		  assertEquals(4, fq.next(), .000001);
//		  assertEquals(11, fq.next(), .000001); //Displpaying a fail Test if comment is removed	
		  assertEquals(5, fq.next(), .000001);	
		  assertEquals(6, fq.next(), .000001);	
		  assertEquals(7, fq.next(), .000001);	
		  assertEquals(8, fq.next(), .000001);		
	   }
	   
  @Test public void ofTestInfinite()
	   {
	  boolean thrown = false;
	      // Test creating a SquareSequence using of. Make sure it throws an Exception
	  	try{
	  		SquareSequence.of(1,2,3);
	  	}catch(Exception e)
	  	{
	  		thrown = true;
	  	}
	  	assertTrue(thrown);
	   }
	   
	   
	   @Test public void averageTestFinite() { 
	      // Check the average returned for a finite sequence with and withut arguments
		  assertEquals(2 , (FiniteSequence.of(1,2,3).average()), .0001);
		  assertEquals(-1 , (FiniteSequence.of().average()), .0001); 
		  
	   } 
//	   
	   @Test public void averageTestInfinite() { 
	      // Check the average returned for nn infinite sequence with and without arguments
	   SquareSequence sq = new SquareSequence();
		  assertEquals(1.66 , sq.average(3), .01); 
		  boolean thrown = false;
			try{
				sq.average();  	
			}catch(Exception e)
		  	{
		  		thrown = true;
		  	}
		  	assertTrue(thrown);
		   }
	   
	   
	   @Test public void toArrayTest() {
		   long[] a = FiniteSequence.of(0,1,4).toArray(3);
		   assertEquals(0, a[0] , 0.1);
		   
		   SquareSequence sq = new SquareSequence();
		   long [] b = sq.toArray(3);
		   assertEquals(0, b[0]);
	   }
	    
	   @Test public void resetTest() {
	      // Check if reset() restarts the sequence.
		   SquareSequence sq = new SquareSequence();
		   assertEquals(0, sq.next(), 0.1);
		   assertEquals(1, sq.next(), 0.1);
		   assertEquals(4, sq.next(), 0.1);
		   assertEquals(9, sq.next(), 0.1);
		   assertEquals(16, sq.next(), 0.1);
			sq.reset();
		  assertEquals(0, sq.next(), .1);
	   }

}

