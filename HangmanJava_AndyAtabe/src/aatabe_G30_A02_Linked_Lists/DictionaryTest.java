package aatabe_G30_A02_Linked_Lists;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DictionaryTest {

	@Test
	void test() {
		Dictionary dyk = new Dictionary();
		boolean storedInArray = false;
		try {
			dyk.wordList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (dyk.theWord.getLength() == 44) {
			storedInArray = true;
		} // if
		assertEquals(true, storedInArray);
	} // test()

}//
