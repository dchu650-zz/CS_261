import java.util.ArrayList;


public abstract class Symbol 
{
	protected String name;//the name of a variable or terminal
	protected ArrayList<Rule> rightSide;//the rule associated with a variable

	public Symbol()
	{	
	}
	
	public abstract String getName();//gets the name of a variable or terminal
	public abstract boolean hasRuleList();//whether or not his symbol has a rule (if it's a terminal or not)
	public abstract Rule getRule();//gets the rule of this symbol (if it has one. Terminals have no rule)
}
