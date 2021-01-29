//Alex Jones
//APC 390
//Homework 5
//11-15-20

//class holds single word

package h6;

//hashcode is not overwritten, use toString if using as key in hashmap
// TODO override hashcode and equals

public class Word extends Synonym {
	private String word;

	public Word() {
		word = "";
	}

	public Word(String inputWord) {
		// Strips any trailing or preceding whitespace off inputed word and saves it
		try {
		word = inputWord.strip();
		
		}catch(Exception e) {
			
		}finally {}
		
	}

	public String toString() {
		return (word);
	}
}
