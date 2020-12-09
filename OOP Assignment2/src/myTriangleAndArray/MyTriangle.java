package myTriangleAndArray;

import myPoint.MyPoint;

public class MyTriangle {
		private MyPoint[] v = new MyPoint[3];
		{
			v[0] = new MyPoint();
			v[1] = new MyPoint();
			v[2] = new MyPoint();
		}
		
		public MyTriangle(int x1,int y1,int x2,int y2,int x3,int y3) {
			v[0].setXY(x1,y1);
			v[1].setXY(x2,y2);
			v[2].setXY(x3,y3);
		}
		
		public MyTriangle(MyPoint paraV1,MyPoint paraV2,MyPoint paraV3) {
			v[0] = paraV1;
			v[1] = paraV2;
			v[2] = paraV3;
		}
		
		public String toString() {
			return ("MyTriangle[v1="+v[0].toString()+" ,v2="
					                          +v[1].toString()+" ,v3="+v[2].toString()+"]");
		}
		
		public double getPerimeter() {
			return v[0].distance(v[1])+v[1].distance(v[2])+v[2].distance(v[0]);
		}
		
		public String getType() {
			if(v[0].distance(v[1])==v[1].distance(v[2])&&v[1].distance(v[2])==v[2].distance(v[0])) {
				return "Equilateral";
			}
			if((v[0].distance(v[1])==v[1].distance(v[2])&&v[1].distance(v[2])!=v[2].distance(v[0]))||
					(v[0].distance(v[2])==v[1].distance(v[2])&&v[1].distance(v[2])!=v[2].distance(v[0]))||
					(v[0].distance(v[1])==v[0].distance(v[2])&&v[1].distance(v[2])!=v[2].distance(v[1]))) {
				return "Isosceles";
			}
			else {
				return "Scalene";
			}
		}
}
