package stringandarrays;

public class RepeatedCharacter {
	public Character repeated(String str) {
		int [] flags = new int[256];
		int len = str.length();
		if(len == 0)
			return null;
		else if(len <= 1)
			return str.charAt(0);
		int prev = (int) str.charAt(0);
		flags[prev] = 2;
		for(int i = 1; i < len; i++) {
			int cur = (int) str.charAt(i);
			if(cur != prev) {
				if(flags[prev] == 1) 
					return Character.toChars(prev)[0];
				else
					flags[cur]++;
			}
			else {
				flags[cur]++;
			}
			prev = cur;
		}
		return null;
	}
}
