package recursion;

public class BinarySearch {
final int NOT_FOUND = -1;
	
	//Binary Search Wrapper
	public int find(int number, int[] array) {
		int length = array.length;
		if(length == 0)
			return this.NOT_FOUND;
		int upper = length - 1;
		int lower = 0;
		return binarySearch(array, lower, upper, number);
	}
	
	//Recursive Binary Search
	private int binarySearch(int[] array, int lower, int upper, int target) {
		int center = (upper + lower)/2;
		if(lower >= upper && array[center] != target)
			return this.NOT_FOUND;
		else if(array[center] == target)
			return center;
		else if(array[center] > target) 
			return binarySearch(array, lower, center - 1, target);
		else
			return binarySearch(array, center + 1, upper, target);
	}
}
