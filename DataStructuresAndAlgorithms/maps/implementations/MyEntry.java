package maps.implementations;

public class MyEntry<K,V> {
	
	
	public K key;
	public V value;
	public MyEntry<K,V> next;

	public MyEntry(K key, V value) {
		this.key   = key;
		this.value = value;
		this.next = null;
	}
}
