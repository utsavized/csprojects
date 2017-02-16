package stringandarrays;

public class InterleavedString {
	//If string is interleave of other 2 strings
		public boolean isShuffle(String str1, String str2, String str3) {
			if(str1.length() + str2.length() != str3.length())
				return false;
			
			int ps1, ps2, ps3;
			ps1 = ps2 = ps3 = 0;
			
			while(ps3 < str3.length()) {
				if(ps1 < str1.length() && str3.charAt(ps3) == str1.charAt(ps1))
					ps1++;
				else if(ps2 < str2.length() && str3.charAt(ps3) == str2.charAt(ps2))
					ps2++;
				else
					return false;
				ps3++;
			}
			return true;
		}
}
