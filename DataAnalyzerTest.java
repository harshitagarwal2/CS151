
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author harshit
 */
public class DataAnalyzerTest {
    public static void main(String[] args) {
        String filename = null;
        try {
            filename = args[0];
        } catch (Exception e) {
            System.out.println("Enter a File Locarion in Command Line");
        }
        if(filename != null){
        DataAnalyzer da = new DataAnalyzer();
        ArrayList<Integer> array = da.ReadFile(filename);
            System.out.println("Minimum from the Given File is: " + da.getMin(array));
            System.out.println("Maximum from the Given File is: " + da.getMax(array));
            System.out.println("Average from the Given File is: " + da.getAverage(array)); 
        }   
   }
}