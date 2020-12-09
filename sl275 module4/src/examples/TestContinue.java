package examples;

public class TestContinue {
  public static void main(String[] args) {
    int x = 0;
    int y = 0;

    test:
    for (; x <= 2; x++ ) {
	System.out.println("top of outer do " + x);
	y = x;
        while ( y <= 2 ) {
	    System.out.println("top of inner do " + y);
 	    if ( y == 1 ) continue test;
	    System.out.println("bottom of inner do " + y);
	    y++;
	}
	System.out.println("bottom of outer do " + x);
    };
/*
    test:
    do {
	System.out.println("top of outer do " + x);
        do {
	    System.out.println("top of inner do " + x);
 	    if ( x == 1 ) continue test;
	    System.out.println("bottom of inner do " + x);
	} while ( true );
	System.out.println("bottom of outer do " + x);
    } while ( x <= 2 );
*/
  }
}
