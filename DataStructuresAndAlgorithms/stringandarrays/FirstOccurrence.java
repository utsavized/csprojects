package stringandarrays;

import java.util.HashMap;

public class FirstOccurrence {
	/*First letter in input string that only occurs once

	PC:
	- Scan through input string
	- Insert each character to HashMap<Character, Integer>
	  - If exists, increment count by 1
	  - Else, add with count of 1
	- Scan through input string
	- For each char, check count on HashMap
	  - Return the first char found that has a count of 1.

	Time: O(n)
	Space: O(n)*/

	public Character firstOccurrence(String str) {
	    //Checks
	    if(str == null)
	        return null;
	    
	    if(str.length() == 1)
	        return str.charAt(0);
	    
	    char [] ch = str.toCharArray();
	    HashMap<Character, Integer> hm = new HashMap<>();
	    
	    //Scan through input string
	    for(char c : ch) {
	        Character co = new Character(c);
	        
	        //If exists, increment count by 1
	        if(hm.containsKey(co)) {
	            int count = hm.get(co);
	            hm.put(co, count + 1);
	        }
	        
	        //Else, add with count of 1
	        else {
	            hm.put(co, 1);
	        }
	    }
	    
	    //Scan through input string
	    for(char c : ch) {                                    //For each char, check count on HashMap
	        Character co = new Character(c);
	        if(hm.containsKey(co) && hm.get(co) == 1)         //Return the first char found that has a count of 1
	            return co;
	    }
	    
	    //All characters have duplicates
	    return null;
	}
}
