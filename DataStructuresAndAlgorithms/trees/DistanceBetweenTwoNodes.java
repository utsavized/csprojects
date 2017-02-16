package trees;

import java.util.ArrayList;
import java.util.Stack;

import reusableobjects.TreeNode;

public class DistanceBetweenTwoNodes {
	/**
	 * AMZ 57 Distance between two nodes -- Exclusive
	 * - Calculate distance from root to each node
	 * - Find out of both are on the same side
	 * - Do some calculations based on that
	 */
	
	public int distance(TreeNode root, int val1, int val2) {
		if(root == null)
			return -1;
		
		ArrayList<Integer> depths = getDepths(root, val1, val2, 0, new ArrayList<Integer>(), 0);
		//If both depths have not been found,
		//then both nodes do not exist in tree
		if(depths.size() < 2)
			return -1;
		
		int sides = checkSide(root.left, val1, val2, 0);
		int distance = 0;
		if(sides == 1) 
			distance = depths.get(0) + depths.get(1) - 1;
		else
			distance = Math.abs(depths.get(0) - depths.get(1)) - 1;
		return distance;
	}
	
	private ArrayList<Integer> getDepths(TreeNode node, int val1, int val2, int depth, ArrayList<Integer> depths, int found) {
		if(node == null)
			return depths; 
		
		if(found == 2)
			return depths;
		
		if(node.val == val1 || node.val == val2) {
			depths.add(depth);
			found++;
		}
		
		depths = getDepths(node.left, val1, val2, depth + 1, depths, found);
		depths = getDepths(node.right, val1, val2, depth + 1, depths, found);
		
		return depths;
		
	}
	
	private int checkSide(TreeNode node, int val1, int val2, int found) {
		if(node == null)
			return found;
		
		if(found == 2)
			return found;
		
		if(node.val == val1 || node.val == val2)
			found++;
		
		found = checkSide(node.left, val1, val2, found);
		found = checkSide(node.right, val1, val2, found);
		
		return found;
	}
	
	
	
	
	
	/**
	 * AMZ 57 Mod, BT instead of BST
	 * Assuming Distance between two nodes = in order traversal distance
	 * Assuming exclusive distance
	 * @param root
	 */
	
	public int distanceBetween(TreeNode root, int val1, int val2) {
		if(root == null)
			return -1;
		
		if(val1 == val2)
			return 0;
		
		
		int distance = 0;
		int nodesFound = 0;
		
		Stack<TreeNode> s = new Stack<>();
		s = addAllLeft(root, s);
		
		while(!s.isEmpty()) {
			TreeNode node = s.pop();
			if(node.val == val1 || node.val == val2)
				nodesFound++;
			if(nodesFound == 2)
				return distance - 1;
			if(nodesFound == 1)
				distance++;
			if(node.right != null)
				s = addAllLeft(node.right, s);
		}
		//Indicates that both or one of the two nodes do not exist in tree
		return -1;
	}
	
	private Stack<TreeNode> addAllLeft(TreeNode node, Stack<TreeNode> s) {
		while(node != null) {
			s.push(node);
			node = node.left;
		}
		
		return s;
	}
}
