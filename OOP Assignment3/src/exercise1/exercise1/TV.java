package exercise1;

public class TV extends AudioDevice{
		public TV() {
			super();
		}
		public void changeChannel(){}
		public void adjustColor() {}
		public void increaseVol() {
			super.increaseVol();
			System.out.println("TV Volume increased.");
		}
		public void decreaseVol() {
			super.decreaseVol();
			System.out.println("TV Volume decreased.");
		}
		

}
