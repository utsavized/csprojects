import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Test for TreeMap
 * @author Antonio Claros Molina, Scott Krieder, and Utsav Pandey 
 * @date 09/15/2010 
 * @version 1.0
 */

public class MyTreeMapTest extends MyMapTest{
	TreeMap<Object,Object> x;
	TreeMap<Object,Object> y;
	public MyTreeMapTest(){
		x = new TreeMap<Object,Object>();
		y = new TreeMap<Object,Object>();
	}
	
	/*
	 * Tests below are NOT required for the project but these 
	 * are the functions that are additionally found in a TreeMap.
	 * So these would be the additional tests that would need to 
	 * be implemented for the TreeMap to be completely tested
	 */
	
	@SuppressWarnings("unchecked")
	@Test
	public void cloneTest() throws Exception{
		x.put("Utsav", "Nepal");
		TreeMap<Object,Object> copy = (TreeMap<Object,Object>) x.clone();
		assertEquals(x.get("Utsav"),copy.get("Utsav"));
	}
	
	@Test
	public void firstKeyTest() throws Exception{
		x.put("Utsav", "Nepal");
		x.put("Scott", "Omaha");
		x.put("Antonio", "Spain");
		assertEquals(x.firstKey(),"Antonio");
	}
	
	@Test
	public void lastKeyTest() throws Exception{
		x.put("Utsav", "Nepal");
		x.put("Scott", "Omaha");
		x.put("Antonio", "Spain");
		assertEquals(x.lastKey(),"Utsav");
	}
	
	@Test
	public void headMapTest() throws Exception{
		SortedMap<Object,Object> temp = new TreeMap<Object,Object>();
		x.put("Utsav", "Nepal");
		x.put("Scott", "Omaha");
		x.put("Antonio", "Spain");
		x.put("Rooney", "England");
		y.put("Antonio", "Spain");
		temp = x.headMap("Rooney");
		assertTrue(y.equals(temp));
	}
	
	@Test
	public void tailMapTest() throws Exception{
		SortedMap<Object,Object> temp = new TreeMap<Object,Object>();
		x.put("Utsav", "Nepal");
		x.put("Scott", "Omaha");
		x.put("Antonio", "Spain");
		x.put("Rooney", "England");
		y.put("Scott", "Omaha");
		y.put("Utsav", "Nepal");
		y.put("Rooney", "England");
		temp = x.tailMap("Rooney");
		assertTrue(y.equals(temp));
	}
	
	@Test
	public void subMapTest() throws Exception{
		SortedMap<Object,Object> temp = new TreeMap<Object,Object>();
		x.put("Utsav", "Nepal");
		x.put("Scott", "Omaha");
		x.put("Antonio", "Spain");
		x.put("Rooney", "England");
		y.put("Scott", "Omaha");
		y.put("Rooney", "England");
		temp = x.subMap("Rooney","Utsav");
		assertTrue(y.equals(temp));
	}
	
	
	
	
}
