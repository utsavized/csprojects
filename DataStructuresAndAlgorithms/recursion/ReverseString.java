package recursion;

public class ReverseString {
	//Reverse
	public String reverse(String str) {
		int left = 0;
		int right = str.length() - 1;
		char [] ch = str.toCharArray();
		return reverseRecurse(ch, left, right);
	}
	
	//Reverse recursion
	private String reverseRecurse(char [] ch, int left, int right) {
		if(left >= right)
			return new String(ch);
		else {
			char temp = ch[left];
			ch[left] = ch[right];
			ch[right] = temp;
			return reverseRecurse(ch, ++left, --right);
		}
	}
	
	//Reverse words
	public String reverseWords(String str) {
		String [] words = str.split(" ");
		StringBuffer temp = new StringBuffer();
		for(String s : words) {
			s = reverse(s);
			temp.append(s);
		}
		return reverse(temp.toString());
	}
}
