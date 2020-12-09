package exercise2;

public class Fish extends Animal implements Pet {
		private String name; 
		public Fish() {
			super(0);
		}
		
		public void walk() {
			System.out.println("They cannot walk.");
			return;
		}
		
		public String getName(){
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public void play() {
			System.out.println("Fish started playing.");
		}
		
		public void eat() {
			System.out.println("Fish started eating.");
		}
}
