package trees;

import reusableobjects.TreeNode;

public class InOrderSuccessorBST {
	/**
	 * In order successor -- assumes parent node exists.
	 * @param node
	 * @return
	 */
	public TreeNode inorderSuccessor(TreeNode node) {
		if(node == null) return null;
		if(node.right != null)
			return getLowestLeaf(node.right);
		else
			return getAncestor(node);
	}
	
	private TreeNode getAncestor(TreeNode node) {
		while(node != null) {
			if(node.parent.left.equals(node))
				return node.parent;
			node = node.parent;
		}
		return null;
	}
	
	private TreeNode getLowestLeaf(TreeNode node) {
		while(node.left != null)
			node = node.left;
		
		return node;
	}
}
