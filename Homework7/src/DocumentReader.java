import java.io.*;
import java.util.Arrays;

/**
 * A class with some example code for reading a directory of documents for the Plagarism Catcher
 * @author Joel
 */
public class DocumentReader
{
	
	/**
	 * An example method for reading in a set of documents, parsing them into n-grams
	 * @param directoryPath the path to the directory (i.e., small_set)
	 * @param n the size of each n-gram (number of words to consider as a bunch)
	 */
	public static void readFiles(String directoryPath, int n)
	{
		File dir = new File(directoryPath); //a file to represent the directory
		if(!dir.isDirectory())
			throw new IllegalArgumentException("Specify a directory, not a file!");

		String[] files = dir.list(); //get list of files in the directory
		for(int fi=0; fi<files.length; fi++) //go through the list
		{
			File f = new File(directoryPath+"/"+files[fi]); //make a file object representing that file in the directory
			try{
				System.out.println("*** Processing "+f.getPath()+" ***");
				//Scanner doesn't work with these text files, so need to use a lower-level class BufferedReader.
				//It works similarly to the Scanner though
				BufferedReader reader = new BufferedReader(new FileReader(f)); 
				String text = ""; //the whole text of the document
				String line = reader.readLine(); //the line we're currently reading
				while(line != null)
				{
					text += line.toLowerCase()+" "; //append the (lowercase) version of the line to the text
					line = reader.readLine();
				}
				String[] words = text.split("\\W+"); //split into an array of words (breaking on any number of non-word letters)

				for(int i=0; i<words.length-(n-1); i++)
				{
					String[] ngram = Arrays.copyOfRange(words, i, i+n); //load a chunk of n words into a local array
				
					//print out the array (for example)
					for(int j=0; j<ngram.length; j++)
						System.out.print(ngram[j]+ " ");
					System.out.println();
					
					//your other processing would likely go here!
				}
				
				reader.close();
			}
			catch(IOException ioe) //in case of any problems
			{
				System.out.println("Error reading file: "+files[fi]);
			}
		}


	}

	//demo of the method
	public static void main(String[] args)
	{
		readFiles("large_set", 6);
	}
}
