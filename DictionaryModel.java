import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import predictive.Dictionary;
import predictive.TreeDictionary;

/**
 * A DictionaryModel class The DictionaryModel that is given the keys typed and
 * perfrom several actions such as getMessage, addCharacter,
 * removeLastCharacter, nextMatch and acceptWorld. It implements the
 * DictionaryModelInterface.
 * 
 * @author Vasileios Manolis
 *
 */
public class DictionaryModel extends Observable implements DictionaryModelInterface {

	private String signature; // the signature of a word
	private int index; // the index for the matched word
	private List<String> matches; // a List of Strings with the all matches
	private List<String> sentence; // a List of Strings with all the user input (in the format of words)

	/**
	 * A constructor that takes the path name of a dictionary file and initialises
	 * the internal data structures.
	 * 
	 * @param dictionaryFile
	 *            the path name of a dictionary file
	 * @throws java.io.IOException
	 *             the exception thrown
	 */
	public DictionaryModel(String dictionaryFile) throws java.io.IOException {

		this.dictionary = new TreeDictionary(dictionaryFile);

		this.matches = new ArrayList<String>();
		this.matches.add("");
		this.signature = "";
		this.index = 0;

		this.sentence = new ArrayList<String>();

	}

	/**
	 * A second constructor that uses a default dictionary file
	 * 
	 * @throws java.io.IOException
	 *             the exception thrown
	 */
	public DictionaryModel() throws java.io.IOException {

		// this("/Users/vasilis/Desktop/words");
		this("/usr/share/dict/words");

	}

	private Dictionary dictionary; // a dictionary object

	/**
	 * A getter for Dictionary
	 * 
	 * @return the dictionary
	 */
	public Dictionary getDictionary() {
		return dictionary;
	}

	/**
	 * A getter for signature
	 * 
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * A getter for index
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * A getter for matches
	 * 
	 * @return the matches
	 */
	public List<String> getMatches() {
		return matches;
	}

	/**
	 * A getter for sentence
	 * 
	 * @return the sentence
	 */
	public List<String> getSentence() {
		return sentence;
	}

	/**
	 * A method that returns a list of the words typed in so far, including the last
	 * word (or prefix) which has not yet been accepted.
	 * 
	 * @return a List<String> of the words typed in so far
	 */
	@Override
	public List<String> getMessage() {
		ArrayList<String> getMessage = new ArrayList<String>(this.sentence);
		String currentAlphabetic = "";
		if (this.matches.size() > 0) {
			currentAlphabetic = this.matches.get(this.index);
		} else {
			currentAlphabetic = "";
		}

		getMessage.add(currentAlphabetic);

		return getMessage;
	}

	@Override
	/**
	 * A method that adds a numeric key that has been typed in by the user and
	 * extends the current word (or prefix) with possible matches for the new key.
	 */
	public void addCharacter(char key) {

		ArrayList<String> newMatchedWords = new ArrayList<String>(
				this.dictionary.signatureToWords(this.signature + key));
		if (newMatchedWords.size() > 0) {
			this.signature = this.signature + key;
			this.matches = newMatchedWords;
			if (this.index >= this.matches.size()) {
				this.index = 0;
			}
			this.setChanged();
			this.notifyObservers();
		}

	}

	@Override
	/**
	 * A method that removes the last character typed in and enlarges the possible
	 * matches for the current word.
	 */
	public void removeLastCharacter() {

		if (this.signature.length() > 0) {

			String matchedWord = this.matches.get(this.index);
			matchedWord = matchedWord.substring(0, this.signature.length() - 1);
			this.signature = this.signature.substring(0, this.signature.length() - 1);
			if (this.signature.length() > 0) {
				this.matches = new ArrayList<String>(this.dictionary.signatureToWords(this.signature));
				// the matches is ordered in natural order, as it derives from the dictionary
				this.index = Collections.binarySearch(this.matches, matchedWord);
			} else {
				this.matches = new ArrayList<String>();
				this.matches.add("");
				this.index = 0;
			}
			this.setChanged();
			this.notifyObservers();
		}

	}

	@Override
	/**
	 * A method that cycles through the possible matches for the current word.
	 */
	public void nextMatch() {
		this.index++;
		if (this.index == matches.size()) {
			this.index = 0;
		}
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	/**
	 * A method that accepts the current matched word and adds it to the composed
	 * message.
	 */
	public void acceptWord() {
		// We add the Current Alphabetic Word to the Sentence
		String currentAlphabetic = "";
		if (this.matches.size() > 0) {
			currentAlphabetic = this.matches.get(this.index);
		} else {
			currentAlphabetic = "";
		}

		this.sentence.add(currentAlphabetic);
		// We clear the values to get ready for the next input
		this.matches = new ArrayList<String>();
		this.matches.add("");
		this.signature = "";
		this.index = 0;
		this.setChanged();
		this.notifyObservers();
	}

	// /**
	// * A getter for the index of the matches
	// * @return
	// */
	// public String getterMatchesIndex() {
	// String suggestion = "";
	// suggestion = this.matches.get(this.index);
	// return suggestion;
	// }
	//
}
