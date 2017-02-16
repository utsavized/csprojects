package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ThreeSum {
	/**
	 * Find all triplets that sum to a given number
	 * @param num
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> triplets = new ArrayList<ArrayList<Integer>>();
        
        int length = num.length;
        if(length < 3)
            return triplets;
        
        Arrays.sort(num);
        
        HashSet<ArrayList<Integer>> hs = new HashSet<ArrayList<Integer>>();
        
        for(int i = 0; i < length; i++) {
            int j, end;
            end = length - 1;
            j = end - 1;
            while(end > i) {
                int twoSum = num[i] + num[end];
                int third = 0 - twoSum;
                
                ArrayList<Integer> triplet = new ArrayList<Integer>();
                
                while(j > i) {
                    if(num[j] < third)
                        break;
                    if(num[j] == third) {
                       triplet.add(num[i]);
                       triplet.add(num[j]);
                       triplet.add(num[end]);
                       break;
                    }
                    j--;
                }
            
                end--;
                j = end - 1;
            
                if(triplet.size() > 0 && !hs.contains(triplet)) {
                    triplets.add(triplet);
                    hs.add(triplet);
                }
            }
        }
        
        return triplets;
    }
	
	/**
	 * Print all triplets that sum to a given number
	 * @param array
	 * @param k
	 */
	public void threeSum2(int [] array, int k) {
		/**
		 * Sort array, then use HashMap
		 * O(nlogn) Time; O(n) Space
		 */
		if(array.length < 3)
			return;
		
		HashSet<Integer> hs = new HashSet<>();
		Arrays.sort(array);
		
		for(int i = 0; i < array.length; i++) hs.add(array[i]);
		for(int i = 0; i < array.length; i++) {
			if(i + 1 < array.length) {
				int sum = array[i] + array[i+1];
				if(hs.contains(k - sum)) {
					hs.remove(k - sum);
					System.out.println("{" + array[i] + "," + array[i+1] + "," + (k - sum) + "}");
				}
			}
		}
	}
}
