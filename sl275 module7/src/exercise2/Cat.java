package exercise2;

public class Cat extends Animal implements Pet{
	private String name;
	
	public Cat(String name) {
		super(4);
		this.name = name;
	}
	
	public Cat() {
		super(4);
		new Cat("\0");
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void play() {
		System.out.println("Cat started playing.");
	}
	
	public void walk() {
		super.walk();
	}
	
	public void eat() {
		System.out.println("Cat started eating.");
	}
	
	
}
