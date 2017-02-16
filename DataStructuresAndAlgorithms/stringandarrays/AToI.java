package stringandarrays;

public class AToI {
	public long atoi(String str) {
		char [] num = str.toCharArray();
        int power = 1;
        int integer = 0;
        boolean sign = false;
        for(int i = num.length - 1; i >= 0; i--) {
            if(num[i] == '-') {
                if(sign)
                    return 0;
                else {
                    integer *= -1;
                    sign = true;
                }
            }
            else if(num[i] == '+') {
                if(sign)
                    return 0;
                else {
                    sign = true;
                }
            }
            else if (isDigit(num[i])) {
                int digit = Character.getNumericValue(num[i]); 
                digit    *= power;
                power    *= 10;
                integer  += digit;
            }
        }
        return integer;
    }
    
    private boolean isDigit(char val) {
        int ascii = (int) val;
        return (ascii >= 48 && ascii <= 57);
    }
}
