package reusableobjects;

public class Node<T> {
	private Node<T> next;
	private T data;
	public Node() {
		this.next = null;
		this.data = null;
	}
	
	public Node<T> getNext() {
		return this.next;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public T getData() {
		return this.data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
}


