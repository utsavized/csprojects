import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Tests for a custom Map implementation ChainedHashMap using the tests
 * for MyMap
 * @author Antonio Claros Molina, Scott Krieder, and Utsav Pandey 
 * @date 09/24/2010 
 * @version 1.0
 */
public class ChainedHashMapTest extends MyMapTest{
	//Initialize the hash functions
	ToInteger<Integer> digitSum = new hashFunctionSum();
	ToInteger<Integer> lastTwo = new hashFunctionLastTwo();
	ToInteger<Integer> lastThree = new hashFunctionLastThree();
	ToInteger<Integer> modulo = new hashFunctionModulo();
	
	/**
	 * Test for hashFunctionSum
	 * @throws Exception
	 */
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void hashFunctionSumTest() throws Exception {
		Map x = new ChainedHashMap(digitSum,1000);
		
		x.put(12,12);
		x.put(21,21);
		x.put(111,111);
		x.put(111,111);
		x.put(1234,1234);
		x.put(5500,5500);
		
		assertEquals(x.get(12),12);
		assertEquals(x.get(21),21);
		assertEquals(x.get(111),111);
		assertEquals(x.get(1234),1234);
				
	}

	/**
	 * Test for hashFunctionLastTwo
	 * @throws Exception
	 */
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void hashFunctionLastTwoTest() throws Exception {
		Map x = new ChainedHashMap(lastTwo,1000);
		
		x.put(12,12);
		x.put(21,21);
		x.put(111,111);
		x.put(111,111);
		x.put(1234,1234);
		x.put(5500,5500);
		
		assertEquals(x.get(12),12);
		assertEquals(x.get(21),21);
		assertEquals(x.get(111),111);
		assertEquals(x.get(1234),1234);
	}

	/**
	 * Test for hashFunctionLastThree
	 * @throws Exception
	 */
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void hashFunctionLastThreeTest() throws Exception {
		Map x = new ChainedHashMap(lastThree,1000);
		
		x.put(12,12);
		x.put(21,21);
		x.put(111,111);
		x.put(111,111);
		x.put(1234,1234);
		x.put(5500,5500);
		
		assertEquals(x.get(12),12);
		assertEquals(x.get(21),21);
		assertEquals(x.get(111),111);
		assertEquals(x.get(1234),1234);
		}
	
	/**
	 * Test for hashFunctionModulo
	 * @throws Exception
	 */
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void hashFunctionModuloTest() throws Exception {
		Map x = new ChainedHashMap(modulo,1000);
		
		x.put(12,12);
		x.put(21,21);
		x.put(111,111);
		x.put(111,111);
		x.put(1234,1234);
		x.put(5500,5500);
		
		assertEquals(x.get(12),12);
		assertEquals(x.get(21),21);
		assertEquals(x.get(111),111);
		assertEquals(x.get(1234),1234);
		}
}