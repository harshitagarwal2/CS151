/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author harshit
 */

public class Economy_Class extends helper_functions {
    
    /**
     * Shows the Seats of the economy class.
     * @param mat An array passed to the function whose seats are to be shown.
     */
        public void showEconomyClassSeating(char mat[][])
    {
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = 0; j < mat[i].length; j++)
            {  System.out.print(mat[i][j] + " ");
                if(j== 2 )
                    System.out.print("         ");
            }
            System.out.println("");
        }
    }
        
       /**
        * The function adds the person /s to the seats desired by him and prints an error message if no seats are available
        * 
        * @param e Takes an Array input.
        */
    void addToEconomy(char [][] e) {
    int choice,num,i,j,k,s;
    boolean found;
    int aisle[][]={{2,3},{1,4},{0,5}};
    num=getPersonTraveling(3);
          for(i=1;i<=num;i++)   {
              System.out.println("passenger "+ i + " enter seating choice: ");
              s=getSeating(1);
              found=false;
              j=-1;
              while(!found&&j<29){
            	j++;
                 for(k=0;k<2&&!found;k++)	{
                  if(e[j][aisle[s][k]]=='-')	{
                	  e[j][aisle[s][k]]='X';
                      found=true;
                     }
                  }
                }
    if(found)
                  System.out.println("seat found row " +j+6);
    else
                  System.out.println("seat not found");
            }
    }
    
}
