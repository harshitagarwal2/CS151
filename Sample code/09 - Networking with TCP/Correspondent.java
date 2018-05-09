

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.*;

class Correspondent {
   protected Socket sock;
   protected BufferedReader sockIn;
   protected PrintWriter sockOut;

   public Correspondent() { } // init fields later
   public Correspondent(Socket s) {
      sock = s;
      initStreams();
   }


   protected void initStreams() {
      try {
         sockIn = new BufferedReader(
            new InputStreamReader(
               sock.getInputStream()));
         sockOut = new PrintWriter(
            sock.getOutputStream(), true);
      } catch(IOException e) {
         System.err.println(e.getMessage());
      }
   }



   protected void close() {
      try {
         sock.close();
      } catch(IOException e) {
         System.err.println(e.getMessage());
      }
   }



   protected void requestConnection(String host, int port) {
      try {
         sock = new Socket(host, port);
         initStreams();
         System.out.println("hello1");
      } catch(UnknownHostException uhe) {
         System.err.println("unknown host " + uhe);
         System.exit(1);
      } catch(IOException ioe) {
         System.err.println("failed to create streams " + ioe);
         System.exit(1);
      }
   }



   public void send(String msg) {
      sockOut.println(msg);
      System.out.println("hello2");
   }

   public String receive() {
      String msg = null;
      try {
         msg = sockIn.readLine();
         System.out.println("hello21");
      } catch(Exception e) {
         System.err.println(e.getMessage());
         System.exit(1);
      }
      return msg;
   }
} // Correspondent