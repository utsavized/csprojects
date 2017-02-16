/**
 * 
 */
package occurrences;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Laufer
 * 
 */
public class Constants {

	public static final String s7 = "politics without principle\n"
			+ "wealth without work\n" + "pleasure without conscience\n"
			+ "knowledge without character\n" + "business without morality\n"
			+ "science without humanity\n" + "and worship without sacrifice";

	public static final String s8 = "politics without principle\n"
			+ "wealth without without without work\n"
			+ "pleasure without conscience\n" + "knowledge without character\n"
			+ "business without morality morality morality\n"
			+ "science without humanity without\n"
			+ "and worship without sacrifice";

	public static final Map<String, List<Integer>> s7index = getS7Index();

	public static final Map<String, List<Integer>> s8index = getS8Index();

	private static Map<String, List<Integer>> getS7Index() {
		Map<String, List<Integer>> result = new TreeMap<String, List<Integer>>();
		result.put("politics", Arrays.asList(new Integer[] { 1 }));
		result.put("without", Arrays.asList(new Integer[] { 1, 2, 3, 4, 5,
				6, 7 }));
		result.put("principle", Arrays.asList(new Integer[] { 1 }));
		result.put("wealth", Arrays.asList(new Integer[] { 2 }));
		result.put("work", Arrays.asList(new Integer[] { 2 }));
		result.put("pleasure", Arrays.asList(new Integer[] { 3 }));
		result.put("conscience", Arrays.asList(new Integer[] { 3 }));
		result.put("knowledge", Arrays.asList(new Integer[] { 4 }));
		result.put("character", Arrays.asList(new Integer[] { 4 }));
		result.put("business", Arrays.asList(new Integer[] { 5 }));
		result.put("morality", Arrays.asList(new Integer[] { 5 }));
		result.put("science", Arrays.asList(new Integer[] { 6 }));
		result.put("humanity", Arrays.asList(new Integer[] { 6 }));
		result.put("and", Arrays.asList(new Integer[] { 7 }));
		result.put("worship", Arrays.asList(new Integer[] { 7 }));
		result.put("sacrifice", Arrays.asList(new Integer[] { 7 }));
		return result;
	}
	
	private static Map<String, List<Integer>> getS8Index() {
		Map<String, List<Integer>> result = new TreeMap<String, List<Integer>>();
		result.put("politics", Arrays.asList(new Integer[] { 1 }));
		result.put("without", Arrays.asList(new Integer[] { 1, 2, 2, 2, 3, 4, 5,
				6, 6, 7 }));
		result.put("principle", Arrays.asList(new Integer[] { 1 }));
		result.put("wealth", Arrays.asList(new Integer[] { 2 }));
		result.put("work", Arrays.asList(new Integer[] { 2 }));
		result.put("pleasure", Arrays.asList(new Integer[] { 3 }));
		result.put("conscience", Arrays.asList(new Integer[] { 3 }));
		result.put("knowledge", Arrays.asList(new Integer[] { 4 }));
		result.put("character", Arrays.asList(new Integer[] { 4 }));
		result.put("business", Arrays.asList(new Integer[] { 5 }));
		result.put("morality", Arrays.asList(new Integer[] { 5, 5, 5 }));
		result.put("science", Arrays.asList(new Integer[] { 6 }));
		result.put("humanity", Arrays.asList(new Integer[] { 6 }));
		result.put("and", Arrays.asList(new Integer[] { 7 }));
		result.put("worship", Arrays.asList(new Integer[] { 7 }));
		result.put("sacrifice", Arrays.asList(new Integer[] { 7 }));
		return result;
	}
}
