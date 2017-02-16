package stacks.implementations;

import reusableobjects.Node;

public class LinkedStack<T> implements IStack<T> {
	private Node<T> top;
	private int nodeCount;
	public LinkedStack() {
		this.top = null;
		this.nodeCount = 0;
	}
	
	@Override
	public void push(T item) {
		Node<T> newNode = new Node<T>();
		newNode.setData(item);
		
		if(this.nodeCount == 0) {
			this.top = newNode;
			this.nodeCount++;
		}
		else {
			newNode.setNext(this.top); 
			this.top = newNode;
			this.nodeCount++;
		}
	}
	
	@Override
	public T pop() throws Exception {
		T poppedElm;
		if(this.top == null)
			throw new Exception("Cannot pop empty list.");
		else {
			poppedElm = top.getData();
			top = top.getNext();
		}
		return poppedElm;
	}
	
	@Override
	public T top() throws Exception {
		if(this.top == null)
			throw new Exception("Cannot pop empty list.");
		return this.top.getData();
	}
	
	@Override
	public int size() {
		return this.nodeCount;
	}

	@Override
	public boolean isEmpty() {
		return (this.nodeCount == 0);
	}
}