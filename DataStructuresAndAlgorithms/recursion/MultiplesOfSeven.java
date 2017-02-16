package recursion;

public class MultiplesOfSeven {
	public int count7(int n) {
		if(n == 0)
			return 0;
		else if(n % 10 == 7)
			return count7(n / 10) + 1;
		else
			return count7(n / 10);

	}
}
