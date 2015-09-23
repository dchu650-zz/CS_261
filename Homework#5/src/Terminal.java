
public class Terminal extends Symbol
{
	//fields
	private String value;

	public Terminal(String name)
	{
		value = name;
	}

	@Override
	public String getName() {
		
		return value;
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
