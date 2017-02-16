package sorting.implementations;

public class SelectionSort {
	public void selectionsort(int[] x) {
	    for (int i=0; i < x.length-1; i++) {
	        int minIndex = i;      // Index of smallest remaining value.
	        for (int j= i + 1; j < x.length; j++) {
	            if (x[minIndex] > x[j]) {
	                minIndex = j;  // Remember index of new minimum
	            }
	        }
	        if (minIndex != i) { 
	            //Exchange current element with smallest remaining.
	            int temp = x[i];
	            x[i] = x[minIndex];
	            x[minIndex] = temp;
	        }
	    }
	}
}
