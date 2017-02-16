package recursion;

public class Parantheses {
	/**
	 * Print all possible valid parenthesis combo.
	 * HAS BUGS!
	 * @param path
	 * @param l
	 * @param r
	 * @param len
	 */
	private void par(StringBuffer path, int l, int r, int len) {
		if(r > l)
			return;
		if(l == len && r == len) {
			System.out.println(path.toString());
			return;
		}
		if(path.length() == 1 && path.charAt(0) == ')')
			return;
		
		if(l < len) {
			path.append("(");
			par(path, l+1, r, len);
			path.deleteCharAt(path.length() - 1);
		}
		if(r < len) {
			path.append(")");
			par(path, l, r+1, len);
			path.deleteCharAt(path.length() - 1);
		}
		
		return;
	}
	
	public void par(int len) {
		par(new StringBuffer(), 0, 0, len);
	}
}
