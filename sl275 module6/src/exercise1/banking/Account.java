package exercise1.banking;

public class Account {
	protected double balance;
	
	public Account(double init_Balance){
		balance = init_Balance;
	}
	
	public void setBalance(double paraBalance) {balance = paraBalance;}
	
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
