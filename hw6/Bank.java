package bank.one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Bank {
	
	public void getTransactions(String fileToRead, int numOfWorkers)
			throws FileNotFoundException, InterruptedException {
		
		BufferedReader br = new BufferedReader(new FileReader(new File(fileToRead)));
		String temp = null;
		try {
			temp = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (temp != null) {
			String s[] = temp.split(" ");
			int id1 = Integer.parseInt(s[0]);
			int id2 = Integer.parseInt(s[1]);
			int amount = Integer.parseInt(s[2]);
			try {
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Transaction trans = new Transaction(id1, id2, amount, accounts);
			queue.put(trans);
		}

		for (int i = 0; i < numOfWorkers; i++) {
			Transaction e = new Transaction(-1, 0, 0, accounts);
			queue.put(e);
		}

		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void printValues() throws InterruptedException {	
		count.await();
		for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
			System.out.println(this.accounts[i].toString());
		}
	}

	

	public void runParallel(int workers) throws InterruptedException {
		List<Worker> theWorkers = new ArrayList<Worker>();
		for (int i = 0; i < workers; i++) {
			Worker worker = new Worker();
			theWorkers.add(worker);
			worker.start();
		}
	}
	
	public Bank(int numOfWorkers) {
		accounts = new Account[NUMBER_OF_ACCOUNTS];
		for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
			accounts[i] = new Account(i);
		}

		queue = new ArrayBlockingQueue<>(12);
		count = new CountDownLatch(numOfWorkers);
		semaphoreLock = new Semaphore(6);
	}

	class Worker extends Thread {
		public void run() {
			
			boolean finished = false;
			while (!finished) 
				try {
					semaphoreLock.acquire();
					Transaction trans = queue.take();
					if (trans.getAcct1().getId() < 0) {
						finished = true;
						count.countDown();
					} else {
						trans.makeTransaction();
					}
					semaphoreLock.release();
				} catch (Exception e) {

				
			}
		}
	}
	
	private static final int NUMBER_OF_ACCOUNTS = 20;
	private Account[] accounts;
	private BlockingQueue<Transaction> queue;
	private CountDownLatch count;
	private Semaphore semaphoreLock;


}
