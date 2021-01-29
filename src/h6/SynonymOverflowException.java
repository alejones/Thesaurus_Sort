package h6;

public class SynonymOverflowException extends Exception {

	private static final long serialVersionUID = 1;
	
	public SynonymOverflowException() {
		System.out.println("To many synonyms were given");
	}
	
	public SynonymOverflowException(String word) {
		System.out.println(word + " has to many synonyms");
	}


}
