import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;

public class Grammar
{
	//fields
	private HashMap<Variable,Rule> variableHashMap;//use a HashMap to associate a variable with a Rule object, which contains an ArrayList of the "right sides" for that variable
	
	/**
	 * Constructor for the grammar.
	 */
	public Grammar()
	{
		variableHashMap = new HashMap<Variable,Rule>();
	}

	
	public void addVariable(Variable var)
	{
		variableHashMap.put(var, new Rule());
	}
	
	public ArrayList<String> getListVariableNames()
	{
		ArrayList<String> variableNameList = new ArrayList<String>();
	
		AbstractSet<Variable> keySet= (AbstractSet<Variable>) variableHashMap.keySet();
		
		for(Variable v: keySet)
		{
			variableNameList.add(v.getName());
		}
		
		return variableNameList;
	} 
	
	
}
