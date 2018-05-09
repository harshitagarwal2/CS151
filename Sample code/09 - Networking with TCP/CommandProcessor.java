


import java.util.*;
import java.io.*;
import java.net.*;
import java.util.*;

class CommandProcessor extends RequestHandler {
    public CommandProcessor(Socket s) {
        super(s);
    }
   protected String response(String msg) {
      String[] tokens = msg.split("\\s");
      double[] args = new double[tokens.length - 1];
      for(int i = 1; i < tokens.length; i++) {
         try {
            args[i - 1] = Double.parseDouble(tokens[i]);
         } catch(NumberFormatException nfe) {
            return "arguments must be numbers";
         }
      }
      if (tokens[0].equals("add")) return "" + add(args);
      //if (tokens[0].equals("mul")) return "" + mul(args);
      return "unrecognized command: " + tokens[0];
   }


   private double add(double[] args) {
      double result = 0;
      for (int i = 0; i < args.length; i++) {
         result += args[i];
      }
      return result;
   }
   
} // CommandProcessor