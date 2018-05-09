public interface NumberSequence
{
   long next();
   
   void reset();
   
   default boolean hasNext() { return true; }
   
   default double average(int n) {
	return 0;   
   }
   
   default long[] toArray(int n) {
	   long[] a = {0};
	   return a;
   }
   
   default double average() {
	  throw new UnsupportedOperationException("Provide a valid finite Seq.");
   }
 
   static  NumberSequence of(long ... args) throws NoSuchMethodException {
	   throw new NoSuchMethodException("fail");
   }
   
   
}