//Alex Jones
//APC 390
//Homework 5
//11-15-20

//Class to handle when a word is being input to the thesaurus but does not contain any synonyms

package h6;

public class NoSynonymsException extends Exception{

	private static final long serialVersionUID = 1;
	
	public NoSynonymsException() {
		System.out.println("input file contained a line with no synonyms");
	}
	
	public NoSynonymsException(String word) {
		if( word.equals(" ") || word.equals(""))
			System.out.println("input file contained an empty line");
		else
			System.out.println(word + " did not have any synonyms");
	}
}