package stringandarrays;

public class PalindromeSpecial {
	public boolean isPalindrome(String s) {
        if(s == null)
            return false;
        
        if(s.length() < 2)
            return true;
        
        char [] ch = s.toLowerCase().toCharArray();
        
        return isPalindrome(ch, 0, s.length() - 1);
    }
    
    private boolean isPalindrome(char [] ch, int start, int end) {
        if(!isValid(ch[start]) || !isValid(ch[end])) {
            while(start < ch.length && !isValid(ch[start])) start++;
            while(end >= 0 && !isValid(ch[end])) end--;
        }
        
        if(end - start == 1)
            return (ch[start] == ch[end]) ? true : false;
        
        if(start >= end)
            return true;
            
        
        return (ch[start] == ch[end]) && isPalindrome(ch, start + 1, end - 1);
    }
    
    private boolean isValid(char c) {
        int ascii = (int) c;
        if((ascii >= 48 && ascii <= 57) || (ascii >= 97 && ascii <= 122))
            return true;
        else
            return false;
    }
}
