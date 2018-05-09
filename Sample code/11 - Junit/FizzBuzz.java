public class FizzBuzz {
    public static String foo(int i) {
        String str = "";
        if (i%3 == 0) {
            str+="Fizz";
        }
        if (i%5 == 0) {
            str+="Buzz";
        }
        return str;
    }
}