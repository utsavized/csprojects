package linkedlists.implementations;

import reusableobjects.Node;


public class LinkedList {
	private Node head;
	private Node tail;
	public LinkedList() {
		this.head = null;
		this.tail = null;
	}
	
	public Node getHead(){
		return this.head;
	}
	
	public void insert(Node node) {
		if(this.head == null) {
			this.head = node;
			this.tail = node;
		}
		else {
			this.tail.setNext(node);
			this.tail = node;
		}
	}
	
	public Node insertAfter(Node node, Object data) throws Exception{
		if(this.head == null) throw new Exception("List is empty.");
		Node newNode = new Node();
		newNode.setData(data);
		if(node == null) {
			newNode.setNext(this.head);
			this.head = newNode;
			return newNode;
		}
		else {
			Node nextNode = this.head;
			while(nextNode != null){
				if(nextNode == node) {
					newNode.setNext(nextNode.getNext());
					nextNode.setNext(newNode);
					return newNode;
				}
				nextNode = nextNode.getNext();
			}
		}
		return null;
	}
	
	public boolean remove(Node node) {
		if(node == this.head) {
			Node remNode = this.head;
			this.head = this.head.getNext();
			remNode.setNext(null);
			return true;
		}
		else {
			Node nextNode = this.head;
			while(nextNode != null) {
				if(nextNode.getNext() == node) {
					Node remNode = nextNode.getNext();
					nextNode.setNext(remNode.getNext());
					remNode.setNext(null);
					return true;
				}
				nextNode = nextNode.getNext();
			}
		}
		return false;
	}
	
	public Node mNode(int m) throws Exception {
		if(this.head == null) throw new Exception("List is empty.");
		int count = 1;
		Node mNode = this.head;
		Node nextNode = this.head;
		while(nextNode != null) {
			nextNode = nextNode.getNext();
			if(count > m + 1)
				mNode = mNode.getNext();
			count++;
		}
		return mNode;
	}
	
	public void reverse() {
		if(this.head == null || this.head.getNext() == null) 
			return;
		Node nextNode = this.head;
		//Get tail
		while(nextNode != null) {			
			if(nextNode.getNext() == null)
				this.tail = nextNode;
			nextNode = nextNode.getNext();
		}
		
		Node t1,t2;
		t1 =  this.tail;
		t2 = null;
		
		while(this.head != t1) {
			Node insert = this.head;
			this.head = insert.getNext();
			t1.setNext(insert);
			insert.setNext(t2);
			t2 = insert;
		}
	}


}
