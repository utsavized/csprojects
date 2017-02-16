import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Test for a custom Map implementation MyMap
 * @author Antonio Claros Molina, Scott Krieder, and Utsav Pandey 
 * @date 09/18/2010 
 * @version 1.0
 */

public class MyMapTest {
	Map<Object, Object> x ;
	Map<Object, Object> y ;

	/**
	 * Constructor for MyMapTest
	 */
	public MyMapTest() {
		x = new MyMap();
		y = new MyMap();
	}
	
	/**
	 * Testing if an empty map returns a 0 for size
	 * @throws Exception
	 */
	@Test
	public void sizeZeroTest() throws Exception {
		assertEquals(x.size(),0);
		x.remove("Scott");
		assertEquals(x.size(),0);
    }
	/**
	 * Testing if size returns the correct values when
	 * duplicate key/value are added
	 * @throws Exception
	 */
	@Test
	public void sizeTest2equals() throws Exception {
		x.put("Antonio","Malaga");
		x.put("Antonio","Malaga");
		assertEquals(x.size(),1);
    }
	/**
	 * Testing if size returns the correct value for
	 * multiple insertions
	 * @throws Exception
	 */
	@Test
	public void sizeTestM() throws Exception {
		x.put("Antonio","Malaga");
		x.put("Scott", "Omaha");
		x.put("Utsav", "Nepal");
		int s = x.size();
		boolean check = false;
		if(s == 3)
			check = true;
		assertTrue(check);
    }
	/**
	 * Testing if isEmply is <b>false</b> for a non-empty Map
	 * @throws Exception
	 */
	@Test
	public void isEmptyTest() throws Exception {
		x.put("Antonio","Malaga");
		assertFalse(x.isEmpty());
    }
	/**
	 * Testing if is <b>true</b> for an empty Map
	 * @throws Exception
	 */
	@Test
	public void isEmptyTestTwo() throws Exception {
		assertTrue(x.isEmpty());
    }
	/**
	 * Testing if hash codes for identical maps are equal
	 * @throws Exception
	 */
	@Test
    public void hashTest() throws Exception {
        y.put("Antonio","Malaga");
        x.put("Antonio","Malaga");
        assertEquals(y.hashCode(),x.hashCode());
    }
	/**
	 * Testing if empty sets are equal
	 * @throws Exception
	 */
	@Test
    public void equalsTestZero() throws Exception  {
		assertTrue(x.equals(y));
	}
	/**
	 * Testing is equals returns <b>false</b> for non-equal maps
	 * @throws Exception
	 */
	@Test
	public void equalsTestOne() throws Exception  {
		x.put("Antonio","Malaga");
		assertFalse(x.equals(y));
		assertFalse(y.equals(x));
	}
	
	/**
	 * Testing is equals returns <b>false</b> for non-equal maps
	 * and <b>true</b> for equal maps
	 * @throws Exception
	 */
	
