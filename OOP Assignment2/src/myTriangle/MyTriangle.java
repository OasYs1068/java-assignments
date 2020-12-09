package myTriangle;

import myPoint.MyPoint;

public class MyTriangle {
		private MyPoint v1 = new MyPoint();
		private MyPoint v2 = new MyPoint();
		private MyPoint v3 = new MyPoint();
		
		public MyTriangle(int x1,int y1,int x2,int y2,int x3,int y3) {
			v1.setXY(x1,y1);
			v2.setXY(x2,y2);
			v3.setXY(x3,y3);
		}
		
		public MyTriangle(MyPoint paraV1,MyPoint paraV2,MyPoint paraV3) {
			v1 = paraV1;
			v2 = paraV2;
			v3 = paraV3;
		}
		
		public String toString() {
			return ("MyTriangle[v1="+v1.toString()+" ,v2="
					                          +v2.toString()+" ,v3="+v3.toString()+"]");
		}
		
		public double getPerimeter() {
			return v1.distance(v2)+v2.distance(v3)+v3.distance(v1);
		}
		
		public String getType() {
			if(v1.distance(v2)==v2.distance(v3)&&v2.distance(v3)==v3.distance(v1)) {
				return "Equilateral";
			}
			if((v1.distance(v2)==v2.distance(v3)&&v2.distance(v3)!=v3.distance(v1))||
					(v1.distance(v3)==v2.distance(v3)&&v2.distance(v2)!=v3.distance(v1))||
					(v1.distance(v2)==v1.distance(v3)&&v2.distance(v3)!=v3.distance(v2))) {
				return "Isosceles";
			}
			else {
				return "Scalene";
			}
		}
}
