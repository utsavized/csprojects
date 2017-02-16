package numbers;

public class GCD {
	/**
	 * Calculates GCD
	 * @param a
	 * @param b
	 * @return
	 */
	public int gcd(int a, int b) {
		/*
		 * Let us think of the approach first. How can we get
		 * the GCD between two numbers?
		 * Well, if A divides B perfectly, or vice versa,
		 * then the smaller one is right away the GCD.
		 * If not, then we start from the smaller one, (say i = smaller num)
		 * and keep decrementing until we reach 2.
		 * At each step, we MOD both numbers by i and see if both are
		 * perfectly divisible. If at any point they are, then that i, is the
		 * GCD. If the end up reaching two and we still have no result,
		 * then their GCD is one -- for instance, with 3 and 5.
		 * 
		 */
		
		if(a % b == 0) return a;
		if(b % a == 0) return b;
		
		int i = (a < b) ? a : b;
		while(i > 1) {
			if(a % i == 0 && b % i == 0)
				return i;
			i--;
		}
		return 1;
	}
	
	
}
