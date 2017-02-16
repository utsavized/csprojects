package numbers;

public class DecimalBinary {
	/**
	 * Converts Integer to Binary
	 * @param decimal
	 * @return
	 */
	public String decimalToBinary(int decimal) {
		/*
		 * Think about how you would do this in real life.
		 * You divide the number repeatedly by 2, then see of the reminder is 
		 * 0 or 1. Write each reminder, bottom-up to form the binary representation.
		 * Do the same thing here.
		 * Watch out for the bottom up thing, hence insert each reminder
		 * at the beginning of the String Builder. 
		 */
		StringBuilder sb = new StringBuilder();
		while(decimal > 0) {
			int rem = decimal % 2;
			sb.insert(0, rem);
			decimal -= rem;
			decimal /= 2;
		}
		return sb.toString();
	}
}
