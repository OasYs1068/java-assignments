package exercise1;

class TestArrays {
	
	public static void printArray(int[] array) {
		System.out.print('<');
		for ( int i = 0; i < array.length-1; i++ ) {
			// print an element
			System.out.print(array[i]);
			// print a comma delimiter if not the last element
			if ( i+1< array.length-1 ) {
				System.out.print(", ");
			}
		}
		System.out.println('>');
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array1 = {2,3,5,7,9,11,13,17,19};
		int[] array2 = array1;// static distribution
		int matrix[][] = new int[5][];
		
		for(int i=0;i<5;++i) {
			matrix[i]=new int[i+1];
		}//dynamic distribution
		
		printArray(array1);
		
		for(int i=0;i<9;i++) {
			array2[i] = i;
		}
		
		printArray(array2);
		printArray(array1);
		
		for(int i=0;i<5;++i) {
			for(int j=0;j<=i;++j) {
				matrix[i][j]=i*j;
			}
		}
		
		for(int i=0;i<5;i++) {
			System.out.print("matrix["+i+"] is ");
			printArray(matrix[i]);
		}
	}

}
