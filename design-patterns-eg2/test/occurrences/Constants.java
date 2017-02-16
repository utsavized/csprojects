/**
 * 
 */
package occurrences;


/**
 * @author Laufer
 * @modified_by Utsav Pandey, Antonio Claros Molina, Scott Krieder
 * @modified_on 11/4/2010
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
	
	public static final Index s7index = getS7Index();	//Changed to Index
	public static final Index s8index = getS8Index();	//Changed to Index

	private static Index getS7Index() {
		Index result = new DefaultIndexImpl();
		
		//Use index.add
		result.add("politics", 1);
		result.add("without", 1);
		result.add("without", 2);
		result.add("without", 3);
		result.add("without", 4);
		result.add("without", 5);
		result.add("without", 6);
		result.add("without", 7);
		result.add("principle", 1);
		result.add("wealth", 2);
		result.add("work", 2);
		result.add("pleasure", 3);
		result.add("conscience", 3);
		result.add("knowledge", 4);
		result.add("character", 4);
		result.add("business", 5);
		result.add("morality", 5);
		result.add("science", 6);
		result.add("humanity", 6);
		result.add("and", 7);
		result.add("worship", 7);
		result.add("sacrifice", 7);
		return result;
	}
	
	private static Index getS8Index() {
		Index result = new DefaultIndexImpl();
		
		//Use index.add
		result.add("politics", 1);
		result.add("without", 1);
		result.add("without", 2);
		result.add("without", 2);
		result.add("without", 2);
		result.add("without", 3);
		result.add("without", 4);
		result.add("without", 5);
		result.add("without", 6);
		result.add("without", 6);
		result.add("without", 7);
		result.add("principle", 1);
		result.add("wealth", 2);
		result.add("work", 2);
		result.add("pleasure", 3);
		result.add("conscience", 3);
		result.add("knowledge", 4);
		result.add("character", 4);
		result.add("business", 5);
		result.add("morality", 5);
		result.add("morality", 5);
		result.add("morality", 5);
		result.add("science", 6);
		result.add("humanity", 6);
		result.add("and", 7);
		result.add("worship", 7);
		result.add("sacrifice", 7);
		return result;
	}
}