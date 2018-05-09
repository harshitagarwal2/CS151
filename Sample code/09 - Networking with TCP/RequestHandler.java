
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.*;

class RequestHandler extends Correspondent implements Runnable {
   public RequestHandler(Socket s) {super(s); }

   public void setSocket(Socket s) {

   }
   // override in a subclass:
   protected String response(String msg) {
    System.out.println("hello4");
      return "echo: " + msg;
   }
   public void run() {
      while(true) {
         String msg = receive();
         System.out.println("hello5");
         System.out.println("received: " + msg);
         //if (msg == null) continue;
         if (msg.equals("quit")) break;
         msg = response(msg);
         System.out.println("hello4");
         System.out.println("sending: " + msg);
         send(msg);
      }
      close();
      System.out.println("request handler shutting down");
   }
}