package trees;

import java.util.ArrayList;

import reusableobjects.TreeNode;

public class KSmallest {
	/*Input:  BST (root node), and int k
	Output: kth smallest node from the BST

	Brainstorm:
	- In order traversal to array n 
	- Return node where (k - 1)th element of array n

	*** Can possibly short circuit, and not need extra space
	*** May need to have global vars though

	Complexity:
	- O(log n) time
	- O(n) space*/

	public TreeNode kthElementBST(TreeNode root, int k) {
	    ArrayList<TreeNode> elements = kthElementBST(root, new ArrayList<TreeNode>());
	    return elements.get(k - 1);
	}

	private ArrayList<TreeNode> kthElementBST(TreeNode root, ArrayList<TreeNode> elements) {
	    if(root.left == null && root.right == null) {
	        elements.add(root);
	        return elements;
	    }
	    
	    elements = kthElementBST(root.left, elements);
	    elements.add(root);
	    elements = kthElementBST(root.right, elements);
	    
	    return elements;
	}

}
