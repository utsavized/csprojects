package trees.implementations;

import reusableobjects.TreeNode;

public class BinarySearchTree{
		/**
	 * Create tree from serialized BST Integer array
	 * @param serializedTree
	 * @return
	 */
	public TreeNode create(Integer [] serializedTree) {
		if(serializedTree == null || serializedTree.length == 0)
			return null;
		
		TreeNode root = new TreeNode(serializedTree[0]);
		for(int i = 1; i < serializedTree.length; i++) {
			root = insert(serializedTree[i], root);
		}
		
		return root;
	}
	
	public TreeNode insert(Object val, TreeNode root) {
		TreeNode node = new TreeNode((int) val);
		TreeNode runner = root;
		while(true) {
			if(node.val <= runner.val) {
				if(runner.left == null) {
					runner.left = node;
					break;
				}
				runner = runner.left;
			}
			else {
				if(runner.right == null) {
					runner.right = node;
					break;
				}
				runner = runner.right;
			}	
		}
		
		return root;
	}
	
	public TreeNode remove(Object val, TreeNode root) {
		int v = (int) val;
		if(root == null)
			return null;
		
		if(root.val > v) 
			root.left = remove(val, root.left);
		else if(root.val < v)
			root.right = remove(val, root.right);
		else {
			if(root.left == null && root.right == null) {
				root = null;
				return root;
			}
			else if(root.left == null) {
				root.val = root.right.val;
				root.right = root.right.right;
				root.left = root.right.left;
				return root;
			}
			else if(root.right == null) {
				root.val = root.left.val;
				root.left = root.left.left;
				root.right = root.left.right;
				return root;
			}
			else {
				root.val = getVal(root.left);
				root.left = remove(root.val, root.left);
			}
		}
		return root;
			
	}
	
	private int getVal(TreeNode root)
	{
		while (root.right != null) 
			root = root.right;
		return root.val;
	}
	
	public boolean exists(Object val, TreeNode root) {
		TreeNode node = search(val, root);
		if(node == null)
			return false;
		else
			return true;
	}
	
	public TreeNode search(Object val, TreeNode root) {
		int v = (int) val;
		TreeNode runner = root;
		while(runner != null) {
			if(v == runner.val)
				return runner;
			else if(v < runner.val)
				runner = runner.left;
			else
				runner = runner.right;
		}
		return null;
	}
	
}
