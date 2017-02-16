package stringandarrays;

public class RunLengthEncoding {
	/**
	 * Performs RLE on a given string
	 * O(n) time; O(n) space
	 * @param str
	 * @return
	 */
	public String encode(String str) {
		if(str == null) return null;
		if(str.length() < 3) return str;
		
		int [] ascii = new int[256];
		StringBuilder sb = new StringBuilder();
		char [] ch = str.toCharArray();
		
		for(int i = 0; i < ch.length; i++)
			ascii[(int)ch[i]]++;
		
		for(int i = 0; i < ch.length; i++) {
			sb.append(ch[i]);
			sb.append(ascii[(int)ch[i]]);
			int j = i;
			while(j + 1 < ch.length && ch[j + 1] == ch[j])
				j++;
			i = j + 1;
		}
		
		return (sb.length() < str.length()) ? sb.toString() : str;
		
		
	}
}
