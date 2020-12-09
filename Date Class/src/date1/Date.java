package date1;

public class Date {
		private int year,month,day;
		
		public Date(int paraYear, int paraMonth, int paraDay) {
			year = paraYear;
			month = paraMonth;
			day = paraDay;
		}
		
		public void setData(int paraYear, int paraMonth, int paraDay) {
			if(paraYear<0||paraMonth<=0||paraDay<=0) {
				System.out.println("Error!");
			}
			//leap years
			else if(paraYear%4==0||(paraYear%100==0&&paraYear%400!=0)) {
				if(paraMonth==2&&paraDay==29) {
					year = paraYear;
					month = paraMonth;
					day = paraDay;
				}
				else if(paraMonth>12||paraDay>31) {
					System.out.println("Error!");
				}
				else if(paraMonth==2&&paraDay>29) {
					System.out.println("Error!");
				}
				else if((paraMonth==4||paraMonth==6||paraMonth==9||paraMonth==11)&&paraDay>30) {
					System.out.println("Error!");
				}
				else {
					year = paraYear;
					month = paraMonth;
					day = paraDay;
				}
			}
			//common years
			else {
				if(paraMonth>12||paraDay>31) {
					System.out.println("Error!");
				}
				else if(paraMonth==2&&paraDay>28) {
					System.out.println("Error!");
				}
				else if((paraMonth==4||paraMonth==6||paraMonth==9||paraMonth==11)&&paraDay>30) {
					System.out.println("Error!");
				}
				else {
					year = paraYear;
					month = paraMonth;
					day = paraDay;
				}
			}
			
		}
		
		public void addOneDay() {
			//leap year
			if(year%4==0||(year%100==0&&year%400!=0)) {
				if(month==12&&day==31) {
					year = year + 1;
					month = 1;
					day = 1;
				}
				else if(day<29) {
					day = day+1;
				}
				else if((month==4||month==6||month==9||month==11)&&day==30){
					month = month + 1;
					day = 1;
				}
				else if(month==2&&day==29) {
					month = month + 1;
					day = 1;
				}
				else if(day==31) {
					month = month + 1;
					day = 1;
				}
			}
			//common years
			else {
				if(month==12&&day==31) {
					year = year + 1;
					month = 1;
					day = 1;
				}
				else if(day<28) {
					day = day+1;
				}
				else if((month==4||month==6||month==9||month==11)&&day==30){
					month = month + 1;
					day = 1;
				}
				else if(month==2&&day==28) {
					month = month + 1;
					day = 1;
				}
				else if(day==31) {
					month = month + 1;
					day = 1;
				}
			}
		}
		
		public void display() {
			System.out.println(day+"/"+month+"/"+year);
		}
}
