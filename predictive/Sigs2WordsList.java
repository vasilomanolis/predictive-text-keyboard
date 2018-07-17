package predictive;

import java.util.Set;

/**
 * 
 * A command-line program for calling the faster signatureToWords method. Below
 * you can find in comments the time difference comparizon.
 * 
 * @author Vasileios Manolis
 * 
 * 
 */
public class Sigs2WordsList {

	/**
	 * A main method for calling the faster signatureToWords
	 * 
	 * @param args
	 *            signatures of the words
	 */
	public static void main(String[] args) {

		ListDictionary dict1 = new ListDictionary("/usr/share/dict/words");

		for (String s : args) {

			System.out.print(s + " : ");
			Set<String> co1 = (Set<String>) dict1.signatureToWords(s);

			for (String word : co1) {

				System.out.print(word + " ");
			}

			System.out.println();
		}
	}

}

// COMPARING THE TIME TAKEN TO COMPLETE THE EXECUTATION OF Sigs2WordsList AND
// Sigs2WordsList:
// Vasiliss-MacBook-Pro:predictive vasilis$ time java -cp ..
// predictive.Sigs2WordsList 4663
// 4663 hood ione ioof good hond inne gond hone hoof gone goof home gome
//
// real 0m1.197s - THE REAL TIME FOR THE Sigs2WordsList IS 0m1.197s
// user 0m2.578s
// sys 0m0.259s
// Vasiliss-MacBook-Pro:predictive vasilis$ time java -cp ..
// predictive.Sigs2WordsProto 4663
// 4663 : [hood, ione, ioof, good, hond, inne, gond, hone, hoof, gone, goof,
// home, gome]
//
// real 0m1.719s - THE REAL TIME FOR THE Sigs2WordsProto IS 0m1.719s
// user 0m2.580s
// As a result, as expected the REAL TIME OF Sigs2WordsList 0m1.197s <
// Sigs2WordsList 0m1.719s
// The time difference can be even more noticeable when giving larger inputs.
// For example, if we provide the same
// "4663" for 26 times, the time difference is as follow:
// FOR Sigs2WordsList:
// real 0m1.209s
// user 0m2.687s
// sys 0m0.252s
// FOR Sigs2WordsProto:
// real 0m19.505s
// user 0m20.968s
// sys 0m0.467s
// As a result, as expected the REAL TIME OF Sigs2WordsList 0m1.209s <
// Sigs2WordsProto 0m19.505s
// To sum up, for the argument "4663" the Sigs2WordsList is aprox. faster by
// 30.3%. When we give the same argument 26 times, the Sigs2WordsList is aprox.
// 93.8% faster.
