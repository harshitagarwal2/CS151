/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author harshit
 */

import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
public class DataAnalyzer {
  private ArrayList<Integer> Numbers;

    DataAnalyzer(){
    Numbers = new ArrayList<Integer>();
    }  

    public ArrayList<Integer> ReadFile( String filename){
        System.out.println(filename);
        if(filename != null){
            String line = null;
            try {
                FileReader fr = new FileReader(filename);
                BufferedReader br = new BufferedReader(fr);
                while((line = br.readLine()) != null){
                    Numbers.add(Integer.parseInt(line));
                     }
             br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("The following file cannot be found" + ex );
            } catch (IOException ex) {
                System.out.println("The following Error is found " + ex);
            }
         
        }
        else{
           System.out.println("Cannot find the File name or given File name is wrong");
        }
        if(Numbers != null) 
            return Numbers;
        else
            return null;
    }
    
    public Integer getMax( ArrayList<Integer> array) {
        return Collections.max(array);
    }
    
    public Integer getMin( ArrayList<Integer> array) {
        return Collections.min(array);
    }
    
    public Integer getAverage(ArrayList<Integer> array) {
        int sum = 0 ;
        for(Integer i : array )
            sum += i;
        return sum/array.size();
    }
}
