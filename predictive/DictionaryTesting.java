package predictive;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * @author <Vasileios Manolis> This class contains the test cases for first part
 *         of Worksheet 3
 */

public class DictionaryTesting {

	@Test
	public void wordToSignatureTest() {

		String expected1 = "227235662";
		String actual1 = PredictivePrototype.wordToSignature("barcelona");
		assertEquals(expected1, actual1);

		String expected2 = "222   ";
		String actual2 = PredictivePrototype.wordToSignature("abc123");
		assertEquals(expected2, actual2);

		String expected3 = "4663";
		String actual3 = PredictivePrototype.wordToSignature("home");
		assertEquals(expected3, actual3);

		String expected4 = "   ";
		String actual4 = PredictivePrototype.wordToSignature("   ");
		assertEquals(expected4, actual4);

		String expected5 = "";
		String actual5 = PredictivePrototype.wordToSignature("");
		assertEquals(expected5, actual5);

		String expected6 = "46 63";
		String actual6 = PredictivePrototype.wordToSignature("ho!me");
		assertEquals(expected6, actual6);
	}

	@Test
	public void signatureToWordsPrototypeTest() {

		Set<String> expectedWords1 = new HashSet<String>();
		expectedWords1.add("barcelona");
		Set<String> actualWords1 = PredictivePrototype.signatureToWords("227235662");
		assertEquals(expectedWords1, actualWords1);

		Set<String> expectedWords2 = new HashSet<String>();
		expectedWords2.add("hood");
		expectedWords2.add("ione");
		expectedWords2.add("good");
		expectedWords2.add("gond");

		expectedWords2.add("hone");
		expectedWords2.add("hoof");
		expectedWords2.add("gone");
		expectedWords2.add("goof");
		expectedWords2.add("home");

		Set<String> actualWords2 = PredictivePrototype.signatureToWords("4663");
		assertEquals(expectedWords2, actualWords2);

		Set<String> expectedWords3 = new HashSet<String>();
		Set<String> actualWords3 = PredictivePrototype.signatureToWords("46!63");
		assertEquals(expectedWords3, actualWords3);

		Set<String> expectedWords4 = new HashSet<String>();
		Set<String> actualWords4 = PredictivePrototype.signatureToWords("");
		assertEquals(expectedWords4, actualWords4);
	}

	@Test
	public void ListDictionaryTest() {

		ListDictionary dict1 = new ListDictionary("/Users/vasilis/Desktop/words");
		// ListDictionary dict1 = new ListDictionary("/usr/share/dict/words");

		Set<String> actualWords1 = new HashSet<String>();
		actualWords1 = dict1.signatureToWords("227235662");
		Set<String> expectedWords1 = new HashSet<String>();
		expectedWords1.add("barcelona");
		assertEquals(expectedWords1, actualWords1);

		Set<String> actualWords2 = new HashSet<String>();
		actualWords2 = dict1.signatureToWords("4663");

		Set<String> expectedWords2 = new HashSet<String>();
		expectedWords2.add("hood");
		expectedWords2.add("ione");
		expectedWords2.add("ioof");
		expectedWords2.add("good");
		expectedWords2.add("hond");
		expectedWords2.add("inne");
		expectedWords2.add("gond");
		expectedWords2.add("hone");
		expectedWords2.add("hoof");
		expectedWords2.add("gone");
		expectedWords2.add("goof");
		expectedWords2.add("home");
		expectedWords2.add("gome");
		expectedWords2.add("hoof");
		expectedWords2.add("hoof");

		assertEquals(expectedWords2, actualWords2);

		Set<String> actualWords3 = new HashSet<String>();
		actualWords3 = dict1.signatureToWords("");
		Set<String> expectedWords3 = new HashSet<String>();
		assertEquals(expectedWords3, actualWords3);

		Set<String> actualWords4 = new HashSet<String>();
		actualWords4 = dict1.signatureToWords("46!63");
		Set<String> expectedWords4 = new HashSet<String>();
		assertEquals(expectedWords4, actualWords4);

	}

	@Test
	public void MapTest() {

		Dictionary map1 = new MapDictionary("/Users/vasilis/Desktop/words");

		// Dictionary map1 = new MapDictionary("/usr/share/dict/words");

		Set<String> expected = new TreeSet<String>();

		expected.add("geode");
		expected.add("geoff");
		Set<String> result = map1.signatureToWords("43633");
		assertEquals(expected, result);

		expected.clear();
		result = map1.signatureToWords("265dsg43");
		assertEquals(expected, result);

		expected.clear();
		expected.add("gekko");
		expected.add("hello");
		result = map1.signatureToWords("43556");
		assertEquals(expected, result);

		expected.clear();
		result = map1.signatureToWords("");
		assertEquals(expected, result);

	}

	@Test
	public void TreeTest() {

		// Dictionary tree1 = new TreeDictionary("/Users/vasilis/Desktop/words");
		Dictionary tree1 = new TreeDictionary("/usr/share/dict/words");

		Set<String> expected = new TreeSet<String>();

		expected.add("gende");
		expected.add("genee");
		expected.add("genfe");
		expected.add("geode");
		expected.add("geoff");
		expected.add("hende");
		expected.add("henef");

		Set<String> result = tree1.signatureToWords("43633");
		assertEquals(expected, result);

		expected.clear();
		result = tree1.signatureToWords("265dsg43");
		assertEquals(expected, result);

		expected.clear();
		expected.add("gekko");
		expected.add("gellm");
		expected.add("hellm");
		expected.add("helln");
		expected.add("hello");

		result = tree1.signatureToWords("43556");
		assertEquals(expected, result);

	}

}