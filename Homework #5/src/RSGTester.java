import java.util.ArrayList;


public class RSGTester
{

	/**
	 * @param args Array of args: input file, number of sentences to generate, name of start variable, name of output file
	 */
	public static void main(String[] args)
	{
		// TODO make the rsg take in the string args as a parameter
		RandomSentenceGenerator rsg = new RandomSentenceGenerator(args);
		//
		Grammar gram = rsg.getGrammar();
		ArrayList<Variable> gramList = gram.getVarList();

		//Test whether or not the variable and rules are all linked up correctly.
		Variable v = gram.getVariable("verbclause");
		System.out.println("VAR NAME: "+v.getName());
		v.getRule().printSymbols();
		System.out.println("VAR RSIDE NAME: "+v.getRule().get(0).getName());
		System.out.println("VAR RSIDE RSIDE NAME: "+v.getRule().get(0).getRule().get(0).getName());
		//		//get v, get it's rule, get it's first sym, get that sym's rule, get it's first sym, get that sym's name 


		System.out.println(rsg.generateRandomSentence());
	}

}
