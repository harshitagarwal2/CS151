package bank.one;

public class Account {
	
	private int id;
	private int balance;
	private int transactions;
	
	public Account(int number){
		id = number;
		balance = 1000;	
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTransactions(int transactions) {
		this.transactions = transactions;
	}

	public int getTransactions(){
		return transactions;
	}

	public int getBalance(){
		return balance;
	}
	
	public synchronized void setBalance(int amount){
		balance += amount;
	}
	
	public synchronized void incrementTransactions(){
		transactions++;
	}
	@Override
	public String toString(){
		return "acct:" + id + " bal:" + balance + " trans:" + transactions;
	}

}
