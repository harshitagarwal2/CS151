package udp;

import java.util.Scanner;
public class APISender {

   public static void main(String[] args) {
      int port = 4312;
      String host = "localhost";
      String msg;
      try {

         Scanner scan = new Scanner(System.in);
         MyDatagramSocket sock = new MyDatagramSocket();
         sock.send(host, port, "connect");
         String token = sock.receive();
         System.out.println(token);

         while(true) {
            System.out.println("Enter a message, Press Ctrl+C to quit");
            msg = scan.next().trim();
            msg = token + "^" + msg;
            sock.send(host, port, msg);
            System.out.println("sending: " + msg);
            msg = sock.receive();
            System.out.println("received: " + msg);
            sock.close();
         }
      } catch(Exception e) {
         System.err.println(e.getMessage());
      }
   }

}
