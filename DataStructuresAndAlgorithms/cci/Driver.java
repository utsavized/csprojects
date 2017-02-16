package cci;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "abcdef";
		String [] T = {"a", "bc", "ade", "af", "aa", "uts", "afi", "abcdef"};
		Hard h = new Hard();
		h.checkTagainstS(s, T);

	}

}
