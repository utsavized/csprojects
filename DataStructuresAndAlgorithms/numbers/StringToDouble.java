package numbers;

public class StringToDouble {
	public double stringToDouble(String s) {
		if(s == null)
			return 0;
		
		char [] num = s.toCharArray();
		int power = 1;
		double number = 0;
		int curNum = 0;
		for(int i = num.length - 1; i >= 0; i--) {
			if(num[i] == '-')
				number *= -1;
			else if(num[i] == '+')
				continue;
			else if(num[i] == '.') {
				number /= power;
				power = 1;
				continue;
			}
			
			curNum  = Character.getNumericValue(num[i]) * power;
			number += curNum;
			power  *= 10;
		}
		return number;
	}
}
