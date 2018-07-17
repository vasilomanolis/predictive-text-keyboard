package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

/**
 * An implementiation of predictive text with a Map
 * 
 * @author Vasilis
 *
 */
public class MapDictionary implements Dictionary {

	private Map<String, String> dictionary;

	/**
	 * A constructor for MapDictionary
	 * 
	 * @param path
	 *            the path the file dictionary is saved
	 */
	public MapDictionary(String path) {

		// A HashMap has been chosen as this implementation of Map allow to store Key &
		// value pairs. We chose to store the "word" as an appropriate key because the
		// signature would be the same for several words.
		this.dictionary = new HashMap<>();

		Scanner s = null;
		try {
			s = new Scanner(new File(path));

			while (s.hasNextLine()) {

				// add to the list after checking if its valid
				String testedWord = s.nextLine().toLowerCase();

				if (isValidWord(testedWord)) { // I ignore again the non-valid values

					String signature = PredictivePrototype.wordToSignature(testedWord);
					this.dictionary.put(testedWord, signature);

				}
			}

			s.close();

		} catch (

		FileNotFoundException e) {
			e.printStackTrace();

		} finally {
			// Scanner could also close here

		}

	}

	/**
	 * A signatureToWords method
	 * 
	 * @param signature
	 *            the signature of the word
	 */
	@Override
	public Set<String> signatureToWords(String signature) {
		// a Set of Strings to be stored
		Set<String> matchedWords = new TreeSet<String>();

		if (dictionary.containsValue(signature)) {

			Set<Entry<String, String>> allWords = dictionary.entrySet();
			Iterator<Entry<String, String>> i = allWords.iterator();

			while (i.hasNext()) {

				Map.Entry<String, String> added = (Map.Entry<String, String>) i.next();
				if (signature.equals(added.getValue())) {

					matchedWords.add(added.getKey());
				}
			}
		}

		return matchedWords;

	}

	/**
	 * A method to check if the word contains letter matches the format "[a-zA-Z]+"
	 * The implementation is simple, does not take into account unicode format or
	 * characters in other languages.
	 * 
	 * @param word
	 *            the alphabetic version of the word
	 * @return true if it matches the format, if not, it returns false
	 */
	private static boolean isValidWord(String word) {
		return word.matches("[a-zA-Z]+");

	}

	// public static void main(String[] args) {
	// Dictionary dict = new MapDictionary("/Users/vasilis/Desktop/words");
	//
	// expected.add("good");
	// expected.add("gone");
	// expected.add("home");
	// expected.add("hone");
	// expected.add("hood");
	// expected.add("hoof");
	// expected.add("ioof");
	// expected.add("ione");
	// expected.add("inne");
	// expected.add("gome");
	// expected.add("gond");
	// expected.add("hond");
	// expected.add("goof");
	//
	// Set<String> result = dict.signatureToWords("4663");
	// System.out.println(result);
	// }

}
