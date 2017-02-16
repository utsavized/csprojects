package trees;
import reusableobjects.TreeNode;

public class PruneTree {
	/*Input:  Root node of Binary Tree, int k
	Output: Tree pruned of all nodes that lie in the path where sum of complete path is less than k

	Idea:
	- Do a post order traversal
	- if leafnode and sum of path < k, remove node; continue traveral
*/
	public TreeNode prune(TreeNode root, int k) {
	    return prune(root, 1, k);
	}

	private TreeNode prune(TreeNode node, int sum, int k) {
	    if(node.right == null && node.left == null) {
	        if(sum < k) return null;
	        else return node;
	    }
	    
	    node.left = prune(node.left, sum++, k);
	    node.right = prune(node.right, sum++, k);
	    if(node.right == null && node.left == null) {
	        if(sum < k) return null;
	        else return node;
	    }
	    return node;
	}

}
