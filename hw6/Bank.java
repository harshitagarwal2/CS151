import java.util.concurrent.*;


public class Bank {
    BlockingQueue<Transaction> queue;
    private final Transaction nullTrans = new Transaction(-1,0,0);

    public Bank() {
        queue = new LinkedBlockingQueue();
    }

    // TODO: Add code for actually updating accounts. 
    // Should you make this synchronized? Why or why not?
    // If not, how do you avoid concurrency issues?
    public synchronized void processTransaction(Transaction t) {
        System.out.println("Updating account with transaction");
    }

    private class Worker extends Thread {
        public void run() {
            try {
                Transaction t;
                do {
                    t = queue.take();
                    processTransaction(t);
                    System.out.println(this.getName() + t);
                } while (t.fromAccount != -1);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            System.out.println(this.getName()  + "exiting");
        }
    }


    public static void main(String[] args) {
        Bank bank = new Bank();

        // TODO: Replace the following with code to generate as
        // many workers as needed. The number of workers is
        // Given as a commandline argument. 
        Worker w1 = bank.new Worker();
        Worker w2 = bank.new Worker();

        try {
            w1.start();
            w2.start();

            // TODO: replace the following with code for 
            // reading from the file and putting the transactions 
            // into the BlockingQueue
            System.out.println("Putting 10 values from main");
            for (int i = 0; i< 10; i++) {
                bank.queue.put(new Transaction(
                    (int)(Math.random()*10), 
                    (int)(Math.random()*10), 
                    (int)(Math.random()*1000)));
            }

            bank.queue.put(bank.nullTrans);
            bank.queue.put(bank.nullTrans);
            System.out.println("Main finished adding all transactions");
            // TODO: Add code here to wait for ALL the workers to finish
            w1.join();
            w2.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        System.out.println("All threads done");
        
    }
}

class Transaction {
    int fromAccount;
    int toAccount;
    int amount;

    public Transaction(int from, int to, int amt) {
        fromAccount = from;
        toAccount = to;
        amount = amt;
    }

    public String toString() {
        return "Transaction: from = " + fromAccount + ", to = " + toAccount + " amount = " + amount;
    }

}

//TODO: Create an Account class with id, num of transactions and account balance