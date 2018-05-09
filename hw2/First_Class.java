/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author harshit
 */
public class First_Class extends helper_functions {
    
    /**
     * shows the seating of a first class 
     * @param mat array which can be passed to object of any size.
     */
    public void showFirstClassSeating(char mat[][])
    {
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = 0; j < mat[i].length; j++)
            {  System.out.print(mat[i][j] + " ");
                if(j== 1)
                    System.out.print("            ");
            }
            System.out.println("");
        }
    }
    
    /**
     * function which adds to the given array.
     * @param f the array of the first-class object.
     */
    public void addtoFirst(char  [][] f) {
    int  k; 
    int[][] aisle = {{1,2},{0,4}};

    boolean found;
    /**
     * first gets the number of persons travelling together and then loops over all the persons to assign the desired seat to them.
     * The functions fills from the start of the array and then go to the last.
     */
    int num=getPersonTraveling(2);
          for(int i=1;i<=num;i++){
              System.out.println("passenger "+ i +"\nenter seating choice: ");
            int s=getSeating(0);
          found=false;
              int j=-1;
              while(!found&&j<4)
                 {j++;
                 for(k=0;k<2&&!found;k++)
                  if(f[j][aisle[s][k]]=='-')
                     {f[j][aisle[s][k]]='X';
                     found=true;
                     }
                                }
    if(found)
                  System.out.println("seat found row is "+ j+1);
    else
                  System.out.println("seat not found");
        }
    }
    
}
