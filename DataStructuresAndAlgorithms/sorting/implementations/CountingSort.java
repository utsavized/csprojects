package sorting.implementations;

public class CountingSort {
	public void countingsort(int [] array, int range) {
		int [] count = new int[range];
		for(int i : array) {
			count[i]++;
		}
		
		int index = 0;
		for(int i = 0; i < count.length; i++) {
			for(int j = 0; j < count[i]; j++) {
				array[index++] = i;
			}
		}
	}
}
