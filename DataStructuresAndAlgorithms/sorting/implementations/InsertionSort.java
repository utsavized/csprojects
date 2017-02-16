package sorting.implementations;

public class InsertionSort {
	public int[] insertionsort(int[] input){
		int temp;
		
	    for (int i = 1; i < input.length; i++) {
		    for(int j = i ; j > 0 ; j--){
			    if(input[j] < input[j-1]){
				    temp = input[j];
				    input[j] = input[j-1];
				    input[j-1] = temp;
			    }
		    }
	    }
	    return input;
	}
}
