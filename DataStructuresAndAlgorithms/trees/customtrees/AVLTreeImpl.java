package trees.customtrees;
import reusableobjects.*;

public class AVLTreeImpl {
	private EmployeeNode root;
	public AVLTreeImpl(EmployeeNode root) {
		this.root = root;
	}

	public EmployeeNode getRoot() {
		return this.root;
	}
	
	public void insert(EmployeeNode node) {
		if(root == null) {
			root = node;
			return;
		}
		insert(root, node);
	}
	
	private EmployeeNode insert(EmployeeNode root, EmployeeNode node) {
		if(root.employee.equals(node.employee)) {
			return null;
		}
		//Root is smaller
		else if(root.compareTo(node) < 0) {
			if(root.right == null)
				root.right = node;
			else 
				root.right = insert(root.right, node);
		}
		//Root is greater
		else {
			if(root.left == null)
				root.left = node;
			else 
				root.left = insert(root.left, node);
		}
		//Check balance
		root = checkBalance(root);
		this.root = root;
		return root;
	}

	private EmployeeNode checkBalance(EmployeeNode root) {
		if(balanceFactor(root) > 1) {
			//Left is heavy
			if(balanceFactor(root.left) > 0) 
				root = rotateR(root);
			else 
				root = rotateLR(root);
		}
		else if(balanceFactor(root) < -1) {
			//Right is heavy
			if(balanceFactor(root.right) > 0) 
				root = rotateRL(root);
			else 
				root = rotateL(root);
		}
		return root;
	}
	
	private int balanceFactor(EmployeeNode node) {
		return height(node.left) - height(node.right);
	}
	
	
	private EmployeeNode rotateL(EmployeeNode root) {
		EmployeeNode temp = root;
		root = root.right;
		temp.right = root.left;
		root.left = temp;
		return root;
	}
	
	private EmployeeNode rotateR(EmployeeNode root) {
		EmployeeNode temp = root;
		root = root.left;
		temp.left = root.right;
		root.right = temp;
		return root;
	}
	
	private EmployeeNode rotateLR(EmployeeNode root) {
		root.right = rotateL(root.right);
		root = rotateR(root);
		return root;
	}
	
	private EmployeeNode rotateRL(EmployeeNode root) {
		root.left = rotateR(root.left);
		root = rotateL(root);
		return root;
	}
	
	public EmployeeNode remove(Employee o) {
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
		//Check balance
		root = checkBalance(root);
		this.root = root;
		return root;
	}
	private Employee retrieveData(EmployeeNode root)
	{
		while (root.right != null) 
			root = root.right;
		return root.employee;
	}

	public EmployeeNode search(Object o) {
		return search(root, new EmployeeNode((Employee) o));
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
	
	public int height(EmployeeNode tree) {
		if(tree == null)
			return -1;
		
		int heightLeft = height(tree.left);
		int heightRight = height(tree.right);
		
		return ((heightLeft > heightRight) ? heightLeft : heightRight) + 1; 
	}

}
