package stacks.implementations;

public interface IStack<T> {
	public void push(T item);
	public T pop() throws Exception;
	public T top() throws Exception;
	public int size();
	public boolean isEmpty();
}
