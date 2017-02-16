package trees;

import reusableobjects.TreeNode;

public class LCAOfBT {
	/**
	 * Finds the LCA with the top down approach
	 * Using this approach, same nodes are repeatedly
	 * traversed, resulting in O(n^2) complexity
	 * 
	 * @param root
	 * @param nodeA
	 * @param nodeB
	 * @return LCA
	 */
	public TreeNode LCATopDown(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
		if(root == null)
			return null;
		if(root.equals(nodeA) || root.equals(nodeB))
			return root;
		
		int found = 0;
		while(root != null) {
			found = LCATopDown(root.left, nodeA, nodeB, 0);
			if(found == 1)
				return root;
			else if(found == 0) {
				if(root.right.equals(nodeA) || root.right.equals(nodeB))
					return root.right;
				root = root.right;
			}
			else {
				if(root.left.equals(nodeA) || root.left.equals(nodeB))
					return root.left;
				root = root.left;
			}
		}
		
		return null;
	}
	
	/**
	 * Recursive traversal part that calculates how many nodes
	 * matched in the given subtree
	 * @param root
	 * @param nodeA
	 * @param nodeB
	 * @param found
	 * @return numbers of matches (0, 1, 2)
	 */
	private int LCATopDown(TreeNode root, TreeNode nodeA, TreeNode nodeB, int found) {
		if(root == null)
			return found;
		
		found = LCATopDown(root.left, nodeA, nodeB, found);
		if(root.equals(nodeA) || root.equals(nodeB))
			found++;
		found = LCATopDown(root.right, nodeA, nodeB, found);
		return found;
	}
	
	/**
	 * Finds the LCA with the bottom up approach
	 * Using this approach, same nodes are NOT repeatedly
	 * traversed, resulting in O(n) complexity 
	 * @param root
	 * @param nodeA
	 * @param nodeB
	 * @return
	 */
	public TreeNode LCABottomUp(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
		if(root == null)
			return null;
		if(root.equals(nodeA) || root.equals(nodeB))
			return root;
		TreeNode left  = LCABottomUp(root.left, nodeA, nodeB);
		TreeNode right = LCABottomUp(root.right, nodeA, nodeB);
		if(left != null && right != null)
			return root;
		else 
			return (left == null) ? right : left;
	}
	
	/**
	 * Finds the LCA iteratively -- parent pointer is required
	 * @param root
	 * @param nodeA
	 * @param nodeB
	 * @return LCA
	 */
	public TreeNode LCAParent(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
		//0 will store depth of A, 1 will store depth of B
		int [] depths = new int[2];
		this.getDepths(root, nodeA, nodeB, 0, depths, 0);		//Get depths of both A and B
		int difference = Math.max(depths[0], depths[1]);		//Get the difference between the two nodes
		while(difference != 0) {								//Move node with greater depth up, so it is level with other node's depth
			if(depths[0] > depths[1])
				nodeA = nodeA.parent;
			else
				nodeB = nodeB.parent;
			difference--;
		}
		
		while(!nodeA.equals(nodeB)) {							//Once nodes are level in depth, advance both until they meet at a common node.
			if(nodeA == null || nodeB == null)
				return null;
			nodeA = nodeA.parent;
			nodeB = nodeB.parent;
		}
		
		return nodeA;											//Common node is LCA.
	}
	
	private void getDepths(TreeNode root, TreeNode A, TreeNode B, int depth, int [] depths, int index) {
		//If root is null, or both depths have been found, return
		if(root == null || index == 2)
			return;
		
		//Traverse left
		this.getDepths(root.left, A, B, depth + 1, depths, index);
		//Check if depth of node needs to be recorded
		if(root.equals(A)) {
			depths[0] = depth;
			index++;
		}
		else if(root.equals(B)) {
			depths[1] = depth;
			index++;
		}
		//Traverse right
		this.getDepths(root.right, A, B, depth + 1, depths, index);
	}
}
