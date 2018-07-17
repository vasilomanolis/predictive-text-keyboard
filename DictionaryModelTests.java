import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * @author <Vasileios Manolis> This class contains the test cases for Worksheet
 *         4
 */

public class DictionaryModelTests {

	DictionaryModel dictModel = new DictionaryModel("/Users/vasilis/Desktop/words");
	// BEFORE RUNNING THE TESTS WE
	// SHOULD REMOVE THE " throws
	// java.io.IOException" FROM THE
	// CONSTRUCTOR IN THE
	// DictionaryModel Class

	@Test
	public void testAddCharacter() {

		List<String> matchesTester = new ArrayList<String>();
		matchesTester.add("");

		assertEquals(dictModel.getSignature(), "");
		assertEquals(dictModel.getMatches(), matchesTester);

		dictModel.addCharacter('4');

		List<String> matchesTester2 = new ArrayList<String>();

		matchesTester2.add("g");
		matchesTester2.add("h");
		matchesTester2.add("i");

		assertEquals(dictModel.getMatches(), matchesTester2);
		dictModel.addCharacter('6');
		assertEquals(dictModel.getSignature(), "46");

		dictModel.addCharacter('6');
		dictModel.addCharacter('3');
		dictModel.addCharacter('5');
		dictModel.addCharacter('3');
		dictModel.addCharacter('2');
		dictModel.addCharacter('2'); // Also testing extra 2 with no matches
		dictModel.addCharacter('2');
		dictModel.addCharacter('2');
		dictModel.addCharacter('2');
		dictModel.addCharacter('2');

		assertEquals(dictModel.getSignature(), "466353");
		List<String> matchesTester3 = new ArrayList<String>();

		matchesTester3.add("goodle");
		matchesTester3.add("homeke");
		matchesTester3.add("homele");
		matchesTester3.add("hondle");
		matchesTester3.add("honfle");
		matchesTester3.add("hoodle");
		matchesTester3.add("hoofle");

		assertEquals(dictModel.getMatches(), matchesTester3);

	}

	@Test
	public void testGetMessage1() {

		List<String> sentenceTester1 = new ArrayList<String>();

		sentenceTester1.add("");
		dictModel.getMessage();

		assertEquals(dictModel.getMessage(), sentenceTester1);

	}

	@Test
	public void testGetMessage2() {

		dictModel.addCharacter('4');
		List<String> sentenceTester2 = new ArrayList<String>();

		sentenceTester2.add("g");

		assertEquals(dictModel.getMessage(), sentenceTester2);

	}

	@Test
	public void testRemoveLastCharacter() {

		List<String> matchesTester = new ArrayList<String>();
		matchesTester.add("");

		dictModel.addCharacter('4');
		dictModel.addCharacter('6');
		dictModel.addCharacter('6');
		dictModel.addCharacter('3');
		dictModel.addCharacter('5');
		dictModel.addCharacter('3');
		dictModel.removeLastCharacter();
		assertEquals(dictModel.getSignature(), "46635");

		dictModel.removeLastCharacter();
		assertEquals(dictModel.getSignature(), "4663");

		dictModel.removeLastCharacter();
		assertEquals(dictModel.getSignature(), "466");

		dictModel.removeLastCharacter();
		assertEquals(dictModel.getSignature(), "46");

		dictModel.removeLastCharacter();
		assertEquals(dictModel.getSignature(), "4");

		dictModel.removeLastCharacter();
		assertEquals(dictModel.getSignature(), "");

	}

	@Test
	public void testRemoveLastCharacter2() {

		List<String> matchesTester = new ArrayList<String>();
		matchesTester.add("");

		dictModel.addCharacter('4');
		dictModel.addCharacter('6');
		dictModel.addCharacter('6');
		dictModel.addCharacter('3');
		dictModel.addCharacter('5');
		dictModel.addCharacter('3');
		dictModel.removeLastCharacter();
		dictModel.removeLastCharacter();
		dictModel.removeLastCharacter();
		dictModel.removeLastCharacter();
		dictModel.removeLastCharacter();

		List<String> matchesTester2 = new ArrayList<String>();

		matchesTester2.add("g");
		matchesTester2.add("h");
		matchesTester2.add("i");

		assertEquals(dictModel.getMatches(), matchesTester2);

		dictModel.removeLastCharacter();
		List<String> matchesTester3 = new ArrayList<String>();

		matchesTester3.add("");

		assertEquals(dictModel.getMatches(), matchesTester3);

	}

	@Test
	public void testIndex() {

		dictModel.addCharacter('4');

		assertEquals(dictModel.getIndex(), 0);

	}
}
