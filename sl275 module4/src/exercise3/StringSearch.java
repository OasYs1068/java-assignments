package exercise3;

public class StringSearch {
//		private String str1,str2;
//		String string1,string2;
		
//		public StringSearch(String paraStr1,String paraStr2) {
//			str1 = paraStr1;
//			str2 = paraStr2;
//			string1 = new String(str1);
//			string2 = new String(str2);
//		}
//		
//		public int getLength1() {
//			return string1.length();
//		}
//		
//		public int getLength2() {
//			return string2.length();
//		}
//		
		public boolean isSubString(String string1, String string2) {
			int i=0;
			int flag=0;
			for(int j=0;j<string2.length();++j) {
				if(string1.charAt(i)==string2.charAt(j)) {
					while(string1.charAt(i)==string2.charAt(j)) {
						flag++;//有一个字符相同，计数器+1
						if(i==string1.length()-1||j==string2.length()-1) {
							break;
						}//到达字符串末尾，跳出while循环
						++i;
						++j;
					}				
				}
				
				if(flag>0&&flag<string1.length()) {
					flag=0;//字符仅一部分符合，计数器归零
					j = j-string1.length()+1;//比对结束后倒回长字符串位置
				}
			}
			if(string1.length()==1) {
				for(int k=0;k<string2.length();k++) {
					if(string1.charAt(0)==string2.charAt(k)) {
						return true;
					}
				}
			}
			if(flag==string1.length()) {
				return true;
			}
			else {
				return false;
			}
				
			
			
		}
		
}
