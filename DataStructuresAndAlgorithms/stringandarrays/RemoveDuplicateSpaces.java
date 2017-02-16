package stringandarrays;

public class RemoveDuplicateSpaces {
	public String removeSpaces(String str) {
		if(str == null) return null;
		
		int left, right;
		char[] strCh = str.toCharArray();
		left = 0;
		right = strCh.length - 1;
		
		while(left < strCh.length && strCh[left] == ' ') 	left++;
		while(right >= 0 && strCh[right] == ' ') 			right--;
		
		StringBuilder sb = new StringBuilder();
		int index = 0;
		
		
		while(left < right) {
			if(strCh[left] == ' ') {
				sb.insert(index, ' ');
				while(left < strCh.length && strCh[left] == ' ') left++;
			}
			else { 
				sb.insert(index, strCh[left++]);
			}
			index++;
			
			if(strCh[right] == ' ') {
				sb.insert(index, strCh[right--]);
				while(right >= 0 && strCh[right] == ' ') right--;
			}
			else
				sb.insert(index, strCh[right--]);
		}
		return sb.toString();
	}
}
