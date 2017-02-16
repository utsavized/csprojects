package trees;

import reusableobjects.TreeNode;

public class PerimeterOfTree {
	/*    
	     100
	    /   \ 
	  50     150
	 / \     /
   24   57  130
  /  \        \
 12  30       132
  e.g: the output should be 
  100, 50, 24, 12, 30, 57, 132, 150
	*/
	/**
	 * Prints the perimeter of a tree
	 * @param root
	 */
	public void perimeter(TreeNode root) {
		/*
		 * The trick here is to do a preorder of the left subtree,
		 * then the opposite of preorder on the right
		 * (NOT postorder, but preorder, with right before left).
		 * This effectively prints the entire left edge and the
		 * right edge. The tricky part is the inner edges.
		 * For that, we keep boolean value that keep track of when you have
		 * entered the right subtree that is not null, for the left and 
		 * vice versa for the right.
		 * Once the boolean is true, i.e. we have entered the opposite, inner edge,
		 * we only print leaf nodes.
		 */
		if(root == null) return;
		System.out.print(root.val + " ");
		perimeterLeft(root.left, false);
		perimeterRight(root.right, false);
	}
	
	public void perimeterLeft(TreeNode root, boolean visitedRight) {
		if(root == null) return;
		
		if(visitedRight) {
			if(root.left == null && root.right == null) System.out.print(root.val + " ");
		}
		else
			System.out.print(root.val + " ");
		
		perimeterLeft(root.left, visitedRight);
		if(root.right != null)
			perimeterLeft(root.right, true);
	}
	
	public void perimeterRight(TreeNode root, boolean visitedLeft) {
		if(root == null) return;
		
		if(visitedLeft) {
			if(root.left == null && root.right == null) System.out.print(root.val + " ");
		}
		else
			System.out.print(root.val + " ");
		
		perimeterRight(root.right, visitedLeft);
		if(root.left != null)
			perimeterRight(root.left, true);
	}
}
