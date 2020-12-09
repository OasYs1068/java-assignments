package exercise3;

final class TestStrSearch {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringSearch test1 = new StringSearch();
		
		System.out.println(test1.isSubString("cat", "Its a cat."));
		System.out.println(test1.isSubString("bat", "Its a cat."));
		System.out.println(test1.isSubString("cac", "Its a cat."));
		System.out.println(test1.isSubString("Its", "Its a cat."));
		System.out.println(test1.isSubString("a", "Its a cat."));
		System.out.println(test1.isSubString("hat","hehathihat"));
		System.out.println(test1.isSubString("lmao", "It is one's sincere expression to say lmfao when he feels pleased."));
 	}

}
