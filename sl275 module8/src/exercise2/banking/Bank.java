package exercise2.banking;

public class Bank {
		private Customer[] customers;
		private int numberOfCustomers;
		private static Bank bankInstance = new Bank();
		
		private Bank() {
			customers = new Customer[9];
		}//singleton pattern programming
		
		public void addCustomer(String fName,String lName) {
			Customer customer = new Customer(fName,lName);
			for(int i=0;i<9;i++) {
				if(customers[i]==null) {
					customers[i]=customer;
					numberOfCustomers++;
					return;
				}
			}
		}
		
		public static Bank getBank() {
			return bankInstance;
		}
		
		public int getNumOfCustomers() {
			return numberOfCustomers;
		}
		
		public Customer getCustomer(int index) {
			return customers[index];
		}
}
