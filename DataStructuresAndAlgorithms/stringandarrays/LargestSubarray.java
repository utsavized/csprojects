package stringandarrays;

public class LargestSubarray {
	public int largestSeqSum(int [] array) {
	     
		 System.out.print("Array: ");
		 printArray(array, 0, array.length - 1);
		 System.out.println("");
		 
		 int length = array.length;
	     if(length == 0)
	         return 0;
	     if(length == 1)
	         return array[0];
	     
	     int max = array[0];
	     int currentMax = 0;
	     int cs, ce, start, end;
	     cs = ce = start = end = 0;
	     
	     for(int i = 0; i < length; i++) {
	         ce = i;
	         if(currentMax + array[i] < array[i]) {
	        	 currentMax = array[i];
	        	 cs = i + 1;
	         }
	         if(max < currentMax) {
	        	 max = currentMax;
	        	 start = cs;
	        	 end = ce;
	         }
	     }
	     
	     System.out.print("Sub: ");
	     printArray(array, start, end);
	     return max;
	 }
	 
	 private void printArray(int [] array, int start, int end) {
		 for(int i = start; i <= end; i++) {
			 System.out.print(array[i] + " ");
		 }
	 }
}
