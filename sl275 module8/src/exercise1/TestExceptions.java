package exercise1;

public class TestExceptions {
  public static void main(String[] args) {
	  try{
		  for ( int i = 0; true; i++ ) {
			  System.out.println("args[" + i + "] is '" + args[i] + "'");
	      }
	  }catch(ArrayIndexOutOfBoundsException e) {
		  System.out.println("Exception caught: java.lang.ArrayIndexOutOfBoundsException : 3");
	  }finally {
		  System.out.println("Quiting...");
	  }
		  
  }
}
