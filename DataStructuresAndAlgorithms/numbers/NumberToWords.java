package numbers;

import java.util.HashMap;

public class NumberToWords {
	/**
	 * Converts number to English representation.
	 * Not 100% functional I think -- has some bugs.
	 * @param n
	 * @return
	 */
	public String englishNumber(int n) {
	     HashMap<Integer, String> hM = buildHashMap();
	     int pos = 0;
	     StringBuilder sb = new StringBuilder();
	     while(n != 0) {
	         int d = n % 10;
	         n = n / 10;
	         if(pos > 1) {
	        	int power = (int) Math.pow(10, pos);
	        	sb.insert(0, hM.get(power));
	            sb.insert(0, " ");
	            sb.insert(0, hM.get(d)); 
	            sb.insert(0, " ");
	         }
	         else if(d > 0) {
	             int power = (int) Math.pow(10, pos);
	             sb.insert(0, hM.get(d * power));
	             sb.insert(0, " ");
	         }
	         pos++;
	     }
	     return sb.toString();
	 }
	 
	 private HashMap<Integer, String> buildHashMap() {
	     HashMap<Integer, String> hM = new HashMap<>();
	     hM.put(1, "One");
	     hM.put(2, "Two");
	     hM.put(3, "Three");
	     hM.put(4, "Four");
	     hM.put(5, "Five");
	     hM.put(6, "Six");
	     hM.put(7, "Seven");
	     hM.put(8, "Eight");
	     hM.put(9, "Nine");
	     hM.put(10, "Ten");
	     hM.put(11, "Eleven");
	     hM.put(12, "Twelve");
	     hM.put(13, "Thirteen");
	     hM.put(14, "Fourteen");
	     hM.put(15, "Fifteen");
	     hM.put(16, "Sixteen");
	     hM.put(17, "Seventeen");
	     hM.put(18, "Eighteen");
	     hM.put(19, "Nineteen");
	     hM.put(20, "Twenty");
	     hM.put(30, "Thirty");
	     hM.put(40, "Forty");
	     hM.put(50, "Fifty");
	     hM.put(60, "Sixty");
	     hM.put(70, "Seventy");
	     hM.put(80, "Eighty");
	     hM.put(90, "Ninety");
	     hM.put(100, "Hundred");
	     hM.put(1000, "Thousand");
	     
	     return hM;
	 }
}
