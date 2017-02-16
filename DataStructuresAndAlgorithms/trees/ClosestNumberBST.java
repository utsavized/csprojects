package trees;
import reusableobjects.TreeNode;
public class ClosestNumberBST {
	public int findClosest(TreeNode root, int num) {
		int maxDiff = Math.abs(root.val - num);;
		int closest = 0;
		while(root != null) {
			int diffLeft = 0;
			int diffRight = 0;
			int curMax = 0;
			if(root.left == null && root.right == null) {
				closest = root.val;
				break;
			}
			if(root.left != null && root.right != null) {
				diffLeft = Math.abs(root.left.val - num);
				diffRight = Math.abs(root.right.val - num);
				curMax = Math.min(diffLeft, diffRight);
				if(curMax >= maxDiff) {
					closest =  root.val;
					break;
				}
				else {
					maxDiff = curMax;
					root = (diffLeft <= diffRight) ? root.left : root.right;
				}
			}
			else if(root.right == null) {
				curMax = Math.abs(root.left.val - num);
				if(curMax >= maxDiff) {
					closest =  root.val;
					break;
				}
				else {
					maxDiff = curMax;
					root = root.left;
				}
			}
			else {
				curMax = Math.abs(root.right.val - num);
				if(curMax >= maxDiff) {
					closest =  root.val;
					break;
				}
				else {
					maxDiff = curMax;
					root = root.right;
				}
			}
		}
		return closest;
	}
}
