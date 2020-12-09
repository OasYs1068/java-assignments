package exercise1;
//import java.util.*;

public class TestElecDevice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Scanner in = new Scanner(System.in);
		ElectricalDevice ed;
		AudioDevice ad;
		ed = new Refrigerator();
		ed.turnOn();
		ed.turnOff();
		ad = new TV();
		ad.turnOn();
		ad.increaseVol();
		ad.decreaseVol();
		ad.turnOff();
		ad = new Radio();
		ad.turnOn();
		ad.increaseVol();
		ad.decreaseVol();
		ad.turnOff();
	}

}
