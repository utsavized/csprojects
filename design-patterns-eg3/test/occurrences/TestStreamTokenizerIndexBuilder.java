package occurrences;

import static occurrences.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.StreamTokenizer;
import java.io.StringReader;

import org.junit.Test;

import occurrences.DefaultIndexImpl;
import occurrences.Index;
import occurrences.StreamTokenizerIndexBuilder;

/**
 * @author Laufer
 * 
 */
public class TestStreamTokenizerIndexBuilder {

	/**
	 * Test method for
	 * {@link occurrences.StreamTokenizerIndexBuilder#StreamTokenizerIndexBuilder(java.io.StreamTokenizer)}.
	 */
	@Test
	public void testStreamTokenizerIndexBuilder() {
		try {
			new StreamTokenizerIndexBuilder(null);
			fail("expected assertion != null");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Test method for
	 * {@link occurrences.StreamTokenizerIndexBuilder#build(occurrences.Index)}.
	 */
	@Test
	public void testBuild() {
		Index index = new DefaultIndexImpl();
		StreamTokenizerIndexBuilder builder = new StreamTokenizerIndexBuilder(index);
		builder.buildFrom(new StreamTokenizer(new StringReader(s7)));
		assertEquals(s7index, index);
	}

	/**
	 * Test method for
	 * {@link occurrences.StreamTokenizerIndexBuilder#build(occurrences.Index)}.
	 */
	@Test
	public void testBuild2() {
		Index index = new DefaultIndexImpl();
		StreamTokenizerIndexBuilder builder = new StreamTokenizerIndexBuilder(index);
		builder.buildFrom(new StreamTokenizer(new StringReader(s8)));
		assertEquals(s8index, index);
	}

}
