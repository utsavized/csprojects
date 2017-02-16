import java.util.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <i>Measures the performance of ChainedHashMap when used with the 
 * four hash functions: hashFunctionSum, hashFunctionLastTwo,
 * hashFunctionLastThree and hashFunctionModulo. It also compares
 * the performance against Java's built-in maps: HashMap, TreeMap
 * and LinkedHashMap.</i>
 * @author Antonio Claros Molina, Scott Krieder, and Utsav Pandey 
 * @date 09/25/2010 
 * @version 1.0
 * @param
 * 		addTable large LinkedList of random Integer (between 0 - 99999) used to
 * 		insert values into the maps
 * @param
 * 		queryTable small LinkedList of random Integer (between 0 - 99999) used
 * 		search and get values from the maps
 * @param
 * 		size the size of the large LinkedList addTable -- Change this size if
 * 		BeforeClass setup is taking too long. But make sure this is larger than
 * 		n used for insertion size used in function testPerformance().
 * @param
 * 		query the size of the small LinkedList queryTable  -- Change this size if
 * 		BeforeClass setup is taking too long. But make sure this is larger than
 * 		r used for retrieval size used in function testPerformance().
 * @param
 * 		timeCompareShort keeps track of the shortest measurement time in milliseconds
 * @param
 * 		timeCompareLong keeps track of the longest measurement time in milliseconds
 */

public class ChainedHashMapPerformance {
	static LinkedList<Integer> addTable = new LinkedList<Integer>(); //Permament long Insertion List
	static LinkedList<Integer> queryTable = new LinkedList<Integer>();//Permanent not-so-long Query list
	static int size = 10000000;
	static int query = 1000000;
	long timeCompareShortI = 500;
	long timeCompareLongI = 0;
	long timeCompareShortR = 500;
	long timeCompareLongR = 0;
	
	//For printing & labeling purposes 
	String[] hashType = {"digitSum", "lastTwo", "lastThree", "modulo", "none"};
	String[] mapType = {"HashMap", "TreeMap", "LinkedHashMap", "ChainedHashMap"};	
	
	/**
	 * <i>BeforeClass that sets up the two LinkedLists:
	 * 	addTable and queryTable, by adding random numbers</i>
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Global Insertion LinkedList Size: " + size);
		System.out.println("Global Retrieval LinkedList Size: " + query);
		System.out.print("Setting Up LinkedLists");
		int dot = size/5;
		//Setup the long linkedlist of insertion values
		for(int i = 0; i<size; i++){
			
			//***formatting**//
			if(i == dot || i == dot*2 || i == dot*3 || i == dot * 4)
				System.out.print(".");
			//***formatting**//
			
