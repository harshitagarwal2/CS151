package udp;

public class UDPSender {

   public static void main(String[] args) {
      int port = 4444;
      String host = "localhost";
      String msg = "greetings";
      if (1 <= args.length) {
         msg = args[0];
      }
      if (2 <= args.length) {
         port = Integer.parseInt(args[1]);
      }
      if (3 <= args.length) {
         host = args[2];
      }
      try {
         MyDatagramSocket sock = new MyDatagramSocket();
         System.out.println("sending: " + msg);
         sock.send(host, port, msg);
         msg = sock.receive();
         System.out.println("received: " + msg);
         sock.close();
      } catch(Exception e) {
         System.err.println(e.getMessage());
      }
   }

}
