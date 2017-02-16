package maps.implementations;

class Entry {
	public Entry(String key, String value) {
		super();
		this.key = key;
		this.value = value;
		this.next = null;
	}
	public String key;
	public String value;
	Entry next;
	
}

public class HashMap implements IMap<String, String> {
	
	//A.k mod 2^r >> (w-r)
	//A => odd integer 2^(w-1) < A < 2^w
	//Don't pick A too close to a power of 2
	//This is faster than division method
		 
	private Entry [] array;
	final int SIZE = 51;
	int elementCount = 0;
	
	public HashMap() {
		this.array = new Entry[SIZE];
	}
	
	@Override
	public int size() {
		return this.elementCount;
	}

	@Override
	public boolean isEmpty() {
		return (this.elementCount == 0);
	}

	@Override
	public void put(String key, String value) {
		int hashCode = getHashCode(key);
		if(this.containsKey(hashCode)) {
			Entry e = this.array[hashCode];
			while(e != null) {
				if(e.key.equals(key)) {
					e.value = value;
					return;
				}
				else if(e.next == null) {
					e.next = new Entry(key, value);
					return;
				}
				e = e.next;
			}
		}
		else {
			Entry e = new Entry(key, value);
			this.array[hashCode] = e;
		}
		this.elementCount++;
	}

	@Override
	public String get(String key){
		int hashCode = getHashCode(key);
		if(this.containsKey(hashCode)) {
			Entry e = this.array[hashCode];
			while(e != null) {
				if(e.key.equals(key))
					return e.value;
				e = e.next;
			}
		}
		
		throw new NullPointerException();
	}

	@Override
	public String delete(String key) {
		int hashCode = getHashCode(key);
		if(this.containsKey(hashCode)) {
			Entry e = this.array[hashCode];
			while(e != null) {
				Entry del = e.next;
				if(e.key.equals(key)) {
					del = e;
					this.array[hashCode] = e.next;
					e.next = null;
					this.elementCount--;
					return del.value;
				}
				else if(e.next != null && e.next.key.equals(key)) {
					del = e.next;
					e.next = del.next;
					this.elementCount--;
					return del.value;
				}
			}
		}

		throw new NullPointerException();
	}
	
	@Override
	public boolean containsKey(String key) {
		int hashCode = getHashCode(key);
		if(this.array[hashCode] != null) {
			Entry e = this.array[hashCode];
			while(e != null) {
				if(e.key.equals(key))
					return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.array.length; i++) {
			if(this.array[i] != null) {
				Entry e = this.array[i];
				while(e != null) {
					sb.append("[");
					sb.append(e.key);
					sb.append(",");
					sb.append(e.value);
					sb.append("]");
					sb.append(" ");
					
					e = e.next;
				}
			}
		}
		return sb.toString();
	}
	

	private boolean containsKey(int hashCode) {
		return (this.array[hashCode] != null);
	}

	
	private int getHashCode(String key) {
		return key.hashCode() % this.SIZE;
	}
	
}
