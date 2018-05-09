public class CopyingExamples {
	
	public static void main(String[] args) {
		MyPoint p1 = new MyPoint(5,5);
		MyPoint p2 = p1;
		
		p2.x = 15;
		
		System.out.println("p1 = " + p1);
		System.out.println("p2 = " + p1);
		
		MyPoint q1 = new MyPoint(5,5);
		MyPoint q2 = new MyPoint(q1);
		
		q2.x = 15;
		
		System.out.println("q1 = " + q1);
		System.out.println("q2 = " + q1);
//////////////////////////////////////////////
		int[][] a = new int[10][];
		for (int i = 0; i< 10; i++) {
			a[i] = new int[i];
			for (int j = 0; j< i ; j++) {
				a[i][j] = i*j;
			}
		}
		int sum =0;
		int count = 0;
		for (int i = 0; i< 10; i++) {
			
			for (int j = 0; j< a[i].length ; j++) {
				System.out.print(a[i][j] + "\t");
				sum+= a[i][j];
				count++;
			}
			System.out.println("\n");
		}

		System.out.println(sum/count);

	}

}
