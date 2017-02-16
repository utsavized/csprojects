package occurrences;

import java.util.Iterator;

/**
 * An index builder that populates an index from a source represented by an
 * iterator over tokens.
 * 
 * @author Laufer, Modified by Utsav Pandey
 * @modified_date 10/31/2010
 */
public class TokenIteratorIndexBuilder {

	private final Index index;		//index
	
	/**
	 * Constructs a builder for the given index.
	 * 
	 * @param index
	 *            the Index to be built
	 */
	public TokenIteratorIndexBuilder(final Index index) {
		if (index == null)
			throw new IllegalArgumentException("index == null");
		this.index = index;
	}

	/**
	 * Builds the index from the given source.
	 * 
	 * @param source
	 *            the Iterator over Tokens from which to build the index
	 */
	public void buildFrom(final Iterator<Token> source) {
		if (source == null)
			throw new IllegalArgumentException("source == null");
		while(source.hasNext()){				//While list has tokens
			Token token = source.next();		//Get current token
			index.add(token.getWord(), token.getLine());	//Add mappings to index
		}
	}
}
