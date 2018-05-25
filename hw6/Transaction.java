package bank.one;

public class Transaction {
	private Account acct1;
	private Account acct2;
	private int amount;

	public Transaction(int acct1, int acct2, int amount, Account[] accounts) {
		if (acct1 != -1)
			this.acct1 = accounts[acct1];
		else
			this.acct1 = new Account(-1);

		this.acct2 = accounts[acct2];
		this.amount = amount;
	}

	public Account getAcct1() {
		return acct1;
	}

	public synchronized void makeTransaction() {
		acct1.setBalance(-amount);
		acct2.setBalance(amount);
		acct1.incrementTransactions();
		acct2.incrementTransactions();
	}

}
