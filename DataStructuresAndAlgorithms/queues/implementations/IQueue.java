package queues.implementations;

public interface IQueue<T> {
	public void enqueue(T obj);
	public T deque();
	public int size();
}
