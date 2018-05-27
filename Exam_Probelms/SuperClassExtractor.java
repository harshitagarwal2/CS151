package exam;

import java.lang.reflect.*;

public class SuperClassExtractor  {
	/**     * Uses reflection to get the entire hierarchy of the given class.    
	 *   * For example getHierarchy("java.util.ArrayList") should return     
	 *    * Object > AbstractCollection > AbstractList > ArrayList   
	 *    
	 *      *     * @param  className  The name of the class (e.g., String)     * @return       
	 *            A string representing the hierarchy     */ 
	public static String getHierarchy(String className) 
	{   
		String hierarchyString = null;
		
		try {
			Class parent = Class.forName(className).getSuperclass();
			Class c = parent.getClass();
			System.out.println(c.getName());
			while(parent != null) {
				String s = parent.getName();
				if(hierarchyString != null) {
					hierarchyString = s + ">" + hierarchyString; 
				}
				else hierarchyString = s;
				parent = parent.getSuperclass();
			}
		} 
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return hierarchyString;
	}
	public static void main(String[] args) {
		System.out.println(getHierarchy("java.util.Stack"));
	}
}	
