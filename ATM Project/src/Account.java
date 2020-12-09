public class Account {
	private double balance;//Can be inherited:public or protected
	private double interestRate;
	
	public Account(double init_Balance){
		balance = init_Balance;
	}
	public Account(double balance, double interest_rate) {
		this.balance = balance;
		interestRate = interest_rate;
	}
	
	public void setBalance(double paraBalance) {balance = paraBalance;}
	
	public double getBalance() {return balance;}
	
	public String deposit(double amount) {
		balance = balance + amount;
		return ("Deposit successful.\nRemaining balance: "+balance);
	}

	public String withdraw(double amount) {
		if(balance>=amount) {
			balance = balance - amount;
			return ("Withdrawal successful.\nRemaining balance: "+balance);
		}
		else {
			return "Withdrawal failed, insufficient fund.";
		}
	}
}
