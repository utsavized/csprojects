package numbers;

public class SwapInt {
	/**
	 * Swap two integers without using temp
	 * @param a
	 * @param b
	 */
	public void swapIntInPlace(int a, int b) {
	    System.out.println("a: " + a);
	    System.out.println("b: " + b);
	    
        b = a * b;
        a = b / a;
        if(a > b)
        	b = a / b;
        else
        	b = b / a;

	    System.out.println("a: " + a);
	    System.out.println("b: " + b);
	}
	
}
