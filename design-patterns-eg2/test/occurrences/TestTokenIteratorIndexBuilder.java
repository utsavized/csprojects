package occurrences;

import static occurrences.Constants.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import occurrences.DefaultIndexImpl;
import occurrences.Index;
import occurrences.StreamTokenizerIndexBuilder;
import occurrences.Token;
import occurrences.TokenIteratorIndexBuilder;

/**
 * @author Laufer, Modified by Utsav Pandey
 * @modified_date 10/31/2010
 */
public class TestTokenIteratorIndexBuilder {

	/**
	 * Test method for
	 * {@link occurrences.TokenIteratorIndexBuilder#TokenIteratorIndexBuilder(java.util.Iterator)}.
	 */
	@Test
	public void testTokenIteratorIndexBuilder() {
		try {
			new StreamTokenizerIndexBuilder(null);
			fail("expected assertion != null");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Test method for
	 * {@link occurrences.TokenIteratorIndexBuilder#build(occurrences.Index)}.
	 */
	@Test
	public void testBuild() throws Exception {
		Index index = new DefaultIndexImpl();
		TokenIteratorIndexBuilder builder = new TokenIteratorIndexBuilder(index);
		builder.buildFrom(stringToList(s7).iterator());
		assertEquals(s7index, index);
	}
	
	

	/**
	 * Test method for
	 * {@link occurrences.TokenIteratorIndexBuilder#build(occurrences.Index)}.
	 */
	@Test
	
	public void testBuild2() throws Exception {
		Index index = new DefaultIndexImpl();
		TokenIteratorIndexBuilder builder = new TokenIteratorIndexBuilder(index);
		builder.buildFrom(stringToList(s8).iterator());
		assertEquals(s8index, index);
	}

	/**
	 * Converts a string to a list of tokens.
	 * 
	 * @param string
	 *            the input string
	 * @return the resulting list of tokens
	 * @throws IOException 
	 */
	private List<Token> stringToList(final String string) throws IOException {

		List<Token> list = new ArrayList<Token>();		//List to store tokens
		StreamTokenizer s = new StreamTokenizer(new StringReader(string));
		s.ordinaryChar('.');
		//StreamTokenizer
		int token;
		while ((token = s.nextToken()) != StreamTokenizer.TT_EOF) {
			if (token == StreamTokenizer.TT_WORD)
				list.add(new DefaultToken(s.sval,s.lineno()));	//Add tokens to list
		}
		return list;	//Return list of tokens
	}
}
