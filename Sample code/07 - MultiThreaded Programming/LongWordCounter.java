import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LongWordCounter
{
   public static Callable<Integer> countLongWords(
      String filename, int minLength)
   {
      return () -> 
         {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            int count = 0;
            for (String line : lines)
            {
               String[] words = line.split("[\\PL]+");
               for (String word : words)
                  if (word.length() >= minLength) count++;
            }
            
            return count;
         };
   }

   public static void main(String[] args) throws Exception
   {
      String[] filenames = { 
            "alice30.txt",
            "war-and-peace.txt",
            "crsto10.txt"
      };
      ExecutorService service = Executors.newCachedThreadPool();
      List<Callable<Integer>> tasks = new ArrayList<>();
      for (String filename : filenames)
      {
         tasks.add(countLongWords(filename, 0));
      }
      List<Future<Integer>> resultFutures = service.invokeAll(tasks);
      int total = 0;
      for (Future<Integer> resultFuture : resultFutures)
      {
         total += resultFuture.get();
      }
      System.out.println(total);
      service.shutdown();
   }
}