public class Generics {
// Shows basic use of the ArrayList and iterators // with the generics.
public static void demoList() {
    // ** Create a List<String>
    List<String> a = new ArrayList<String>(); a.add("Don't");
    a.add("blame");
    a.add("me");
    // ** foreach -- iterate over collection easily for (String str: a) {
               System.out.println(str);

    // ** Instead of Iterator, make an Iterator<String> Iterator<String> it = a.iterator();
    while (it.hasNext()) {
    // NOTE: no cast required here -- it.next() is a String String string = it.next();
    System.out.println(string);
    ￼￼￼
    }
    // ** Likewise, can make a List<Integer> List<Integer> ints = new ArrayList<Integer>(); for (int i = 0; i < 10; i++) {
               ints.add(new Integer(i * i));
           }
    // No casts needed here -- it knows they are Integer
    int sum = ints.get(0).intValue() + ints.get(1).intValue();
    // With auto Unboxing, can just write it like this... sum = ints.get(0) + ints.get(1);
    // Can go back and forth between typed Collections and untyped "raw" // forms -- may get a warning.
    List<String> genList = new ArrayList(); // warning
    List rawList = new ArrayList<String>(); // no warning rawList.add("hello"); // warning
           genList = rawList; // warning
           rawList = genList; // no warning
    }
