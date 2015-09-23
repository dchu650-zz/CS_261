import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * 
 * This class is designed to explore some of the characteristics of the List interface.
 * 
 * @author Joel Ross (adapted from Chuck Hommel)
 * @version Sp13
 */

public class SecretListTester 
{
	/**
	 * @param args not used
	 */
	public static void main(String[] args) 
	{
		// Make a tester. All the action is in the constructor
		new SecretListTester();
	}    

	/**
	 * Makes the tester
	 */
	public SecretListTester ()
	{
		/* Pick a test to run */
		String[] testOptions = {
				"1. Add items at the end of the list",
				"2. Adding items at the beginning of the list",
				"3. Traversing the list using the get() method",
				"4. Traversing the list using an Iterator",
				"5. Accessing arbitrary items in the list",
				"6. Removing items from the front of the list",
				"7. Removing arbitrary items from the list",
				"8. Quit",
		}; //array initialization shorthand. Example: int[] nums = {1,2,3,4};
		

		int choice;  // user choice
		do  // keep asking until user wants to quit
		{
			String result = (String)JOptionPane.showInputDialog (null, //Check the JOptionPane documentation for how this works
				"Select a test to run on the list:", "CS261 List Tester", 
				JOptionPane.QUESTION_MESSAGE, null, 
				testOptions, testOptions[0]);
			if(result != null) //convert String choosen into an int
				choice = Integer.parseInt(result.substring(0, 1)); //These should be familiar...
			else
				choice = -1;
			switch (choice) //effectively lets us write a giant if/then block where we're "switching" on the value of choice. See pg 609
			{
				case 1: addEnd();
					break; //break says to stop doing the switch; e.g. don't do the following cases
				case 2: addBeginning();
					break;
			}
		} while (choice > 0 && choice != 8); //7 would be the "Quit" option
	}


	/**
	 * Tests adding Strings to the end of the list, and prints the timing results
	 */
	private void addEnd()
	{
		System.out.println("*****STARTING ADDING TO END TESTS*****"); //done with a test batch

		
		//declare lists
		List<String> list; //declare the list we'll be testing with
		
		long startTime; //time clock started
		long stopTime; //time clock stopped
		long runTime; //total running time across ties
		int n = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of items to test"));
		int tries = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of repetitions to perform"));
		
		System.out.println("Adding " + n + " items to end of List Type 1...");
		runTime = 0; //reset the counter
		for(int i=0; i<tries; i++)
		{
			list = new SecretList1<String>(); //try secret 1!
			startTime = System.currentTimeMillis();  // start test
			for (int j=0; j<n; j++)
			{
				list.add("CS261");
			}
			stopTime = System.currentTimeMillis();
			runTime += (stopTime-startTime); //add the elapsed time to the running total
		}
		System.out.println("Average time per test: "  + (runTime/(double)tries) + " milliseconds.");
		System.out.println("Average time per operation: " + (runTime/(double)(tries*n)) + " milliseconds");
		System.out.println("---");
		

		System.out.println("Adding " + n + " items to end of List Type 2...");
		runTime = 0; //reset the counter
		for(int i=0; i<tries; i++)
		{
			list = new SecretList2<String>(); //try secret 2!
			startTime = System.currentTimeMillis();  // start test
			for (int j=0; j<n; j++)
			{
				list.add("CS261");
			}
			stopTime = System.currentTimeMillis();
			runTime += (stopTime-startTime); //add the elapsed time to the running total
		}
		System.out.println("Average time per test: "  + (runTime/(double)tries) + " milliseconds.");
		System.out.println("Average time per operation: " + (runTime/(double)(tries*n)) + " milliseconds");
		System.out.println("---");

		
		System.out.println("Adding " + n + " items to end of List Type 3...");
		runTime = 0; //reset the counter
		for(int i=0; i<tries; i++)
		{
			list = new SecretList3<String>(); //try secret 3!
			startTime = System.currentTimeMillis();  // start test
			for (int j=0; j<n; j++)
			{
				list.add("CS261");
			}
			stopTime = System.currentTimeMillis();
			runTime += (stopTime-startTime); //add the elapsed time to the running total
		}
		System.out.println("Average time per test: "  + (runTime/(double)tries) + " milliseconds.");
		System.out.println("Average time per operation: " + (runTime/(double)(tries*n)) + " milliseconds");
		System.out.println("---");
	}

	
	/**
	 * Tests adding Strings to the beginning of the list, and prints the timing results
	 */
	private void addBeginning()
	{
		//TODO Fill me in!
	}
}


