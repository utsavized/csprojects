package trees;

import java.util.ArrayList;

import reusableobjects.TreeNode;

public class ValidBST {
	/**
	 * Using MIN and MAX values
	 * to keep track of minimum and maximum
	 * corresponding to left and right
	 * Then check each subtree for BST
	 * property validity on left and right
	 * O(n) Time; O(1) space
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
        /*
         * Since root can have element on the left up to negative infinity
         * and on the  right, up to positive infinity. We use Integer.MIN_VALUE
         * and Integer.MAX_VALUE to represent infinity for out data type.
         * This will have to change depending on whether it is a long or a 
         * double, etc.
         */
		return isValidBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean isValidBSTHelper(TreeNode root, int min, int max){
        //We return true if root is null and there is no
    	//evidence to support otherwise
    	if (root == null) return true;
        
    	/*
    	 * Here, we check if root's left subtree and its right subtree
    	 * both satisfy the BST property. We do that by performing a 
    	 * logical && on the following three conditions, and let recursion
    	 * do its magic.
    	 * The root: 
    	 *   First we check if root's value falls within the minimum and 
    	 *   maximum range.
    	 * For left subtree:
    	 *   We keep the same minimum as the left can continue to decrease
    	 *   up to negative infinity. But we update the maximum to root's value
    	 *   as the left subtree's right child cannot be greater than it's 
    	 *   parent, or it will violate the BST property.
    	 * For right subtree:
    	 *   We follow the same principle as the left subtree. The only
    	 *   difference being that in this case, the right subtree's 
    	 *   minimum get's capped to the root's value, and the maximum
    	 *   value can continue to grow to positive infinity.   	 
    	 */
    	return root.val < max && root.val > min &&
            isValidBSTHelper(root.left, min, root.val) &&
            isValidBSTHelper(root.right, root.val, max);
    }
    
    
    /**
     * Using in order traversal
     * Need to keep track of the previous Node
     * O(n) Time; O(1) space 
     * @param root
     * @return
     */
    public boolean isValidBSTInOrder(TreeNode root) {
    	/*
    	 * We will need to keep track of the in order
    	 * predecessor of root to be able to compare
    	 * whether the in order traversal is resulting
    	 * a sorted order. Since the first node in the
    	 * in order traversal of a BST will be the left-
    	 * most left i.e. the smallest value, the previous
    	 * node will have to start off at null.
    	 */
    	return isValidBSTInOrder(root, null);
    }
    
    private boolean isValidBSTInOrder(TreeNode root, TreeNode prev) {
    	//We return true if root is null and there is no
    	//evidence to support otherwise
    	if(root == null)
    		return true;
    	
    	/*
    	 * This is slightly trickier than a simple in order traversal
    	 * because we will have to keep track of the previous node and 
    	 * make comparisons to confirm sorted order. 
    	 * So first, we will traverse left recursively and see if that 
    	 * violates the BST property.
    	 * Once we return from the left, we will check if previous is null,
    	 * in which case we don't do anything. But if previous has a value,
    	 * and if is greater than root's value, BST has been violated.
    	 * So we return false;
    	 * If everything is fine up to that point, we set previous to
    	 * root, and then continue to traverse on the right. 
    	 * Recursion should then do it's trick.
    	 */
    	if(!isValidBSTInOrder(root.left, prev))  return false;
    	if(prev != null && root.val <= prev.val) return false;
    	prev = root;
    	return isValidBSTInOrder(root.right, prev);
    }
    
    
    /**
     * Using auxiliary array to store nodes
     * in in order traversal. Then check if
     * nodes in array are in sorted order
     * O(n) Time; O(n) space
     * @param root
     * @return
     */
    public boolean isValidBSTArray(TreeNode root) {
    	/*
    	 * In this case, we will need to keep track of the in order
    	 * traversal in an auxiliary array. Once we have the array back
    	 * from our helper function, we check to see if all elements
    	 * in the array are in sorted order. If they are, the tree is 
    	 * a BST, if not, it is not. 
    	 */
    	ArrayList<TreeNode> list = new ArrayList<TreeNode>();
    	list = isValidBSTArray(root, list);
    	if(list.size() == 1) return true;
    	TreeNode node = list.get(0);
    	for(int i = 1; i < list.size(); i++) {
    		if(list.get(i).val <= node.val) return false;
    		node = list.get(i);
    	}
    	return true;
    }
    
    private ArrayList<TreeNode> isValidBSTArray(TreeNode root, ArrayList<TreeNode> list) {
    	/*
    	 * This is a generic in order traversal. Only difference is that
    	 * is stores all nodes to an array then returns it. In other words,
    	 * the array should have the in order traversal in it.
    	 */
    	if(root == null)
    		return list;
    	
    	list = isValidBSTArray(root.left,  list);
    	list.add(root);
    	list = isValidBSTArray(root.right, list);
    	
    	return list;
    }
}
