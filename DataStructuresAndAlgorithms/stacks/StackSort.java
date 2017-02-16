package stacks;

import java.util.Stack;

public class StackSort {
	/**
	 * Sorts a stack in ascending order
	 * O(n^2) Time, O(1) space but heavy recursive overhead.
	 * @param s
	 * @return
	 */
	public Stack<Integer> sort(Stack<Integer> s) {
	    /*
	     * This will pop each element from the stack, 
	     * but each pop will occur at a different recursion
	     * level. Then at each recursion level, insert is 
	     * called to either insert in order
	     */
		if (!s.isEmpty()) {
	        Integer x = s.pop();
	        s = sort(s);
	        s = insert(s, x);
	    }
	    return s;
	}

	public Stack<Integer> insert(Stack<Integer> s, Integer x) {
	    /*
	     * If first element, then simply inserts to stack
	     * else compares the top element with element to be inserted
	     * so it can maintain insertion order. If in order, inserts,
	     * if not, recursively pops until it finds right position 
	     * to insert.
	     */
		if (!s.isEmpty()) {  
	        Integer y = s.peek();
	        if (x >= y) {
	            s.pop();
	            insert(s, x);
	            s.push(y);
	        } 
	        else
	            s.push(x);
	    }
	    else
	    	s.push(x);
	    
	    return s;
	}
}
