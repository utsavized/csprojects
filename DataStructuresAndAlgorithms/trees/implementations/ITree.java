package trees.implementations;

public interface ITree<T> {
	public void insert(T value);
	public void remove(T value);
	public boolean exists(T value);
}
