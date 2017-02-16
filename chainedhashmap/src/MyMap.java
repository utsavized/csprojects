import java.util.*;

/**
 * <i>Custom implementation of Map using ArrayList.</i>
 * @author Utsav Pandey, Scott Kreider, Antonio Claros Molina
 * @version 1.09
 * @date 09/15/2010
 */

public class MyMap implements Map<Object,Object>{
    private List<Object> keyArray   = null;	//Array of keys
    private List<Object> valueArray = null;	//Array of values

    /**
     * Constructor for MyMap
     */
    
    public MyMap(){
        keyArray   = new ArrayList<Object>();
        valueArray = new ArrayList<Object>();
    }
    
    @Override
    public int size() {
        int count = 0;
        count = valueArray.size();
        return count;
    }

    @Override
    public boolean isEmpty(){
        //get the size of the keyArray
        int size = keyArray.size();
        
        if(size == 0){
            return true;
            }
        
        else{
            return false;
            }
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyArray == null) ? 0 : keyArray.hashCode());
		result = prime * result + ((valueArray == null) ? 0 : valueArray.hashCode());
		return result;
	}

	@Override
    public boolean equals(Object m){
    	MyMap temp = (MyMap)m;
    	if(size()!=temp.size()){
    		return false;
    	}
    	else if(keyArray.equals(temp.keyArray)&&valueArray.equals(temp.valueArray)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

	@Override
    public boolean containsKey(Object key) {
        int x = keyArray.indexOf(key);
        if(x == -1){
            return false;
        }
        else{
        return true;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        int x = valueArray.indexOf(value);
        if(x == -1){
            return false;
        }
        else{
        return true;
        }
    }

    @Override
    public Object get(Object key) {
        int keyIndex = keyArray.indexOf(key);
        if(keyIndex == -1){
            return null;
        }
        else{
            Object gotIt = valueArray.get(keyIndex);
            return gotIt;
        }
    }

    @Override
    public Object put(Object key, Object value) {
        if(key==null || value==null)
        	return null;
        else if(containsKey(key)){
        	int indexVal = keyArray.indexOf(key);
        	valueArray.set(indexVal, value);
        }
        else{
        	keyArray.add(key);
            valueArray.add(value);
        }
        return null;
    }

    @Override
    public Object remove(Object key) {
        if(isEmpty())
        	return null;
        else{
        	int keyRemove   = keyArray.indexOf(key);
            int valueRemove = keyArray.indexOf(key);
            if (keyRemove!=-1 && valueRemove!=-1){
            	Object value = valueArray.get(keyRemove);
                keyArray.remove(keyRemove);
                valueArray.remove(valueRemove);
                return value;
            }
            else
            	return null;
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
	public void putAll(Map m){
       Iterator keysIter = m.keySet().iterator();
       while (keysIter.hasNext()) {
           Object k = keysIter.next();
           Object v = m.get(k);
           put(k, v);
       }
    }

    @Override
    public void clear() {
        Boolean c = keyArray.isEmpty();
        while(c != true){
            keyArray.remove(0);
            c = keyArray.isEmpty();
        }
    }

    @Override
    public Set<Object> keySet() {
        return new HashSet<Object>(keyArray);
    }

    @Override
    public Collection<Object> values() {
        return valueArray; 
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Set entrySet() {
        return null;
    }
}
