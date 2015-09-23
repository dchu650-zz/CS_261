import java.util.ArrayList;
import java.util.LinkedList;
/**
 * This document class will go through all of the text in the document and give us the n-grams. The n-grams will be represented by an ArrayList of integers because the hashcodes of the n-grams will be faster to compare and sort.        
 * The entire document will be stored as a java LinkedList of Strings with one word for every String.
 * @author Kramer Canfield and Darren Chu
 * @version 4 May 2013
 */

public class Document
{
	//fields
	private LinkedList<String> totalWords;//A linkedlist that represents all of the words within the document.
	private LinkedList<String> currentNGram;//A linkedlist that represents the words currently being compared.
	private int totalWordsPointer;//a pointer to the current location in the totalWords LinkedList
	private int n;//Represents the size of the n-gram.
	private String fileName;//the name of the file--useful for printing later
	
	private ArrayList<Integer> nGramList;//the collection of all n-grams for the document, the n-grams have been converted to integers using the java hashcode method.
	//This makes searching and sorting much faster as it is faster to compare integers.
	//Since we can sort this list of n-grams, we can do a binary search to cut down the run time of the program. NOTE: problem with binary search algorithm, see README for details
	
	/**
	* The constructor of the Document class that instantiates the instance variables.
	* @param n The number that is used to set the size of n for the n-gram, where n is the number of words. 
	*/
	public Document(int n)
	{
		this.n = n;//set the size of n.
		totalWords = new LinkedList<String>();//Instantiate the LinkedList
		currentNGram = new LinkedList<String>();//Instantiate the LinkedList
		totalWordsPointer = 0;//start at the beginning
		nGramList = new ArrayList<Integer>();
	}



	/**
	 * This method will take the totalWords LinkedList and generate the ArrayList that contains each ngram as a hashcode.
	 */
	public void generateNGramList()
	{
		while(totalWords.size() >= n)
		{
			String ngram = "";
			for(int j=0; j<n; j++)
			{
				ngram += totalWords.remove(0);//get first word from totalWords
				totalWordsPointer++;
			}
			nGramList.add(ngram.hashCode());//hash the ngram string and add it to the arraylist of integers
		}
		//quickSortNGramList();//sort the finished list//this method call is commented out because we could not get our binary search to work and so this method call needlessly slows down the program
		
	}
	
	
	
	/**
	 * This method adds the specified String to the LinkedList of the entire text of the document. 
	 * @param word The String to add to the LinkedList of all words in a document.
	 */
	public void addToTotalWords(String word)
	{
		totalWords.add(word);
	}
	
	
	/* 
	 * SORTING STUFF--USE QUICKSORT ALGORITHM TO SORT THE LIST OF N-GRAMS
	 * These sorting methods were modified from the code posted on Moodle from the lecture on sorting algorithms.
	 */
	
	/**
	 * A launcher method.
	 */
	public void quickSortNGramList()
	{
		quickSortNGramList(0,nGramList.size()-1);
	}
	
	/**
	 * This recursive method partitions the list and uses quicksort to sort the resulting lists. 
	 * @param first
	 * @param last
	 */
	private void quickSortNGramList(int first, int last)
	{
		if(first < last)
		{
			int pivot = partition(first, last);
			quickSortNGramList(first, pivot-1);
			quickSortNGramList(pivot+1, last);
		}
	}
	
	/**
	 * Partition
	 * @param first start of list
	 * @param last end of list
	 * @return final position of the pivot 
	 */
	private int partition(int first, int last)
	{
		int pivot = nGramList.get(0);
		//pick some pivot
		//move the pivot to be at the first spot		
		//System.out.println("partition around: "+pivot);

		int up = first;
		int down = last;
		do
		{
			while(up < last &&  pivot >= nGramList.get(up))
				up++;
			
			while(pivot < (nGramList.get(down)))
				down--;
			
			if(up < down)
			{
				//this is a swap
				int tmp = nGramList.get(first);
				nGramList.set(first,nGramList.get(down)); 
				nGramList.set(down, tmp);
			}
			
		} while(up < down);
		
		
		//this is a swap
		int tmp = nGramList.get(first);
		nGramList.set(first,nGramList.get(down)); 
		nGramList.set(down, tmp);
		
		return down;
	}
	
	/*
	 * The get and set methods.
	 */
	
	/**
	 * This get method returns the text of the entire document as a LinkedList of String objects.
	 * @return totalWords The text of the entire document as a LinkedList of String objects.
	 */
	public LinkedList<String> getTotalWords()
	{
		return totalWords;
	}

	/**
	 * This get method returns the n-gram that is currently being looked at.
	 * @return currentNGram The n-gram that is currently being looked at.
	 */
	public LinkedList<String> getCurrentNGram()
	{
		return currentNGram;
	}
	
	/**
	 * This methods returns the ArrayList of all n-grams in the document.
	 * @return nGramList The ArrayList of all n-grams in the document.
	 */
	public ArrayList<Integer> getNGramList()
	{
		return nGramList;
	}

	/**
	 * This method sets the name of the file to be the specified String.
	 * @param name The String that is the name of the document.
	 */
	public void setFileName(String name)
	{
		this.fileName = name;
	}

	/**
	 * This method gets and returns the name of the file as a String.
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
}
