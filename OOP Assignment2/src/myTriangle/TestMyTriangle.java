package myTriangle;
import myPoint.MyPoint;

public class TestMyTriangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyPoint p1 = new MyPoint(98,46);
		MyPoint p2 = new MyPoint(42,42);
		MyPoint p3 = new MyPoint(29,29);
		
		MyTriangle Tri1 = new MyTriangle(p1,p2,p3);
		
		MyTriangle Tri2 = new MyTriangle(0,0,0,5,5,0);
		System.out.println(Tri1.toString());
		System.out.println(Tri1.getPerimeter());
		System.out.println(Tri1.getType());
		System.out.println(Tri2.toString());
		System.out.println(Tri2.getPerimeter());
		System.out.println(Tri2.getType());
	}

}
