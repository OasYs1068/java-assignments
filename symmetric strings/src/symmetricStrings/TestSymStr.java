package symmetricStrings;

import java.util.Scanner;

class TestSymStr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input=new Scanner(System.in);
		
		SymString test1 = new SymString("lmfao let's try it out.");
		SymString test2 = new SymString("asdfghgfdsa");
		SymString test3 = new SymString(input.nextLine());
		input.close();
		
		System.out.println("Is the string \"lmfao let's try it out.\" symmetric?");
		System.out.println(test1.isSymStr2());
//		System.out.println(test1.isSymStr());
		
		System.out.println("Is the string \"asdfghgfdsa\" symmetric?");
		System.out.println(test2.isSymStr2());
//		System.out.println(test2.isSymStr());
		
		System.out.println("Is the string I entered symmetric?");
		System.out.println(test3.isSymStr2());
//		System.out.println(test3.isSymStr());
	}

}
