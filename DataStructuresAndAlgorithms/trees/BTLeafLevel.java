package trees;

import java.util.LinkedList;
import java.util.Queue;

import reusableobjects.TreeNode;


public class BTLeafLevel {
	
	
	public boolean checkLeafLevel(TreeNode root) {
	    if(root == null)
	        return true;
	    
	    //Assuming node has left and right both
	    Queue<TreeNode> q = new LinkedList<TreeNode>();
	    q.add(root.left);
	    q.add(root.right);
	    int thisLevel = 2;
	    int nextLevel = 0;
	    
	    while(!q.isEmpty()) {
	        boolean nodeHasChild = false;
	        TreeNode node = q.remove();
	        thisLevel--;
	        if(node.left != null) {
	            nodeHasChild = true;
	            q.add(node.left);
	            nextLevel++;
	        }
	        if(node.right != null) {
	            nodeHasChild = true;
	            q.add(node.right);
	            nextLevel++;
	        }
	        if(thisLevel == 0) {
	        	thisLevel = nextLevel;
	        	nextLevel = 0;
	        }
	        else if(nodeHasChild && q.peek().right == null && q.peek().left == null)
	            return false;
	    }
	    
	    return true;

	}
}
