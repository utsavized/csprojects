package occurrences;

import java.io.Serializable;

/**
 * An interface for a simple token consisting of a word and a line number
 * indicating where the word occurs.
 * 
 * @author Laufer
 * 
 */
public interface Token extends Serializable {

	/**
	 * Returns the word associated with this token.
	 * 
	 * @return the word
	 */
	String getWord();

	/**
	 * Returns the line number associated with this token.
	 */
	int getLine();
}
