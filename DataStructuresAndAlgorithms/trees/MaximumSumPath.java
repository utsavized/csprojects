package trees;
import java.util.ArrayList;
import reusableobjects.TreeNode;


public class MaximumSumPath {
	public int maxSumPath(TreeNode root) {
		ArrayList<Integer> maxSum = new ArrayList<Integer>(1);
	    maxSum.add(Integer.MIN_VALUE);
	    getMaxSum(root,maxSum);
	    return maxSum.get(0);
	}

	public int getMaxSum(TreeNode root, ArrayList<Integer> maxSum){
	    if(root==null){
	        return 0;
	    }

	    int leftSum=0,rightSum=0;
	    leftSum = getMaxSum(root.left,maxSum);
	    rightSum = getMaxSum(root.right,maxSum);

	    int curSum = Math.max(root.val,Math.max(root.val+leftSum,root.val+rightSum));
	    maxSum.add(0,Math.max(maxSum.get(0), Math.max(curSum,root.val+leftSum+rightSum)));
	    return curSum;
	}
		
}
