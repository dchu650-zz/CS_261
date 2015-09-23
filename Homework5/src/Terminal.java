
public class Terminal extends Symbol
{
	private String terminalWord;
	
	public Terminal(String word)
	{
		terminalWord = word;
	}
	
	public String toString()
	{
		return terminalWord;
	}
	
	public String evalutate(Terminal t)
	{
		return t.toString();
	}

}
