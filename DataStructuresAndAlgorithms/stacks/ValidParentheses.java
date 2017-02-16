package stacks;

import java.util.Stack;

public class ValidParentheses {
	/**
	 * Check if parenthesis are valid
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
        /*
         * Here is the idea:
         * We loop through our string. Every time we encounter
         * an open bracket, we push to the stack, and if it is closed, we pop
         * from the stack. Since we have different kinds of braces, each time we pop,
         * we check if the closed one we encountered is the same kind that we 
         * popped.If not, we return false.
         * 
         * If we finish traversing the string, we check if the stack is empty.
         * Only if the stack is empty, the parentheses are valid.
         * 
         * Also, in the beginning, if the string has only 1 parenthesis, or 
         * begins with an open one, we return false as that is automatically
         * invalid.
         */
		if(s.length() < 2)
            return false;
        
        if(s.charAt(0) == ')' || s.charAt(0) == '}' || s.charAt(0) == ']')
            return false;
        
        char [] cArray = s.toCharArray();
        
        Stack<Character> st = new Stack<Character>();
        
        for(char p : cArray) {
            if(isOpen(p))
                st.push(p);
            else if(isClosed(p)) {
                boolean valid = false;
                if(st.isEmpty()) return false;
                else valid = isValid(st.pop(), p);
                if(!valid) return false;
            }
            else
                return false;
        }
        
        return st.isEmpty();
    }
    
    private boolean isOpen(char c) {
        return (c == '(' || c == '{' || c == '[');
    }
    
    private boolean isClosed(char c) {
        return (c == ']' || c == '}' || c == ')');
    }
    
    private boolean isValid(char o, char c) {
        if(o == '{' && c == '}') return true;
        if(o == '(' && c == ')') return true;
        if(o == '[' && c == ']') return true;
        return false;
    }
}
