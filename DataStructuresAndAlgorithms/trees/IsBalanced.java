package trees;

import reusableobjects.TreeNode;


/*Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.*/

public class IsBalanced {
	public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        
        int diff = Math.abs(height(root.left) - height(root.right));
        return (diff > 1) ? false : true && isBalanced(root.left) && isBalanced(root.right); 
    }
    
    private int height(TreeNode root) {
        if(root == null)
            return 0;
                
        int hLeft  = height(root.left);
        int hRight = height(root.right);
        
        return Math.max(hLeft, hRight) + 1;
        
    }
}
