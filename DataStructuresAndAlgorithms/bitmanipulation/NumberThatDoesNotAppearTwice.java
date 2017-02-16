package bitmanipulation;

public class NumberThatDoesNotAppearTwice {
	/*
	 * We have two arrays. Once has 1 int missing others are all same.
	 * How to find the missing numbers?
	 * Possible solution is using HashMap
	 * Another possible is get sum of both arrays, then substract -- may result in overflow.
	 * Best solution is to use bitwise XOR
	 */
	
	public int numberAppearingOnce(int [] a, int [] b) {
		int result = 0;
		for(int i : a) {
			result ^= i;
		}
		
		for(int i : b) {
			result ^= i;
		}
		
		return result;
	}
}
