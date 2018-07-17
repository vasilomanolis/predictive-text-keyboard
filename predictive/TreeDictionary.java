package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * A faster approach of a predictive dictionary using Trie
 * 
 * @author Vasileios Manolis
 *
 */
public class TreeDictionary implements Dictionary {

	private TreeDictionary[] treeDictionaries = new TreeDictionary[8];
	private Set<String> words = new TreeSet<String>();

	/**
	 * A Constructor for a dictionary
	 * 
	 * @param path
	 *            the path the file dictionary is saved
	 */
	public TreeDictionary(String path) {

		Scanner s = null;
		try {
			s = new Scanner(new File(path));

			while (s.hasNextLine()) {

				// add to the list after checking if its valid
				String testedWord = s.nextLine().toLowerCase();
				if (isValidWord(testedWord)) { // I ignore again the non-valid values

					String testedSignature = PredictivePrototype.wordToSignature(testedWord);

					if (testedSignature.length() > 0) {
						this.helperConstructor(0, testedSignature, testedWord);
					}

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
	 * A helper method for the dictionary
	 * 
	 * @param testedSignature
	 *            the signature of the world
	 * @param testedWord
	 *            the alphebetic word
	 */
	public void helperConstructor(int i, String testedSignature, String testedWord) {
		if (i == testedSignature.length()) {
			this.words.add(testedWord);
		} else {
			char a = testedSignature.charAt(i);
			if (a >= '2' && a <= '9') {
				int num = Character.digit(a, 10);
				int index = num - 2;
				if (this.treeDictionaries[index] == null) {
					this.treeDictionaries[index] = new TreeDictionary();
				}
				this.words.add(testedWord);
				this.treeDictionaries[index].helperConstructor(i + 1, testedSignature, testedWord);
			}
		}
	}

	/**
	 * An empty constructor
	 */
	public TreeDictionary() {
		for (int i = 0; i < treeDictionaries.length; i++) {
			this.treeDictionaries[i] = null;
		}
		this.words = new HashSet<>();
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

	/**
	 * A faster signature to Words method
	 */
	@Override
	public Set<String> signatureToWords(String signature) {
		Set<String> matchedWords = this.signatureToWordsHelper(0, signature);
		TreeSet<String> matchedWords2 = new TreeSet<String>();
		for (String word : matchedWords) {
			matchedWords2.add(word.substring(0, signature.length()));
		}
		return matchedWords2;
	}

	/**
	 * A helper function for the signatureToWords
	 * 
	 * @param n
	 * @param signature
	 * @return
	 */
	private Set<String> signatureToWordsHelper(int n, String signature) {
		if (n == signature.length()) {
			return new TreeSet<String>(this.words);
		}
		char a = signature.charAt(n);
		if (a >= '2' && a <= '9') {
			int num = Character.digit(a, 10);
			int index = num - 2;
			if (this.treeDictionaries[index] == null) {
				return new TreeSet<String>();
			}
			return this.treeDictionaries[index].signatureToWordsHelper(n + 1, signature);
		}
		return new TreeSet<String>();
	}

	// public static void main(String[] args) {
	// Dictionary dict = new TreeDictionary("/Users/vasilis/Desktop/words");
	//
	// Set<String> result = dict.signatureToWords("4663");
	// System.out.println(result);
	// }

}
