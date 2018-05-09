package udp;


public class UDPServer {
   private MyDatagramSocket sock;
   public UDPServer(int port) {
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
         if (msg.trim().equals("quit")) break;
         msg = response(msg.toUpperCase());
         System.out.println("message sent: " + msg);
         sock.sendResponse(msg);
      }
      sock.sendResponse("bye");
      sock.close();
      System.out.println("bye");
   }

   public static void main(String[] args) {
      int port = 4444;
      if (1 <= args.length) {
         port = Integer.parseInt(args[0]);
      }
      UDPServer server = new UDPServer(port);
      server.controlLoop();
   }

}