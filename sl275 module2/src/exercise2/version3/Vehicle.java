package exercise2.version3;

public class Vehicle {
		private double load; //the current weight of the vehicle's cargo
		private double maxload; //the vehicle's maximum cargo weight limit
		
		Vehicle(double paraMaxload){
			maxload = paraMaxload;
		}//Constructor that sets maxload
		
		public double getLoad() {
			return load;
		}
		
		public double getMaxload() {
			return maxload;
		}
		
		public boolean addBox(double paraAddition) {
		    load = load + paraAddition;
			if(!whetherExccesion(load)) {return true;}
			else {
				return false;
			}
		}
		
		public boolean whetherExccesion(double paraLoad) {
			if(paraLoad>maxload) {
				return true;
			}
			else {return false;}
		}
		
		public double kiloToNewts(double weight) {
		    return (weight * 9.8);
		}
		/*  private double newtsToKilo(double weight) {
		    return (weight / 9.8);
		}
		*/
}
