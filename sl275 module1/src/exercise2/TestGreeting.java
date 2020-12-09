package exercise2;


public class TestGreeting {
	private String Greeting;
	
	public  TestGreeting(String s) {
		Greeting = s;
	}
	public void Greet(String whom) {
		System.out.println(Greeting+"~"+whom+"~");
	}
	public static void main(String[] arg) {
		TestGreeting Hello = new TestGreeting("Goodbye");
		Hello.Greet(" JOJO");
	}
		
}
//
//public class TestGreeting2{
//	
//}
