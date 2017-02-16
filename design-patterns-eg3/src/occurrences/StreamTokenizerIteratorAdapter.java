package occurrences;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An adapter that makes a StreamTokenizer look like an Iterator.
 * 
 * @author Laufer, Modified by Utsav Pandey
 * @modified_date 10/31/2010
 */
public class StreamTokenizerIteratorAdapter implements Iterator<Token> {

	private List<Token> list;	//List to store tokens
	private int count = 0;		//Keep count of the list
	
	/**
	 * Constructs an adapter for the given StreamTokenizer.
	 * 
	 * @param source
	 *            the StreamTokenizer
	 * @throws IOException 
	 */
	public StreamTokenizerIteratorAdapter(final StreamTokenizer source) throws IOException {
		if (source == null)
			throw new IllegalArgumentException("source == null");
		list = iterate(source);	//Get list of tokens from source
	}
	
	//Iterator
	public Iterator<Token> iterator(){
		return this;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return count < list.size();		//if size is greater than count
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Token next() {
		if(count == list.size())		//Check is iteration is complete
			throw new NoSuchElementException();
		return list.get(count++);		//Return current element, then increment count
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	/***
	 * 
	 * @param source
	 * @return
	 * @throws IOException
	 */
	
	private List<Token> iterate(final StreamTokenizer source) throws IOException{
		List<Token> list = new ArrayList<Token>();
		source.ordinaryChar('.');
		int token;
		//StreamTokenizer
		while ((token = source.nextToken()) != StreamTokenizer.TT_EOF) {
			if (token == StreamTokenizer.TT_WORD)
				list.add(new DefaultToken(source.sval,source.lineno()));
		}
		return list;		//Return list of tokens
	}
}
