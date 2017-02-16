package trees;

import reusableobjects.TreeNode;

public class HeightOfTree {
	public int getHeight(TreeNode root) {
		if(root == null)
			return 0;
		
		int hLeft  = getHeight(root.left);
		int hRight = getHeight(root.right);
		
		return Math.max(hLeft, hRight) + 1;
	}
}
