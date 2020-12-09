package mod03.exercise1;

class TestMyPoint {
	public static void main(String[] args) {
		MyPoint start = new MyPoint();
		MyPoint end = new MyPoint();
		MyPoint stray = end;
		
		start.setX(10);
		start.setY(10);
		end.setX(20);
		end.setY(30);
		
		System.out.println("Start point is "+start.toString());
		System.out.println("End point is "+end.toString());
		System.out.println("");
		System.out.println("Stray point is "+stray.toString());
		System.out.println("End point is "+end.toString());
		
	    stray.setX(47);
		stray.setY(30);
		
//		end.setX(47);
//		end.setY(55);
		
		System.out.println("");
		System.out.println("Stray point is "+stray.toString());
		System.out.println("End point is "+end.toString());
		System.out.println("Start point is "+start.toString());
	}
	
}
