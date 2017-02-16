package reusableobjects;


public class TreeNode {
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	public int val;
	public TreeNode(int val) {
		this.left = null;
		this.right = null;
		this.parent = null;
		this.val = val;
	}
	
	@Override
	public boolean equals(Object obj) {
		TreeNode node = (TreeNode) obj;
		if(this.val == node.val)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return Integer.toString(this.val);
	}
}
