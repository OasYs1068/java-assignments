package mod03.examples;

class Assign {
	 
	 //Assign(int a){};
	 
  public static void main (String args []) {
    // declare integer variables
    int x, y;
    // declare and assign floating point
    float z = 3.414f;
    // declare and assign double
    double w = 3.1415;
    // declare and assign boolean 
    boolean truth = true;
    // declare character variable
    char c;
    // declare String variable
    String str;
    // declare and assign String variable
    String str1 = "bye";
    // assign value to char variable
    c = 'A';
    // assign value to String variable
    str = "Hi out there!";
    // assign values to int variables
    x = 6;
    y = 1000;
    
    //����Unicode�����Դ���룬��־����Χ����    
    int ����=1;
    System.out.println("����="+����);
    //int 1st=0;
    
    //int a=99L;
    int a=(int)99l;
    System.out.println(a);
    
  }  
}

class b extends Assign{
	
	private b(){
	  //super();
	
	};
	
}