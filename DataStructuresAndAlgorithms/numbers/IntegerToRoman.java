package numbers;

public class IntegerToRoman {
	
	/**
	 * Given an integer, convert it to a Roman numeral.
     * Input is guaranteed to be within the range from 1 to 3999.
	 * @param num
	 * @return
	 */
	public String intToRoman(int num) {
        //There is Roman representations for
		//0 or negative numbers.
		if(num <= 0)
            return "";
        
		//Get the position (1s, 10s, 100s, etc.)
		//of the most significant digit.
		int p = num;
        int power = 1;
        while(p >= 10) {
            p /= 10;
            power *= 10;
        }
        
        StringBuilder roman = new StringBuilder();
        
        while(num != 0) {
            /*
             * We are trying to this here:
             * Say 523.
             * So, 523 - (523 % 100) = 523 - 23 = 500.
             * 
             */
        	int val = num - (num % power);
            num %= power;
            
            //Once again no representation in Roman
            //for 0, so do nothing except reducing the
            //digit position by power of 10.
            if(val == 0) {
            	power /= 10;
                continue;
            }
            
            //Check if value is 9, as it is represented is
            //reverse order, i.e. as IX and not as VIIII.
            if(val % 9 == 0) {
            	roman.append(getRoman(power));
            	roman.append(getRoman(val + power));
            	power /= 10;
                continue;
            }
            
            //We need to also check it for 400, 40 and 4
            if(val == 400 || val == 40 || val == 4) {
            	int pr = (int) Math.ceil(power * 10/2.0);
            	roman.append(getRoman(pr - val));
            	roman.append(getRoman(pr));
            	power /= 10;
                continue;
            }
            
            //This gives the closes Roman numeral
            int r = getClosestRomanVal(val);
            String rR = getRoman(r);
            
            while(val != 0) {
                val -= r;
                roman.append(rR);
                r = getClosestRomanVal(val);
                rR = getRoman(r);
            }
            
            power /= 10;

        }
        
        return roman.toString();
    
    }
    
    private int getClosestRomanVal(int num) {
        if (num < 5 && num >= 1)
            return 1;
        if (num < 10 && num >= 5)
            return 5;
        if (num < 50 && num >= 10)
            return 10;
        if (num < 100 && num >= 50)
            return 50;
        if (num < 500 && num >= 100)
            return 100;
        if (num < 1000 && num >= 500)
            return 500;
        if (num >= 1000)
            return 1000;
        return 0;
        
    }
    
    private String getRoman(int num) {
        switch (num) {
            case 1:
                return "I";
            case 5:
                return "V";
            case 10:
                return "X";
            case 50:
                return "L";
            case 100:
                return "C";
            case 500:
                return "D";
            case 1000:
                return "M";
            default:
                return "";
        }
    }
}
