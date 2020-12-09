package symmetricStrings;



public class SymString {
	private String str = "01010";
	private int temp = 0, flag = 0;
	StringBuffer strBuffer;
	String string;

	public SymString(String paraStr) {
		str = paraStr;
		strBuffer = new StringBuffer(str);
		string = new String(str);
	}
	

	public int getLength() {
		return (strBuffer.length());
	}

	public boolean isSymStr() {
		for (temp = 0; temp < getLength(); temp++) {
			if (strBuffer.charAt(temp) == strBuffer.charAt(getLength() - (temp + 1))) {
				flag++;
			}
		}
		if (flag == getLength()) {
			return true;
		} 
		else {
			return false;
		}
	}

	public boolean isSymStr2() {
		if (strBuffer.reverse().toString().equals(string)) {
			return true;
		} else {
			return false;
		}
	}
}
