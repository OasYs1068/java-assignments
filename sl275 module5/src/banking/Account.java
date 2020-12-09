package banking;

public class Account {
	private double balance;
	
	public Account(double init_Balance){
		balance = init_Balance;
	}
	
	public double getBalance() {return balance;}
	
	public boolean deposit(double amount) {
		balance = balance + amount;
		return true;
	}
	
	public boolean withdraw(double amount) {
		if(balance>=amount) {
			balance = balance - amount;
			return true;
		}
		else {
			return false;
		}
	}
}
