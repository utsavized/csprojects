package dynamicprogramming;

import java.util.HashMap;

public class Fibonacci {
	public int fibDP(int n) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		return fib(n, hm);
	}
	
	private int fib(int n, HashMap<Integer, Integer> hm) {
		if(n <= 2)
			return 1;
		else {
			if(!hm.containsKey(n)) {
				int f = fib(n-1, hm) + fib(n-2, hm);
				hm.put(n, f);
				return f;
			}
			else
				return hm.get(n);
		}
	}
}
