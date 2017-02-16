package stringandarrays;

public class ReverseWords {
	 public static String reverseSentence(String sentence) {
			char [] sChar = sentence.toCharArray();
			int start = 0;
			reverse(sChar, start, sChar.length - 1);

			for(int i = 0; i < sChar.length; i++) {
				if(sChar[i] == ' ') {
					reverse(sChar, start, i - 1);
					start = i + 1;
					continue;
				}
				if(i == sChar.length - 1) {
					reverse(sChar, start, i);
				}
			}
			return new String(sChar);
		}

		private static void reverse(char [] ch, int start, int end) {
			if(end - start < 1)
				return;

			while(start <= end) {
				char temp = ch[start];
				ch[start++] = ch[end];
				ch[end--] = temp;
			}
		}
		
		
		//More detailed version
		
		public String reverse(String str) {
			if(str == null)
				return null;

			if(str.length() == 1)
				return str;

			char [] ch = str.toCharArray();
			int start = 0;
			int length = ch.length;
			int end = length - 1;

			//Reverse entire string
			reverseStr(ch, start, end);

			while(start < length && end < length) {
				while(start < length && ch[start] == ' ') start++;
				end = start;
				while(end < length - 1 && ch[end + 1] != ' ') end++;
				
				//Reverse word
				if(end < length && start < length)
					reverseStr(ch, start, end++);

				start = end;
			}

			return new String(ch);

		}

		private void reverseStr(char [] str, int start, int end) {
			while(start <= end) {
				swap(str, start++, end--);
			}
		}

		private void swap(char [] str, int a, int b) {
			char temp = str[a];
			str[a] = str[b];
			str[b] = temp;
		}
		
		
		
		public boolean isPowerOfThree(int number) {
			if(number < 3)
				return false;
			
			while(number > 1) {
				if(number % 3 != 0) {
					return false;
				}
				number = number / 3;
			}
			
			return true;
		}
}
