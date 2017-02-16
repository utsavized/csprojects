package recursion.combinationpermutation;

public class Subsets {
	private void subsets(StringBuffer chosen, StringBuffer rest) {
		if(rest.length() == 0) {
			System.out.println(chosen.toString());
		}
		else {
			subsets(chosen.append(rest.charAt(0)), getRest(rest, 0));
			chosen.deleteCharAt(chosen.length() - 1);
			subsets(chosen, getRest(rest, 0));
		}
	}
	
	public void subsets(String str) {
		StringBuffer rest = new StringBuffer(str);
		StringBuffer chosen = new StringBuffer();
		subsets(chosen, rest);
	}
	
	private void perm(StringBuffer chosen, StringBuffer rest) {
		if(rest.length() == 1) {
			System.out.println(chosen.toString() + rest.toString());
			return;
		}
		
		
		for(int i = 0; i < rest.length(); i++) {
			chosen.append(rest.charAt(i));
			perm(chosen, getRest(rest, i));
			chosen.deleteCharAt(chosen.length() - 1);
		}
	}
	
	private StringBuffer getRest(StringBuffer str, int chosen) {
		StringBuffer rest = new StringBuffer();
		for(int i = 0; i < str.length(); i++) {
			if(i != chosen)
				rest.append(str.charAt(i));
		}
		return rest;
	}
	
	public void perm(String str) {
		StringBuffer chosen = new StringBuffer();
		StringBuffer rest = new StringBuffer(str);
		perm(chosen, rest);
		
	}
}
