package CS151.HW6;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebWorker extends Thread {
  
    public WebWorker(String urlString, int rowToUpdate, WebFrame frame) {
        this.urlString = urlString;
        this.rowToUpdate = rowToUpdate;
        this.frame = frame;
    }

    public void run() {
        download();
        frame.releaseWorker(resultStatus, rowToUpdate);
    }

    public void download() {
    	InputStream input = null;
    	StringBuilder contents = null;
        try {
            URL url = new URL(urlString);
            URLConnection myConnection = url.openConnection();
            myConnection.setConnectTimeout(5000);
            myConnection.connect();
            input = myConnection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            char[] a = new char[1000];
            long startTime = System.currentTimeMillis();


            int size = 0;
            int len;
            contents = new StringBuilder(1000);
            while ((len = reader.read(a, 0, a.length)) > 0) {
                contents.append(a, 0, len);
                Thread.sleep(100);
                size += len;
            }
            long endTime = System.currentTimeMillis();
            resultStatus = new SimpleDateFormat("HH:mm:ss").format(new Date(startTime))
                    + "   " + (endTime - startTime)
                    + "ms   " + size + "bytes";
        }
        catch (MalformedURLException ignored) {
            resultStatus = ERROR;
        } catch (InterruptedException exception) {
            resultStatus = INTERRUPTED;
        } catch (IOException ignored) {
            resultStatus = ERROR;
        }
        finally {
            try {
                if (input != null) input.close();
            } catch (IOException ignored) {
            }
        }
    }
    
    private String urlString;
    private int rowToUpdate;
    private WebFrame frame;
    private String resultStatus;
    private static final String ERROR = "error in fetching.Try again after some time.";
    private static final String INTERRUPTED = "fetching was interrupted";

}