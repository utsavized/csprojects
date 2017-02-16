package occurrences;

/**
 * A simple immutable token.
 * 
 * @author Laufer, Modified by Utsav Pandey
 * @Modified_date 10/31/2010
 */
public class DefaultToken implements Token {
	private final String word;				//word
	private final int line;					//line number
	
	/**
	 * Constructs a token from a word and line number.
	 * 
	 * @param word
	 *            the word
	 * @param line
	 *            the line number
	 */
	public DefaultToken(final String word, final int line) {
		if (word == null)
			throw new IllegalArgumentException("word == null");
		if (line <= 0)
			throw new IllegalArgumentException("line <= 0");
		this.word = word;
		this.line = line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.luc.cs.laufer.index.Token#getLine()
	 */
	@Override
	public int getLine() {
		return line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.luc.cs.laufer.index.Token#getWord()
	 */
	@Override
	public String getWord() {
		return word;
	}

	private static final long serialVersionUID = 5320093356413218994L;
}
