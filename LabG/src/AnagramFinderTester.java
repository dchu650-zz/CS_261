import java.io.File;
import java.util.*;

/**
 * A class that provides an interface for using the AnagramFinder
 * @author Joel
 * @version Sp 2013
 */
public class AnagramFinderTester
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the anagram finder!");
		System.out.print("First, enter the name of a dictionary file to use: ");
		Scanner input = null;
		List<String> dictionary = new ArrayList<String>();
		try{
			input = new Scanner(new File(sc.nextLine()));
			while (input.hasNextLine())
				dictionary.add(input.nextLine());
			input.close();
		}catch(Exception e){
			System.out.println("Error reading from file: "+e);
			input.close(); //cleanup
			System.exit(1); //and fail (we need a dictionary)
		}

		AnagramFinder finder = new AnagramFinder(Collections.unmodifiableList(dictionary));
		
		System.out.println();

		boolean finding = true;
		while(finding)
		{
			System.out.print("Enter phrase to anagram-ify (return to quit): ");
			String phrase = sc.nextLine();
			if(phrase.length() != 0)
			{
				System.out.print("Max words to include (0 for no max!): ");
				int max = sc.nextInt();
				sc.nextLine(); //parse the next line (since we read an int)
				finder.print(phrase,max);
				System.out.println();
			}
			else
				finding = false;
		}
		sc.close();
	}
}
