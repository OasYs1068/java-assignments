package date1;

public class TestDate {
		public static void main(String[] args) {
			Date Ashita = new Date(2019,9,11);
			
			Ashita.addOneDay();
			Ashita.display();
			Ashita.setData(2019, 2, 29);
			
			Ashita.setData(2019, 12, 31);
			Ashita.addOneDay();
			Ashita.display();
		}
}
