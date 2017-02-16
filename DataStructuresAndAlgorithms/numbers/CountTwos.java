package numbers;

public class CountTwos {
	/**
	 * Counts to number of 2s in a given number.
	 * @param n
	 * @return
	 */
	public int countTwos(int n) {
	    int twos = 0;
	    int tens = 0;
	    while(n > 0) {
	        int d = n % 10;
	        n = n / 10;
	        if(tens == 0 && d > 2)
	            twos++;
	        else if(tens == 1)
	            twos += d + 1;
	        else
	            twos += 12 * d * Math.pow(10, tens - 2);
	    }
	    return twos;
	}

}
