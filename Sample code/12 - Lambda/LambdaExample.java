import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class LambdaExample {

    class Sorter {
        public void sortDescending(ArrayList<Integer> a){
            InvIntC comp = new InvIntC();
            a.sort(comp) ;
        }
        
    }
    class InvIntC implements Comparator<Integer> {
        @Override
            public int compare(Integer i1, Integer i2) {
                if (i2 < i1)
                    return -1 ;
                else if (i2.equals(i1))
                    return 0;
                else // i2 > i1
                    return 1;
            }
        }

    static Function<Integer,Integer> sq = in1 -> in1 * in1;
    static Function<Integer,Integer> makeLarge = in1 -> in1 * 100;
    static Predicate <Integer> isLarge = x -> x > 1000;

    static Consumer<Object> p = System.out::println;

    static Supplier<String> div = () -> "~~~~~~~~~~~~~~~~~~~~~~";

    public static void main(String [] args) {

        Integer [] x = {1,3,5,4,2,6,12};
        ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(x));
        Sorter s = new LambdaExample().new Sorter();
        s.sortDescending(a);
        System.out.println(a);

        p.accept(div.get());

        // Use a simple Lambda
        a = new ArrayList<Integer>(Arrays.asList(x));
        System.out.println(a);
        // A simple mapping operation. 
        a.sort((i1,i2) -> Integer.compare(i2, i1));
        System.out.println(a);


        p.accept(div.get());

        // A simple terminal operation.
        a.forEach((y) -> System.out.println(sq.apply(y)));

        // Obtain a stream from the arraylist, map it to some function
        // Get the result.
        List sqList = a.stream()
            .map(sq)
            .collect(Collectors.toList());

        System.out.println(sqList);

        p.accept(div.get());

        sqList = a.stream()
            .map(makeLarge)
            .filter(isLarge)
            .collect(Collectors.toList());

        System.out.println(sqList);

        p.accept(div.get());

        System.out.println(isLarge.test(10000));

        a.forEach(p);
        
    } 
}