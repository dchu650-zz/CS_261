import java.util.ArrayList;

/**
 * A class that acts as a grammar; a compilation of variables and their respective rules.
 * @author Jessica Penick
 */
public class Grammar
{	
	private ArrayList<Variable> varList;//binary search tree to store the variables
	
	private String startVar;//name of the start variable
	
	/**
	 * Constructor for a grammar
	 */
	public Grammar()
	{
		varList = new ArrayList<Variable>();
		startVar = "start";
	}
	
	/**
	 * Constructor for a grammar
	 * @param startVar name of the start variable
	 */
	public Grammar(String startVar)
	{
		this();
		this.startVar = startVar;
	}
	
	/**
	 * Adds a variable (and it's respective rule) to this grammar
	 * @param newVar variable to add to this grammar
	 */
	public void addVariable(Variable newVar)
	{
		//store whole variable objects, or their String names?
		varList.add(newVar);
	}
	
	/**
	 * Gets the collection of variables in this grammar
	 * @return collection of variables in this grammar
	 */
	public ArrayList<Variable> getVarList()
	{
		return varList;
	}

	/**
	 * Gets the name of this grammar's start variable
	 * @return the name of this grammar's start variable
	 */
	public String getStartVariableName()
	{
		return startVar;
	}
	
	/**
	 * Given the name of a variable, gets that variable's ruleList
	 * @param varName name of the variable to search for
	 * @return the ruleList of the variable whose name matched this one.
	 */
	public ArrayList<Rule> getVariableRuleList(String varName)
	{
		//find the variable in the binary search tree
		
		for(Variable oneVar : varList)//search through each node
		{
			//compare each node's name to varName
			if(varName.equalsIgnoreCase(oneVar.getName()))//if they match
				return oneVar.getRuleList();//return this variable's rule
		}
		return null;//if we go through the entire collection w/o finding it, return null
	}
	
	/**
	 * Gets the variable whose name matches the given string
	 * @param varName name of the variable to search for
	 * @return returns the variable whose name matched this one.
	 */
	public Variable getVariable(String varName)
	{
		//find the variable in the binary search tree
		
				for(Variable oneVar : varList)//search through each node
				{
					//compare each node's name to varName
					if(varName.equalsIgnoreCase(oneVar.getName()))//if they match
						return oneVar;//return this variable
				}
				return null;//if we go through the entire collection w/o finding it, return null
	}
}
