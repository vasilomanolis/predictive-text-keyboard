package predictive;

/**
 * A command-line program for calling the more efficient Part 4
 * 
 * @param args
 */
public class Sigs2WordsTree {

	/**
	 * A main method for calling the more efficient Part 4
	 * 
	 * @param args
	 *            the signatures of the words
	 */
	public static void main(String[] args) {
		// TreeDictionary("/Users/vasilis/Desktop/words");
		MapDictionary dictionary = new MapDictionary("/usr/share/dict/words");

		for (String s : args) {

			System.out.println(s + " : " + dictionary.signatureToWords(s));
		}

	}

}