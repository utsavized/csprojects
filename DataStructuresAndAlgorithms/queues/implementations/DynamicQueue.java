package queues.implementations;


public abstract class DynamicQueue<T> implements IQueue<T> {
	private int size;
	public DynamicQueue(int size) {
		this.size = size;
	}
	
	public int size() {
		return this.size;
	}
	
	void setSize(int size) {
		this.size = size;
	}
	abstract void resize();
}
