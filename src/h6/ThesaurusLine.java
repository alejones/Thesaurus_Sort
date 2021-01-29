//Alex Jones
//APC 390
//Homework 6
//11-29-20

//Class holds single word and 4 synonyms 

package h6;

import java.util.HashSet;
import java.util.Set;

public class ThesaurusLine {

	private Word key; //not used now that Thesaurus uses HashMap
	private Set<Synonym> values = new HashSet<Synonym>();
	private final int maxSynonyms = 4; // Maximum number of synonyms the class can hold

	// Default constructor
	public ThesaurusLine() {

	}

	// Constructor for key and multiple synonyms objects
	public ThesaurusLine(Word word, Synonym... synonyms) {
		key = word;

		addSynonyms(synonyms);
	}
	
	//Constructor that only takes in synonyms 
	public ThesaurusLine( Synonym... synonyms) {
		addSynonyms(synonyms);
		
	}

	// Add n synonym at a time
	public void addSynonyms (Synonym... inputSynonym) {
		// If 4 synonyms are already stored, do nothing
		if(values.size() < maxSynonyms)
			for(Synonym s : inputSynonym)
				values.add(s);
	}
	
	// Returns values in one string, separated by spaces
	public Set<Synonym> getSynonyms() {
		return (values);

	}

	public String getWord() {
		return (key.toString());
	}

	// Returns key and values. Key is separated from values by tab, values separated
	// by spaces
	public String toString() {
		StringBuilder output = new StringBuilder();

		//removed, HashMap in Thesaurus Class now holds Keys
		// places Key first with two tabs
		//output.append(key + "		");

		// Adds all values to string
		for (Synonym syn : values)
			output.append(syn + ", ");

		// removes last comma and space
		if (output.length() > 2)
			output.setLength(output.length() - 2);

		return (output.toString());

	}
}
