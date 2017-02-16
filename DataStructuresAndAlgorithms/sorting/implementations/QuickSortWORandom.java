package sorting.implementations;

public class QuickSortWORandom {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] ar = {5,4,3,2,1};
		quickSort(ar);

	}

	static void quickSort(int[] ar) {

		if(ar.length > 1) {
			quicksort(ar, 0, ar.length - 1);
		}
		printArray(ar);
	}

	static void quicksort(int [] ar, int start, int end) {
		int length = end - start + 1;
		if(length < 2)
			return;
		if(length == 2) {
			if(ar[start] > ar[end])
				swap(ar, start, end);
			return;
		}

		int pivot = partition(ar, start, end);
		quicksort(ar, start, pivot - 1);
		quicksort(ar, pivot + 1, end);
	}


	static int partition(int[] ar, int start, int end) {
		int length = end - start + 1;
		int pivotIndex = start;
		int pivot = ar[start++];
		while(start < end) {
			while(start < length && ar[start] <= pivot) start++;
			while(end >= 0 && ar[end] > pivot) end--;
			if(start < end) swap(ar, start, end);
		}
		swap(ar, pivotIndex, end);
		return end;
	}  

	static void swap(int [] ar, int a, int b) {
		int temp = ar[a];
		ar[a] = ar[b];
		ar[b] = temp;
	}

	static void printArray(int[] ar) {
		for(int n: ar){
			System.out.print(n+" ");
		}
		System.out.println("");
	}


}
