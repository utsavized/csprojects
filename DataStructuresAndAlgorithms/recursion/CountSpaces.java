package recursion;

public class CountSpaces {
	//Count white spaces recursively
	public int countWSRecursively(String str, int count) {
		int length = str.length();
		for(int i = 0; i < length; i++) {
			if(str.charAt(i) == ' ')
				return countWSRecursively(str.substring(i + 1), ++count);
		}
		return count;
	}
}
