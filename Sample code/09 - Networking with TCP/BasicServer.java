import java.util.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class BasicServer {

    public ServerSocket mySocket;
    public int myPort;

    public BasicServer(int port) {
      try {
         myPort = port;
         mySocket = new ServerSocket(myPort);
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
            String msg = "hello";
            PrintWriter sockOut = new PrintWriter(socket.getOutputStream(), true);
            sockOut.println(msg);
      System.out.println("hello2");
         } // while
      } catch(IOException ioe) {
         System.err.println("Failed to accept socket, " + ioe);
         System.exit(1);
      } // catch
   }
    public static void main(String[] args) {

        BasicServer myServer = new BasicServer(5555);
        myServer.listen();
    }
}