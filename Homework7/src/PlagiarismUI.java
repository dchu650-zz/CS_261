import java.util.Scanner;
/**
 * This is a command-line user interface that was created so it would be nicer for the user to interact with the program.
 * This class uses a java.util.Scanner on System.in to get input from the user. Simply follow the prompts.
 * 
 * @author Kramer Canfield and Darren Chu
 * @version 13 May 2013
 */
public class PlagiarismUI
{
	//fields
	private Scanner sc;//a scanner to get input from the user
	
	/**
	 * This is the constructor that instantiates the scanner and calls the run() method.
	 */
	public PlagiarismUI()
	{
		sc = new Scanner(System.in);//want to get command-line input
		run();//start the program
	}
	
	/**
	 * This is the run method that gets input from the user and starts the program running. The Graph object is instantiated and the necessary methods are called. At the end of the method, the results are printed.
	 */
	public void run()
	{
		System.out.println("Welcome to the Plagiarism Catcher. \n");//have a welcome message
		
		//get input from the user so we can know how to run the program
		System.out.println("Specify the directory: small_set, medium_set, or large_set");
		System.out.print(">");
		String directory = sc.nextLine();

		System.out.println("\nSpecify the size of n (the number of words for each n-gram)");
		System.out.print(">");
		int n = sc.nextInt();
		
		System.out.println("\nSpecify the number of results printed.\n    Specify 0 to print all results.");
		System.out.print(">");
		int numResultsPrinted = sc.nextInt();
		
		
		System.out.println("\nSpecify the number of common n-grams between two documents necessary for the pair of documents to be added to the results.\n    Specify 0 to consider any number of connections greater than 0.");
		System.out.print(">");
		int sharedNGrams = sc.nextInt();
		
		sc.close();//close the scanner as always
		
		//let the user know what's happening so they don't think the program stopped
		System.out.println("Program running...");
		
		//start the program using the user-specified parameters
		Graph graph = SpecialDocumentReader.specialReadFiles(directory, n);    
		graph.setSharedNGrams(sharedNGrams);
		System.out.println("\nComparing all documents...");//let the user know what's happening so they don't think the program stopped
		graph.compareAllDocs();
		System.out.println("\nOrganizing results...");//let the user know what's happening so they don't think the program stopped
		graph.sortEdgeList();
		System.out.println("\n\n**********\nFinal Results: These documents have been flagged for possible plagiarism. \nPlease notify the instructor!\n**********\n");
		graph.printWeightList(numResultsPrinted);
		System.out.println("\nProgram done. \n");
		}
	

}
