package linkedlists;
import reusableobjects.ListNode;

public class MergeTwoSortedLists {
	/**
	 * Merges two sorted lists recursively
	 * @param list1
	 * @param list2
	 * @return
	 */
	public ListNode merge(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		} 
		if (list2 == null) {
			return list1;
		} 

		if (list1.num < list2.num) {
			list1.next = merge(list1.next, list2);
			return list1;
		} else {
			list2.next = merge(list2.next, list1);
			return list2;
		}
	}
	
	/**
	 * Merges two sorted lists iteratively
	 * @param list1
	 * @param list2
	 * @return
	 */
	public ListNode mergeIter(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		} 
		if (list2 == null) {
			return list1;
		}
		
		ListNode merged, other, head;
		merged = head = (list1.num <= list2.num) ? list1 : list2;
		other = (list1.num > list2.num) ? list1 : list2;
		
		
		while(merged.next != null) {
			if(other.num >= merged.num && other.num < merged.next.num) {
				ListNode temp = merged.next;
				merged.next = other;
				other = temp;
			}
			merged = merged.next;	
		}
		merged.next = other;
		return head;
	}
}
