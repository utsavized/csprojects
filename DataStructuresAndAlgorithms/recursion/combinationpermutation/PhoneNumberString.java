package recursion.combinationpermutation;

public class PhoneNumberString {
	/**
	 * All character combinations possible from a phone
	 * number
	 * @param phoneNum
	 */
	public void printWords(String phoneNum) {
		char [] pCh = phoneNum.toCharArray();
		String [] letters = new String[phoneNum.length()];
		for(int i = 0; i < phoneNum.length(); i++) {
			letters[i] = getLetters(pCh[i]);
		}
		
		printWordsRecurse(new StringBuffer(), letters, 0, phoneNum.length());
	}
	
	private void printWordsRecurse(StringBuffer picked, String [] letters, int cur, int length) {
		if(cur == length) {
			System.out.println(picked.toString());
			return;
		}
		
		char [] currentNumber = letters[cur].toCharArray();
		for(int i = 0; i < currentNumber.length; i++) {
			picked.append(currentNumber[i]);
			printWordsRecurse(picked, letters, cur + 1, length);
			picked.deleteCharAt(picked.length() - 1);
		}
	}
	
	private String getLetters(char ch) {
		String letters = "";
		switch(ch) {
			case '0':
				letters = "0";
				break;
			case '1':
				letters = "1";
				break;
			case '2':
				letters = "ABC";
				break;
			case '3':
				letters = "DEF";
				break;
			case '4':
				letters = "GHI";
				break;
			case '5':
				letters = "JKL";
				break;
			case '6':
				letters = "MNO";
				break;
			case '7':
				letters = "PQRS";
				break;
			case '8':
				letters = "TUV";
				break;
			case '9':
				letters = "WXYZ";
				break;
		}
		return letters;
	}
}
