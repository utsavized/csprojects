package numbers;

public class RemoveDuplicatesInPlace {
	/**
	 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
	 * Do not allocate extra space for another array, you must do this in place with constant memory.
	 * For example,
	 * Given input array A = [1,1,2],
	 * Your function should return length = 2, and A is now [1,2].
	 * @param A
	 * @return
	 */
	public int removeDuplicates(int[] A) {
        /**
         * Pretty straight forward.
         * Loop through looking 1 step in advance.
         * If duplicate found, create inner loop to
         * loop until we find a non-duplicate. Shift
         * everything 1 step back as we go forward, 
         * and update length.
         */
		int length = A.length;
        if(length < 2)
            return length;
        int count = length;
        int j = 0;
        int dups = 0;
        for(int i = 0; i < length; i++) {
            j = i + 1;
            while(j < length && A[i] == A[j]) {
                j++;
                dups++;
                count--;
            }
            
            if(j < length) {
                if(dups > 0) {
	            	while(j < length) {
	                    A[j - dups] = A[j];
	                    j++;
	                }
	                length = count;
	                dups = 0;
                }
	            else
	            	continue;
            }
            else
            	return count;
        }
        
        return length;
    }
}
