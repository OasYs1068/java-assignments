package myPoint;

public class TestMyPoint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyPoint Array_P[] = new MyPoint[10];
		for(int j=0;j<10;j++) {
			Array_P[j] = new MyPoint(j+1,j+1);
		}
		MyPoint p = new MyPoint(42,29);
		MyPoint p2 = new MyPoint(98,46);
		System.out.println(p.toString());
		p.setX(29);
		p.setY(42);
		for(int i=0;i<2;i++) {
			System.out.println(p.getXY()[i]);
		}
		
		p.setXY(42, 29);
		System.out.println(p.toString());
		
		System.out.println(p.distance(56,18));
		System.out.println(p.distance(p2));
		System.out.println(p.distance());
		
		for(int j=0;j<10;j++) {
			System.out.println(Array_P[j].toString());
		}
	}

}
