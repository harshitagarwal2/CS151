
import java.util.*;
import java.lang.reflect.*;

public class Table<T extends Integer> {
    ArrayList<ArrayList<T>> entries;
    int rows;
    int columns;
    T temp;

    public Table(int rows, int columns) { 
        this.rows = rows;
        this.columns = columns;
        entries = new ArrayList<ArrayList<T>>(rows);
        for (int i = 0; i < rows; i++) {
            ArrayList<T> row = new ArrayList<T>(columns);
            for (int j = 0; j < columns; j++)
                row.add(temp);
            entries.add(row);
        }

    }

    public void putValue(int row, int column, T value) {
        if (this.rows < row || this.columns < column)
            System.out.println("Table out of bounds");
        ArrayList<T> col = entries.get(row);
        col.set(column, value);
    }

    public ArrayList<T> getRow(int index) {
        return entries.get(index);
    }

    public static void main(String args[]) {
        int rows = 10;
        int columns = 10;
        Table<Integer> myTable = new Table(rows, columns);
        for (int i = 0; i< rows; i++)
            for (int j =0; j < columns; j++)
                myTable.putValue(i, j, i+j);

        for (int i = 0 ; i < rows ; i++)
            System.out.println(myTable.getRow(i));

    }


}