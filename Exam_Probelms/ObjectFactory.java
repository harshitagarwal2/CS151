package exam;

import java.lang.reflect.*;

public class ObjectFactory
{
  /**
   * Uses reflection to create an Object of a given class
   *
   * @param  className  The name of the class (e.g., String)
   *
   * @return             An instance of the given class
   */
  public static Object createObject(String className) {
	  Object obj = null;
try {
	obj =  Class.forName(className).newInstance();
	return obj;
} catch (Exception e) {
	e.printStackTrace();
}
return obj;
}
  
  public static void main(String[] args) {
	Object myov = ObjectFactory.createObject("MinList");	
  }
}
