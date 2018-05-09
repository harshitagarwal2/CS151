
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
   protected ServerSocket mySocket;
   protected int myPort;
   protected Class handlerType;
   public Server() { this(5555); }
   public Server(int port) { this(port, "RequestHandler"); }
   public Server(int port, String htp) {
      try {
         myPort = port;
         mySocket = new ServerSocket(myPort);
         handlerType = Class.forName(htp);
      } catch(Exception e) {
         System.err.println(e.getMessage());
         System.exit(1);
      } // catch
   }
 
   public void listen() {
      System.out.println("server address: " + 
         mySocket.getInetAddress());
      try {
         while(true) {
            System.out.println("Server listening at port " + myPort);
            Socket socket = mySocket.accept(); // blocks
            RequestHandler handler = makeHandler(socket);
            if (handler == null) continue;
            Thread slave = new Thread(handler);
            slave.start();
         } // while
      } catch(IOException ioe) {
         System.err.println("Failed to accept socket, " + ioe);
         System.exit(1);
      } // catch
   }

   public RequestHandler makeHandler(Socket s) {
      RequestHandler handler = null;
      try {
         handler = new RequestHandler(s); //RequestHandler) handlerType.newInstance();
         //handler.setSocket(s);
      } catch(Exception e) {
         System.err.println(e.getMessage());
      }
      return handler;
   }



   public static void main(String[] args) {
      int port = 5555;
      String service = "RequestHandler";
      if (1 <= args.length) {
         port = Integer.parseInt(args[0]);
      }
      if (2 <= args.length) {
         service = args[1];
      }
      Server server = new Server(port, service);
      server.listen();
   }
}