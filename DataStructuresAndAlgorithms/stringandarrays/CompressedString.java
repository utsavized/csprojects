package stringandarrays;

public class CompressedString {
	public String compressedStr(String str) {
		int [] flag = new int[256];
		flag[(int) str.charAt(0)] = 1;
		int uniqueChars = 1;
		for(int i = 1; i < str.length(); i++) {
			if(flag[(int)str.charAt(i)] == 0)
				uniqueChars++;
			flag[(int)str.charAt(i)]++;
		}
		
		int newLength = uniqueChars * 2;
		if( newLength > str.length())
			return str;
		else {
			char [] ch = new char[newLength];
			for(int i = 0; i < ch.length; i++) {
				char c = str.charAt(i);
				ch[i++] = c;
				ch[i] = (char) (flag[(int)c] + '0'); 
			}
			return String.valueOf(ch);
		}	
	}
}
