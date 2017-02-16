package occurrences;

import static occurrences.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

import org.junit.Test;

import occurrences.StreamTokenizerIteratorAdapter;

/**
 * @author Laufer, Modified by Utsav Pandey
 * @modified_date 10/31/2010
 */
public class TestStreamTokenizerIteratorAdapter {

	/**
	 * Test method for
	 * {@link occurrences.StreamTokenizerIteratorAdapter#StreamTokenizerIteratorAdapter(java.io.StreamTokenizer)}.
	 * @throws IOException 
	 */
	@Test
	public void testStreamTokenizerIteratorAdapter() throws IOException {
		try {
			new StreamTokenizerIteratorAdapter(null);
			fail("expected assertion != null");
		} catch (IllegalArgumentException e) {

		}
	}

	/**
	 * Test method for
	 * {@link occurrences.StreamTokenizerIteratorAdapter}.
	 * @throws IOException 
	 */
	@Test
	public void testBuild() throws IOException {
		Index index = new DefaultIndexImpl();
		//Use TIIB to create index
		TokenIteratorIndexBuilder builder = new TokenIteratorIndexBuilder(index);
		//Wrap STIA around StreamTokenizer
		//Use TIIB builder to add index
		builder.buildFrom(new StreamTokenizerIteratorAdapter(new StreamTokenizer(new StringReader(s7))));
		assertEquals(s7index, index);
	}

	/**
	 * Test method for
	 * {@link occurrences.StreamTokenizerIteratorAdapter}.
	 * @throws IOException 
	 */
	@Test
	public void testBuild2() throws IOException {
		Index index = new DefaultIndexImpl();
		//Use TIIB to create index
		TokenIteratorIndexBuilder builder = new TokenIteratorIndexBuilder(index);
		//Wrap STIA around StreamTokenizer
		//Use TIIB builder to add index
		builder.buildFrom((new StreamTokenizerIteratorAdapter(new StreamTokenizer(new StringReader(s8)))));
		assertEquals(s8index, index);
	}
}
