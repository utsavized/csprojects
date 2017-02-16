package stringandarrays;

public class StringProduct {
	public int stringProduct(String s1, String s2) {
		int s1Sign, s2Sign; s1Sign = s2Sign = 1;
		int s1Int, s2Int; s1Int = s2Int = 0;
		char [] ch1 = s1.toCharArray();
		char [] ch2 = s2.toCharArray();
		
		for(int i = 0; i < ch1.length; i++) {
			if(ch1[i] == '-')
				s1Sign = -1;
			else {
				int num = ((int) ch1[i] - '0');
				s1Int  = s1Int + (num * (int) ((s1Sign == -1) ? Math.pow(10, ch1.length - i - 1) : Math.pow(10, ch1.length - i - 2)));
				
			}
		}
		
		for(int i = 0; i < ch2.length; i++) {
			if(ch2[i] == '-')
				s2Sign = -1;
			else {
				int num = ((int) ch2[i] - '0');
				s2Int = s2Int + (num * (int) ((s2Sign == -1) ? Math.pow(10, ch2.length - i - 1) : Math.pow(10, ch2.length - i - 1)));
			}
		}
		
		s1Int = s1Int * s1Sign;
		s2Int = s2Int * s2Sign;
		
		return s1Int * s2Int;
	}
}
