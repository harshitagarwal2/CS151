package udp;

import java.util.List;
import java.util.ArrayList;

public class APIServer {
   private MyDatagramSocket sock;
   ArrayList ids = new ArrayList<String>();
   public APIServer(int port) {
      try {
         sock = new MyDatagramSocket(port);
      } catch (Exception e) {
         System.err.println(e.getMessage());
      }
   }
   protected String response(String msg) {
     return "echo: " + msg;
   }
   public void controlLoop() {
      System.out.println("UDP Server is listening ... ");
      while(true) {
         String msg = sock.receive();
         System.out.println("message received: " + msg);
         if (msg.equals("connect")) {
            String token = getNewToken();
            ids.add(token);
            sock.sendResponse(token);
         } else {
            System.out.println(ids);
            String token = msg.split("^")[0];
            if (!ids.contains("secret"))
               sock.sendResponse("Invalid token");
            sock.sendResponse(msg.toUpperCase());
         }
      }
   }

   private String getNewToken() {
      return "secret";
   }
   public static void main(String[] args) {
      int port = 4312;
      if (1 <= args.length) {
         port = Integer.parseInt(args[0]);
      }
      APIServer server = new APIServer(port);
      server.controlLoop();
   }

}