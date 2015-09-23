import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class RandomSentenceGenerator
{

	//fields
	private Grammar grammar;
	
	
	//constructor
	public RandomSentenceGenerator()
	{
		
	}
	
	
	public void readFromFile(String fileName)
	{   
		//use try-catch because File IO can break sometimes
		try {
			
			grammar = new Grammar();
			Scanner sc = new Scanner(new File(fileName)); //parse the specified file with a new Scanner
			
			
			while(sc.hasNextLine())
			{				
				
				
			}
			sc.close();//because we're done parsing the file
		}
		catch(Exception e) {
			System.out.println("Error loading from file: "+e);//catch the exception if there is one thrown
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	public RightSide parseRightSideLine(String line, Grammar grammar)
	{
		RightSide rs = new RightSide();
		System.out.println("line: "+line);
		String currentName = "";//the name of either the terminal or variable that we're currently building
		for(int i=0; i<line.length(); i++)//now we want to go through the line one character at a time
		{
			if(line.charAt(i)== '<')//if we reach a left angle bracket, then we're at the beginning of a line or the end of the terminal
			{
				
				if(currentName.equals(""))//if the name is empty
				{
					//then we're starting the a new variable
				}
				else
				{
					Terminal terminal = new Terminal(currentName);//make a new terminal because we're done getting all of the characters in the name
					rs.add(terminal);
					currentName = "";//reset the name so we can keep going through the line
				}
				
			}
			if(line.charAt(i) == '\\' && line.charAt(i+1) == '>')//if the right bracket is escaped because it's part of the name                   
			{
				
			}
			if(line.charAt(i) != '\\' && line.charAt(i+1)=='>')//if the right bracket is not escaped
			{
				//then we have reached the end of the variable name, so we should make a variable and add it to the right side
				Variable var = new Variable(currentName);
				rs.add(var);
				currentName = "";//reset the name so we can keep going through the line
			}
		}
		
		return rs;
		
	}
	
	
	
	
	
	
	
	
	private boolean isNewVariable(String s, Grammar g)
	{
		return (g.getListVariableNames().contains(s));//return true if the grammar does not contain the variable already
	}


	private boolean isVariable(String s)
	{
		if(s.startsWith("<") && s.endsWith(">"))
		{
			return true;
		}
		return false;
	}


	public void print()
	{
		System.out.println("List of variables: " + grammar.getListVariableNames());
	}
	
	
	
	
}
