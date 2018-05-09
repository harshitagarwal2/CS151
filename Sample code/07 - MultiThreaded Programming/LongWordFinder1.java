import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

public class LongWordFinder1
{
   public static Callable<String> findLongWords(
      String filename, int minLength)
   {
      return () -> 
         {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines)
            {
               Thread.sleep(0);
               String[] words = line.split("[\\PL]+");
               for (String word : words)
                  if (word.length() >= minLength) return filename + " : " + word;
            }
            
            throw new NoSuchElementException();
         };
   }

   public static void main(String[] args) throws Exception
   {
      String[] filenames = { 
            "Catching_Fire.txt",
            "The_Hunger_Games.txt",
            "Mockingjay1.txt",
            "Mockingjay2.txt"
      };
      ExecutorService service = Executors.newCachedThreadPool();
      List<Callable<String>> tasks = new ArrayList<>();
      for (String filename : filenames)
      {
         tasks.add(findLongWords(filename, 16));
      }
      try
      {
         String result = service.invokeAny(tasks);
         System.out.println(result);
      }
      catch (ExecutionException ex)
      {
         System.out.println("No result");
      }         
      service.shutdown();
   }
}