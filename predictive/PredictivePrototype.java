package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * A class to implement a system of predictive text (T9)
 * 
 * @author Vasileios Manolis
 *
 */
public class PredictivePrototype {

	/**
	 * 
	 * This method takes a word and returns a numeric signature. For example "home"
	 * will return "4663". If the word has any non-alphabetic characters, they will
	 * be replaced with a space " ". Alphabetic characters are considered those from
	 * a-z only.
	 * 
	 * 
	 * String class manipulates characters strings that cannot be changed. That
	 * means that objects of type String are immutable and read only. On the other
	 * side, StringBuffer can represent characters that can be modified. If we used
	 * String that uses the "+" to concatenate two strings, the code would generate
	 * multiple objects each time of type String that wouldn't be able to change. As
	 * a result, with the String we would create more objects that we needed and we
	 * would probably consume more memory whereas with the StrignBuffer, each time
	 * we modify the string.
	 * 
	 * @param word
	 *            the alphabetic version of the word
	 * @return the signature of the word
	 */
	public static String wordToSignature(String word) {
		StringBuffer conversion = new StringBuffer(word);
		StringBuffer signature = new StringBuffer();

		for (int i = 0; i < conversion.length(); i++) {

			char ch = conversion.charAt(i);

			if (!Character.isLetter(ch))
				signature.append(" ");

			else {

				ch = Character.toLowerCase(ch);

				if (ch == 'a' || ch == 'b' || ch == 'c')
					signature.append("2");
				else if (ch == 'd' || ch == 'e' || ch == 'f')
					signature.append("3");
				else if (ch == 'g' || ch == 'h' || ch == 'i')
					signature.append("4");
				else if (ch == 'j' || ch == 'k' || ch == 'l')
					signature.append("5");
				else if (ch == 'm' || ch == 'n' || ch == 'o')
					signature.append("6");
				else if (ch == 'p' || ch == 'q' || ch == 'r' || ch == 's')
					signature.append("7");
				else if (ch == 't' || ch == 'u' || ch == 'v')
					signature.append("8");
				else if (ch == 'w' || ch == 'x' || ch == 'y' || ch == 'z')
					signature.append("9");

			}
		}

		return signature.toString();

	}

	/**
	 * The method takes the given numeric signature and returns a set of possible
	 * matching words from the dictionary. The returned list does not have
	 * duplicates and each word is in lower-case. In this part, if we stored the
	 * dictionary in the Java program the implementation would be inefficient as we
	 * would have to load continuously the dictionary to read from it and that would
	 * consume much memory. At the same time, we wouldn't be able to use binary
	 * search in this part so storing it at this part would be inefficient.
	 * 
	 * @param signature
	 *            the signature of a word
	 * @return the a Set<String> of words (the alphabetic format of the words)
	 */
	public static Set<String> signatureToWords(String signature) {

		// int i = 0;

		Set<String> matchedWords = new HashSet<String>();

		Scanner s = null;
		try {
			s = new Scanner(new File("/usr/share/dict/words"));
			// Scanner should read from /usr/share/dict/words

			while (s.hasNextLine()) {

				// add to the list after checking if its valid
				String testedWord = s.nextLine();
				if (isValidWord(testedWord)) {

					if (signature.equals(wordToSignature(testedWord.toLowerCase()))) {
						matchedWords.add((testedWord.toLowerCase()));
						// System.out.println(testedWord.toLowerCase());
						// System.out.println(i++);
					}
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			// ensure the underlying stream is always closed
			// this only has any effect if the item passed to the Scanner
			// constructor implements Closeable (which it does in this case).
			s.close();
		}
		return matchedWords;

	}

	// TO BE DELETED
	// public static void signatureToWords(String signature) {
	//
	// int i = 0;
	//
	// Set<String> matchedWords = new HashSet<String>();
	//
	// Scanner s = null;
	// try {
	// s = new Scanner(new File("/Users/vasilis/Desktop/words.txt"));
	//
	// while (s.hasNextLine()) {
	//
	// // add to the list after checking if its valid
	//
	// if (isValidWord(s.nextLine())) {
	//
	// if (signature.equals(wordToSignature(s.nextLine().toLowerCase()))) {
	// matchedWords.add(wordToSignature(s.nextLine().toLowerCase()));
	// System.out.println(s.nextLine().toLowerCase());
	// System.out.println(i++);
	// }
	// }
	//
	// }
	//
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	//
	// } finally {
	// // ensure the underlying stream is always closed
	// // this only has any effect if the item passed to the Scanner
	// // constructor implements Closeable (which it does in this case).
	// s.close();
	// }
	//
	// }

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

		// ALTERNATIVE 1
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

	// ALTERNATIVE2: boolean allLetters =
	// someString.chars().allMatch(Character::isLetter);

	// private static boolean convertToLowerCase(String word) {
	//
	//
	//
	//
	// }

	// /**
	// * A main method for testing. DO NOT TAKE INTO CONSIDERATION FOR GRADING
	// *
	// * @param args
	// */
	// public static void main(String[] args) {
	// // System.out.println(wordToSignature("baby"));
	// // System.out.println(isValidWord("babY"));
	// // System.out.println(wordToSignature("HoMe"));
	// // signatureToWords("4663");
	//
	// // System.out.println("elements in hashset" + signatureToWords("999"));
	//
	// // System.out.println("elements in hashset" + signatureToWords("4663"));
	// String expected1 = "227235662";
	// String expected2 = "222 ";
	// String expected3 = "4663";
	// String expected4 = " ";
	// String expected5 = "";
	// String expected6 = "46 63";
	//
	// System.out.println("elements in hashset" + signatureToWords(expected1));
	// System.out.println("elements in hashset" + signatureToWords(expected2));
	// System.out.println("elements in hashset" + signatureToWords(expected3));
	// System.out.println("elements in hashset" + signatureToWords(expected4));
	// System.out.println("elements in hashset" + signatureToWords(expected5));
	// System.out.println("elements in hashset" + signatureToWords(expected6));
	//
	// }

}
