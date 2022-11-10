package aatabe_G30_A02_Linked_Lists;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Dictionary implements Serializable {
	HangmanPlayer aPlayer = new HangmanPlayer();
	SinglyLinkedList<String> theWord = new SinglyLinkedList<String>();
	String fileWord, aWord;
	int minIndex, maxIndex, wordIndex;

	public boolean wordList() {
		// Read the words from the "word_db.txt file"
		boolean flag = false;
		try {
			File file;
			file = new File("word_db.txt");
			Scanner readFile = new Scanner(file);
			// Loops through the txt file and populates the SinglyLinkedList
			while (readFile.hasNext()) {
				fileWord = readFile.nextLine();
				theWord.add(fileWord);
			} // while
		} catch (FileNotFoundException e) {
			flag = true;
		} // FileNotFoundException
		catch (IOException e1) {
			flag = true;
		} // catch
		return flag;
	} // wordList()

	// Method selects a random word's index and displays the word in the frame.
	public HangmanPlayer selectWord(SinglyLinkedList<String> word) {
		Random random = new Random();
		minIndex = 0;
		maxIndex = word.getLength() - 1;
		wordIndex = random.nextInt((maxIndex - minIndex) + 1) + minIndex;
		aWord = word.getElementAt(wordIndex);
		aPlayer.setGuessedWord(aWord);
		System.out.println(aWord);
		theWord.remove(wordIndex);
		return aPlayer;
	} // selectWord()
} // class Dictionary
