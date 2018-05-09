
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


public class Menu {
      
    private static char[][] first = new char[5][4];
    private static char[][] eco = new char[30][6];
   
    
    public static void main(String[] args) {
        
        int choice = 0 ;
        
        Menu air = new Menu();
        helper_functions  a  = new helper_functions();
        First_Class fc = new First_Class();
        Economy_Class ec = new Economy_Class();
        
        a.makeAllSeatsEmpty(first, eco);
   
        do{
            choice = air.mainMenu();
            switch(choice){
                case 1 : {
                    choice = a.getclass();
                    if(choice == 1)
                        fc.addtoFirst(first);
                    else 
                        ec.addToEconomy(eco);
                 }
                break;
                case 2: {
                    a.showSeats(first, eco);
                }
                break;
                default: System.out.println("Enter a valid Input");
            }
        }
        while(choice != 3);
        if(choice ==3)
            System.exit(0);
        
    }
    
    
    /**
     *  Gets the User input for the function to be performed. 
     * @return the choice user makes to be performed by the program.
     */
    public int mainMenu() {
    int choice;
        System.out.println("Please Choose the Following inputs:\n 1- Add Passenger \n 2- Show Seatings \n 3- Quit ");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Choice(1-3): ");
        choice = sc.nextInt();
     return choice;
    }
}




