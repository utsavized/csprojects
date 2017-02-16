package trees;

public class BSTEqual {
	/**
	 * If two arrays are used to form individual BSTs,
	 * will the two BSTs be equal?
	 * @param A
	 * @param B
	 * @return
	 */
	public boolean equalBST(int [] A, int [] B) {
	    if(A.length != B.length)
	        return false;
	    if(A[0] != B[0])
	        return false;
	    
	    int root = A[0];
	    
	    for(int i = 1; i < A.length; i++) {
	        if(A[i] == B[i])
	            continue;
	        else if(i < A.length - 1) {
	            if(A[i] == B[i + 1] && A[i + 1] == B[i] && checkSides(A[i], B[i], root)) {
	                i++;
	                continue;
	            }
	        }
	        return false; 
	    }
	    return true;
	}

	private boolean checkSides(int a, int b, int k) {
	    if(a < k && b > k)
	        return true;
	    else if(b < k && a > k)
	        return true;
	    else
	        return false;
	}
}
