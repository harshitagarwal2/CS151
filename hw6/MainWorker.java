package bank.one;

public class MainWorker implements Runnable{
		private Bank bank;
		private int numOfWorkers;
		String fileToRead;
		
		public MainWorker( Bank bank, int numOfWorkers, String fileToRead){
			this.bank = bank;
			this.numOfWorkers = numOfWorkers;
			this.fileToRead = fileToRead;
		}
		@Override
		public void run() {
			try{
				bank.getTransactions(fileToRead, numOfWorkers);
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
	}