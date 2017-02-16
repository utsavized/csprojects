package cci;

import java.util.HashMap;

public class Hard {
	private HashMap<String, Boolean> createSubsets(HashMap<String, Boolean> hm, String s, StringBuilder out) {
	    if(s.length() == 1) {
	        out.append(s.charAt(0));
	        hm.put(out.toString(), true);
	        out.deleteCharAt(out.length() - 1);
	        return hm;
	    }

	    for(int i = 0; i < s.length(); i++) {
	        out.append(s.charAt(i));
	        hm.put(out.toString(), true);
	        hm = createSubsets(hm, s.substring(i + 1, s.length()), out);
	        out.deleteCharAt(out.length() - 1);
	    }
	    
	    return hm;
	}

	public void checkTagainstS(String s, String [] T) {
	    HashMap<String, Boolean> hm = new HashMap<>();
	    hm = createSubsets(hm, s, new StringBuilder());
	    for(String t : T) {
	        if(hm.containsKey(t))
	            System.out.println(t + ": " + true);
	        else
	            System.out.println(t + ": " + false);
	    }
	}

}
