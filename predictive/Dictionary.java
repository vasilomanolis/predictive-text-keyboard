package predictive;

import java.util.Set;

/**
 * A interface for Dictionary
 * 
 * @author Vasileios Manolis
 *
 */
public interface Dictionary {
	public Set<String> signatureToWords(String signature);
}
