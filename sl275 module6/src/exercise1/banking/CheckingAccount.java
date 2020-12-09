package exercise1.banking;

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
		
		public boolean withdraw(double amount) {
			if(balance>=amount) {
				balance = balance - amount;
				return true;
			}
			else if(overdraftProtection>=(amount-balance)) {
					balance = 0;
					return false;
			}
			else {
				return false;
			}
		}
		
}