	@Test
	public void equalsTestM() throws Exception  {
		x.put("Antonio","Malaga");
		x.put("Scott", "Omaha");
		x.put("Utsav", "Nepal");
		y.put("Antonio","Malaga");
		y.put("Scott", "Omaha");
		assertFalse(x.equals(y));
		assertFalse(y.equals(x));
		y.put("Utsav", "Nepal");
		assertTrue(x.equals(y));
		assertTrue(y.equals(x));
	}
	/**
	 * Testing if a Map contains a certain key
	 * @throws Exception
	 */
	@Test
	public void containsKeyTest() throws Exception {
		assertFalse(x.containsKey("Antonio"));
    }
	/**
	 * Testing if a Map contains multiple key
	 * @throws Exception
	 */
	@Test
	public void containsKeyMTest() throws Exception {
		x.put("Antonio","Malaga");
		x.put("Scott", "Omaha");
		x.put("Utsav", "Nepal");
		assertTrue(x.containsKey("Scott"));
		assertTrue(x.containsKey("Scott"));
		assertFalse(x.containsKey("Pepe"));
		assertTrue(x.containsKey("Antonio"));		
    }
	/**
	 * Testing if map contains a value
	 * @throws Exception
	 */
	@Test
	public void containsValueTest() throws Exception {
		assertFalse(x.containsKey("Japon"));
    }
	@Test
	/**
	 * Testing if map contains multiple values
	 * @throws Exception
	 */
	public void containsValueMTest() throws Exception {
		x.put("Antonio","Malaga");
		x.put("Scott", "Omaha");
		x.put("Utsav", "Nepal");
		assertTrue(x.containsValue("Omaha"));
		assertTrue(x.containsValue("Omaha"));
		assertFalse(x.containsValue("Moon"));
		assertTrue(x.containsValue("Malaga"));
		
    }
	/**
	 * Testing whether the get function returns <b>null</b> 
	 * when key is not found
	 * @throws Exception
	 */
	@Test
	public void getTestZero() throws Exception {
		assertEquals(x.get("Antonio"), null);
    }
	/**
	 * Testing whether the get function returns the value 
	 * when key is found
	 * @throws Exception
	 */
	@Test
	public void getTest() throws Exception {
		x.put("Antonio","smells");
		assertEquals(x.get("Antonio"), "smells");
    }
	/**
	 * Testing whether the put function inserts a <b>null</b> 
	 * value
	 * @throws Exception
	 */
	@Test
	public void putTestZero() throws Exception {
		x.put(null,"X");
		assertEquals(x.size(),0);
	}
	/**
	 * Testing whether the put function inserts a key-values 
	 * correctly
	 * @throws Exception
	 */
	@Test
	public void putTest() throws Exception {
		x.put("Scott", "Omaha");
		assertTrue(x.containsKey("Scott"));
		assertTrue(x.containsValue("Omaha"));
		assertEquals(x.get("Scott"),"Omaha");
		assertEquals(x.size(),1);
	}
	/**
	 * Testing the remove function
	 * @throws Exception
	 */
	@Test
	public void removeZeroTest() throws Exception {
		assertEquals(x.remove("Scott"), null);
	}
	/**
	 * Testing the remove function
	 * @throws Exception
	 */
	@Test
	public void removeOneTest() throws Exception {
		x.put("Scott","Omaha");
		x.remove("Scott");
		assertEquals(x.get("Scott"),null);
	}
	/**
	 * Testing remove with multiple items
	 * @throws Exception
	 */
	@Test
	public void removeMTest() throws Exception {
		x.put("Scott", "Omaha");
		x.put("Utsav", "Nepal");
		assertEquals(x.remove("Antonio"),null);
		x.remove("Scott");
		assertEquals(x.get("Scott"),null);
		assertFalse(x.containsKey("Scott"));
		assertTrue(x.containsKey("Utsav"));
	}
	/**
	 * Testing putAll with single value
	 * @throws Exception
	 */
	@Test
	public void putAllTest() throws Exception {
		x.put("Antonio","Malaga");
		y.putAll(x);
		assertEquals(y.get("Antonio"), "Malaga");
    }
	/**
	 * Testing putAll with multiple values
	 * @throws Exception
	 */
	@Test
	public void putAllTestM() throws Exception {
		x.put("Antonio","Malaga");
		y.put("Scott","Omaha");
		y.putAll(x);
		assertEquals(y.size(),2);
    }
	/**
	 * Testing clear with equals
	 * @throws Exception
	 */
	@Test
	public void clearTest() throws Exception {
		x.put("Antonio","smells");
		x.clear();
		assertEquals(true, x.isEmpty());
    }
	/**
	 * Testing clear with assertsTrue
	 * @throws Exception
	 */
	@Test
	public void clearTestTwo() throws Exception {
		x.put("Antonio","smells");
		x.clear();
		assertTrue(x.isEmpty());
    }
}