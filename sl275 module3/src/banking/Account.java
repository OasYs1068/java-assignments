package banking;

public class Account {
	private double balance;
	
	public Account(double init_Balance){
		balance = init_Balance;
	}
	
	public double getBalance() {return balance;}
	
	public double deposit(double amount) {
		balance = balance + amount;
		return balance;
	}
	
	public double withdraw(double amount) {
		balance = balance - amount;
		return balance;
	}
}
