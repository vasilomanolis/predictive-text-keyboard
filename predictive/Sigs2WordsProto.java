package predictive;

/**
 * A command-line program for calling the signatureToWords from the
 * PredictivePrototype class
 * 
 * @author Vasileios Manolis
 *
 */
public class Sigs2WordsProto {

	/**
	 * A main method for calling the signatureToWords from the PredictivePrototype
	 * class
	 * 
	 * @param args
	 *            signatures of the words
	 */
	public static void main(String[] args) {

		for (int i = 0; i < args.length; i++) {
			PredictivePrototype.signatureToWords(args[i]);
			System.out.print(args[i] + " : ");
			for (String word : PredictivePrototype.signatureToWords(args[i]))
				System.out.print(word + " ");
			System.out.println("");

		}

		// ALTERNATIVE METHOD FOR PRINTING: DO NOT TAKE INTO CONSIDERATION FOR GRADING
		// for (int i = 0; i < args.length; i++) {
		// PredictivePrototype.signatureToWords(args[i]);
		// System.out.println(args[i] + " : " +
		// PredictivePrototype.signatureToWords(args[i]));
		// }

	}

}
