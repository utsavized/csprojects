package trees.customtrees;
import java.util.HashMap;
import java.util.LinkedList;

import reusableobjects.*;


public class EmployeeBST {
	private EmployeeNode root;
	public EmployeeBST(EmployeeNode root) {
		this.root = root;
	}

	public void insert(EmployeeNode node) {
		if(root == null) {
			root = node;
			return;
		}
		insert(root, node);
	}
	
	public EmployeeNode getRoot() {
		return this.root;
	}
	
	private void insert(EmployeeNode root, EmployeeNode node) {
		if(root.employee.equals(node.employee)) {
			return;
		}
		else if(root.compareTo(node) < 0) {
			if(root.right == null)
				root.right = node;
			else
				insert(root.right, node);
		}
		else {
			if(root.left == null)
				root.left = node;
			else
				insert(root.left, node);
		}
	}

	public EmployeeNode remove(Object o) {
		return remove(root, new EmployeeNode((Employee) o));
	}
	
	private EmployeeNode remove(EmployeeNode root, EmployeeNode node) {
		if(root == null)
			return null;
		
		if(root.compareTo(node) > 0) 
			root.left = remove(root.left, node);
		else if(root.compareTo(node) < 0)
			root.right = remove(root.right, node);
		else {
			if(root.left == null && root.right == null) {
				root = null;
				return root;
			}
			else if(root.left == null) {
				root.employee = root.right.employee;
				root.right = null;
				return root;
			}
			else if(root.right == null) {
				root.employee = root.left.employee;
				root.left = null;
				return root;
			}
			else {
				root.employee = retrieveData(root.left);
				root.left = remove(root.left, root);
			}
		}
		return root;
	}
	private Employee retrieveData(EmployeeNode root)
	{
		while (root.right != null) 
			root = root.right;
		return root.employee;
	}
	
		
	public EmployeeNode search(Object o) {
		return search(this.root, new EmployeeNode((Employee) o));
	}
	
	private EmployeeNode search(EmployeeNode root, EmployeeNode node) {
		if(root == null)
			return null;
		if(root.compareTo(node) > 0) 
			return search(root.left, node);
		else if(root.compareTo(node) < 0)
			return search(root.right, node);
		else {
			if(root.equals(node)) return root;
			else return null;
		}
	}
	
	public void listBySalary(){
		inorder(root);
	}
	
	private void inorder(EmployeeNode root) {
		if(root == null)
			return;
		else {
			inorder(root.left);
			System.out.println(root.employee.toString());
			inorder(root.right);
		}
	}
	
	public int height() {
		return height(root);
	}
	
	public boolean isBalanced() {
		int height = height(this.root.left) - height(this.root.right);
		if(height > 1 || height < -1)
			return false;
		else
			return true;
	}
	
	public int height(EmployeeNode tree) {
		if(tree == null)
			return -1;
		int heightLeft = height(tree.left);
		int heightRight = height(tree.right);
		
		if(heightLeft > heightRight)
			return heightLeft + 1;
		else
			return heightRight + 1; 
	}
	
	public HashMap<Integer, LinkedList<EmployeeNode>> createLinkedLists() {
		HashMap<Integer, LinkedList<EmployeeNode>> hM = new HashMap<Integer, LinkedList<EmployeeNode>>();
		return inorderMod(root, 0, hM);
	}
	
	private HashMap<Integer, LinkedList<EmployeeNode>> inorderMod(EmployeeNode root, int depth, HashMap<Integer, LinkedList<EmployeeNode>> hM) {
		if(root == null)
			return hM;
		else {
			hM = inorderMod(root.left, depth + 1, hM);
			if(hM.containsKey(depth)) {
				LinkedList<EmployeeNode> list = hM.remove(depth);
				list.add(root);
				hM.put(depth, list);
			}
			else {
				LinkedList<EmployeeNode> list = new LinkedList<EmployeeNode>();
				list.add(root);
				hM.put(depth, list);
			}
			hM = inorderMod(root.right, depth + 1, hM);
			return hM;
		}
	}
}
