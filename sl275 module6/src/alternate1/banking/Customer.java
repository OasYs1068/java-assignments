package alternate1.banking;

public class Customer {
		private String firstname, lastname;
		private Account account;
		private SavingsAccount savingsAccount = null;
		private CheckingAccount checkingAccount = null;
		
		
		public Customer(String paraFirstName, String paraLastName){
			firstname = paraFirstName;
			lastname = paraLastName;
		}
		
		public String getFirstName() {
			return firstname;
		}
		
		public String getLastName() {
			return lastname;
		}
		
		public void setSavings(SavingsAccount paraAccount) {
			savingsAccount = paraAccount;
		}
		
		public SavingsAccount getSavings() {
			return savingsAccount;
		}
		
		public void setChecking(CheckingAccount paraAccount) {
			checkingAccount = paraAccount;
		}
		
		public Account getChecking() {
			return checkingAccount;
		}
		
		public void setAccount(Account paraAccount) {
			account = paraAccount;
		}
		
		public Account getAccount() {
			return account;
		}
}
