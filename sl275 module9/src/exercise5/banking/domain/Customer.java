/**
 * This class contains a bunch of documentation tags.
 * @author George Gao
 * @version 0.5(beta)
 */

package exercise5.banking.domain;
import java.util.*;

public class Customer implements Comparable<Customer>{
		private String firstname, lastname;
		private Account account;
		private SavingsAccount savingsAccount = null;
		private CheckingAccount checkingAccount = null;
		private List<Account> accounts = new ArrayList<Account>();
		
		/**
		 * construct a customer whose name is set respectively with two strings
		 * @param paraFirstName customer's first name
		 * @param paraLastName customer's last name
		 */
		public Customer(String paraFirstName, String paraLastName){
			firstname = paraFirstName;
			lastname = paraLastName;
		}
		
		/**
		 * adds a new account to a series of accounts that belong to the same customer
		 * @param paraAccount the account to be added
		 * 
		 */
		public void addAccount(Account paraAccount) {
			accounts.add(paraAccount);
		}
		
		/**
		 * return The number of accounts in the series of accounts
		 * @return int the size of the account
		 */
		public int getNumOfAccounts() {
			return accounts.size();
		}
		
		/**
		 * return the account that is on the position of index
		 * @param index the position of account in the List
		 * @return Account the account of this customer
		 */
		public Account getAccount(int index) {
			return accounts.get(index);
		}
		
		/**
		 * return customer's first name
		 * @return String the value of first name
		 */
		public String getFirstName() {
			return firstname;
		}
		
		/**
		 * return the customer's last name
		 * @return String customer's last name
		 */
		public String getLastName() {
			return lastname;
		}
		
		/**
		 * sets up a saving account
		 * @param paraAccount the saving account object of this customer
		 * 
		 */
		public void setSavings(SavingsAccount paraAccount) {
			savingsAccount = paraAccount;
		}
		
		/**
		 * return the saving account
		 * @return SavingsAccount the saving account object of this customer
		 */
		public SavingsAccount getSavings() {
			return savingsAccount;
		}
		
		/**
		 * sets up a checking account
		 * @param paraAccount the checking account object of this customer
		 * 
		 */
		public void setChecking(CheckingAccount paraAccount) {
			checkingAccount = paraAccount;
		}
		
		/**
		 * return the checking account
		 * @return Account the checking account object of this customer
		 */
		public Account getChecking() {
			return checkingAccount;
		}
		
		/**
		 * sets up an account
		 * @param paraAccount the account of this customer
		 * 
		 */
		public void setAccount(Account paraAccount) {
			account = paraAccount;
		}
		
		/**
		 * return an account
		 * @return Account the account of this customer
		 */
		public Account getAccount() {
			return account;
		}
		
		/**
		 * return an iterator of the series of accounts
		 * @return Iterator the series of accounts
		 */
		public Iterator<Account> getAccounts(){
			Iterator<Account> accountsIterator = accounts.iterator();
			return accountsIterator;
		}
		
		/**
		 * 
		 * set the principle of comparing the customers by their names
		 * @param person Another customer introduced for comparison
		 * @return int the result of comparison 
		 */
		public int compareTo(Customer person) {
			String lName = person.getLastName();
			if(!(getLastName()==lName)) {
				return getLastName().compareTo(lName);
			}
			else {
				String fName = person.getFirstName();
				return getFirstName().compareTo(fName);
			}
		}

}
