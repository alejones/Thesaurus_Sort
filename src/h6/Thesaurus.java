//Alex Jones
//APC 390
//Homework 6
//11-29-20

//Class that holds Thesaurus line objects to create the thesaurus

package h6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Thesaurus {
	public static final int thesaurusSize = 100; // Sets the maximum size of the thesaurus
	private int remainingLines = thesaurusSize; // Value to keep track of how many lines are remaining in thesaurus
	private final int synonymSize = 4; // Maximum number of synonyms to send to ThesaurusLine Object
	
	// arrayList to hold the lines of the thesaurus
	private List<ThesaurusLine> lines = new ArrayList<ThesaurusLine>(); 
	

	// Read saved thesaurus from file
	// Repeated words(keys) will not be added twice and new values will now overwrite previous values
	// A previous Thesaurus will be overwritten
	// The thesaurus is sorted after all items have been added
	public void loadData(String fileNameString) {
		File file;
		Scanner scanner = null;

		try {
			file = new File(fileNameString);
			scanner = new Scanner(file);

			// After reading a new file, clear previous dictionary and reset remaining lines
			lines.clear();
			remainingLines = thesaurusSize;
			
			// While file has next line add it to thesaurus
			while (scanner.hasNextLine()) {
				if (remainingLines <= 0) {
					scanner.close();
					throw new ThesaurusOverflowException();
				}
				
				String key = null;
				
				// splits single string into individual elements on commas
				String[] splitLine = scanner.nextLine().strip().split(",", 0);

				// Get first word in splitLine[0] 
				String[] firstInput = splitLine[0].split("\\s+", 2);
				
				try {
					// Iterate through each element in  splitLine
					for (int i = 0; i < splitLine.length; i++) {
						// first word in firstInput is key
						if (i == 0) {
							// Make key lower case to simplify comparisons
							key = firstInput[0].toLowerCase();
							
							//if key is null or empty, do not add it to dictionary and jump to next line
							if (key == null || key == "") {
								break;
							}
							
							//add key to dictionary with empty ThesaurusLine
							//Check to make sure there is a second element, add it if it exists
							if (firstInput.length > 1) {
								lines.add(new ThesaurusLine(new Word(firstInput[0]), new Phrase(firstInput[1])));
								remainingLines--;
							}										
							
							// If there is not a synonym following the key, throw an error
							else
								throw new NoSynonymsException(key);
			
						// remaining words are synonyms
						} else {
							//Thesaurus only takes 4 synonyms, ignore any extra
							if ( i < synonymSize)
								//only adds synonyms to last element in array
								lines.get(lines.size() - 1).addSynonyms(new Phrase(splitLine[i]));
							else
								break;
							
						}//end of else
					}//end of for loop

				}catch(NoSynonymsException s) { }
			} // end of while loop
			scanner.close();

		} catch (ThesaurusOverflowException t) {
		} catch (FileNotFoundException f) {
			System.out.println("The file " + fileNameString + " was not found");
			f.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Complete insertion sort after everything has been added
			insertionSort();
		}
	}

	// Save the current thesaurus to file	
	public void saveThesaurus(String fileName) {
		try {
			PrintWriter out = new PrintWriter(fileName);

			for(ThesaurusLine line  : lines) {
				// Capitalize first letter of key, add two tabs, print synonyms
				out.println(line.getWord().substring(0,1).toUpperCase() + line.getWord().substring(1).toLowerCase() + 
						"\t\t" + 
						line.getSynonyms());
			}
			
			out.close();
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong");
		}finally { }
	}

	// Add a word with synonyms to thesaurus
	// The thesaurus is sorted after the new word is added
	public void addLine(Word wordToAddToDictionary, Synonym... synonyms) {
		try {
			if (remainingLines <= 0)
				throw new ThesaurusOverflowException();

			//set key and new ThesaurusLine object with inputed synonyms
			lines.add(new ThesaurusLine(wordToAddToDictionary, synonyms));
			remainingLines--;
			//only do insertion sort if word was added with no error
			insertionSort();
		} catch (ThesaurusOverflowException t) { 
		} catch( Exception e ) {
			e.printStackTrace();
			System.out.println("Something went wrong");
		} finally { }

		
	}

	// remove key and value from thesaurus and increment lines remaining
	public void removeLine(Word wordToRemove) {
		
		for(int i = 0; i < lines.size(); i++) {
			if (lines.get(i).getWord().toString().equals(wordToRemove.toString())) {
				lines.remove(i);
				remainingLines++;
			}	
		}
	}


	// Returns a set of synonyms for given word
	// Searches for word using binary search
    public Set<Synonym> suggest(Word suggestWordsFor) {
    
	    int first = 0, last = lines.size() - 1, middle;
	    String middleWord;
	    
	    while( first <= last) {
	    	middle = (first + last) / 2;
	    	middleWord = lines.get(middle).getWord().toString();
	    	
	    	// Check to see if middle word is suggestWordsFor
	    	if( middleWord.equals(suggestWordsFor.toString()))
	    		// Return values if found
	    		return(lines.get(middle).getSynonyms());
	    	// If middle word is greater than suggestWordsFor, set last word to middle - 1
	    	else if (middleWord.compareToIgnoreCase(suggestWordsFor.toString()) > 0)
	    		last = middle - 1;
	    	// Else set first word to middle word + 1
	    	else
	    		first = middle + 1;
	    }
	    
    	// Return empty set if suggestWordFor is not found it thesaurus
	   Set<Synonym> s = new HashSet<Synonym>(2);
	   s.add(new Phrase("No synonyms were found"));
	   
	   return( s );
    }
    
    // Sorts the thesaurus using insertion sort
    private void insertionSort() {
    	final int size = lines.size();
    	ThesaurusLine temp;
    	int location;
    	
    	for( int i = 1; i < size; i++) {
    		if(lines.get(i).getWord().toString().compareToIgnoreCase(lines.get(i-1).getWord().toString()) < 0) {
    			temp = lines.get(i);
    			location = i;
    			do {
    				lines.set(location, lines.get(location - 1));
    				location--;
    			} while( location > 0 && lines.get(location - 1).getWord().toString().compareToIgnoreCase(temp.getWord().toString()) > 0);
    			lines.set(location, temp);
    		} // End of if statement
    	} // End of for loop
    } // End of insertionSort
} // End of class

//The value 0 if the argument is a string lexicographically equal to this string; 
//a value less than 0 if the argument is a string lexicographically greater than this string; 
//and a value greater than 0 if the argument is a string lexicographically less than this string.
