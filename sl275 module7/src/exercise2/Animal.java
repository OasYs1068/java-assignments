package exercise2;

abstract class Animal {
		protected int legs;
		
		protected Animal(int paraLegs) {
			legs = paraLegs;
		}
		
		abstract void eat();
		
		public void walk() {
			System.out.println("How this animal walks:  "+legs);
			return;
		}
}
