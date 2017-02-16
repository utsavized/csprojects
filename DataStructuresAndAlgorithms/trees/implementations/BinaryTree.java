package trees.implementations;

import java.util.LinkedList;
import java.util.Queue;

import reusableobjects.TreeNode;

public class BinaryTree {
	private TreeNode root;
	public BinaryTree() {
		this.root = null;
	}
	
	public TreeNode getRoot() {
		return this.root;
	}
	
	public TreeNode createTree(Integer [] array) {
		if(array.length == 0)
			return null;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		int i = 0;
		q.add(new TreeNode((int) array[i++]));
		while(!q.isEmpty() && i < array.length) {
			TreeNode node = q.remove();
			if(i == 1)
				this.root = node;
			
			if(i < array.length && array[i] != null) {
				TreeNode left = new TreeNode(array[i]);
				node.left = left;
				q.add(left);
			}
			if(i + 1 < array.length && array[i + 1] != null) {
				TreeNode right = new TreeNode(array[i + 1]);
				node.right = right;
				q.add(right);
			}
			i += 2;	
		}
		
		return this.root;
	}
	
	@Override
	public String toString() {
		TreeNode n = this.root;
		return inOrder(n, new StringBuilder()).toString();
	}
	
	private StringBuilder inOrder(TreeNode root, StringBuilder s) {
		if(root == null)
			return s;
		
		s = inOrder(root.left, s);
		s.append(root.val);
		s.append(" ");
		s = inOrder(root.right, s);
		
		return s;
	}
}
