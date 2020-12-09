package exercise5.banking.reports;

import exercise4.banking.domain.*;
import java.text.NumberFormat;
import java.util.*;

public class CustomerReport {

  public void generateReport() {
    NumberFormat currency_format = NumberFormat.getCurrencyInstance();
    
    Bank bank = Bank.getBank();/*** STEP 1: RETRIEVE BANK SINGLETON OBJECT HERE ***/

    Customer   customer;

    System.out.println("\t\t\tCUSTOMERS REPORT");
    System.out.println("\t\t\t================");

    Iterator<Customer> bankIt = bank.getCustomers();
    while(bankIt.hasNext()) {
	      customer = (Customer)bankIt.next();
	
	      System.out.println();
	      System.out.println("Customer: "
				 + customer.getLastName() + ", "
				 + customer.getFirstName());
	      
	      Iterator<Account> customerIt = customer.getAccounts();
		  while(customerIt.hasNext()) {
				Account account = customerIt.next();
				String  account_type = "";
			
				// Determine the account type
				if ( account instanceof SavingsAccount ) {
				  account_type = "Savings Account";
				} else if ( account instanceof CheckingAccount ) {
				  account_type = "Checking Account";
				} else {
				  account_type = "Unknown Account Type";
				}
			
				// Print the current balance of the account
				System.out.println("    " + account_type + ": current balance is "
						 + currency_format.format(account.getBalance()));
		   }
    }
  }

}
