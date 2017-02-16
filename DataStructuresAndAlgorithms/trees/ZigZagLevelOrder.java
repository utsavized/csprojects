package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import reusableobjects.TreeNode;


/*Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]*/

public class ZigZagLevelOrder {
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>();
       
        if(root == null)
            return levels;
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        int thisLevel = 1;
        int nextLevel = 0;
        q.add(root);
        
        boolean zig = true;
        
        ArrayList<Integer> level = new ArrayList<Integer>();
        
        while(!q.isEmpty()) {
            TreeNode node = q.remove();
            thisLevel--;
            
            if(zig)
                level.add(node.val);
            else
                level.add(0, node.val);
            
            if(node.left != null) { q.add(node.left); nextLevel++; }
            if(node.right!= null) { q.add(node.right);nextLevel++; }
            
            
            if(thisLevel == 0) {
                thisLevel = nextLevel;
                nextLevel = 0;
                zig = (zig) ? false : true;
                levels.add(level);
                level = new ArrayList<Integer>();
            }  
        }
        
        return levels;
        
    }
}
