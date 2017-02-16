package occurrences;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import occurrences.DefaultIndexImpl;
import occurrences.Index;

/**
 * @author Laufer
 * 
 */
public class TestDefaultIndexImpl {

	private Index index;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		index = new DefaultIndexImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		index.clear();
		index = null;
	}

	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl#DefaultIndexImpl()}.
	 */
	@Test
	public void testDefaultIndexImpl() throws Exception {
		assertEquals(0, index.size());
		assertFalse(index.contains("xyz"));
		assertEquals(0, index.frequency("xyz"));
		assertEquals(Collections.EMPTY_LIST, index.lines("xyz"));
	}

	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl#lines(java.lang.String)}.
	 */
	@Test
	public void testSameEmptyList() throws Exception {
		List<Integer> l1 = index.lines("xyz");
		List<Integer> l2 = index.lines("xyz");
		assertSame(l1, l2);
	}
	
	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl#add(java.lang.String, int)}.
	 */
	@Test
	public void testAdd() {
		int s = index.lines("xyz").size();
		index.add("xyz", 5);
		assertTrue(index.lines("xyz").contains(5));
		assertEquals(s + 1, index.lines("xyz").size());
	}

	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl#add(java.lang.String, int)}.
	 */
	@Test
	public void testAdd2() {
		int s = index.lines("xyz").size();
		index.add("xyz", 5);
		index.add("xyz", 5);
		index.add("xyz", 5);
		assertTrue(index.lines("xyz").contains(5));
		assertEquals(s + 3, index.lines("xyz").size());
	}

	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl#lines(java.lang.String)}.
	 */
	@Test
	public void testLines() throws Exception {
		index.add("xyz", 5);
		index.add("xyz", 3);
		assertEquals(Arrays.asList(5, 3), index.lines("xyz"));
	}

	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl#lines(java.lang.String)}.
	 */
	@Test
	public void testLinesImmutable() throws Exception {
		try {
			index.lines("xyz").add(3);
			fail("lines must return an immutable list");
		} catch (Exception e) { }
		index.add("xyz", 5);
		index.add("xyz", 3);
		try {
			index.lines("xyz").add(3);
			fail("lines must return an immutable list");
		} catch (Exception e) { }
	}

	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl#clear()}.
	 */
	@Test
	public void testClear() throws Exception {
		assertEquals(0, index.size());
		index.add("xyz", 55);
		assertEquals(1, index.size());
		index.clear();
		assertEquals(0, index.size());
	}

	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl#frequency(java.lang.String)}.
	 */
	@Test
	public void testFrequency() {
		int s = index.frequency("xyz");
		int t = index.frequency("abc");
		index.add("xyz", 5);
		assertEquals(s + 1, index.frequency("xyz"));
		assertEquals(t, index.frequency("abc"));
	}

	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl#frequency(java.lang.String)}.
	 */
	@Test
	public void testFrequency2() {
		int s = index.frequency("xyz");
		int t = index.frequency("abc");
		index.add("xyz", 5);
		index.add("xyz", 5);
		index.add("xyz", 5);
		assertEquals(s + 3, index.frequency("xyz"));
		assertEquals(t, index.frequency("abc"));
	}

	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl#frequency(java.lang.String)}.
	 */
	@Test
	public void testFrequency3() {
		int s = index.frequency("xyz");
		int t = index.frequency("abc");
		index.add("xyz", 5);
		index.add("abc", 3);
		assertTrue(index.lines("xyz").contains(5));
		assertTrue(index.lines("abc").contains(3));
		assertEquals(s + 1, index.frequency("xyz"));
		assertEquals(t + 1, index.frequency("abc"));
	}

	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl}.
	 */
	@Test
	public void testSortedMapInterface() {
		assertFalse(Map.class.isAssignableFrom(DefaultIndexImpl.class));
	}

	/**
	 * Test method for
	 * {@link occurences.DefaultIndexImpl}.
	 */
	@Test
	public void testSortedIteration() {
		populateIndex();
		Iterator<Map.Entry<String, List<Integer>>> i = index.iterator();
		Map.Entry<String, List<Integer>> previous = i.next();
		while (i.hasNext()) {
			Map.Entry<String, List<Integer>> next = i.next();
			assertTrue(previous.getKey().compareTo(next.getKey()) <= 0);
		}
	}

	/**
	 * Test method for
	 * {@link edu.luc.cs.laufer.cs473.index.DefaultIndexImpl#add(java.lang.String, int)}.
	 */
	@Test
	public void testMultipleInstances() throws Exception {
		index.clear();
		Index index2 = index.getClass().newInstance();
		index.add("abc", 1);
		index.add("abc", 2);
		index.add("xyz", 3);
		index.add("xyz", 4);
		index2.add("uvw", 1);
		index2.add("uvw", 2);
		assertEquals(2, index.size());
		assertEquals(1, index2.size());
		assertTrue(index.contains("abc"));
		assertTrue(index.contains("xyz"));
		assertTrue(index2.contains("uvw"));
		assertFalse(index2.contains("abc"));
		assertFalse(index2.contains("xyz"));
		assertFalse(index.contains("uvw"));
	}
	
	/**
	 * Test method for
	 * {@link edu.luc.cs.laufer.cs473.index.DefaultIndexImpl#equals(java.lang.Object)}.
	 */
	
	@Test
	public void testEqualsBasic() throws Exception {
		assertTrue(index.equals(index));
		assertFalse(index.equals(null));
		assertFalse(index.equals("asdf"));
	}
	/**
	 * Test method for
	 * {@link edu.luc.cs.laufer.cs473.index.DefaultIndexImpl#equals(java.lang.Object)}.
	 */
	
	
	@Test
	public void testEqualsComplex() throws Exception {
		index.clear();
		Index index2 = index.getClass().newInstance();
		populateIndex();
		assertFalse(index.equals(index2));
		index2.add("uvw", 3);
		index2.add("uvw", 2);
		index2.add("abcd", 7);
		index2.add("ef", 5);
		index2.add("ef", 5);
		index2.add("xyz", 5);
		index2.add("xyz", 1);
		index2.add("xyz", 5);
		index2.add("xyz", 2);
		index2.add("abcd", 4);
		assertTrue(index.equals(index2));
	}
	
	private void populateIndex() {
		index.add("xyz", 5);
		index.add("xyz", 1);
		index.add("ef", 5);
		index.add("ef", 5);
		index.add("xyz", 5);
		index.add("xyz", 2);
		index.add("abcd", 7);
		index.add("uvw", 3);
		index.add("uvw", 2);
		index.add("abcd", 4);
	}

}
