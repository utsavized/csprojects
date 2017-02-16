package numbers;

public class Primes {
	/**
	 * Print all primes from 2 - n
	 * @param n
	 */
	public void printPrimes(int n) {
		/*
		 * This problem is simple once you figure out
		 * how you can check if a number is prime. So think about its
		 * definition. What is a prime number? -- Any number that is
		 * only divisible be 1 or itself.
		 * 
		 * So, if our number is 13. We can say that if no other number
		 * from 2 to 12 perfectly divides it (i.e % num = 0), then 
		 * it is a prime. That is a lot of divisions. Can we do better?
		 * 
		 * Think of it this way. If a number P were to have a perfect
		 * divisors in the set D, then  one of the divisors in D will
		 * be lower than the square root of P. Look at examples:
		 * 
		 * Ni = 13; Nsq = 3 (floor)
		 * We can say that if 2 and 3 do not perfectly divide 13, it
		 * is a prime. Let's try a few more.
		 * 
		 * Ni = 18; Nsq = 4 (floor)
		 * 3, which is lower than 4 divides 18, so not prime.
		 * 
		 * Ni = 43; Nsq = 6 (floor)
		 * 2, 3, 4, 5 and 6, none of them divide 43 perfectly.
		 * Is it a prime? Yes.
		 * 
		 * There you go. Now printing out all primes from 2 - n
		 * is simply a matter of doing two nested loops that
		 * performs the above check.
		 */
		boolean isPrime = true;
		for(int i = 2; i <= n; i++) {
			int j = 2;
			while(j <= Math.sqrt(i)) {
				if(i % j == 0) {
					isPrime = false;
					break;
				}
				j++;
			}
			if(isPrime)
				System.out.println(i);
			isPrime = true;
		}
	}
}
