
public class Variable extends Symbol
{
	//fields
	private String variableName;//the name of the variable
	
	/**
	 * Constructor
	 * @param name
	 */
	public Variable(String name)
	{
		variableName = name;
	}
	
	/**
	 * Returns the name of the variable.
	 * @return variableName The name of the variable that is a String.
	 */
	public String getName()
	{
		return variableName;
	}

	@Override
	public boolean hasRule() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rule getRule() {
		// TODO Auto-generated method stub
		return null;
	}

}
