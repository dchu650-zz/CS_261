import java.util.LinkedList;
/**
*This document class will go through all of the text in the documents and give us the n-grams. The n-grams will be represented by a smaller linkedlist.
*The entire document will be stored as a linkedlist linking each word to the next root.
*@author Darren Chu & Kramer Canfield
*@version 4 May 2013
*/

public class Document

{
	//fields
	private LinkedList<String> totalWords;//A linkedlist that represents all of the words within the document.
	private LinkedList<String> currentNGram;//A linkedlist that represents the words being compared.
	private int n;//Represents the size of the n-gram.
	
	/**
	* The constructor of the Document class. Instantiates both the totalWords linkedlist and the currentNGram linkedlist.
	* The user will set the size of the currentNGram linkedlist by setting n.
	* @param n The number that is used to set the size of how large the currentNGram list of words will be. 
	*/
	public Document(int n)

	{
		this.n = n;//Set the size of n.
		totalWords = new LinkedList<String>();//Instantiate the LinkedList
		currentNGram = new LinkedList<String>();//Instantiate the LinkedList
		this.initializeNGram();
	}

	/**
	* A method that creates and inserts the strings from the word document into the linkedlist with the size of n. 
	*/
	public void initializeNGram()
	{
		for(int i = 0; i<n; i++)//Sets the size of the currentNGram
		{
			currentNGram.add(totalWords.remove());//Add the words from the text document into the currentNGram linkedlist
		}
	}

	/**
	* A method that cycles through the totalWords by removing the first word in the linkedlist
	* and adding the next word in the text document to the end of the linkedlist.
	* @return false if there are no longer any words within the totalWords.
	*/
	public boolean nextNGram()

	{
		if(totalWords.size() != 0)
		{
			currentNGram.remove();//Remove the first word from the currentNGram
			currentNGram.add(totalWords.remove());//Add the next word from the document at the end of the linkedlist.
			return true;
		}
		else
			return false;
	}

}
