package occurrences;

import java.io.IOException;
import java.io.StreamTokenizer;

/**
 * An index builder that populates an index from a source represented by a
 * StreamTokenizer.
 * 
 * @author Laufer, Modified by Utsav Pandey
 * 
 */
public class StreamTokenizerIndexBuilder {
	
	private Index index;
	/**
	 * Constructs a builder for the given index.
	 * 
	 * @param index
	 *            the Index to be built
	 */
	public StreamTokenizerIndexBuilder(final Index index) {
		if (index == null)
			throw new IllegalArgumentException("index == null");
		this.index = index;
	}

	/**
	 * Builds the index from the given source.
	 * 
	 * @param source
	 *            the StreamTokenizer from which to build the index
	 */
	public void buildFrom(StreamTokenizer source) {
		if (source == null)
			throw new IllegalArgumentException("source == null");
		try {
			source.ordinaryChar('.');
			//StreamTokenizer
			int token;
			while ((token = source.nextToken()) != StreamTokenizer.TT_EOF) {
				if (token == StreamTokenizer.TT_WORD) {
					index.add(source.sval, source.lineno());	//Add to index
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
