package aatabe_G30_A02_Linked_Lists;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HangmanPlayerTest {

	@Test
	void test() {
		HangmanPlayer gamePlayer = new HangmanPlayer();
		assertEquals("", gamePlayer.getPlayerName());
		assertEquals("", gamePlayer.getGuessedWord());
		assertEquals(0, gamePlayer.getTotalWins());
		assertEquals(0, gamePlayer.getTotalLosses());
		assertEquals(0, gamePlayer.getGamesPlayed());
		assertEquals(0, gamePlayer.getWrongGuesses());
	} // test

	@Test
	void test2() {
		HangmanPlayer gamePlayer = new HangmanPlayer();
		gamePlayer.setTotalWins(gamePlayer.getTotalWins() + 5);
		assertEquals(5, gamePlayer.getTotalWins());
		gamePlayer.setTotalLosses(gamePlayer.getTotalLosses() + 5);
		assertEquals(5, gamePlayer.getTotalLosses());
		gamePlayer.setGamesPlayed(gamePlayer.getGamesPlayed() + 5);
		assertEquals(5, gamePlayer.getGamesPlayed());
		gamePlayer.setWrongGuesses(gamePlayer.getWrongGuesses() + 5);
		assertEquals(5, gamePlayer.getWrongGuesses());
	} // test2

	@Test
	void test3() {
		HangmanPlayer gamePlayer = new HangmanPlayer();
		gamePlayer.incrementWins();
		gamePlayer.incrementLosses();
		gamePlayer.incrementGamesPlayed();
	} // test3
} // HangmanPlayerTest
