package linkedlists;

import reusableobjects.ListNode;

public class AddTwoNumbers {
	/**
	 * Adds two numbers represented by two linked lists
	 * @param a
	 * @param b
	 * @return 
	 */
	public ListNode addTwoNumbers(ListNode a, ListNode b) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(a == null && b == null) return null;
        else if(a == null) return b;
        else if(b == null) return a;
        
        ListNode runner = new ListNode(-1);
        ListNode head = runner;
        
        int carry = 0;
        
        while( a!= null && b!= null) {
            int val = a.num + b.num + carry;
            carry = (val > 9) ? 1 : 0;
            val = (carry == 1) ? val % 10 : val;
            if(runner.num == -1)
                runner.num = val;
            else {
                runner.next = new ListNode(val);
                runner = runner.next;
            }
            a = a.next; b = b.next;
        }
        ListNode rem = (a == null) ? b : a;
        while(rem != null) {
            int val = rem.num + carry;
            carry = (val > 9) ? 1 : 0;
            val = (carry == 1) ? val % 10 : val;
            runner.next = new ListNode(val);
            runner = runner.next;
            rem = rem.next;            
        }
        if(carry == 1)
            runner.next = new ListNode(carry);
        
        return head;
    }
}
