package exercise2.banking;

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
	
	public void withdraw(double amount) throws OverdraftException{
		if(balance>=amount) {
			balance = balance - amount;
		}
		else {
			throw new OverdraftException("Insufficient funds", amount - balance);
		}
	}
}
