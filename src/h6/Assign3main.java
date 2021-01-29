//Alex Jones
//APC 390
//Homework 5
//11-15-20

//Creates a thesaurus with user inputed data

//This iteration implements error handling with custom exception classes

package h6;

import javax.swing.JOptionPane;

public class Assign3main {
	static final int LOAD = 1, SAVE = 2, ADD_LINE = 3, REMOVE_LINE = 4, SUGGEST_WORD = 5, QUIT = 6;
	static final String welcomeMessage = "This program implements an interactive thesaurus. It allows you to add\n"
			+ "new lines to a thesaurus, remove lines from the thesaurus.\n"
			+ "In addition, you can load an existing thesaurus file, save the current\n" + "thesaurus to a file.\n";
	static final String promptMessage = "What would you like to do?  Please enter the number next to your selection\n"
			+ LOAD + ": Load a thesaurus from a file\n" + SAVE + ": Save current thesaurus to a file\n" + ADD_LINE
			+ ": Add a line to the current thesaurus\n" + REMOVE_LINE + ": Remove a line from the current thesaurus\n"
			+ SUGGEST_WORD + ": Suggest a synonym for a word\n" + +QUIT + ": Quit\n";

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, welcomeMessage);
		Thesaurus mythesaurus = new Thesaurus();
		
		int userSelection = 0;
		while (userSelection != QUIT) {
			String input = JOptionPane.showInputDialog(promptMessage);
			try {
				userSelection = Integer.parseInt(input);

			} catch (NumberFormatException e) {
				if (input == null) {
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Input");
					continue;
				}
			} finally {
			}
			processSelection(mythesaurus, userSelection);

		}
	}

	private static void processSelection(Thesaurus mythesaurus, int userSelection) {
		switch (userSelection) {
		case LOAD:
			// Remember that you can have text files in your Eclipse Project
			// If this file is in src/a1, you could read the synonyms.txt file in your a1
			// package
			// using the "fileName" src/a1/synonyms.txt
			String fileName = JOptionPane.showInputDialog("Please enter the name of the thesaurus file to load");
			mythesaurus.loadData(fileName);
			break;
		case SAVE:
			fileName = JOptionPane.showInputDialog("Please enter the name of the thesaurus file to save");
			mythesaurus.saveThesaurus(fileName);
			break;
		case ADD_LINE:
			Word wordToAddtoThesaurus = new Word(
					JOptionPane.showInputDialog("Please enter the word you want to add to thesaurus"));
			Synonym synonym1 = new Phrase(
					JOptionPane.showInputDialog("Please enter the first synonym for " + wordToAddtoThesaurus));
			Synonym synonym2 = new Phrase(
					JOptionPane.showInputDialog("Please enter the second synonym for " + wordToAddtoThesaurus));
			Synonym synonym3 = new Phrase(
					JOptionPane.showInputDialog("Please enter the third synonym for " + wordToAddtoThesaurus));
			Synonym synonym4 = new Phrase(
					JOptionPane.showInputDialog("Please enter the fourth synonym for " + wordToAddtoThesaurus));
			mythesaurus.addLine(wordToAddtoThesaurus, synonym1, synonym2, synonym3, synonym4);
			break;
		case REMOVE_LINE:
			Word wordToRemoveFromThesaurus = new Word(
					JOptionPane.showInputDialog("Please enter the word whose line you want to remove from thesaurus"));
			mythesaurus.removeLine(wordToRemoveFromThesaurus);
			break;
		case SUGGEST_WORD: 
			Word suggestWordsFor = new Word(
					JOptionPane.showInputDialog("Please enter the word for which you want a synonym."));
			JOptionPane.showMessageDialog(null, mythesaurus.suggest(suggestWordsFor));
			break;
		case QUIT:
			JOptionPane.showMessageDialog(null, "Have a nice day");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Invalid Input");
		}
	}

}
