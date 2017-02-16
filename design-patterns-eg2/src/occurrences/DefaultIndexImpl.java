package occurrences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * An efficient implementation of an index that keeps the index in alphabetical
 * order.
 * 
 * @author Laufer
 * @modified_by Utsav Pandey, Antonio Claros Molina, Scott Krieder
 * @modified_on 11/4/2010
 */
public class DefaultIndexImpl implements Index {
	
	private Map<String, List<Integer>> index;
	
	/**
	 * Constructor creates the map
	 */
	public DefaultIndexImpl(){
		index = new TreeMap<String, List<Integer>>();	//Create new Map
	}

	/**
	 * Returns the map in which the index is stored. Performs lazy
	 * initialization. Must be used in other methods instead of direct access to
	 * the map instance variable.
	 * 
	 * @return the map
	 */
		
	protected Map<String, List<Integer>> getMap() {
		return this.index;								//Return Map
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see occurences.Index#lines(java.lang.String)
	 */
	@Override
	public List<Integer> lines(final String word) {
		if(getMap()==null)								//If empty
			return Collections.emptyList(); 			//Return empty list
		else{
			if(getMap().containsKey(word)){				//If word exists
				List<Integer> lines = new ArrayList<Integer>();		//Create new list
				lines = getMap().get(word);							//Get list of line number from Map
				return Collections.unmodifiableList(lines);			//Return immutable List of line nums
			}
			else										//If word does not exist
				return Collections.emptyList();				//Return immutable empty list
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.luc.cs.laufer.index.Index#add(java.lang.String, int)
	 */
	@Override
	public void add(final String word, final int line) {
		List<Integer> lines = new ArrayList<Integer>();
		if(getMap().containsKey(word)){						//If word already exists
			lines = getMap().get(word);						//Get the list of line numbers
			lines.add(line);							//Add new line number to the list
			getMap().put(word, lines);						//Insert (Word, Line Numbers) to the Map
		}
		else{											//If word does not exist
			lines.add(line);							//Add new line number
			getMap().put(word, lines);						//Insert (Word, Line Numbers) to the Map
		}
	
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.luc.cs.laufer.index.Index#frequency(java.lang.String)
	 */
	@Override
	public int frequency(final String word) {
		if(getMap()==null)									//If Map is empty
			return 0;										//Frequency is 0
		else if(getMap().containsKey(word)){				//If Map has mappings
			int frequency = getMap().get(word).size();			//Get line number(s) for the word
			return frequency;						//Return the size of the list
														//  containing line numbers
		}
		else
			return 0;									//All else, return 0
	}

	@Override
	public Iterator<Entry<String, List<Integer>>> iterator() {
		return getMap().entrySet().iterator();			//Return the iterator of Entry Sets of the Map
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see occurences.Index#clear()
	 */
	@Override
	public void clear() {
		getMap().clear();								//Clear Map
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see occurences.Index#getSize()
	 */
	@Override
	public int size() {
		return getMap().size();							//Return size of Map
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see occurences.Index#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String word) {
		if(getMap()==null)								//If Map is null
			return false;									//No word exists
		else{											//if Map is not null
			if(getMap().containsKey(word))					//Check if word exists
				return true;									//If exists, return true
			else											//if word does not exist
				return false;									//return false
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object that) {
		//Check if that is not null and that it is an instance of the same class as this
		if (that != null  && this.getClass() == that.getClass())
		{															//If true
	    	//Cast object to type Index
			Index thatObject = (Index)that;
			//Create new EntrySet iterator for that
			Iterator<Entry<String,List<Integer>>> thatIter = thatObject.iterator();
			//Create new EntrySet iterator for this
			Iterator<Entry<String,List<Integer>>> thisIter = this.iterator();
			//New unsorted (because TreeMap has already sorted the EntrySet) Set to store EntrySet of this 
			Set<Entry<String,List<Integer>>> thisEntry = new HashSet<Entry<String,List<Integer>>>();
			//New unsorted (because TreeMap has already sorted the EntrySet) Set to store EntrySet of that
			Set<Entry<String,List<Integer>>> thatEntry = new HashSet<Entry<String,List<Integer>>>();
			//Add EntrySet of this to the Set 
			while(thisIter.hasNext())
				thisEntry.add(thisIter.next());
			//Add EntrySet of that to the Set
			while(thatIter.hasNext())
				thatEntry.add(thatIter.next());
			//Compare entrySets of this and that
			if(thisEntry.equals(thatEntry)){
				return true;					//if equal, return true
			}
			else{								//if not equal, return false
				return false;
			}
		}
		//Check if that is not null and that it is an instance of the same class as this
	    else{
	    	return false;						//return false
	    }
	}
	
	/**
	 * Overrides hashcode
	 * @return hashCode
	 */
	//Always override hashCode with equals
	@Override
	public int hashCode(){
		return 17 + 31 * getMap().hashCode(); 
	}
	

	private static final long serialVersionUID = 8119984256641351649L;
}
