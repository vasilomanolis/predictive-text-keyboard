package predictive;

/**
 * A class that creates WordSig objects in order to pair the numeric signatures
 * with words,
 * 
 * @author Vasileios Manolis
 *
 */
public class WordSig implements Comparable<WordSig> {

	private String words;
	private String signature;

	/**
	 * A Constructor for WordSig object
	 * 
	 * @param words
	 *            the actual word
	 * @param signature
	 *            the signature of the world
	 */
	public WordSig(String words, String signature) {
		this.words = words;
		this.signature = signature;
	}

	/**
	 * A compareTo method that sorts the objects according to their signatures
	 * 
	 * @return a integer after comparing the objects according to natural order of
	 *         their signature (using a compareTo method)
	 */
	@Override
	public int compareTo(WordSig ws) {
		return signature.compareTo(ws.signature);
	}

	/**
	 * A getter for the word
	 * 
	 * @return the actual word (alphabetic format of a word)
	 */
	public String getWords() {
		return words;
	}

	/**
	 * A getter for the signature
	 * 
	 * @return the signature of a word
	 */
	public String getSignature() {
		return signature;
	}

}
