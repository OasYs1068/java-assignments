package exercise3.banking.domain;

import java.util.*;

public class Bank {
		private List<Customer> customers;
		private static Bank bankInstance = new Bank();
		
		private Bank() {
			customers = new ArrayList<Customer>();
		}//singleton pattern programming
		
		public void addCustomer(String fName,String lName) {
			customers.add(new Customer(fName,lName));
//			Iterator<Customer> customerIterator = customers.iterator();
//			while(customers.hasNext()) {}
		}
		
		public static Bank getBank() {
			return bankInstance;
		}
		
		public int getNumOfCustomers() {
			return customers.size();
		}
		
		public Customer getCustomer(int index) {
			return customers.get(index);
		}
		
		public Iterator<Customer> getCustomers() {
			Iterator<Customer> customersIterator = customers.iterator();
			return customersIterator;
		}
}
