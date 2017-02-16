package reusableobjects;

public class MyTreeNode<T extends Comparable<T>> {
	public MyTreeNode<T> left;
	public MyTreeNode<T> right;
	public T value;
	
	public MyTreeNode(T value) {
		this.value = value;
	}
	
	public int compareTo(T value) {
		return this.value.compareTo(value);
		
	}
	
}
