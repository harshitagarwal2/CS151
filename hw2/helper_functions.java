
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author harshit
 */
public class helper_functions {
    
	/**
	 * modifies any given array of any length to "-".
	 * @param a  array provided by parameter "a"
	 */
    public void unassignedSeating(char [][] a){
        int i,j;
        for(i=0;i<a.length;i++)
        for(j=0;j<a[i].length;j++)
         a[i][j]= '-';
        
    }
    
    /**
     * Makes all the seats in the given airplane array empty.
     * @param first
     * @param eco
     */
    public void makeAllSeatsEmpty(char [][] first, char [][] eco){
        unassignedSeating(eco);
        unassignedSeating(first);
    }
    
    
    /**
     * gets the choice of the Seats preferred by the travelling alone or together(2 or 3).
     * @param n differentiates between the economy class and first class. 
     * @return returns the choice made by the user of which seats he chooses.
     */
    public int getSeating(int n) {
    int choice=0;
    Scanner sc = new Scanner(System.in);
    String [] mess={"aisle","window","center"};
    int[][] order = {{0,1,0},{0,2,1}};
    int max[]={2,3};
    int i,j;
    while(choice<1||choice>max[n]){
            for(i=0;i<max[n];i++)
                 System.out.println(i+1 + " - " + mess[order[n][i]]+ "  " );
             System.out.println("Enter Choice:");
             choice = sc.nextInt();
             if(choice<1||choice>max[n])
                 System.out.println("Invalid Entry");
         }
     return choice-1;
    }
  
/**
 * gets the class chosen by the user i.e first or economy
 * @return the class number.
 */
    public int getclass() {
      int choice;
          System.out.println("Please Choose the Following inputs:\n 1- First Class \n 2- Economy Class ");
          Scanner sc = new Scanner(System.in);
          System.out.print("Enter Choice(1-2): ");
          choice = sc.nextInt();
          while(choice<1||choice>2){
              System.out.println("\n Invalid Input Please enter again");
              System.out.print("Enter Choice(1-2): ");
          choice = sc.nextInt();
          }
       return choice;
    }
    
    /**
     * gets the number of persons travelling together.
     * @param n differentiate in the number of persons which is passed in first class and economy class.
     * @return the number of people travelling together.
     */
    public int getPersonTraveling(int n) {
    int choice;
        System.out.println("How many people are traveling together (max "+n+"): ");
    Scanner sc = new Scanner(System.in);
    choice  = sc.nextInt();
    while(choice<1||choice>n)
        {
            System.out.println("Invalid entry\n");
            System.out.println("How many people are traveling together (max "+ n+ "): ");
            choice = sc.nextInt();
          }
    return choice;
    }
    
    /**
     * displays the seats empty or occupied in the airplane model.
     * @param first_object defines object of first-class
     * @param eco_object	defines the object of economy class.
     */
    public void showSeats(char[][]first_object, char[][] eco_object ) {
        First_Class first = new First_Class();
        first.showFirstClassSeating(first_object);
        
        Economy_Class eco = new Economy_Class();
        eco.showEconomyClassSeating(eco_object);
    }

    
}
