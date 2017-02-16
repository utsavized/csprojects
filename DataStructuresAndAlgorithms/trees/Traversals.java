package trees;

import java.util.Stack;

import reusableobjects.TreeNode;

public class Traversals {
	public void inorder(TreeNode root) {
		if(root == null)
			return;
		
		inorder(root.left);
		System.out.print(root.val + " ");
		inorder(root.right);
		
	}
	
	public void inorderIter(TreeNode root) {
		if(root == null)
			return;
		
		Stack<TreeNode> s = new Stack<>();
		s = addAllLeft(root, s);
		
		while(!s.isEmpty()) {
			TreeNode node = s.pop();
			System.out.print(node.val + " ");
			if(node.right != null)
				s = addAllLeft(node.right, s);
		}
	}
	
	private Stack<TreeNode> addAllLeft(TreeNode node, Stack<TreeNode> s) {
		while(node != null) {
			s.push(node);
			node = node.left;
		}
		
		return s;
	}
	
	public void preorder(TreeNode root) {
		if(root == null)
			return;
		
		System.out.print(root.val + " ");
		preorder(root.left);
		preorder(root.right);
		
	}
	
	public void postorder(TreeNode root) {
		if(root == null)
			return;
		
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.val + " ");
		
		
	}
	
}
