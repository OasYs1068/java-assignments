package myTriangleAndArray;

public class TestMyTrangle {
	public static void main(String[] args) {
		MyTriangle Tri = new MyTriangle(1,1,2,0,3,3);
		
		System.out.println(Tri.toString());
		System.out.println(Tri.getPerimeter());
		System.out.println(Tri.getType());
	}
		
}
