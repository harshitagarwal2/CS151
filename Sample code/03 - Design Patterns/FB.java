public class FizzBuzz {
    public static String foo(int x) {
        if (x % 5 == 0 && x % 3 == 0) {
            return "FizzBuzz";
        } else if (x % 5 == 0) {
            return "Buzz";
        } else if (x % 3 == 0) {
            return "Fizz";
        } else 
            return "";
    }
}