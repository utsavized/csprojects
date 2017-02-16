package linkedlists;

import reusableobjects.ListNode;

public class DeleteNthNode {
	public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n < 1)
            return null;
        
        int count = 0;
        ListNode nth = null;
        ListNode runner = head;
        
        while(runner != null) {
            if(count == n) 
                nth = head;
            if(count > n)
                nth = nth.next;
                
            runner = runner.next;
            count++;
        }
        
        if(nth == null) {
            ListNode temp = head;
            head = head.next;
            temp.next = null;
            return head;
        }
            
        nth.next = nth.next.next;
        
        return head;
    }
}
