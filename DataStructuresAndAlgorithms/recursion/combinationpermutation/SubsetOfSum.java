package recursion.combinationpermutation;

public class SubsetOfSum {
	public static void getSubsets(int [] array, int max) {
		getSubsets(array, new int[2], max, 0, 0);
	}

	private static void getSubsets(int [] array, int [] chosen, int max, int start, int count) {
		if(count == max) { 
			System.out.println("{" + chosen[0] + ", " + chosen[1] + "};");
			return;
		}
		
		for(int i = start; i < array.length; i++) {
			chosen[count] = array[i];
			getSubsets(array, chosen, max, i + 1, count + 1);
			
		}
	}
}
