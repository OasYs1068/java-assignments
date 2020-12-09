package package1;

import package2.Book;

public class TestBook {
	  public static void main(String[] args) {
		  Author ahTeck = new Author("Tan Ah Teck", "ahteck@nowhere.com", 'm');
		  Book theBook = new Book("The Book",ahTeck, 25.00);
		  
		  System.out.println("Test getAuthor(): "+theBook.getAuthor());
		  System.out.println("Test getName(): "+theBook.getName());
		  System.out.println("Test getPrice(): "+theBook.getPrice());
		  System.out.println("Test getQuantity(): "+theBook.getQuantity());
		  
		  System.out.println("The author's name is "+theBook.getAuthorName()+" and email is "+theBook.getAuthorEmail());
		  
		  
	  }
}
