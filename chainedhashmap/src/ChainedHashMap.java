import java.util.*;
/**
 * 
 */

/**
 * Interface that declares the apply function, implemented my ChainedHashMap
 */
interface ToInteger<T>{
	/**
	 * @param arg of type T
	 */
	int apply(T arg);
}

/**
 * <i>Custom HashMap implementation with chaining (to avoid collisions)
 * using Array and LinkedList.</i>
 * @author Antonio Claros Molina, Scott Krieder, and Utsav Pandey 
 * @date 09/23/2010 
 * @version 1.0
 * @param hasher the hash function that will be used
 * @param size size of the array table consisting the hash keys
 * @param table array consisting of the hash keys
 */
public class ChainedHashMap implements Map<Integer,Integer>{
	ToInteger<Integer> hashFunction;
	LinkedList<Integer>[] table;
	static int tableSize;
	
	/**
	 * <i>Constructor initializes hash function and table size</i>
	 * @param hasher the hash function that will be used
	 * @param size size of the array table consisting the hash keys
	 */
	@SuppressWarnings("unchecked")
	ChainedHashMap(ToInteger<Integer> hasher, int size){
		hashFunction = hasher;
		tableSize = size;
		table = new LinkedList[tableSize];
	}
	
	/**
	 * <i>Gets the hash key value</i>
	 * @param key the key for which a hash will be generated
	 * @return generated hash key value for the given key
	 */
	int getHashValue(int key){
		int hashKey = hashFunction.apply(key);
		return hashKey;
	}
	
	@Override
	public Integer put(Integer key, Integer value){
		//Get hashkey
		int hashKey = getHashValue(key);
		boolean found = false;
		//Just in case we end up using hashFunctionThree with an array size of 100
		//In that case it would try to insert at index higher than 100. This cond. 
		//helps avoid that overflow.
		if(hashKey<tableSize){
			if(table[hashKey]==null){			//If hashtable @ index has no buckets
				table[hashKey] = new LinkedList<Integer>();		//Add new bucket
				table[hashKey].add(value);
				found = true;
				return null;
			}
			else								//If bucket already exists
				//Traverse buckets to see if value already exists
				//Map cannot insert duplicate keys
				for(Integer linkedListLoop: table[hashKey])
					if(linkedListLoop == key)
						found = true;
			if(!found){
				//If no duplicates, add new value
				table[hashKey].add(value);
				return null;
			}
			else
				return null;
		}
		return null;
	}
	
	@Override
	public Integer get(Object key){
		int hashKey = getHashValue((Integer)key); 
		//Just in case we end up using hashFunctionThree with an array size of 100
		//In that case it would try to retireve from an index higher than 100. This cond. 
		//helps avoid a null pointer.
		if(hashKey<tableSize){
			if(table[hashKey]==null)		//If hashtable @ index has no buckets
				return null;				//Not found
			
			else{
				//Traverse buckets till value is found
				for(Integer linkedListLoop: table[hashKey])
				{
					if(linkedListLoop.equals((Integer)key))
						return (Integer)key;				//Found
				}
				return null;								//Not found
			}
		}
		return null;
	}

	@Override
	public void clear() {
		table = null;
	}

	@Override
	public boolean containsKey(Object key) {
		int hashKey = getHashValue((Integer)key);
		if(hashKey<tableSize){
			if(table[hashKey]==null)		//If hashtable @ index has no buckets
				return false;				//Not found
		}
		else{
			if(table[hashKey].contains((Integer)key))
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		int hashKey = getHashValue((Integer)value);
		if(hashKey<tableSize){
			if(table[hashKey]==null)		//If hashtable @ index has no buckets
				return false;				//Not found
		}
		else{
			if(table[hashKey].contains((Integer)value))
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(table == null)
			return true;
		else
			return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void putAll(Map<? extends Integer, ? extends Integer> m) {
		Iterator keysIter = m.keySet().iterator();
	       while (keysIter.hasNext()) {
	           Object k = keysIter.next();
	           Object v = m.get(k);
	           put((Integer)k, (Integer)v);
	       }
	}

	@Override
	public Integer remove(Object key) {
		int hashKey = getHashValue((Integer)key);
		if(hashKey<tableSize){
			if(table[hashKey]==null)		//If hashtable @ index has no buckets
				return null;				//Not found
		}
		else{
			if(table[hashKey].contains((Integer)key))
			{
				table[hashKey].remove(key);
				return (Integer)key;
			}
			else
				return null;
		}
		return null;
	}

	@Override
	public int size() {
		int size = 0;
		for(int i = 0; i<tableSize; i++){
			size = size + table[i].size();
		}
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		return result;
	}
	
	@Override
	public Set<java.util.Map.Entry<Integer, Integer>> entrySet() {
		//Not required for this project
		return null;
	}

	@Override
	public Set<Integer> keySet() {
		//Not required for this project
		return null;
	}

	@Override
	public Collection<Integer> values() {
		//Not required for this project
		return null;
	}
}

/**
 * <i>Finds the hash key according to the sum of the digits</i>
 */
class hashFunctionSum implements ToInteger<Integer>{
	/**
	 * Finds the hash key
	 * @param 
	 * 		arg key to be hashed
	 * @return
	 * 		sum of digits
	 */
	public int apply(Integer arg){
		int temp = arg;
		int sum = 0;
		while(temp > 0)
		{
			sum += temp%10;
			temp /= 10;	
		}			
		return sum;
	}
}

/**
 * Finds the hash key according to the last two digits
 */
class hashFunctionLastTwo implements ToInteger<Integer>{
	/**
	 * Finds the hash key
	 * @param 
	 * 		arg key to be hashed
	 * @return
	 * 		last two digits
	 */
	public int apply(Integer arg){
		double num = arg/100.0;
		String temp = Double.toString(num);
		String[] split = temp.split("\\.");
		int lastTwo = Integer.parseInt(split[1]);
		return lastTwo;
	}
}

/**
 * Finds the hash key according to the last three digits
 */
class hashFunctionLastThree implements ToInteger<Integer>{
	/**
	 * Finds the hash key
	 * @param 
	 * 		arg key to be hashed
	 * @return
	 * 		last there digits
	 */
	public int apply(Integer arg){
		double num = arg/1000.0;
		String temp = Double.toString(num);
		String[] split = temp.split("\\.");
		int lastThree = Integer.parseInt(split[1]);
		return lastThree;
	}
}

/**
 * Finds the hash key according to the modulo by hash table size
 */
class hashFunctionModulo implements ToInteger<Integer>{
	/**
	 * Finds the hash key
	 * @param 
	 * 		arg key to be hashed
	 * @return
	 * 		modulo key by hash table size
	 */
	public int apply(Integer arg){
		int num = arg;
		int hashModulo = num % ChainedHashMap.tableSize;
		return hashModulo;
	}
		
}