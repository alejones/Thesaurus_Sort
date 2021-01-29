//Alex Jones
//APC 390
//Homework 5
//11-15-20

//Exception Class to handle when a Thesaurus has to many words input into it

package h6;

public class ThesaurusOverflowException extends Exception {
	
	private static final long serialVersionUID = 2;


	public ThesaurusOverflowException() {
		System.out.println("The thesurus is full");
	}
}
