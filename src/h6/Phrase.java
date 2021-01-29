//Alex Jones
//APC 390
//Homework 5
//11-15-20

//Class lets the thesaurus have synonyms with multiple words, as long as they are separated by commas

package h6;

public class Phrase extends Word {
	private Word[] words;

	// Constructor to accept a string with one or more words, separated by commas
	// TODO should be changed to only accept an array of word objects
	public Phrase(String inputString) {
		super(inputString);

		String[] temp; // TODO bad variable name

		// Split inputString on all whitespace
		temp = inputString.split("//s+", 0);

		// Create array to contain all words in the inputString
		words = new Word[temp.length];

		// Add each word to array as a Word object
		for (int i = 0; i < temp.length; i++) {
			words[i] = new Word(temp[i]);
		}
	}

	public String toString() {
		StringBuilder output = new StringBuilder();

		for (Word word : words)
			if (word != null)
				output.append(word + " ");

		return (output.toString().strip());
	}

}
