public class LongWordFinder
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
 if (word.length() >= minLength) return word;
 }

 throw new NoSuchElementException();
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