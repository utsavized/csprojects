package occurrences;

import java.util.List;
import java.util.Map;

/**
 * An index for a text document. For each word that occurs in the document, the
 * index holds a list of line numbers where the word occurs.
 * 
 * @author Laufer
 */
public interface Index extends Iterable<Map.Entry<String, List<Integer>>> {

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
	 * Checks whether the index contains a particular word.
	 * 
	 * @param word
	 *            the word
	 * @return whether the index contains the word
	 */
	boolean contains(String word);

	/**
	 * Returns the frequency of a word in a document.
	 * 
	 * @param word
	 *            the word
	 * @return the frequency
	 */
	int frequency(String word);

	/**
	 * Returns the list of line numbers (possibly containing duplicates) where a
	 * word occurs in a document.
	 * 
	 * @return the list of line numbers
	 */
	List<Integer> lines(String word);

	/**
	 * Removes all data from this index.
	 */
	void clear();

	/**
	 * Returns the number of words in this index.
	 * 
	 * @return the number of words
	 */
	int size();
}
