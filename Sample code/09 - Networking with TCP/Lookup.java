


import java.io.*;
import java.net.*;

public class Lookup {

    public static void main(String[] args) {
        String hostname = "localhost";
        try { 
           InetAddress a = InetAddress.getByName(hostname);
           System.out.println(hostname + ":" + a.getHostAddress()); 
        }  catch (UnknownHostException e) {
             System.out.println("No address found for " + hostname); 
        } 

    }

}