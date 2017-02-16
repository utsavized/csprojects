package searching;

public class KRank {
	public int kLargest(int [] A, int k) {
		return kSmallest(A, 0, A.length - 1, A.length - k + 1);
	}
	
	public int kSmallest(int [] A, int k) {
		return kSmallest(A, 0, A.length - 1, k);
	}
	
	
	
	private int kSmallest(int [] A, int low, int high, int item) {
		if(low == high) 
			return A[low];
		
		int pivot = partition(A, low, high);
		int rank = pivot - low + 1; 
		if(rank == item)
			return A[pivot];
		if(rank > item) 
			return kSmallest(A, low, pivot - 1, item);
		else
			return kSmallest(A, pivot + 1, high, item - rank);
				
		
	}
	
	/**
	 * Partitions the array based on a random pivot
	 * @param array
	 * @param start
	 * @param end
	 * @return
	 */
	private int partition(int [] array, int start, int end) {
		/*
		 * Let's look at how the partitioning works:
		 * First, we pick a random index within the array size
		 * Then we swap the random index, with the first element 
		 * of the array. 
		 * Now, until low is lower than high, we keep swapping
		 * any higher element on index[low] with any lower element on
		 * index[high]. ** Take caution and check of out of bounds here.
		 * Once done, our high should be exactly at the element,
		 * that should be swapped back with the pivot, i.e. currently
		 * the first element of the array.
		 * We then return the index of where the pivot after swapping
		 * is i.e. index of high.
		 */
		int random = getRandom(start, end);
		swap(array, start, random);
		int pivot = array[start];
		int low = start + 1;
		int high = end;

		//This while loop is pretty straightforward
		while(low < high) {
			//These inner loops are tricky as they my run out of bounds
			//We need to check be careful of that.
			while(low <= end && array[low] <= pivot) low++;
			while(high >= start && array[high] > pivot) high--;
			//We also need to check if low is lower than high
			//before swapping as sometimes low and high have crossed
			//places, in which case, we should NOT be swapping.
			if(low < high) swap(array, high, low);
		}	
		swap(array, start, high);
		return high;
	}
	
	/**
	 * Generates random number in the specified range
	 * @param min
	 * @param max
	 * @return
	 */
	private int getRandom(int min, int max) {
		//Math.random() generated random number between 0 and 1.
		//Math.random() * (max - min) increases the range by (max - min)
		//min + Math.random() * (max - min) shifts the range by min value
		//Therefore random number is generated in given range
		return (min + (int) (Math.random() * (max - min)));
	}
	
	private void swap(int [] A, int a, int b) {
		int temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
}
