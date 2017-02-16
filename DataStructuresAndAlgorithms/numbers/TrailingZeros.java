package numbers;


public class TrailingZeros {
	/**
	 * Calculate trailing zeros in a factorial
	 * @param n
	 * @return
	 */
	 public int trailingZeros(int n) {
	     int factorial = 1;
	     if(n > 1)
	         factorial = factorial(n);
	     
	     if(factorial < 10)
	         return 0;
	         
	     int z = 0;
	     while(factorial % 10 == 0) {
	         factorial = factorial / 10;
	         z++;
	     }
	     return z;
	 }
	 
	/**
	 * Calculate factorial
	 * @param n
	 * @return
	 */
	 private int factorial(int n) {
	     if(n < 2)
	         return 1;
	     
	     return n * factorial(n - 1);
	 }
}
