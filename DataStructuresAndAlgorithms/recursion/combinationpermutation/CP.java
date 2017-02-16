package recursion.combinationpermutation;

public class CP {
	
	public void largestContiguousSubArray(int [] array) {
	    //TODO: Validations
	    
	    //Initialization of pointers, etc.
	    int length = array.length;
		int max, start, end;
	    max = start = end = 0;
	    
	    for(int i = 0; i < length; i++) {
	        int e, m, sum;
	        e = i; 
	        m = sum = 0;
	        for(int j = i; j < length; j++) {
	            sum += array[j];
	            if(sum > m) {
	                m = sum;
	                e = j;       
	            }
	        }
	        if(m > max) {
	            max = m;
	            start = i;
	            end = e;
	        }
	    }
	    
	    for(int i = start; i <= end; i++) {
	        System.out.print(array[i] + " "); 
	    }
	}
}
