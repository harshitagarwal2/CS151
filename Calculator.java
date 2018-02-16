
public class Calculator {
	public static double add(double a , double b) {
		return a+b;
	}
	
	public static double diff(double a , double b) {
		return a-b;
	}
	
	public static double mul(double a , double b) {
		return a*b;
	}
	
	public static double div(double a , double b) {
		return a/b;
	}
	
	public static void main(String[] args) {
		if(args.length != 0)
		{	
			try {
					
				double input1 = Double.parseDouble(args[1]);
				double input2 = Double.parseDouble(args[2]);
				switch (args[0]) {
					case "add": System.out.println("This addition is " + add(input1 , input2));
						break;
					case "diff": System.out.println("This Difference is " + diff(input1 , input2));
						break;
					case "mul": System.out.println("This multiplication is " + mul(input1 , input2));
						break;
					case "div": System.out.println("This division is " + div(input1 , input2));
						break;
					default:	System.out.println("Choose a valid operation");
						break;
					}
				}catch(Exception e) {
				System.out.println("Enter two number Inputs in the command line");
					}
			}
			else	System.out.println("Enter the Calculator operation to be performed - add diff mul div ");
		}
}
