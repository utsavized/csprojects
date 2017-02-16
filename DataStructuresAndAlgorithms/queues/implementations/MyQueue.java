package queues.implementations;


public class MyQueue<T> extends DynamicQueue<T> {
	Object [] queue;
	int first;
	int last;
	public MyQueue(int size) {
		super(size);
		this.queue = new Object[size];
		this.first = 0;
		this.last = -1;
	}

	@Override
	public void enqueue(T obj) {
		if(last == super.size() - 1) {
			resize();
		}
		
		this.queue[++last] = obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T deque() {
		T obj;
		if(this.first > this.last)
			throw new NullPointerException();
		else {
			obj = (T) this.queue[this.first];
			this.queue[this.first++] = null;
			
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public T peek() {
		T obj;
		if(this.first > this.last)
			throw new NullPointerException();
		else
			obj = (T) this.queue[this.first];
		return obj;
	}

	@Override
	void resize() {
		int currentSize = this.last - this.first + 1;
		if(currentSize < super.size()/2) {
			int newFirst = 0;
			for(int i = this.first; i <= this.last; i++) {
				this.queue[newFirst++] = this.queue[this.first];
				this.queue[first++] = null;
			}
		}
		else {
			int newSize = super.size() * 2;
			Object [] newArray = new Object[newSize];
			for(int i = this.first; i < currentSize; i++) {
				newArray[i] = this.queue[i];
			}
			this.queue = newArray;
			super.setSize(newSize);
		}
		this.first = 0;
		this.last = currentSize - 1;
	}
	
	@Override
	public int size() {
		return (this.first > this.last) ? 0 : this.last - this.first + 1;
	}
	
}
