package maps.implementations;

public interface IMyMap<K,V> {
	public boolean containsKey(K key);
	public boolean isEmpty();
	public void put(K key, V value);
	public V remove(K key);
	public V get(K key);
	public int getHashCode(K key);
}
