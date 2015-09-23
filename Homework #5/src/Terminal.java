
public class Terminal extends Symbol
{	
	/**
	 * Default constructor for Variable
	 */
	public Terminal()
	{
		this("terminal");
	}
	
	/**
	 * Constructor for variable that names it
	 * @param name name of this variable object
	 */
	public Terminal(String name)
	{
		this.name = name;
	}
	
	/**
	 * Gets the name of this Terminal
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Method from parent (symbol)
	 */
	public Rule getRule()
	{
		return null;
	}

	/**
	 * Tells whether or not this terminal has a rule (it will not)
	 */
	public boolean hasRuleList()
	{
		return false;
	}
}
