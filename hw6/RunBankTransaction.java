package bank.one;

import java.io.FileNotFoundException;

public class RunBankTransaction {
	public static void main(String args[]) throws FileNotFoundException, InterruptedException {
		if (args.length != 2 || Integer.parseInt(args[1]) <= 0) {
			System.out.println("The program requires the Input file and the number of threads to work on the File to get the transaction.");
		} else {
			String fileToRead = args[0];
			int numOfWorkers = Integer.parseInt(args[1]);
			Bank bank = new Bank(numOfWorkers);

			MainWorker mainWorker = new MainWorker(bank, numOfWorkers, fileToRead);

			bank.runParallel(numOfWorkers);
			Thread mainThread = new Thread(mainWorker);

			mainThread.start();

			bank.printValues();
		}
	}

}
