package stringandarrays;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Node implements Comparable<Node> {
	public Integer val;
	public int listIndex;
	public int elemIndex;
	public Node(Integer val, int listIndex, int elemIndex) {
		this.val = val;
		this.listIndex = listIndex;
		this.elemIndex = elemIndex;
	}
	
	@Override
	public int compareTo(Node o) {
		if		(this.val > o.val) return  1;
		else if (this.val < o.val) return -1;
		else				       return  0;
	}
}

class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		return o1.compareTo(o2);
	}
}

public class MergeNSortedLists {
	public ArrayList<Integer> mergeNSortedLists(ArrayList<ArrayList<Integer>> sortedLists) {
		Queue<Node> heap = new PriorityQueue<>(sortedLists.size() * 3, new NodeComparator());
		for(int i = 0; i < sortedLists.size(); i++)
			heap.add(new Node(sortedLists.get(i).get(0), i, 0));

		ArrayList<Integer> mergedList = new ArrayList<>();
		while(!heap.isEmpty()) {
			Node elem = heap.remove(); 
			mergedList.add(elem.val);
			if(elem.elemIndex + 1 < sortedLists.get(elem.listIndex).size())
				heap.add(new Node(sortedLists.get(elem.listIndex).get(elem.elemIndex + 1), elem.listIndex, elem.elemIndex + 1));
		}
		return mergedList;
	}
}