			Random rand = new Random();
			int num = rand.nextInt(99999);
			if(num>0)						//Add only positive numbers
				addTable.add((Integer)num);
		}
		//Setup the not-so-long linkedlist of retrieval/query values
		dot = query/5;
		for(int i = 0; i<query; i++){
			
			//***formatting**//
			if(i == dot || i == dot*2 || i == dot*3 || i == dot * 4)
				System.out.print(".");
			//***formatting**//
			
			Random rand = new Random();
			int num = rand.nextInt(99999);
			if(num>0)						//Query only positive numbers
				queryTable.add((Integer)num);
		}
		System.out.print("done!");
	}
	
	/**
	 * <i>Measures the performance of the maps</i>
	 * @param n number of insertions - Change this size value if
	 * 			you want tests to be limited to lesser number of
	 * 			insertions
	 * @param r number of retrievals - Change this size value if
	 * 			you want tests to be limited to lesser number of
	 * 			retrievals 
	 */
	@Test
	public void testPerformance(){
		int n;				//Insertion size for performance measure (assigned later)
		int r = 250000;	//Retrieval size for performance measure (used by all tests)
		
		System.out.println("\n\nTesting Performance now...");
		
		/*Performance measure calls
		 *
		 * hashType: 1. digitSum 2. lastTwo 3.lastThree 4. modulo 5. none
		 * mapType: 1. HashMap 2. TreeMap 3. LinkedHashMap 4. ChainedHashMap
		 *******************************************************************/
		
		//Hash function initializations		
		ToInteger<Integer> digitSum = new hashFunctionSum();
		ToInteger<Integer> lastTwo = new hashFunctionLastTwo();
		ToInteger<Integer> lastThree = new hashFunctionLastThree();
		ToInteger<Integer> modulo = new hashFunctionModulo();
		
		/* Number of insertions:
		 * 100
		 * 1000
		 * 10000
		 * 100000
		 * Number of retrievals:
		 * Assigned to r at the begining of this method 
		 */
		
		for(n = 100; n<=100000;n=n*10){
			//ChainedHashMap, modulo 101, size 101
			Map<Integer,Integer> chainedHashMapM1 = new ChainedHashMap(modulo,101);
			doMeasure(hashType[3],mapType[3],101,chainedHashMapM1,n,r);
			//ChainedHashMap, modulo 1009, size 1009
			Map<Integer,Integer> chainedHashMapM2 = new ChainedHashMap(modulo,1009);
			doMeasure(hashType[3],mapType[3],1009,chainedHashMapM2,n,r);
			//ChainedHashMap, modulo 10007, size 10007
			Map<Integer,Integer> chainedHashMapM3 = new ChainedHashMap(modulo,10007);
			doMeasure(hashType[3],mapType[3],10007,chainedHashMapM3,n,r);
			//ChainedHashMap, modulo 100003, size 100003
			Map<Integer,Integer> chainedHashMapM4 = new ChainedHashMap(modulo,100003);
			doMeasure(hashType[3],mapType[3],100003,chainedHashMapM4,n,r);
			//Other Map initializations
			Map<Integer,Integer> chainedHashMapDS = new ChainedHashMap(digitSum,100);
			Map<Integer,Integer> chainedHashMapLTwo = new ChainedHashMap(lastTwo,100);
			Map<Integer,Integer> chainedHashMapLThree = new ChainedHashMap(lastThree,1000);
			Map<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
			Map<Integer,Integer> treeMap = new TreeMap<Integer,Integer>();
			Map<Integer,Integer> linkedHashMap = new LinkedHashMap<Integer,Integer>();
			//Other map measurements
			doMeasure(hashType[4],mapType[0],0,hashMap,n,r);
			doMeasure(hashType[4],mapType[1],0,treeMap,n,r);
			doMeasure(hashType[4],mapType[2],0,linkedHashMap,n,r);
			doMeasure(hashType[1],mapType[3],0,chainedHashMapLTwo,n,r);
			if(n>100 && r>100)
				doMeasure(hashType[2],mapType[3], 0,chainedHashMapLThree,n,r);
			doMeasure(hashType[0],mapType[3],0,chainedHashMapDS,n,r);
		}	
		//Print longest and shortest measurement times
		System.out.println("\n\n-----------------------------------------------");
		System.out.println("Longest measurement time for insertion: " + timeCompareLongI);
		System.out.println("Shortest measurement time insertion: " + timeCompareShortI);
		System.out.println("");
		System.out.println("Longest measurement time for retrieval: " + timeCompareLongR);
		System.out.println("Shortest measurement time retrieval: " + timeCompareShortR);
	}
	
	/**
	 * <i>Performs insertion, retirevals and calculates time taken
	 * in milliseconds</i>
	 * @param labelHash for printing purpose -- states which hash function is being used
	 * @param labelMap for printing purpose -- states which map is being used
	 * @param modulo for printing purpose -- if hash function is modulo, states
	 * 			what int is being used for the modulo (101, 1009.. etc)
	 * @param map where insertion and retrieval will be performed
	 * @param n number of insertions
	 * @param r number of retrievals
	 */
	void doMeasure(String labelHash, String labelMap, int modulo, Map<Integer,Integer> map, int n, int r)
	{
		//**Some formatting
		System.out.println("");
		System.out.println("");
		System.out.println("************************************");
		System.out.println("Hash Type: " + labelHash);
		if(modulo!=0)
			System.out.println("Modulo: " + modulo);
		System.out.println("Map Type: " + labelMap);
		System.out.println("No. of insertions: " + n);
		System.out.println("No. of retrievals: " + r);
		System.out.println("------------------------------------");
		//**formatting End
		
		
		long timeStart = System.currentTimeMillis(); //Start timer!
		
		int i = 0;
		for(Integer linkedListLoopAdd: addTable){
			if(i==n) break; //to make sure we don't add more than n
			map.put(linkedListLoopAdd,linkedListLoopAdd); //Used key and value and Integers
			i++;
		}
		long timeEnd = System.currentTimeMillis(); //stop timer!
		long time = timeEnd - timeStart;
		
		//**Some formatting
		System.out.println(n + " values inserted in " + time + " ms.");
		System.out.println("------------------------------------");
		//**formatting End
		
		//Store the longest measurement time thus far for insertions
		if(timeCompareLongI<time)
			timeCompareLongI = time;
		//Store the shortest measurement time thus far for insertions
		if(timeCompareShortI>time)
			timeCompareShortI = time;
		
		timeStart = System.currentTimeMillis(); //Start timer!
		
		int j = 0;
		int check = 0;
		Integer get;
		for(Integer linkedListLoopQuery: queryTable){
			if(j==r) break; //to make sure we don't query more than n or r
			if(map.get(linkedListLoopQuery)==null){
				//to make sure we don't query outside the query size
				//Had to add this as .equals() does not work with null 
				//values. And using == resulted in incorrect results due to 
				//Integer Cashing (-128 to 127)
				get = 0;	//Zero does not conflict with any key as
							//only +ve random nums are inserted
			}
			else
				get = map.get(linkedListLoopQuery);
			if(get.equals(linkedListLoopQuery)){ 	//Now we can use .equals() to campare
				check++;							//count num of keys found
			}
			else{}
			j++;
		}
		
		timeEnd = System.currentTimeMillis(); //stop timer!
		time = timeEnd - timeStart;
		System.out.println(check + " values retrieved in " + time + " ms.");	
		
		//Store the longest measurement time thus far for retrievals
		if(timeCompareLongR<time)
			timeCompareLongR = time;
		//Store the shortest measurement time thus far for retrievals
		if(timeCompareShortR>time)
			timeCompareShortR = time;
		
		//Formatting again!
		System.out.println("------------------------------------");
		//End of formatting
	}

	/**
	 * <i>Frees up some memory after calculations are finished</i>
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		addTable = null;	//free up some memory
		queryTable = null;
	}
}
