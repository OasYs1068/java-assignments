package exercise2.banking;

import java.util.*;

public class CheckingAccount extends Account {
		private double overdraftProtection;
		private SavingsAccount protectedBy = null;
		
		public CheckingAccount(double balance) {
			super(balance);
		}
		
		public CheckingAccount(double balance, double protect) {
			super(balance);
			overdraftProtection = protect;
		}
		
		public CheckingAccount(double balance, SavingsAccount protect) {
			super(balance);
			protectedBy = protect;
		}
		
		public void withdraw(double amount) throws OverdraftException{
			if(balance>=amount) {
				balance = balance - amount;
			}
			else if(overdraftProtection==0) {
					throw new OverdraftException("No overdraft protection!",amount - balance);
			}
//			else if(protectedBy.getBalance()>=(balance-amount)) {
//					protectedBy.setBalance(protectedBy.getBalance() - (amount - balance));
//					balance = 0;
//					throw new OverdraftException("No overdraft protection!",amount - balance);
//			}
			else if(overdraftProtection>=(amount-balance)) {		
					overdraftProtection = overdraftProtection - (amount-balance);
					balance = 0;	
			}
			else {
				throw new OverdraftException("Insufficient funds for overdraft protection.",amount);
			}
		}
		
		
}
