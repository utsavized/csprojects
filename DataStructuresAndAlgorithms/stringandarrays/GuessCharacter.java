package stringandarrays;

import java.util.HashMap;

import reusableobjects.Hit;


public class GuessCharacter {
	public Hit calculate(String guess, String solution) {
	     Hit hits = new Hit();
	     HashMap<Character, Boolean> hM = new HashMap<>();
	     for(int i = 0; i < guess.length(); i++) {
	         if(guess.charAt(i) != solution.charAt(i)) {
	             if(!hM.containsKey(guess.charAt(i)))
	                 hM.put(guess.charAt(i), true);
	         }
	         else {
	             hits.exactHits++;
	             if(hM.containsKey(guess.charAt(i)))
	            	 hM.remove(guess.charAt(i));
	         }
	     }
	     for(int i = 0; i < solution.length(); i++) {
	         if(hM.containsKey(solution.charAt(i)))
	             hits.pseudoHits++;
	     }
	     
	     return hits;
	 }
}
