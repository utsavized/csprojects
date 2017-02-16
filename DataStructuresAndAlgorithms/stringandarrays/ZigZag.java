package stringandarrays;


/*
 
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

 
 */



public class ZigZag {
	public String convert(String s, int nRows) {
		// Start typing your Java solution below
        // DO NOT write main() function
        int length = s.length();
        if (length <= nRows || nRows == 1) return s;
        char[] chars = new char[length];
        int step = 2 * (nRows - 1);
        int count = 0;
	    for (int i = 0; i < nRows; i++){
    		int interval = step - 2 * i;
    		for (int j = i; j < length; j += step){
    		   	chars[count] = s.charAt(j);
    			count++;
    			if (interval < step && interval > 0 && j + interval < length && count <  length){
    				chars[count] = s.charAt(j + interval);
    				count++;
    			}
    		}
    	}
        return new String(chars);
    }
}
