package recursion.combinationpermutation;

public class StringPaths {
	private void paths(StringBuffer path, int m, int n, int M, int N) {
		if(m == M-1 && n == N-1) {
			System.out.println(path.toString());
			return;
		}
		if(n < N-1) {
			path.append("R");
			paths(path, m, n+1, M, N);
			path.deleteCharAt(path.length() - 1);
		}
		if(m < M-1) {
			path.append("D");
			paths(path, m+1, n, M, N);
			path.deleteCharAt(path.length() - 1);
		}
		
		return;
	}
	
	public void paths(int M, int N) {
		paths(new StringBuffer(), 0, 0, M, N);
	}
}
