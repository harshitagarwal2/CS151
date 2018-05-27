package MysteryArray;
import java.util.Iterator;

public class MysteryArray implements Iterable {
private Integer[] array;

public MysteryArray(int[] arr) {
array = new Integer[arr.length];

for(int i=0 ; i<arr.length; i++) {
array[i] = arr[i];
}
}

@Override
public Iterator iterator() {
return new ParityIterator();
}


class ParityIterator implements Iterator<Integer> {
private int index = -1;
private int count =0;

public Integer next() {
if(hasNext()) {
if(count == 0 ) {
index++;
count++;
return array[index];
}

if(index % 2 == 0) {
index = index + 2;
if(index >= array.length)
{
if(array[array.length -1] % 2 == 0 ) {
index = array[array.length -2];
}
else index = array[array.length -1] ;
}
}
else index -= 2;
if(array[index] != null)
{
count++;
return array[index];
}
}
return null;
}

public boolean hasNext() {
if(count < array.length ) return true;
return false;
}
}

}