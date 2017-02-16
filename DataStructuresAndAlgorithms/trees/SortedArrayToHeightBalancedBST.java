package trees;

import reusableobjects.TreeNode;

public class SortedArrayToHeightBalancedBST {
	/**
	 * Create height balanced search tree from sorted array
	 * @param array
	 * @param low
	 * @param high
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] array) {
        return createBST(array, 0, array.length - 1);
        
    }
    
    private TreeNode createBST(int [] array, int low, int high){
    	/*
		 * Uses the binary search approach
		 */
		if(array == null)
			return null;
		
		if(high < low)
			return null;
		
		int pivot = (high + low)/2;
		TreeNode node = new TreeNode(array[pivot]);
		node.left  = createBST(array, low, pivot - 1);
		node.right = createBST(array, pivot + 1, high);
		
		return node;
	}
}
