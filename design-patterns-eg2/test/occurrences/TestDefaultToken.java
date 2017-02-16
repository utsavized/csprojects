package occurrences;

import static org.junit.Assert.*;

import org.junit.Test;

import occurrences.DefaultToken;
import occurrences.Token;

/**
 * @author Laufer
 * 
 */
public class TestDefaultToken {

	/**
	 * Test method for
	 * {@link occurrences.DefaultToken#DefaultToken(java.lang.String, int)}.
	 */
	@Test
	public void testDefaultToken() {
		new DefaultToken("abc", 5);
	}

	/**
	 * Test method for
	 * {@link occurrences.DefaultToken#DefaultToken(java.lang.String, int)}.
	 */
	@Test
	public void testDefaultTokenNegative() {
		try {
			new DefaultToken("abc", -1);
			fail("expected failed assertion > 0");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Test method for
	 * {@link occurrences.DefaultToken#DefaultToken(java.lang.String, int)}.
	 */
	@Test
	public void testDefaultTokenNull() {
		try {
			new DefaultToken(null, 77);
			fail("expected failed assertion != 0");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Test method for
	 * {@link occurrences.DefaultToken#getLine()}.
	 */
	@Test
	public void testGetWord() {
		Token token = new DefaultToken("abc", 5);
		assertEquals("abc", token.getWord());
	}

	/**
	 * Test method for
	 * {@link occurrences.DefaultToken#getWord()}.
	 */
	@Test
	public void testGetLine() {
		Token token = new DefaultToken("abc", 5);
		assertEquals(5, token.getLine());
	}

}
