package recursion;

public class PalindromeString {
	//Palindrome Wrapper
	public boolean isPalindrome(String str) {
		str = str.toLowerCase();
		str = str.trim();
		StringBuffer s = new StringBuffer();
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) != ' ')
				s.append(str.charAt(i));
		}
		return palindrome(s.toString());
	}
	
	//Palindrime
	private boolean palindrome(String str) {
		int length = str.length();
		if(length <= 1)
			return true;
		else
			return str.charAt(0) == str.charAt(length-1) && palindrome(str.substring(1, length - 1));
	}
}
