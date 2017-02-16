package linkedlists;

import reusableobjects.ListNode;

public class ReverseLinkedList {
	
	public ListNode reverse(ListNode head) {
		if(head == null || head.next == null) 
			return head;
		ListNode runner = head;
		ListNode tail = null;
		//Get tail
		while(runner != null) {			
			if(runner.next == null)
				tail = runner;
			runner = runner.next;
		}
		
		ListNode t2;
		t2 = null;
		
		while(head != tail) {
			ListNode insert = head;
			head = insert.next;
			tail.next = insert;
			insert.next = t2;
			t2 = insert;
		}
		
		return head;
	}
	
	public ListNode reverseRec(ListNode head) {
		if(head == null)
			return null;
		
		if(head.next == null)
			return head;
		
		ListNode rest = head.next;
		head.next = null;
		ListNode reverse = reverseRec(rest);
		rest.next = head;
		
		return reverse;
		
		
	}
	
	
}
