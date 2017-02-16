import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Test for HashMap
 * @author Antonio Claros Molina, Scott Krieder, and Utsav Pandey 
 * @date 09/15/2010 
 * @version 1.0
 */

public class MyHashMapTest extends MyMapTest{
	HashMap<Object,Object> x;
	HashMap<Object,Object> y;
	public MyHashMapTest(){
		x = new HashMap<Object,Object>();
		y = new HashMap<Object,Object>();
	}
	
	/*
	 * Tests below are NOT required for the project but these 
	 * are the functions that are additionally found in a HashMap.
	 * So these would be the additional tests that would need to 
	 * be implemented for the HashMap to be completely tested
	 */
	
	@Test
	public void putTest() throws Exception{
		x.put(null,"X");
		x.put("Utsav", "Nepal");
		assertTrue(x.containsKey("Utsav"));
		assertTrue(x.containsValue("Nepal"));
		assertEquals(x.get("Utsav"),"Nepal");
		assertEquals(x.size(),2);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void cloneTest() throws Exception{
		x.put("Utsav", "Nepal");
		HashMap<Object,Object> copy = (HashMap<Object,Object>) x.clone();
		assertEquals(x.get("Utsav"),copy.get("Utsav"));
	}
}
