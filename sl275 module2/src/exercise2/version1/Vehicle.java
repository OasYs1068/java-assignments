package exercise2.version1;

public class Vehicle {
		public double load; //the current weight of the vehicle's cargo
		public double maxload; //the vehicle's maximum cargo weight limit
		
		Vehicle(double paraMaxload){
			maxload = paraMaxload;
		}//Constructor that sets maxload
		
		public double getLoad() {
			return load;
		}
		
		public double getMaxload() {
			return maxload;
		}
}
