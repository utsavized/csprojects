package recursion;

public class GCD {
	public int gcdRec(int number1, int number2)
	{
	    // find the remainder
 	    int remainder = number1 % number2;
	    // base condition - if the number divide
	    if (remainder == 0)
	        return number2;
	    else // recurse
	        return gcdRec(number2, remainder);
	}
}
