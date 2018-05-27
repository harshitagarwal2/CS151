package exam;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class FindPalindromeMultiThreading {

	public static void main(String[] args) {
		List<BigInteger> r = new ArrayList<>();
		BigInteger start = new BigInteger("0", 10);
	    ExecutorService executorService = null;
		long length = Long.parseLong(args[0]);
		List<Future<List<BigInteger>>> futures = new ArrayList<>();
		int n = Integer.parseInt(args[1]);
		executorService = Executors.newFixedThreadPool(n);	
		for (int i = 0; i < n; i++)		
		{
		   long from = i * length / n; 		
		   long to = (i + 1) * length / n;	 
		    
		   Callable<List<BigInteger>> task = () ->
		      {
		         List<BigInteger> rtask = new ArrayList<>(); 	
		         for (long j = from; j < to; j++)
		         {
		            BigInteger a = start.add(BigInteger.valueOf(j)).pow(2);
		            if (isPalindrome(a)) rtask.add(a);						
		         }
		         return rtask;
		      };
		   futures.add(executorService.submit(task));
		}
		for (Future<List<BigInteger>> f : futures)
			try {
				r.addAll(f.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		for(BigInteger b : r) {
			System.out.println(b);
		}
	}


	private static boolean isPalindrome(BigInteger a) {
		return a.compareTo(getReverse(a)) == 0;
	}

	public static BigInteger getReverse(BigInteger n) {
	    return new BigInteger(new StringBuilder().append(n).reverse().toString());
	}

}