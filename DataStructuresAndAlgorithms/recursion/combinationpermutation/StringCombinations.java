package recursion.combinationpermutation;

public class StringCombinations {
	//Combinations of a string wrapper
	public void getCombinations(String str) {
		str = str.trim();
		StringBuffer out = new StringBuffer();
		int length = str.length();
		int start = 0;
		int end = length;
		recurseCombinations(str, out, start, end);
	}
	
	//Combinations of a string
	private void recurseCombinations(String str, StringBuffer out, int start, int end) {
		if(end - start == 0)
			return;
		else {
			for(int i = start; i < end; i++) {
				out.append(str.charAt(i));
				System.out.println(out.toString());
				recurseCombinations(str, out, ++start, end);
				out.deleteCharAt(out.length() - 1);
			}
		}
	}
}
