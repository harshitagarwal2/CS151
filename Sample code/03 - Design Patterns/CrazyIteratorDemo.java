import java.util.*;

class MyList<T> extends ArrayList<T> {
    public Iterator<T> iterator() {
        return new CrazyIterator<T>(this);
    }
}
class CrazyIterator<T> implements Iterator<T> {
    List<T> list;
    public CrazyIterator(List<T> l) {list = l;}

    public boolean hasNext() { return list.size() != 0;}

    public T next() { 
        //Get the next random element
        int i = (int)(Math.random()*list.size());
        return list.get(i);
    }
}
public class CrazyIteratorDemo {

    public static void print(Iterator it) {
        int i = 0;
        while (it.hasNext() && i < 100) {
            System.out.println(it.next());
            i++;
        }

    }
    public static void main(String[] args) {
        
        // Method 1: Subclass List
        
        /*MyList<String> mylist1 = new MyList<String>();
        mylist1.add("Scala");
        mylist1.add("Ruby");
        mylist1.add("Python");
        mylist1.add("Go");
        mylist1.add("Kotlin");

        Iterator it1 = mylist1.iterator();
        print(it1);*/

       // Method 2: Customize Iterator with the list
        List<String> mylist2 = new ArrayList<String>();
        mylist2.add("PHP");
        mylist2.add("Perl");
        mylist2.add("C");
        mylist2.add("Java");
        mylist2.add("COBOL");

        Iterator it2 = new CrazyIterator(mylist2);
        print(it2);

    }

}