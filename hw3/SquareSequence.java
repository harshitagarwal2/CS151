
public class SquareSequence implements NumberSequence {
	private int count = 0 ;
	
	@Override
	public long next() {
		int num =0;
		num = count * count;
		count++;
		return num;
	}
	
	public long[] toArray(int n) {
		long[] a = new long[n];
		for(int i=0 ; i<n ; i++)
		{
			a[i] = next();
		}
		return a;
	}
	
	public double average(int n) {
		double avg = 0;
		for(int i=0 ; i< n ; i++)
			avg += next();
		double newavg = avg/n;
		return newavg;
	}
	
   public static  NumberSequence of(long ... args) throws NoSuchMethodException {
		   throw new NoSuchMethodException("fail");
	   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SquareSequence sq = new SquareSequence();
		long[] a = sq.toArray(5);
		
		for(long l : a)
			System.out.println(l);
		sq.reset();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		count = 0;
	}

}
