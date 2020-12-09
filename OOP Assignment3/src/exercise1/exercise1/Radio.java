package exercise1;

public class Radio extends AudioDevice{
		public Radio() {
			super();
		}
		public void adjustWavelength() {}
		public void increaseVol() {
			super.increaseVol();
			System.out.println("Radio Volume increased.");
		}
		public void decreaseVol() {
			super.decreaseVol();
			System.out.println("Radio Volume decreased.");
		}
}
