package exercise2;

class TestAnimal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fish d = new Fish();
		Cat c = new Cat("Fluffy");
		Animal a = new Fish();
		Animal e = new Spider();
		Pet p = new Cat();
		
		d.walk();
		System.out.println(c.getName());
		System.out.println(p.getName());
		p.setName("Boah");
		System.out.println(p.getName());
		c.walk();
		p.play();
		c.eat();
		d.eat();
		a.eat();
		e.walk();
		((Animal)c).walk();
		
	}

}
