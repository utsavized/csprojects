package amz;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class PermToSet {
	public void permToSet(String perm) {
	    //Split permutations into tokens
	    String [] perms = perm.split(", ");
	    
	    //To keep track of the sets
	    HashMap<String, LinkedList<String>> hm = new HashMap<String, LinkedList<String>>();
	    
	    for(String p : perms) {
	        char [] pc = p.toCharArray();
	        Arrays.sort(pc);
	        if(!hm.containsKey(new String(pc))) {
	            LinkedList<String> list = new LinkedList<String>();
	            list.add(p);
	            hm.put(new String(pc), list);
	        }
	        else {
	            LinkedList<String> list = hm.get(new String(pc));
	            list.add(p);
	            hm.put(new String(pc), list);
	        }
	    }
	    
	    Collection<LinkedList<String>> c = hm.values();
	    Iterator<LinkedList<String>> i = c.iterator();
	    while(i.hasNext()) {
	    	LinkedList<String> list = i.next();
	    	Iterator<String> j = list.iterator();
	    	System.out.print("(");
	    	while(j.hasNext()) {
	    		System.out.print(j.next());
	    		if(j.hasNext())
	    			System.out.print(", ");
	    	}
	    	System.out.print(") ");
	    } 
	}
}
