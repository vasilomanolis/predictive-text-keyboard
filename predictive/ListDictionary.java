package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * A class to faster implement a system of predictive text (T9) storing the
 * dictionary in a ArrayList and using binary search
 * 
 * @author Vasileios Manolis
 *
 */
public class ListDictionary implements Dictionary {

	private ArrayList<WordSig> dictionary;

	/**
	 * A Constructor for a dictionary that reads the whole dictionary and stores in
	 * an ArrayList an Object WordSig for each entry that includes the alphabetic
	 * version and the signature of each word
	 * 
	 * @param path
	 *            the path the file dictionary is saved
	 */
	public ListDictionary(String path) {

		this.dictionary = new ArrayList<WordSig>();

		Scanner s = null;
		try {
			s = new Scanner(new File(path));

			while (s.hasNextLine()) {

				// add to the list after checking if its valid
				String testedWord = s.nextLine();
				if (isValidWord(testedWord)) { // I ignore again the non-valid values

					String testedSignature = PredictivePrototype.wordToSignature(testedWord);
					WordSig testedPair = new WordSig(testedWord, testedSignature);
					this.dictionary.add(testedPair);

				}
			}

			s.close();
			Collections.sort(this.dictionary);

		} catch (

		FileNotFoundException e) {
			e.printStackTrace();

		} finally {
			// Scanner could also close here

		}

	}

	/**
	 * A faster implementation of signatureToWords using binary search. The method
	 * takes the given numeric signature and returns a set of possible matching
	 * words from the dictionary. The returned list does not have duplicates and
	 * each word is in lower-case.
	 * 
	 * @param signature
	 *            the signature of a word
	 * @return a Set<String> of mathced words (alphabetic format of the word)
	 */
	@Override
	public Set<String> signatureToWords(String signature) {

		Set<String> matchedWords = new HashSet<String>();

		int index = Collections.binarySearch(this.dictionary, new WordSig(null, signature));

		if (index >= 0) {

			matchedWords.add(this.dictionary.get(index).getWords().toLowerCase());

			int left = index - 1;
			int right = index + 1;

			while (this.dictionary.get(left).getSignature().equals(signature)) {
				matchedWords.add(this.dictionary.get(left).getWords().toLowerCase());
				left--;
			}

			while (this.dictionary.get(right).getSignature().equals(signature)) {
				matchedWords.add(this.dictionary.get(right).getWords().toLowerCase());
				right++;
			}
		}

		return matchedWords;
	}

	/**
	 * A method to check if the word contains letter matches the format "[a-zA-Z]+"
	 * The implementation is simple, does not take into account unicode format or characters in other languages.
	 * @param word
	 *            the alphabetic version of the word
	 * @return true if it matches the format, if not, it returns false
	 */
	private static boolean isValidWord(String word) {
		return word.matches("[a-zA-Z]+");

		// ALTERNATIVE 1 / DO NOT TAKE INTO CONSIDERATION FOR GRADING
		// public boolean isAlpha(String name) {
		// char[] chars = name.toCharArray();
		//
		// for (char c : chars) {
		// if(!Character.isLetter(c)) {
		// return false;
		// }
		// }
		//
		// return true;
	}

//	/**
//	 * DO NOT TAKE INTO CONSIDERATION FOR GRADING
//	 * A main method for testing
//	 * @param args
//	 */
//	public static void main(String[] args) {
//
//		ListDictionary dict1 = new ListDictionary("/Users/vasilis/Desktop/words");
//
//		System.out.println(dict1.signatureToWords("4663"));
//
//
//	}

}
