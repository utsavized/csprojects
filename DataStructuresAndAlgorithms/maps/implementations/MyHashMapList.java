package maps.implementations;

public class MyHashMapList<K,V> implements IMyMap<K,V> {
	private MyEntry<K,V> [] table;
	private int elementCount;
	private final int DEFAULT_SIZE = 13;
	private int size;

	@SuppressWarnings("unchecked")
	public MyHashMapList() {
		this.table = new MyEntry[DEFAULT_SIZE];
		this.elementCount = 0;
		this.size  = DEFAULT_SIZE;
	}

	@SuppressWarnings("unchecked")
	public MyHashMapList(int n) {
		this.table = new MyEntry[n];
		this.elementCount = 0;
		this.size  = n;
	}

	@Override
	public boolean containsKey(K key) {
		int hashCode = getHashCode(key);
		return (this.table[hashCode] != null);
	}
	
	@Override
	public boolean isEmpty() { 
		return (elementCount == 0); 
	}

	@Override
	public void put(K key, V value) {
		int hashCode = getHashCode(key);
		if(this.table[hashCode] != null) {
			MyEntry<K,V> values = this.table[hashCode];
			MyEntry<K,V> entry = containsEntry(values, key);
			if(entry == null) {
				entry = new MyEntry<>(key, value);
				entry.next = values;
				this.table[hashCode] = entry;
				this.elementCount++;
			}
			else {
				if(!entry.value.equals(value)) {
					entry.value = value;
				}
			}
		}
		else {
			MyEntry<K,V> entry = new MyEntry<>(key, value);
			this.table[hashCode] = entry;
			this.elementCount++;
		}
	}
	
	@Override
	public V get(K key) {
		int hashCode = getHashCode(key);
		if(this.table[hashCode] != null) {
			MyEntry<K,V> entry = containsEntry(this.table[hashCode], key); 
			return (entry != null) ? entry.value : null;
		}
		return null;
	}

	@Override
	public V remove(K key) {
		int hashCode = getHashCode(key);
		if(this.table[hashCode] != null) {
			MyEntry<K,V> prevEntry = removeEntry(this.table[hashCode], key);
			if(prevEntry != null) {
				MyEntry<K,V> removeElm = prevEntry.next;
				prevEntry.next = removeElm.next;
				removeElm.next = null;
				this.elementCount++;
				return removeElm.value;
			}
		}
		return null;
	}
	
	@Override
	public int getHashCode(K key) {
		return key.hashCode() % this.size;
	}

	private MyEntry<K,V> containsEntry(MyEntry<K,V> head, K key) 
	{
		while(head != null) {
			if(head.key.equals(key))
				return head;
			head = head.next;
		}
		return null;
	}

	private MyEntry<K,V> removeEntry(MyEntry<K,V> head, K key) {
		while(head.next != null) {
			if(head.next.key.equals(key)) {
				return head;
			}
			head = head.next;
		}
		return null;
	}
}