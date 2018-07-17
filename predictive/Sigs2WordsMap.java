package predictive;

/**
 * A command-line program for calling the more efficient Part 3
 * 
 * @author Vasileios Manolis
 *
 */
public class Sigs2WordsMap {

	/**
	 * A main method for calling the more efficient Part 3
	 * 
	 * @param args
	 *            arguments of the main method - signatures of the words
	 */
	public static void main(String[] args) {
		// MapDictionary dictionary = new MapDictionary("/Users/vasilis/Desktop/words");
		MapDictionary dictionary = new MapDictionary("/usr/share/dict/words");

		for (String s : args) {

			System.out.println(s + " : " + dictionary.signatureToWords(s));
		}

	}

}