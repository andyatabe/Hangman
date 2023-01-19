package aatabe_G30_A02_Linked_Lists;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class GameFrame extends JFrame implements ActionListener, Serializable {

	HangmanPlayer aPlayer; // object to store letter
	HangmanPlayer thePlayer = new HangmanPlayer(); // object for name
	Dictionary dyk = new Dictionary();
	String displayHidden;
	private JPanel hangmanPanel;
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu gameOptionMenu;
	private JMenu gameControlsMenu;
	private JMenu gameRulesMenu;
	private JMenu aboutMenu;
	private JMenuItem newGameMenuItem;
	private JMenuItem quitGameMenuItem;
	private JMenuItem scoreboardMenuItem;
	private JMenuItem gameRulesMenuItem;
	private JMenuItem aboutGameMenuItem;
	private JTextField txtFieldEnterLetter;
	private JLabel lblhangman;
	private JTextArea hangmanTextArea;
	private JLabel lblInput;
	private JButton btnHint;
	private JLabel lblCurrentPlayer;
	private JTextArea textAreaCurrentPlayer;
	private JLabel lblPastPlayers;
	private JTextArea textAreaPreviousPlayer;
	private JLabel lblGameStatus;
	private JTextArea textAreaGameStatus;
	private JLabel lblGuessedLetters;
	private JTextArea textAreaGuessedLetters;
	private JLabel lblWelcome;
	private JLabel lblPierre;
	private JLabel lblhead;
	private JLabel lblNoose1;
	private JLabel lblNoose5;
	private JLabel lblNoose3;
	private JLabel lblNoose4;
	private JLabel lblNoose1_2;
	private JLabel lblNoose6;
	private JLabel lblNoose7;
	private JLabel lblBody;
	private JLabel lblLeftLeg;
	private JLabel lblRightLeg;
	private JLabel lblLeftHand;
	private JLabel lblRightHand;
	private JLabel lblFloor;
	private JLabel lblParental;
	private JLabel lblParentalAd;
	private JLabel lblNoose8;
	private JTextArea scoreboardTextArea;
	private JLabel lblScoreboard;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} // main

	/**
	 * Create the frame.
	 */
	public GameFrame() {
		setTitle("TLOP4 Hangman Game");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1900, 885);
		hangmanPanel = new JPanel();
		hangmanPanel.setBackground(new Color(186, 85, 211));
		hangmanPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(hangmanPanel);
		hangmanPanel.setLayout(null);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1882, 26);
		hangmanPanel.add(menuBar);

		gameOptionMenu = new JMenu("Game Options");
		gameOptionMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(gameOptionMenu);

		newGameMenuItem = new JMenuItem("New Game");
		gameOptionMenu.add(newGameMenuItem);

		quitGameMenuItem = new JMenuItem("Quit Game");
		gameOptionMenu.add(quitGameMenuItem);

		gameControlsMenu = new JMenu("Game Controls");
		gameControlsMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(gameControlsMenu);

		scoreboardMenuItem = new JMenuItem("Scoreboard");
		scoreboardMenuItem.setEnabled(false);
		gameControlsMenu.add(scoreboardMenuItem);

		gameRulesMenu = new JMenu("Instructions");
		gameRulesMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(gameRulesMenu);

		gameRulesMenuItem = new JMenuItem("Learn");
		gameRulesMenu.add(gameRulesMenuItem);

		aboutMenu = new JMenu("About");
		aboutMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(aboutMenu);

		aboutGameMenuItem = new JMenuItem("About");
		aboutMenu.add(aboutGameMenuItem);

		lblhangman = new JLabel("TLOP4 HANGMAN GAME");
		lblhangman.setForeground(new Color(0, 0, 0));
		lblhangman.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
		lblhangman.setHorizontalAlignment(SwingConstants.CENTER);
		lblhangman.setBounds(408, 13, 436, 82);
		hangmanPanel.add(lblhangman);

		hangmanTextArea = new JTextArea();
		hangmanTextArea.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		hangmanTextArea.setVisible(false);
		hangmanTextArea.setEnabled(false);
		hangmanTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		hangmanTextArea.setBackground(Color.WHITE);
		hangmanTextArea.setEditable(false);
		hangmanTextArea.setBounds(54, 126, 405, 442);
		hangmanPanel.add(hangmanTextArea);

		lblInput = new JLabel("Enter a letter:");
		lblInput.setForeground(Color.BLACK);
		lblInput.setVisible(false);
		lblInput.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblInput.setBounds(344, 615, 236, 33);
		hangmanPanel.add(lblInput);

		txtFieldEnterLetter = new JTextField();
		txtFieldEnterLetter.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		txtFieldEnterLetter.setForeground(new Color(0, 0, 0));
		txtFieldEnterLetter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (guessWord(txtFieldEnterLetter.getText()) == 1) {
					JOptionPane.showMessageDialog(frame, "Please enter only 1 letter. Click OK and try again.",
							"Input Error", JOptionPane.ERROR_MESSAGE);
				} else if (guessWord(txtFieldEnterLetter.getText()) == 2) {
					JOptionPane.showMessageDialog(frame, "Please enter your guess. Click OK and try again.",
							"Input Error", JOptionPane.ERROR_MESSAGE);
				} else if (guessWord(txtFieldEnterLetter.getText()) == 3) {
					JOptionPane.showMessageDialog(frame, "    Congratulations, you guessed correct!", "Valid Input",
							JOptionPane.PLAIN_MESSAGE);
					changeUnderscoreToLetter(txtFieldEnterLetter.getText());
					txtFieldEnterLetter.setText("");
					hangmanTextArea.setText(displayHidden);
					winningCondition();
				} else if (guessWord(txtFieldEnterLetter.getText()) == 4) {
					JOptionPane.showMessageDialog(frame, "Unfortunately, you guessed wrong. Click OK and try again.",
							"Invalid Input", JOptionPane.PLAIN_MESSAGE);
					aPlayer.incrementWrongGuesses();
					textAreaGuessedLetters.append(txtFieldEnterLetter.getText().toUpperCase() + ",   ");
					txtFieldEnterLetter.setText("");

					// displays the man hanging from the pole with a noose tied around neck when
					// they have guessed wrong letters.

					if (aPlayer.getWrongGuesses() == 1) {
						lblhead.setVisible(true);
					} else if (aPlayer.getWrongGuesses() == 2) {
						lblBody.setVisible(true);
					} else if (aPlayer.getWrongGuesses() == 3) {
						lblLeftHand.setVisible(true);
					} else if (aPlayer.getWrongGuesses() == 4) {
						lblRightHand.setVisible(true);
					} else if (aPlayer.getWrongGuesses() == 5) {
						lblRightLeg.setVisible(true);
					} else if (aPlayer.getWrongGuesses() == 6) {
						lblLeftLeg.setVisible(true);
						ImageIcon icon = new ImageIcon("sad.png");
						JOptionPane.showMessageDialog(frame,
								"Unfortunately, you ran out of guesses. Better luck next time. \n        Select the \"New Game\" option to continue playing.",
								"Game End", JOptionPane.INFORMATION_MESSAGE, icon);
						textAreaGameStatus
								.setText("  The word to guess was: " + aPlayer.getGuessedWord().toUpperCase());
						savePlayerGame();
						aPlayer.incrementGamesPlayed();
					} // if
				} // else if
			} // actionPerformed(ActionEvent)
		});
		txtFieldEnterLetter.setVisible(false);
		txtFieldEnterLetter.setEnabled(false);
		txtFieldEnterLetter.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldEnterLetter.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		txtFieldEnterLetter.setBounds(579, 606, 141, 50);
		hangmanPanel.add(txtFieldEnterLetter);
		txtFieldEnterLetter.setColumns(10);

		btnHint = new JButton("Get Hint!");
		btnHint.setBorder(new LineBorder(Color.BLACK, 5, true));
		btnHint.setForeground(Color.WHITE);
		btnHint.setVisible(false);
		btnHint.setEnabled(false);
		btnHint.setBackground(new Color(0, 0, 0));
		btnHint.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnHint.setBounds(931, 141, 125, 50);
		hangmanPanel.add(btnHint);

		lblCurrentPlayer = new JLabel("Current Player:");
		lblCurrentPlayer.setForeground(Color.BLACK);
		lblCurrentPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayer.setVisible(false);
		lblCurrentPlayer.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblCurrentPlayer.setBounds(912, 204, 168, 16);
		hangmanPanel.add(lblCurrentPlayer);

		textAreaCurrentPlayer = new JTextArea();
		textAreaCurrentPlayer.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		textAreaCurrentPlayer.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		textAreaCurrentPlayer.setVisible(false);
		textAreaCurrentPlayer.setEnabled(false);
		textAreaCurrentPlayer.setEditable(false);
		textAreaCurrentPlayer.setBounds(902, 233, 189, 36);
		hangmanPanel.add(textAreaCurrentPlayer);

		lblPastPlayers = new JLabel("Previous Player(s):");
		lblPastPlayers.setForeground(Color.BLACK);
		lblPastPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		lblPastPlayers.setVisible(false);
		lblPastPlayers.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblPastPlayers.setBounds(902, 454, 203, 26);
		hangmanPanel.add(lblPastPlayers);

		textAreaPreviousPlayer = new JTextArea();
		textAreaPreviousPlayer.setBorder(new LineBorder(Color.BLACK, 5, true));
		textAreaPreviousPlayer.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		textAreaPreviousPlayer.setVisible(false);
		textAreaPreviousPlayer.setEnabled(false);
		textAreaPreviousPlayer.setEditable(false);
		textAreaPreviousPlayer.setBounds(857, 483, 288, 208);
		hangmanPanel.add(textAreaPreviousPlayer);

		lblGameStatus = new JLabel("Game Status");
		lblGameStatus.setForeground(Color.BLACK);
		lblGameStatus.setVisible(false);
		lblGameStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameStatus.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblGameStatus.setBounds(515, 711, 187, 26);
		hangmanPanel.add(lblGameStatus);

		textAreaGameStatus = new JTextArea();
		textAreaGameStatus.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		textAreaGameStatus.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		textAreaGameStatus.setVisible(false);
		textAreaGameStatus.setEnabled(false);
		textAreaGameStatus.setEditable(false);
		textAreaGameStatus.setBounds(369, 750, 495, 50);
		hangmanPanel.add(textAreaGameStatus);

		textAreaGuessedLetters = new JTextArea();
		textAreaGuessedLetters.setBorder(new LineBorder(Color.BLACK, 5, true));
		textAreaGuessedLetters.setForeground(Color.RED);
		textAreaGuessedLetters.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		textAreaGuessedLetters.setVisible(false);
		textAreaGuessedLetters.setEnabled(false);
		textAreaGuessedLetters.setBounds(902, 311, 187, 130);
		hangmanPanel.add(textAreaGuessedLetters);

		lblGuessedLetters = new JLabel("Guessed Letter(s):");
		lblGuessedLetters.setForeground(Color.BLACK);
		lblGuessedLetters.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuessedLetters.setVisible(false);
		lblGuessedLetters.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblGuessedLetters.setBounds(902, 282, 189, 16);
		hangmanPanel.add(lblGuessedLetters);

		lblWelcome = new JLabel("Select \"Game Options\" then click \"New Game\" to start playing.");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(Color.BLACK);
		lblWelcome.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblWelcome.setBounds(54, 521, 1096, 156);
		hangmanPanel.add(lblWelcome);

		lblPierre = new JLabel("");
		lblPierre.setBounds(10, -28, 187, 186);
		lblPierre.setIcon(new ImageIcon("pierre.png"));
		hangmanPanel.add(lblPierre);

		lblhead = new JLabel("o");
		lblhead.setForeground(Color.BLACK);
		lblhead.setFont(new Font("OCR A Extended", Font.PLAIN, 99));
		lblhead.setBounds(714, 177, 76, 98);
		hangmanPanel.add(lblhead);

		lblNoose1 = new JLabel("|");
		lblNoose1.setForeground(Color.BLACK);
		lblNoose1.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblNoose1.setBounds(515, 194, 38, 147);
		hangmanPanel.add(lblNoose1);

		lblNoose5 = new JLabel("|");
		lblNoose5.setForeground(Color.BLACK);
		lblNoose5.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblNoose5.setBounds(515, 307, 38, 98);
		hangmanPanel.add(lblNoose5);

		lblNoose3 = new JLabel("|");
		lblNoose3.setForeground(Color.BLACK);
		lblNoose3.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblNoose3.setBounds(515, 101, 38, 176);
		hangmanPanel.add(lblNoose3);

		lblNoose4 = new JLabel("|");
		lblNoose4.setForeground(Color.BLACK);
		lblNoose4.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblNoose4.setBounds(515, 345, 38, 176);
		hangmanPanel.add(lblNoose4);

		lblNoose1_2 = new JLabel("|");
		lblNoose1_2.setForeground(Color.BLACK);
		lblNoose1_2.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblNoose1_2.setBounds(515, 413, 38, 176);
		hangmanPanel.add(lblNoose1_2);

		lblNoose6 = new JLabel("____");
		lblNoose6.setForeground(Color.BLACK);
		lblNoose6.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblNoose6.setBounds(532, 49, 223, 115);
		hangmanPanel.add(lblNoose6);

		lblNoose7 = new JLabel("|");
		lblNoose7.setForeground(Color.BLACK);
		lblNoose7.setFont(new Font("Tahoma", Font.PLAIN, 67));
		lblNoose7.setBounds(733, 108, 38, 147);
		hangmanPanel.add(lblNoose7);

		lblBody = new JLabel("|");
		lblBody.setForeground(Color.BLACK);
		lblBody.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblBody.setBounds(726, 221, 47, 147);
		hangmanPanel.add(lblBody);

		lblLeftLeg = new JLabel("/");
		lblLeftLeg.setForeground(Color.BLACK);
		lblLeftLeg.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblLeftLeg.setBounds(714, 311, 38, 147);
		hangmanPanel.add(lblLeftLeg);

		lblRightLeg = new JLabel("\\");
		lblRightLeg.setForeground(Color.BLACK);
		lblRightLeg.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblRightLeg.setBounds(738, 311, 38, 147);
		hangmanPanel.add(lblRightLeg);

		lblLeftHand = new JLabel("/");
		lblLeftHand.setForeground(Color.BLACK);
		lblLeftHand.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblLeftHand.setBounds(714, 241, 38, 147);
		hangmanPanel.add(lblLeftHand);

		lblRightHand = new JLabel("\\");
		lblRightHand.setForeground(Color.BLACK);
		lblRightHand.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblRightHand.setBounds(738, 241, 38, 147);
		hangmanPanel.add(lblRightHand);

		lblFloor = new JLabel("_");
		lblFloor.setForeground(Color.BLACK);
		lblFloor.setFont(new Font("Tahoma", Font.PLAIN, 98));
		lblFloor.setBounds(506, 433, 160, 147);
		hangmanPanel.add(lblFloor);

		lblParentalAd = new JLabel("");
		lblParentalAd.setBounds(1023, 704, 244, 165);
		lblParentalAd.setIcon(new ImageIcon("parental.png"));
		hangmanPanel.add(lblParentalAd);

		lblNoose8 = new JLabel("/");
		lblNoose8.setForeground(Color.BLACK);
		lblNoose8.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblNoose8.setBounds(532, 116, 38, 147);
		hangmanPanel.add(lblNoose8);

		scoreboardTextArea = new JTextArea();
		scoreboardTextArea.setEditable(false);
		scoreboardTextArea.setBorder(new LineBorder(Color.BLACK, 5, true));
		scoreboardTextArea.setBackground(Color.WHITE);
		scoreboardTextArea.setForeground(new Color(0, 0, 0));
		scoreboardTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		scoreboardTextArea.setBounds(1185, 158, 685, 642);
		hangmanPanel.add(scoreboardTextArea);

		JLabel label = new JLabel("New label");
		label.setBounds(1318, 253, 56, 16);
		hangmanPanel.add(label);

		lblScoreboard = new JLabel("TLOP4 SCOREBOARD");
		lblScoreboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreboard.setForeground(Color.BLACK);
		lblScoreboard.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblScoreboard.setBounds(1318, 91, 436, 82);
		hangmanPanel.add(lblScoreboard);

		newGameMenuItem.addActionListener(this);
		quitGameMenuItem.addActionListener(this);
		aboutGameMenuItem.addActionListener(this);
		gameRulesMenuItem.addActionListener(this);
		btnHint.addActionListener(this);
		scoreboardMenuItem.addActionListener(this);

	} // GameFrame()

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == aboutGameMenuItem) {
			JOptionPane.showMessageDialog(this, String.format("%40s%39s%39s%40s", "TLOP4 Hangman Game\n",
					"Andy Atabe\n", "2022\n", "Heritage College"), "About Game", JOptionPane.PLAIN_MESSAGE);
		} // about game
		else if (e.getSource() == quitGameMenuItem) {
			ImageIcon icon = new ImageIcon("wave.png");
			JOptionPane.showMessageDialog(this, "Thank You for playing TLOP4 Hangman Game!", "Quitting Game",
					JOptionPane.PLAIN_MESSAGE, icon);
			System.exit(0);
		} // quit game
		else if (e.getSource() == gameRulesMenuItem) {
			JOptionPane.showMessageDialog(this, String.format("%1s%1s%1s%1s%90s",
					"- There's a bank of words, you have to guess each word by guessing it's letter.\n",
					"- Enter one letter at a time in the text box.\n",
					"- If the letter entered matched one of the letters in the word, that letter is displayed.\n",
					"- Once all letters in the word have been correctly guessed, you win.\n\n", "GOOD LUCK!!"),
					"Game Rules", JOptionPane.PLAIN_MESSAGE);
		} // game rules
		else if (e.getSource() == newGameMenuItem) {
			Scanner readFile = new Scanner("scorebaord.ser");
			if (playerName() == true) {
				if (!dyk.wordList()) {
					aPlayer = dyk.selectWord(dyk.theWord);
					if (readFile.hasNext()) {
						loadPlayerGame();
					}
					aPlayer = dyk.selectWord(dyk.theWord);
				} else {
					JOptionPane.showMessageDialog(frame, "Game currupted. Try again later.", "Currupted File",
							JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				} // else
				hangmanTextArea.setVisible(true);
				hangmanTextArea.setEnabled(true);
				lblPastPlayers.setVisible(true);
				lblGameStatus.setVisible(true);
				lblGuessedLetters.setVisible(true);
				lblCurrentPlayer.setVisible(true);
				lblWelcome.setVisible(false);
				lblhead.setVisible(false);
				lblBody.setVisible(false);
				lblRightHand.setVisible(false);
				lblLeftHand.setVisible(false);
				lblRightLeg.setVisible(false);
				lblLeftLeg.setVisible(false);
				txtFieldEnterLetter.setVisible(true);
				txtFieldEnterLetter.setEnabled(true);
				textAreaGameStatus.setVisible(true);
				textAreaGameStatus.setEnabled(true);
				textAreaCurrentPlayer.setVisible(true);
				textAreaCurrentPlayer.setEnabled(true);
				textAreaPreviousPlayer.setVisible(true);
				textAreaPreviousPlayer.setEnabled(true);
				textAreaGuessedLetters.setVisible(true);
				textAreaGuessedLetters.setEnabled(true);
				scoreboardMenuItem.setEnabled(true);
				lblInput.setVisible(true);
				btnHint.setVisible(true);
				btnHint.setEnabled(true);
				currentTurn();
				replaceLetters();
				hangmanTextArea.setText(displayHidden);
				aPlayer.setWrongGuesses(0);
				textAreaGameStatus.setText("");
				txtFieldEnterLetter.setText("");
				textAreaGuessedLetters.setText("");
			} // if
		} // else if
		else if (e.getSource() == btnHint) {
			getHint();
			hangmanTextArea.setText(displayHidden);
			btnHint.setEnabled(false);
		} // else if
		else if (e.getSource() == scoreboardMenuItem) {
			scoreboard();
		} // else if

	} // actionPerformed()

	public void pinrt() {
		System.out.println("lmao");
	}

	// method which displays pop up when "New game" is clicked and validates a
	// player name has been entered
	public boolean playerName() {
		boolean isValid;
		String pName = "";
		do {
			isValid = true;
			ImageIcon icon = new ImageIcon("door.png");
			pName = (String) JOptionPane.showInputDialog(this, "Enter your player's username: ", "Player Input",
					JOptionPane.QUESTION_MESSAGE, icon, null, null);
			if (pName != null) {
				if (pName.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Player's username must be entered to play.", "Name Error",
							JOptionPane.WARNING_MESSAGE);
					isValid = false;
				} // if
			} else {
				return false;
			} // else
		} while (!isValid);
		thePlayer.setPlayerName(pName);
		JOptionPane.showMessageDialog(this, "Welcome to the TLOP4 Hangman Game: " + thePlayer.getPlayerName() + "   ",
				"Player Name", JOptionPane.INFORMATION_MESSAGE);
		return true;
	} // playerName()

	public void currentTurn() {
		textAreaCurrentPlayer.setText(" " + thePlayer.getPlayerName() + "'s gameplay.\n");
	} // currentTurn()

	// method determines where the guess the right word, if they entered more than 2
	// characters, if they did not enter a character or if their guess is wrong
	public int guessWord(String guess) {
		int flag = -1;
		if (aPlayer.getWrongGuesses() != 6) {
			if (guess.length() > 1) {
				flag = 1;
			} else if (guess.isEmpty()) {
				flag = 2;
			} else {
				for (int x = 0; x < aPlayer.getGuessedWord().length(); ++x) {
					String letter = Character.toString(aPlayer.getGuessedWord().charAt(x));
					if (guess.equalsIgnoreCase(letter)) {
						flag = 3;
						break;
					} else {
						flag = 4;
					} // else
				} // for
			} // else
		} // if
		return flag;
	} // int guessWord()

	// replace the word read from the file with and underscore
	public void replaceLetters() {
		displayHidden = aPlayer.getGuessedWord();
		for (int x = 0; x < aPlayer.getGuessedWord().length(); x++) {
			char letter = aPlayer.getGuessedWord().charAt(x);
			displayHidden = displayHidden.replace(letter, '*');
		} // for
	} // replace letter

	// method replaces the word which the user has to guess with underscores
	public void changeUnderscoreToLetter(String guessedLetter) {
		for (int x = 0; x < aPlayer.getGuessedWord().length(); x++) {
			if (guessedLetter.charAt(0) == aPlayer.getGuessedWord().charAt(x)) {
				displayHidden = changeChar(displayHidden, guessedLetter.charAt(0), x);
			} // if
		} // for
	} // changeUnderscoreToLetter(String)

	// method changes the word into single characters so guessing a single letter in
	// the word is possible
	public String changeChar(String word, char letter, int idx) {
		char[] chars = word.toCharArray();
		chars[idx] = letter;
		return String.valueOf(chars);
	}// changeChar(String, char, int)

	// method checks to see if they won or they lost the game
	public void winningCondition() {
		if (aPlayer.getGuessedWord().equalsIgnoreCase(hangmanTextArea.getText())) {
			aPlayer.setTotalLosses(aPlayer.getTotalLosses() + 1);
			ImageIcon icon = new ImageIcon("congrats.png");
			JOptionPane.showMessageDialog(frame,
					"  Congratulations, you won the game! \nSelect \"New Game\" to continue playing.", "Game Won",
					JOptionPane.INFORMATION_MESSAGE, icon);
			textAreaGameStatus.setText("  You correctly guessed: " + aPlayer.getGuessedWord().toUpperCase());
			savePlayerGame();
			aPlayer.incrementWins();
			aPlayer.incrementGamesPlayed();
		} else if (aPlayer.getWrongGuesses() == 6) {
			aPlayer.setTotalLosses(aPlayer.getTotalLosses() + 1);
			ImageIcon icon = new ImageIcon("sad.png");
			JOptionPane.showMessageDialog(frame,
					"Unfortunately, You ran out of guesses. Better luck next time. \n                   Start a \"New Game\" to continue playing.",
					"Game End", JOptionPane.PLAIN_MESSAGE, icon);
			aPlayer.incrementLosses();
			aPlayer.incrementGamesPlayed();
		} // else if
	}// winningCondition()

	// method loops through the word and selects a random letter then displays it to
	// user
	public void getHint() {
		Random random = new Random();
		int minIndex = 0;
		int maxIndex = aPlayer.getGuessedWord().length() - 1;
		int wordIndex = random.nextInt((maxIndex - minIndex) + 1) + minIndex;
		for (int x = 0; x < aPlayer.getGuessedWord().length(); x++) {
			char aLetter = aPlayer.getGuessedWord().charAt(wordIndex);
			if (aLetter == aPlayer.getGuessedWord().charAt(x)) {
				displayHidden = changeChar(displayHidden, aLetter, x);
			} // if
		} // for
	}// getHint()

	public void savePlayerGame() {
		aPlayer.setPlayerName(thePlayer.getPlayerName());
		FileOutputStream fileOutput;
		ObjectOutputStream objectOutput;
		try {
			fileOutput = new FileOutputStream("scoreboard.ser");
			objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(aPlayer);
			objectOutput.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error saving the players.", "Player save Error",
					JOptionPane.ERROR_MESSAGE);
		} // catch

	}// savePlayerGame()

	public void loadPlayerGame() {
		aPlayer.setPlayerName(thePlayer.getPlayerName());
		FileInputStream fileOutput;
		ObjectInputStream objectInput;
		Scanner readTheFile = new Scanner("scoreboard.ser");
		if (readTheFile.hasNext()) {
			try {
				fileOutput = new FileInputStream("scoreboard.ser");
				objectInput = new ObjectInputStream(fileOutput);
				aPlayer = (HangmanPlayer) objectInput.readObject();
				objectInput.close();
				fileOutput.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "Error loading the players.", "Player Load Error",
						JOptionPane.ERROR_MESSAGE);
			} // catch
		} // if
	}// savePlayerGame()

	public void scoreboard() {
		scoreboardTextArea.setText(String.format("%-32s%-32s%-30s%-30s\n", "Player Name", "Total Wins", "Total Losses",
				"Total Games Played"));
		scoreboardTextArea.append(String.format("%-32s%-33s%-30s%-29s\n", "--------------------", "-------------------",
				"-------------------", "-----------------------"));
		scoreboardTextArea.append(String.format("%-39s%-45s%-49s%-29s\n", thePlayer.getPlayerName(),
				aPlayer.getTotalWins(), aPlayer.getTotalLosses(), aPlayer.getGamesPlayed()));
	}
} // class GameFrame();
