package stringandarrays;

import java.util.HashSet;

public class LongestConsecutiveSubsequence {
	/**
	 * 	Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     * 	For example,
	 * 	Given [100, 4, 200, 1, 3, 2],
	 * 	The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
	 * 	Your algorithm should run in O(n) complexity.
	 * @param num
	 * @return
	 */
	public int longestConsecutive(int[] num) {
        if(num.length < 2)
            return num.length;
        
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i : num) {
            hs.add(i);
        }
        
        int maxCount = 0;
        
        for(int i : num) {
            int count = 0;
            if(hs.contains(i)) {
                hs.remove(i);
                count++;
                int runner = i + 1;
                while(hs.contains(runner)) {
                    hs.remove(runner++);
                    count++;
                }
                runner = i - 1;
                while(hs.contains(runner)) {
                    hs.remove(runner--);
                    count++;
                }
                maxCount = Math.max(maxCount, count);
                if(hs.size() == 0)
                    break;
            }
        }
        
        return maxCount;
    }
}
