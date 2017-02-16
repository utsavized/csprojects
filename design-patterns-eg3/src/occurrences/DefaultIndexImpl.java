package occurrences;

import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * An efficient implementation of an index that keeps the index in alphabetical
 * order.
 * 
 * @author Laufer, Modified by Utsav Pandey
 * @Modified_date 10/31/2010
 */
public class DefaultIndexImpl extends TreeMap<String, List<Integer>> implements Index {
	/**
	 * Constructor
	 * @param (empty)
	 */
	public DefaultIndexImpl(){
		super(); //Get Map from super
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.luc.cs.laufer.index.Index#add(java.lang.String, int)
	 */
	@Override
	public void add(final String word, final int line) {
		List<Integer> value = new ArrayList<Integer>(); //ArrayList to store line numbers
		if(this.containsKey(word)){						//If word already exists
			value = this.get(word);						//Get the list of line numbers
			value.add(line);							//Add new line number to the list
			this.put(word, value);						//Insert (Word, Line Numbers) to the Map
		}
		else{											//If word does not exist
			value.add(line);							//Add new line number
			this.put(word, value);						//Insert (Word, Line Numbers) to the Map
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.luc.cs.laufer.index.Index#frequency(java.lang.String)
	 */
	@Override
	public int frequency(final String word) {
		List<Integer> value = new ArrayList<Integer>();	//ArrayList to store line numbers
		if(this==null)									//If Map is empty
			return 0;									//Frequency is 0
		else if(this.containsKey(word)){				//If Map has mappings
			value = this.get(word);						//Get line number(s) for the word
			return value.size();						//Return the size of the list
														//  containing line numbers
		}
		else
			return 0;									//All else, return 0
	}

	private static final long serialVersionUID = 8119984256641351649L;
}
