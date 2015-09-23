
public abstract class Symbol
{

	private String s;
	
	/**
	 * constructor
	 */
	public Symbol()
	{
	}
	
	public abstract String getName();
	public abstract boolean hasRule();
	public abstract Rule getRule();
	
	
	
}
