package package2;

import package1.Author;

public class Book {
	private String name;
	private Author author;
	private double price;
	private int quantity = 0;
	
	public Book(String paraName, Author paraAuthor, double paraPrice) {
		name = paraName;
		author = paraAuthor;
		price = paraPrice;
	}
	
	public Book(String paraName, Author paraAuthor, double paraPrice, int paraQuantity) {
		name = paraName;
		author = paraAuthor;
		price = paraPrice;
		quantity = paraQuantity;
	}
	
	public String getName() {
		return name;
	}
	
	public Author getAuthor() {
		return author;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setPrice(double paraPrice) {
		price = paraPrice;
	}
	
	public void setQuantity(int paraQuantity) {
		quantity = paraQuantity;
	}
	
	public String toString() {
		return ("Book[name="+name+", "+author.toString()+", price= "+price+", quantity="+quantity+"]");
	}
	
	public String getAuthorName() {
		   return author.getName();
		   // cannot use author.name as name is private in Author class
		}
	
	public String getAuthorEmail() {
		   return author.getEmail();
	}
	
	public char getAuthorGender() {
		   return author.getGender();
	}

}
