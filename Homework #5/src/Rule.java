import java.util.ArrayList;
import java.util.Random;


public class Rule extends ArrayList<Symbol>
{	
	private String name;

	/**
	 * Default constructor for Rule
	 */
	public Rule()
	{
		//a rule is either [variable(s) + term(s)] OR term(s) OR variable(s)
	}

	/**
	 * Constructor that takes the name of the leftside variable
	 * @param varName name of variable
	 */
	public Rule(String varName)
	{
		name = varName;
	}
	
	/**
	 * Gets the name of this rule (its left side variable name)
	 * @return the name of this rule
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets a random symbol from this Rule (for terminals)
	 * @return Returns a random symbol from this rule
	 */
	public Symbol getRandomSymbol()
	{	
		Random rand = new Random();
		int index = rand.nextInt(this.size());
		
		return this.get(index);
	}

	/**
	 * Tells whether or not this Rule has and variables
	 * @return true if this Rule has one or more variables
	 */
	public boolean hasVariable()
	{
		for(Symbol sym : this)
		{
			if(sym instanceof Variable)
				return true;
		}
		return false;
	}
	
	/**
	 * Prints all the symbols in this rule on a line
	 */
 	public void printSymbols()
	{
		int i = 1;
		for(Symbol sym : this)
		{
			System.out.println(i+".--"+sym.getName()+"--");
			i++;
		}
		System.out.println();
	}
}
