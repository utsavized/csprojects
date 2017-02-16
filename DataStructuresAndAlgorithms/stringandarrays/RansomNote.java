package stringandarrays;

import java.util.HashMap;

public class RansomNote {
	/*
	 * A ransom note can be formed by cutting words out of a magazine to form a new sentence. 
	 * How would you figure out if a ransom note (string) can be formed from a given magazine (string)?
	 */
	public boolean isRansomNotePossible(String mag, String note) {
		if(mag == null || note == null) return false;
		//We cannot form a ransom note if magazine 
		//is shorter than the note itself
		if(mag.length() < note.length()) return false;
		if(note.length() == 0) return false;
		
		char [] magC  = mag.toCharArray();
		char [] noteC = note.toCharArray();
		
		//Let us now add the magazine character count to a HashMap
		HashMap<Character, Integer> hm = new HashMap<>();
		for(char c : magC) {
			if(hm.containsKey(c)) {
				Integer count = hm.get(c);
				hm.put(c, ++count);
			}
			else
				hm.put(c, 1);
		}
		
		//Now let us check if ransom note can be formed
		for(char c : noteC) {
			if(hm.containsKey(c)) {
				Integer count = hm.get(c);
				if(++count == 0) hm.remove(c);
				else hm.put(c, count);
			}
			else
				return false;
		}
		
		//This means all letter required for ransom note exist in hash map.
		return true;
	}
}
