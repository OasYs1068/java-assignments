package myPoint;

public class MyPoint {
		private int x = 0;
		private int y = 0;
		
		public MyPoint() {}
		
		public MyPoint(int paraX, int paraY) {
			x = paraX;
			y = paraY;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public int[] getXY() {
			int[] XY = new int[2];
			XY[0] = x;
			XY[1] = y;
			return XY;
		}
		
		public void setX(int paraX) {
			x = paraX;
		}
		
		public void setY(int paraY) {
			y = paraY;
		}
		
		public void setXY(int paraX, int paraY) {
			x = paraX;
			y = paraY;
		}
		
		public String toString() {
			return("("+getXY()[0]+","+getXY()[1]+")");
		}
		
		public double distance(int anotherX, int anotherY) {
			return Math.sqrt((x-anotherX)*(x-anotherX)+(y-anotherY)*(y-anotherY));
		}

		public double distance(MyPoint another) {
			return Math.sqrt((x-another.getX())*(x-another.getX())+(y-another.getY())*(y-another.getY()));
		}
		
		public double distance() {
			return Math.sqrt(x*x+y*y);
		}
}
