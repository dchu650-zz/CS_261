import java.util.ArrayList;
import java.util.Random;
public class Variable extends Symbol
{
	private String variable;	
	private Random random;
//	protected ArrayList<Symbol> symbol;
	
	public Variable()
	{
//		symbol = new ArrayList<Symbol>;
		random = new Random();
	}
	
	public Variable(String a)
	{
		variable = a;
	}
	
	public String toString()
	{
		return variable;
	}

	
}

