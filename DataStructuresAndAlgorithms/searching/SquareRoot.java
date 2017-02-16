package searching;

public class SquareRoot {
	public double squareRoot(int num) {
		if(num < 0)
			return -1;
		return binaryRoot(num, 0, num);
	}
	
	private double binaryRoot(double num, double low, double high) {
		double pivot = (high + low) / 2;
		
		//Must make room for some tolerance factor
		//So rounding pivot to 2 decimal places and rounding possible
		//square to 1 decimal places -- this should effectively render
		//A square root within 0.01 tolerance.
		pivot = Math.round(pivot * 100.0) / 100.0; //Round to two decimal places
		double sqrt = pivot * pivot;
		sqrt =  Math.round(sqrt * 10.0) / 10.0; //Round to two decimal places
		if(sqrt == num)
			return pivot;
		else if(sqrt < num)
			return binaryRoot(num, pivot, high);
		else
			return binaryRoot(num, low, pivot);
	}
}
