import java.util.ArrayList;

public class FiniteSequence implements NumberSequence {

	private static ArrayList<Long> numbers;
	private int count = 0;

	public FiniteSequence(ArrayList<Long> numbers2) {
		numbers = new ArrayList<Long>();
		numbers = numbers2;
	}

	@Override
	public long next() {
		long num = 0;
		if(hasNext()) {
			num = numbers.get(count);
			count++;
		}
		return num;
	}
	
	public boolean hasNext() {
		int var = count;
		if(numbers.get(var++) != null) 
			return true;
		else
			return false;
	}

	static NumberSequence of(long... ls) {
		ArrayList<Long> n = new ArrayList<Long>();
		try {
			for (long l : ls) 
				n.add(l);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FiniteSequence(n);
	}
	
	public double average() {
		if(numbers.size() == 0 )
			return -1;
		double avg = 0;
		double newavg =0;
		for(long l : numbers) {
			avg += l;
		}
		try {
			 newavg = avg/numbers.size();
		}
		catch(Exception e) {
			newavg = -1;
		}
		return newavg ;
	}
	
	public long[] toArray(int n) {
	long[] a = new long[n];
	for(int i=0 ; i< n; i++)
		a[i] = (long) numbers.get(i);
	return a;	
	}
	
	public double average(int n) {
		double avg= 0; 
		for(int i=0 ; i<n ; i++ )
			avg += numbers.get(i);
		return avg/n;
	}

	@Override
	public void reset() {
		numbers.clear();
		count= 0;
	}

}
