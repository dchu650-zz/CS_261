import java.util.ArrayList;
import java.util.Random;


public class Variable extends Symbol
{	
	/**
	 * Default constructor for Variable
	 */
	public Variable()
	{
		this("variable");
	}
	
	/**
	 * Constructor for variable that names it
	 * @param name name of this variable object
	 */
	public Variable(String name)
	{
		this(name, null);
	}
	
	/**
	 * Constructor for Rule that provides the Variable for the left-hand side of this rule
	 * @param leftSide the Variable on the left-hand side of this rule
	 */
	public Variable(String name, ArrayList<Rule> rightSide)
	{
		this.name = name;
		this.rightSide = rightSide;
	}

	/**
	 * Sets a ruleList (list of rightSides) for this variable
	 * @param newRuleList the rightSide of the rule for this variable
	 */
	public void setRuleList(ArrayList<Rule> newRuleList)
	{
		this.rightSide = newRuleList;
	}
	
	/**
	 * Gets the rule associated with this variable
	 * @return the rule associated with this variable (may be null)
	 */
	public Rule getRule()
	{
		Random rand = new Random();
		int index = rand.nextInt(rightSide.size());
		
		return rightSide.get(index);
	}
	
	/**
	 * Gets the name of this variable
	 * @return the name of this variable
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Tells whether or not this variable has a ruleList
	 */
	public boolean hasRuleList()
	{
		if(rightSide != null)
			return true;
		return false;
	}
	
	/**
	 * Gets this variable's list of rightSides (a list of Rules)
	 * @return An arrayList that represents this variable's rightSides
	 */
	public ArrayList<Rule> getRuleList()
	{
		return rightSide;
	}
}
