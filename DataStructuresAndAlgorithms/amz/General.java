package amz;

import java.util.HashMap;

public class General {
	
	
	/**
	 * AMZ 84
	 * @param letter
	 * @param dict
	 */
	public void printWords(char [][] letter, HashMap<String, String> dict) {
	    int m = letter.length;
	    int n = letter[0].length;
	    
	    StringBuilder word = new StringBuilder();
	    for(int i = 0; i < m; i++) {
	        for(int j = 0; j < n; j++) {
	            int left = j; 
	            int right = j + 1;
	            int up = i;
	            int down = i + 1;
	            while(left >= 0) {
	                word.append(letter[i][left--]);
	                if(dict.containsKey(word.toString()))
	                    System.out.println(word.toString());
	            }
	            word.delete(1, word.length());
	            while(right < n) {
	                word.append(letter[i][right++]);
	                if(dict.containsKey(word.toString()))
	                    System.out.println(word.toString());
	            }
	            word.delete(0, word.length());
	            while(up >= 0) {
	                word.append(letter[up--][j]);
	                if(dict.containsKey(word.toString()))
	                    System.out.println(word.toString());
	            }
	            word.delete(1, word.length());
	            while(down < m) {
	                word.append(letter[down++][j]);
	                if(dict.containsKey(word.toString()))
	                    System.out.println(word.toString());
	            }
	            word.delete(0, word.length());
	        }
	    }
	}
	
	public int find(int [] array, int k) {
	    int start = 0;
	    int end = array.length - 1;
	    int pivot = (start + end) / 2;
        while(start <= end) {
	        if(array[pivot] == k) {
	            while(pivot > 0 && array[pivot - 1] == array[pivot]) pivot--;
	            return pivot;
	        }
	        else if(array[pivot] > k)
	            end = pivot - 1;
	        else
	            start = pivot + 1;
	        
	        pivot = (start + end) / 2;
	    }
	    return -1;
	}
	
	public int nearestLarger(int num) {
		if(num < 10)
			return num;
		
		char [] n = Integer.toString(num).toCharArray();
		boolean lastDigitEqual = true;
		
		for(int i = 0; i < n.length; i++) {
			int picked = Integer.parseInt(Character.toString(n[i]));
			int pickedIndex = i;
			int j = i + 1;
			int lowestDifference = 10;
			
			if(j < n.length) {
				int lowestNum = Integer.parseInt(Character.toString(n[j]));
				
				for(j = i + 1; j < n.length; j++) {
					int compare = Integer.parseInt(Character.toString(n[j]));	
					int currentDiff = compare - picked;
					if(currentDiff == 0) {
						lowestDifference = currentDiff ;
						pickedIndex = j;
						lastDigitEqual = true;
						break;
					}
					
					if(lastDigitEqual) {
						if(currentDiff >= 0 && currentDiff  < lowestDifference) {
							lowestDifference = currentDiff ;
							pickedIndex = j;
						}
					}
					
					else {
						if(compare < lowestNum) {
							pickedIndex = j;
						}
					}
						
				}
				char temp = n[i];
				n[i] = n[pickedIndex];
				n[pickedIndex] = temp;
				if(n[i] != n[pickedIndex])
					lastDigitEqual = false;
			}
		}
		
		return Integer.parseInt(new String(n));
	} 
	
	
	

	
}
