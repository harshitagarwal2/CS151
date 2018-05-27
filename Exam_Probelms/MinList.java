package exam;

import java.util.ArrayList;
import java.util.List;

public class MinList<T> extends ArrayList<T> { 
    List<T> items;
    /** Construct new empty list */ 
    public MinList()  
    { items = new ArrayList<T>(); } 

    /** add new item x to list */ 
    public void add(T x) { 
    	if(x instanceof Comparable<?>)
    	items.add(x);
    	else
    		System.out.println("Enter a valid input");
    }

    
    
    /** return smallest value in a non-empty list */
	public T minItem() { 
        assert items.size() > 0; 
        T minValue = (T)items.get(0); 
        for (T val: items) { 
            if (minValue instanceof Comparable<?> )
               ((Comparable<T>) minValue).compareTo(val); 
        } 
        return minValue; 
    }
	
}