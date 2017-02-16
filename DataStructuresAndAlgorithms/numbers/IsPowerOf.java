package numbers;

public class IsPowerOf {
	/**
	 * Calculates if num is a power of the base
	 * @param num
	 * @param base
	 * @return
	 */
	public boolean isPowerOf(int num, int base) {
		/*
		 * How can you know if 9 is a power of 3?
		 * 1. It is perfectly divisible by 3.
		 * 
		 * Is that enough? No, because 6 is perfectly divisible
		 * by 3 too, but is not a power of 3. Let's add one more
		 * constraint.
		 * 
		 * 2. If we repeatedly divide 9 by 3, each result should
		 * sill be perfectly divisible by 3, and at some point
		 * 9 should end up as 1.
		 * 
		 * Example:
		 * Is 9 a power of 3?
		 * - 9 % 3 == 0? Yes. Then 9/3 = 3.
		 * - 3 % 3 == 0? Yes. Then 3/3 = 1. 
		 * Since at each step, number was perfectly divisible by 3,
		 * and it eventually reached 1, 9 is a power of 3.
		 * 
		 * Is 6 a power of 3?
		 * - 6 % 3 == 0? Yes. Then 6/3 = 2/
		 * - 2 % 3 == 0? No. Therefore, not a power of 3.
		 */
		
		//This is a tricky case here a any number to the power 0 is 1.
		//So 1 is a power of any number.
		if(num == 1)
			return true;
		else if(num < base)
			return false;
		
		while(num > 1) {
			if(num % base != 0)
				return false;
			num /= base;
		}
		return true;
	}
}
