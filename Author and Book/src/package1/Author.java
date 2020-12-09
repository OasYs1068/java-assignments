package package1;

public class Author {
		private String name, email;
		private char gender;//Gender is either m or f
		
		public Author(String paraName, String paraEmail, char paraGender) {
			name = paraName;
			email = paraEmail;
			gender = paraGender;
		}
		
		public String getName() {
			return name;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String paraEmail) {
			email = paraEmail;
		}
		
		public char getGender() {
			return gender;
		}
		
		public String toString() {
			return ("Author[name= "+name+", email= "+email+", gender= "+gender+"]");
		}
}