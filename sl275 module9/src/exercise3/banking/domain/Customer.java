package exercise3.banking.domain;
import java.util.*;

public class Customer {
		private String firstname, lastname;
		private Account account;
		private SavingsAccount savingsAccount = null;
		private CheckingAccount checkingAccount = null;
		private List<Account> accounts = new ArrayList<Account>();
		
		public Customer(String paraFirstName, String paraLastName){
			firstname = paraFirstName;
			lastname = paraLastName;
		}
		
		public void addAccount(Account paraAccount) {
			accounts.add(paraAccount);
		}
		
		public int getNumOfAccounts() {
			return accounts.size();
		}
		
		public Account getAccount(int index) {
			return accounts.get(index);
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
		
		public Iterator<Account> getAccounts(){
			Iterator<Account> accountsIterator = accounts.iterator();
			return accountsIterator;
		}
}
