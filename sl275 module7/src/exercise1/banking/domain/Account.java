package exercise1.banking.domain;

public class Account {
	protected double balance;//Can be inherited:public or protected
	
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
