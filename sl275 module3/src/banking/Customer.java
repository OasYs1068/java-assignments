package banking;

public class Customer {
		private String firstname, lastname;
		private Account account;
		
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
		
		public void setAccount(Account paraAccount) {
			account = paraAccount;
		}
		
		public Account getAccount() {
			return account;
		}
}
