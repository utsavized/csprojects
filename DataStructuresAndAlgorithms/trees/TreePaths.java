package trees;

import java.util.ArrayList;
import java.util.Stack;

import reusableobjects.TreeNode;

public class TreePaths {
	
	/**
	 * Longest path between two nodes
	 * @param root
	 * @return
	 */
	public int longestPath(TreeNode root) {
		Stack<TreeNode> s = new Stack<TreeNode>();
		HeightOfTree h = new HeightOfTree();
		
		//Add all the left children 
		//to stack until null
		while(root != null) {
			s.push(root);
			root = root.left;
		}
		
		//Pop each node, from bottom up,
		//calculate path cost left + right + node
		//Update max path accordingly
		int depth = 0;
		int maxPath = 0;
		while(!s.isEmpty()) {
			TreeNode current = s.pop();
			depth++;
			int path = depth + h.getHeight(current.right);
			maxPath = (path > maxPath) ? path : maxPath;
		}
		return maxPath;
	}
	
	
	/*Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

	For example:
	Given the below binary tree and sum = 22,
	              5
	             / \
	            4   8
	           /   / \
	          11  13  4
	         /  \      \
	        7    2      1
	return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.*/
	
	public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
            return false;
        if(root.val == sum && root.left == null && root.right == null)
            return true;
        
        return hasPathSum(root, sum, root.val, 0);
    }
    
    private boolean hasPathSum(TreeNode root, int sum, int curSum, int depth) {
        if(root.left == null && root.right == null)
            return (sum == curSum && depth > 0);
        
        boolean hasPath = false;
        if(root.left != null)
            hasPath = hasPathSum(root.left, sum, curSum + root.left.val, depth + 1);
        if(!hasPath && root.right != null)
            hasPath = hasPathSum(root.right, sum, curSum + root.right.val, depth + 1);
        
        return hasPath;
    }
    
    /*Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

	For example:
	Given the below binary tree and sum = 22,
	              5
	             / \
	            4   8
	           /   / \
	          11  13  4
	         /  \    / \
	        7    2  5   1
	return

	[
	   [5,4,11,2],
	   [5,8,4,5]
	]*/
    
    
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        
        if(root == null)
            return paths;
        
        path.add(root.val);
        
        if(root.val == sum && root.left == null && root.right == null) {
            paths.add(path);
            return paths;
        }
            
        paths = hasPathSum(root, sum, root.val, 0, paths, path);
        return paths;
    }
    
    private ArrayList<ArrayList<Integer>> hasPathSum(TreeNode root, int sum, int curSum, int depth, ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> path) {
        if(root.left == null && root.right == null) {
            if (sum == curSum && depth > 0) {
                paths.add(path);
            }
            return paths;
        }
        
        if(root.left != null) {
            path.add(root.left.val);
            paths = hasPathSum(root.left, sum, curSum + root.left.val, depth + 1, paths, new ArrayList<Integer>(path));
            path.remove(path.size() - 1);
        }
        if(root.right != null) {
            path.add(root.right.val);
            paths = hasPathSum(root.right, sum, curSum + root.right.val, depth + 1, paths, new ArrayList<Integer>(path));
            path.remove(path.size() - 1);
        }
        
        return paths;
    }
    
    
    /*
	Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

	An example is the root-to-leaf path 1->2->3 which represents the number 123.

	Find the total sum of all root-to-leaf numbers.

	For example,

	    1
	   / \
	  2   3
	The root-to-leaf path 1->2 represents the number 12.
	The root-to-leaf path 1->3 represents the number 13.

	Return the sum = 12 + 13 = 25.
	*/
	
	public int sumNumbers(TreeNode root) {
        //When root is null
        if(root == null)
            return 0;
        //When root has no children
        if(root.left == null && root.right == null)
            return root.val;
        //To keep track of each path
        StringBuilder sb = new StringBuilder();
        //To keep track of all paths
        ArrayList<String> paths = new ArrayList<String>();
        //DFS
        paths = dfs(root, sb, paths);
        //Generate sum
        int sum = 0;
        for(int i = 0; i < paths.size(); i++) {
            sum += Integer.parseInt(paths.get(i));
        }
        
        return sum;
        
        
    }
    
    private ArrayList<String> dfs(TreeNode root, StringBuilder sb, ArrayList<String> paths) {
        if(root == null)
            return paths;
            
        if(root.left == null && root.right == null) {
            sb.append(root.val);
            paths.add(sb.toString());
            sb.delete(sb.length() - 1, sb.length());
            return paths;
        }
            
        sb.append(root.val);
        paths = dfs(root.left, sb, paths);
        paths = dfs(root.right, sb, paths);
        sb.delete(sb.length() - 1, sb.length());
        return paths;
    }
    
    
}
