import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class WebWorker extends Thread {
/*
  This is the core web/download i/o code...*/
     String urlString;
     public WebWorker(String url) {
     	urlString = url;
     }
     public void run() {
     	System.out.println("Fetching...." + urlString);
 		InputStream input = null;
		StringBuilder contents = null;
		try {
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
		
			// Set connect() to throw an IOException
			// if connection does not succeed in this many msecs.
			connection.setConnectTimeout(5000);
			
			connection.connect();
			input = connection.getInputStream();

			BufferedReader reader  = new BufferedReader(new InputStreamReader(input));
		
			char[] array = new char[1000];
			int len;
			contents = new StringBuilder(1000);
			while ((len = reader.read(array, 0, array.length)) > 0) {
				System.out.println("Fetching...." + urlString + len);
				contents.append(array, 0, len);
				Thread.sleep(100);
			}
			
			System.out.print(contents.toString());
			
		}
		// Otherwise control jumps to a catch...
		catch(MalformedURLException ignored) {
			System.out.println("Exception: " + ignored.toString());
		}
		catch(InterruptedException exception) {
			// YOUR CODE HERE
			// deal with interruption
			System.out.println("Exception: " + exception.toString());
		}
		catch(IOException ignored) {
			System.out.println("Exception: " + ignored.toString());
		}
		// "finally" clause, to close the input stream
		// in any case
		finally {
			try{
				if (input != null) input.close();
			}
			catch(IOException ignored) {}
		}
	}

	
}
