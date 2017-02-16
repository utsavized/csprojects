package occurrences;

import java.util.List;
import java.util.Map;

/**
 * An index for a text document. For each word that occurs in the document, the
 * index holds a list of line numbers where the word occurs.
 * 
 * @author Laufer
 */
public interface Index extends Map<String, List<Integer>> {

	/**
	 * Adds an index entry for a word at the given line number.
	 * 
	 * @param word
	 *            the word
	 * @param line
	 *            the line number
	 */
	void add(String word, int line);

	/**
	 * Returns the frequency of a word in a document.
	 * 
	 * @param word
	 *            the word
	 * @return the frequency
	 */
	
	int frequency(String word);
}