import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * An class that will search for all anagrams within a text file
 * @author Darren Chu
 *
 */
public class AnagramFinder 
{
	
	private List<String> theDictionary;//dictionary field	
	
	public AnagramFinder(List<String> dictionary)
	{
		theDictionary = dictionary;
	}
	/**
	 * This constructor should take the given dictionary (a list of words) and use that dictionary in finding anagrams.
	 * @param phrase The phrase that we want to call the anagram program on
	 * @param max The maximum about of checks on the anagram program
	 */
	public void print(String phrase, int max)
	{
		LetterSet thePhrase = new LetterSet(phrase); //we choose a letter from the String phrase
		long start = System.currentTimeMillis();
		findWords(thePhrase, new ArrayList<String>(),0); //the initial method call
		long end = System.currentTimeMillis();
		long endTime = end-start;
		System.out.println("search completed in "+endTime/1000.0+" seconds");
	}

	/**
	 * This method prints to System.out all of the combinations of words from the finder's dictionary 
	 * that are anagraphs of the given phrase and include at most max words 
	 * (or unlimited words if max is 0).
	 * @param thePhrase The possible letter sets
	 * @param theWords The list of words in the anagram
	 * @param dictNum The counter for which dictionary entry
	 */
	public void findWords(LetterSet thePhrase, ArrayList<String> theWords, int dictNum)
	{				
		if(thePhrase.isEmpty()) //no more letters to use base case
		{
			System.out.println(theWords); //we have found a solution
			return;
		}
		if(dictNum >= theDictionary.size()) //another base case in case the 
			return; //return, cause we're done
		for(int i = 0; i<theDictionary.size(); i++)
		{
			String word = theDictionary.get(i); //get the word we're using		
			LetterSet wordLetters = new LetterSet(word); //make it a letter set
			if(thePhrase.sub(wordLetters) != null) //checks if the "word" is a part of the solution
			{
				theWords.add(word); //add the word to our solution
				findWords(thePhrase.sub(wordLetters), theWords, dictNum++); //recurse with our new, smaller set of letters and current solution
				theWords.remove(word); //since we're done exploring with that word, remove it in order to try the next
			}
		}
	}
	
}


