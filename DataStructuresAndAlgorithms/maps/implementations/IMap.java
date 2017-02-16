package maps.implementations;

public interface IMap<K,V> {
	public int size();
	public boolean isEmpty();
	public void put(K key, V value);
	public V get(K k);
	public V delete(K key);
	public boolean containsKey(K key);
}
