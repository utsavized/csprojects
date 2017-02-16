package trees;

import java.util.Stack;

import reusableobjects.TreeNode;

/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
*/

public class FlattenBTreeToRight {
	public void flatten(TreeNode root) {
        if(root == null)
            return;
        
        TreeNode runner = root;
        Stack<TreeNode> q = new Stack<TreeNode>();
        
        while(runner.left != null || runner.right != null || !q.isEmpty()) {
            if(runner.left != null) {
                if(runner.right != null) q.push(runner.right);
                runner.right = runner.left;
                runner.left = null;
                runner = runner.right;
            }
            
            if(runner.left == null && runner.right != null)
                runner = runner.right;  
                
            if(runner.left == null && runner.right == null && !q.isEmpty()) {
                runner.right = q.pop();
                runner = runner.right;
            }
            
        }
    }
}
