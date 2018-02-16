
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author harshit
 */

public class RandomStringGenerator {
     private ArrayList<Character> range;

    public RandomStringGenerator() {
        range = new ArrayList <> ();
    }
     
     
      
      public void addRange(char start , char end)
          {
               for (char i = start; i <= end; i++) {
                  range.add(i);
                   }
          }
      
      public String nextString(int size) {
          String generatedString = "";
          for(int i=0; i< size ; i++){
              Random r = new Random();
              char c = range.get(r.nextInt(range.size()));
              generatedString += c;
          }
          return generatedString;
      }
      
      public static void main(String[] args) {
        RandomStringGenerator rsm  = new RandomStringGenerator();
        rsm.addRange('a', 'z');
        rsm.addRange('A','Z');
        String s= rsm.nextString(10);
          System.out.println(s);
    }
}
