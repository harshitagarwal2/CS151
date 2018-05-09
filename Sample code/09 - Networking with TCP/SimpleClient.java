


import java.util.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleClient extends Correspondent {

   protected BufferedReader stdin;
   protected PrintWriter stdout;
   protected PrintWriter stderr;


   public SimpleClient(String host, int port) {
      requestConnection(host, port);
      System.out.println("hello10");
      stdout = new PrintWriter(
         new BufferedWriter(
               new OutputStreamWriter(System.out)), true);
      stderr = new PrintWriter(
         new BufferedWriter(
            new OutputStreamWriter(System.out)), true);
      stdin = new BufferedReader(
         new InputStreamReader(System.in));
   }


   public void controlLoop() {
      while(true) {
         try {
            stdout.print("-> ");
            stdout.flush();
            String msg = stdin.readLine();
            if (msg == null) continue;
            if (msg.equals("quit")) break;
            stdout.println("sending: " + msg);
            send(msg);
            System.out.println("hello14");
            msg = receive();
            stdout.println("received: " + msg);
         } catch(IOException e) {
            stderr.println(e.getMessage());
            break;
         }
      }
      send("quit");
      stdout.println("bye");
   }


   public static void main(String[] args) {
      int port = 5555;
      String host = "localhost";
      if (1 <= args.length) {
         port = Integer.parseInt(args[0]);
      }
      if (2 <= args.length) {
         host = args[1];
      }
      SimpleClient client = new SimpleClient(host, port);
      client.controlLoop();
   }
}