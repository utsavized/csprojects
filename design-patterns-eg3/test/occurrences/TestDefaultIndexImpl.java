package occurrences;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.SortedMap;
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
	 * {@link occurrences.DefaultIndexImpl#DefaultIndexImpl()}.
	 */
	@Test
	public void testDefaultIndexImpl() throws Exception {
		assertTrue(index.isEmpty());
		assertEquals(0, index.frequency("xyz"));
	}

	/**
	 * Test method for
	 * {@link occurrences.DefaultIndexImpl#add(java.lang.String, int)}.
	 */
	@Test
	public void testAdd() {
		int s = index.containsKey("xyz") ? index.get("xyz").size() : 0;
		index.add("xyz", 5);
		assertTrue(index.get("xyz").contains(5));
		assertEquals(s + 1, index.get("xyz").size());
	}
	

	/**
	 * Test method for
	 * {@link occurrences.DefaultIndexImpl#add(java.lang.String, int)}.
	 */
	@Test
	public void testAdd2() {
		int s = index.containsKey("xyz") ? index.get("xyz").size() : 0;
		index.add("xyz", 5);
		index.add("xyz", 5);
		index.add("xyz", 5);
		assertTrue(index.get("xyz").contains(5));
		assertEquals(s + 3, index.get("xyz").size());
	}


	/**
	 * Test method for
	 * {@link occurrences.DefaultIndexImpl#frequency(java.lang.String)}.
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
	 * {@link occurrences.DefaultIndexImpl#frequency(java.lang.String)}.
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
	 * {@link occurrences.DefaultIndexImpl#frequency(java.lang.String)}.
	 */
	@Test
	public void testFrequency3() {
		int s = index.frequency("xyz");
		int t = index.frequency("abc");
		index.add("xyz", 5);
		index.add("abc", 3);
		assertTrue(index.get("xyz").contains(5));
		assertTrue(index.get("abc").contains(3));
		assertEquals(s + 1, index.frequency("xyz"));
		assertEquals(t + 1, index.frequency("abc"));
	}

	/**
	 * Test method for
	 * {@link occurrences.DefaultIndexImpl}.
	 */
	@Test
	public void testSortedMapInterface() {
		assertTrue(SortedMap.class.isAssignableFrom(DefaultIndexImpl.class));
	}

	/**
	 * Test method for
	 * {@link occurrences.DefaultIndexImpl}.
	 */
	@Test
	public void testSortedIteration() {
		populateIndex();
		Iterator<String> i = index.keySet().iterator();
		String previous = i.next();
		while (i.hasNext()) {
			String next = i.next();
			assertTrue(previous.compareTo(next) <= 0);
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
		assertTrue(index.containsKey("abc"));
		assertTrue(index.containsKey("xyz"));
		assertTrue(index2.containsKey("uvw"));
		assertFalse(index2.containsKey("abc"));
		assertFalse(index2.containsKey("xyz"));
		assertFalse(index.containsKey("uvw"));
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
