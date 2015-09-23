import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is a modified version of the supplied DocumentReader class.
 * @author Kramer Canfield and Darren Chu
 * @version 11 May 2013
 */
public class SpecialDocumentReader
{
	/**
	 * This method uses the supplies code and especially the String[] of words to build Document objects and add them to the graph, which then compares all of the documents. 
	 * @param directoryPath The path to the folder containing the documents to look at.
	 * @param n the size of the n-gram (the number of words).
	 * @return graph The newly created Graph object made from the documents in the specified directory.
	 */
	public static Graph specialReadFiles(String directoryPath, int n)
	{
		
		File dir = new File(directoryPath); //a file to represent the directory
		if(!dir.isDirectory())
			throw new IllegalArgumentException("Specify a directory, not a file!");

		String[] files = dir.list(); //get list of files in the directory
		
		Graph graph = new Graph();
		
		for(int fi=0; fi<files.length; fi++) //go through the list
		{
			File f = new File(directoryPath+"/"+files[fi]); //make a file object representing that file in the directory
			try{
				if(! f.getName().equals(".DS_Store"))
				{
				Document newDocument = new Document(n);
				//System.out.println("*** Processing "+f.getPath()+" ***");
				//Scanner doesn't work with these text files, so need to use a lower-level class BufferedReader.
				//It works similarly to the Scanner though
				BufferedReader reader = new BufferedReader(new FileReader(f)); 
				String text = ""; //the whole text of the document
				String line = reader.readLine(); //the line we're currently reading
				while(line != null)
				{
					text += line.toLowerCase()+" ";//append the (lowercase) version of the line to the text
					line = reader.readLine();
				}
				String[] words = text.split("\\W+"); //split into an array of words (breaking on any number of non-word letters)
				//this array becomes very useful for later
				
				//FOR DEBUGGING
				//System.out.println("DOC NAME: "+f.getName());
				//System.out.println("WORDS: " + words);
				
				
				for(int i=0; i<words.length; i++)
				{	
					newDocument.addToTotalWords(words[i]);//for each of the words in the entire document, add the word to the LinkedList of words that represents the text
					
					//FOR DEBUGGING
					//System.out.println("IN LOOP");
					//System.out.println("words["+i+"]: "+words[i]);
					//System.out.println("LENGTH OF ARRAY " +words.length);
				}
				
				newDocument.setFileName(f.getName());//set the name of the file in the document object so we have it later for printing results
				newDocument.generateNGramList();//generate the list of n-grams
				
				//FOR DEBUGGING
				//System.out.println("the total words before pushing onto stack: "+newDocument.getTotalWords());
				//System.out.println("total words of "+newDocument.getFileName()+": "+newDocument.getTotalWords());
				
				graph.getTheStack().push(newDocument);//add the finished document object to the stack of documents in the graph
				
				reader.close();
				}
			}
			catch(IOException ioe) //in case of any problems
			{
				System.out.println("Error reading file: "+files[fi]);
			}
		}

		return graph;//return the graph object so we can call other methods on it
		
		
		
		
	}
}
