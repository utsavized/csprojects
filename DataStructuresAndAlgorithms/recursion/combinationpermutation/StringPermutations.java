package recursion.combinationpermutation;

public class StringPermutations {
	/**
	 * Permuatation of a string
	 * @param str
	 */
	public void getPermutations(String str) {
		/**
		 * This method will use an auxiliary bit vector
		 * to keep track of which elements have already been chosen.
		 */
		str.trim();
		boolean [] chosen = new boolean[str.length()];
		char [] chr = str.toCharArray();
		StringBuffer out = new StringBuffer();
		recursePermutation(chr, chosen, out);
	}
	
	//Permutations of a string
	private void recursePermutation(char [] chr, boolean [] chosen, StringBuffer out) {
		int chrLen = chr.length;
		//If our permutation string becomes equal to our 
		//Input string, we print and exit recursion
		if(out.length() == chrLen)
			System.out.println(out.toString());
		
		else {
			//We loop though each character in the input string
			for(int i = 0; i < chrLen; i++) {
				//But we only operate on those that have not been 
				//marked as chosen in our bit vector
				if(!chosen[i]) {
					out.append(chr[i]);
					chosen[i] = true;
					recursePermutation(chr, chosen, out);
					out.deleteCharAt(out.length() - 1);
					chosen[i] = false;
				}
			}
		}
	}
	
	/**
	 * Permutations of a string without using
	 * bit vector.
	 * @param str
	 */
	public void permutations(String str) {
		/**
		 * In this method, we will NOT use a bit vector
		 */
		StringBuilder rest = new StringBuilder(str);
		StringBuilder perm = new StringBuilder();
		permutations(perm, rest, str.length());
	}
	
	public void permutations(StringBuilder perm, StringBuilder rest, int length){
		if(perm.length() == length) {
			System.out.println(perm.toString());
			return;
		}
		
		for(int i = 0; i < rest.length(); i++) {
			perm.append(rest.charAt(i));
			rest.deleteCharAt(i);
			permutations(perm, rest, length);
			rest.insert(i, perm.charAt(perm.length() - 1));
			perm.deleteCharAt(perm.length() - 1);
		}
	}
	
	
}
