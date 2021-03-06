package exercise1;
import java.util.Scanner;

class Table{
	private String name;
	private double height,weight,length,width;
	public Scanner scan = new Scanner(System.in);
	public Table(double width, double weight, double height, double length, String name) {
		this.width=width;
		this.weight=weight;
		this.length=length;
		this.height=height;
		this.name=name;
	}
	
	public double area() {
		double area = width*length*height;
		return area;
	}
	
	public void display() {
		System.out.println(width+" "+length+" "+height+" "+weight+" "+name);
	}
	
	public void setName() {
//		name = "Jardani";
		System.out.println("Please enter the name of the table as you please:");
		name = scan.nextLine();
	}
	
	public void setWidth(double parameterWidth) {
		System.out.println("Now would you enter the width?");
		width = parameterWidth;
	}
	
	public String getName() {
		return name;
	}
	
	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getLength() {
		return length;
	}

}

public class TestTable {

	public static void main(String[] args) {
		Table table1 = new Table(1.0, 1.0, 2.0, 2.0, null);
		Scanner scan = new Scanner(System.in);
		
		table1.setName();
		table1.setWidth(scan.nextDouble());
		scan.close();
		if(table1.getWidth()<=0||table1.getLength()<=0||table1.getWeight()<=0||table1.getHeight()<=0) {
			System.out.println("error!");
		}
		else 
		{
			System.out.println(table1.getWidth()+" "+table1.getWeight()+" "+table1.getHeight()+" "+table1.getLength()+" "+table1.getName());
			System.out.println("The area of "+ table1.getName() +" is "+table1.getLength()*table1.getHeight()*table1.getWidth());
		}
	
	}
	
	
}
