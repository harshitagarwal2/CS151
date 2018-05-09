/////////////////////////////////////////////
// Pascal's triangle                       //
/////////////////////////////////////////////

public class JaggedArray {

    public static void main(String[] args) {
        int[][] a = new int[10][];
        for (int i = 0 ; i < 10; i++) {
            a[i] = new int[i+1];
            for (int j = 0 ; j <= i ; j++) {
                if (i!=0 && j!=0 && j != i)
                    a[i][j] = a[i-1][j]+a[i-1][j-1];
                else
                    a[i][j] = 1; // Adjust for zero indexing
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
        System.out.println(sum);
        System.out.println(count);
        System.out.println(sum/count);
    }
}