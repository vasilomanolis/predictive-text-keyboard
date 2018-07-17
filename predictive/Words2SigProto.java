package predictive;

/**
 * A command-line program for calling the wordToSignature from the
 * PredictivePrototype class
 * 
 * @author Vasileios Manolis
 *
 */
public class Words2SigProto {

	/**
	 * A main method for calling the wordToSignature from the PredictivePrototype
	 * class
	 * 
	 * @param args
	 *            the word in alphabetic format
	 */
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println(PredictivePrototype.wordToSignature((args[i])));
		}
	}

}
