package trees.implementations;

import reusableobjects.MyTreeNode;

public class MyBinarySearchTree<T extends Comparable<T>> implements ITree<T> {
	private MyTreeNode<T> root;
	private int size;
	
	public MyBinarySearchTree() {
		this.root = null;
		this.size = 0;
	}
	
	public MyBinarySearchTree(MyTreeNode<T> root) {
		this.root = root;
		size = (root != null) ? 1 : 0;
	}

	@Override
	public void insert(T value) {
		if(value == null)
			return;
		if(this.root == null) {
			this.root = new MyTreeNode<T>(value);
			return;
		}
		MyTreeNode<T> runner = this.root;
		while(true) {
			if(runner.value.compareTo(value) == 1) {
				if(runner.left == null) {
					runner.left = new MyTreeNode<T>(value);
					this.size++;
					return;
				}
				runner = runner.left;
			}
			else if(runner.value.compareTo(value) == -1) {
				if(runner.right == null) {
					runner.right = new MyTreeNode<T>(value);
					this.size++;
					return;
				}
				runner = runner.right;
			}
			else
				return;
		}
	}

	@Override
	public void remove(T value) {
		if(value == null)
			return;
		this.root = remove(this.root, value);
	}
	
	private MyTreeNode<T> remove(MyTreeNode<T> root, T value) {
		if(this.root == null)
			return null;
		
		if(root.value.compareTo(value) == -1)
			root.right = remove(root.right, value);
		else if(root.value.compareTo(value) == 1)
			root.left  = remove(root.left, value);
		else {
			//No child
			if (root.left  == null && root.right == null) return null;
			//One child -- Right
			if (root.left  == null) return root.right;
		    //One child -- Left
			if (root.right == null) return root.left;
		    //Both children
			MyTreeNode<T> temp = getSmallestNode(root.right);
	        //copy key field from temp to root
	        root.value = temp.value;
 	        //now delete temp from root's right subtree and return
	        root.right = remove(root.right, temp.value);
	        
	        return root;   
		}
		return root;
	}
	
	private MyTreeNode<T> getSmallestNode(MyTreeNode<T> node) {
	    if (node.left == null) return node;
	    else return getSmallestNode(node.left);
	}

	@Override
	public boolean exists(T value) {
		return (find(value) != null);
	}
	
	private MyTreeNode<T> find(T value) {
		if(value == null)
			return null;
		MyTreeNode<T> runner = this.root;
		while(runner != null) {
			if(runner.value.compareTo(value) == 0)
				return runner;
			else if(runner.value.compareTo(value) == 1)
				runner = runner.left;
			else
				runner = runner.right;
		}
		return runner;
	}
}
