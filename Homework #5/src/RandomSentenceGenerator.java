import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Random Sentence Generator that takes a grammar text file and randomly generates a specified 
 * number of sentences.
 * @author Jessica Penick
 *
 */
public class RandomSentenceGenerator
{
	private Grammar gram;

	/**
	 * Constructor for a Random Sentence Generator
	 */
	public RandomSentenceGenerator()
	{
		this(10);//default number of sentences 10
	}

	/**
	 * Constructor for a random sentence generator
	 * @param args main method arguments
	 */
	public RandomSentenceGenerator(String[] args)
	{
		this(args[0], new Integer(args[1]), args[2], args[3]);
	}

	/**
	 * Constructor for a Random Sentence Generator
	 * @param count number of sentences to randomly generate
	 */
	public RandomSentenceGenerator(int count)
	{
		this("cat_and_dog.txt", count, "start", "sentences.txt");
	}

	/**
	 * Constructor for a random sentence generator
	 * @param inFileName name of the text file with grammar
	 * @param count number of sentences to generate
	 * @param startVar name of start variable
	 * @param outFileName name to give text file of generated sentences
	 */
	//#j# except you don't do anything with the startVar parameter, so you end up ignoring it!!
	public RandomSentenceGenerator(String inFileName, int count, String startVar, String outFileName)
	{
		loadGrammar(inFileName);
		String toFile = ""; //#j# might move some of this into a separate "generate" method...
		for(int i=0; i<count; i++)
		{
			String s = generateRandomSentence();
			System.out.println(s);
			toFile = toFile +s+ "\n";
		}
		try
		{//make a text file out of the generated sentences
			BufferedWriter out = new BufferedWriter (new FileWriter(outFileName));
			out.write(toFile);
			out.close();
		}
		catch (Exception e)
		{ 
			System.out.println("Problem creating file: "+e); 
		} 
	}

	/**
	 * Generates a single random sentence based on the loaded grammar
	 * @return A string representation of the generated sentence
	 */
	public String generateRandomSentence()
	{
		String toReturn = "";
		Rule startRule = gram.getVariable(gram.getStartVariableName()).getRule();

		for(Symbol sym : startRule)
		{
			if(sym instanceof Terminal) //#j# we don't want to do this if we can avoid it!! Instead just have the symbols evaluate themselves, so you can have them react however they want!
				toReturn = toReturn+sym.getName();
			if(sym instanceof Variable)
			{
				if(!sym.getRule().hasVariable())//if sym is a "terminal rule" (a term rule will have no variables) //#j# if you use recursion properly, you won't need to do this! Let the classes recurse themselves!
				{
					ArrayList<Symbol> terms = new ArrayList<Symbol>();
					terms = seekTerminal(((Variable)sym), new ArrayList<Symbol>());//seek its terminals //#j# why the casting??
					for(Symbol s : terms)
					{
						toReturn = toReturn+s.getName();
					}
				}
				else
					toReturn = toReturn+generateRandomSentence(sym.getName());
			}
		}
		toReturn = toReturn.substring(0,1).toUpperCase()+toReturn.substring(1);//proper capitalization! //#j# eh, the proper capitalization should be specified by the grammar. What if I'm generating code? I may not want capitalization!!
		return toReturn;
	}


	/**
	 * Helper for generateRandomSentence method
	 * @param vName name for variable to collect string of terminals for
	 * @return the part of a generated sentence that was derived from the given variable
	 */
	private String generateRandomSentence(String vName) //#j# this does a lot of the same work as the previoius... why the duplicate methods?
	{
		String toReturn = "";//sentence
		Variable var = gram.getVariable(vName);//get startVar rule

		for(Symbol sym : var.getRule())
		{
			ArrayList<Symbol> terms = new ArrayList<Symbol>();
			if(sym instanceof Terminal)
				toReturn = toReturn+sym.getName();
			if(sym instanceof Variable)
			{
				if(!sym.getRule().hasVariable())//if sym is a "terminal rule" (a term rule will have no variables)
					terms = seekTerminal(((Variable)sym), terms);//seek its terminals
				else
					toReturn = toReturn+generateRandomSentence(sym.getName());

				for(Symbol s : terms)
					toReturn = toReturn+s.getName();
			}
		}
		return toReturn;//return the complete sentence
	}

	/**
	 * Follows a variable's rule (and the rules in those variables) all the way to a terminal
	 * then adds these to an arrayList of Terminals RECURSIVELY. //#J# where is the recursion? Unless it's that the terms.add calls this method in the grammar... and that seems messy.
	 * @param v variable to travel through
	 * @param terms list of terminals from this variable
	 * @return the list of terminals from this variable
	 */
	private ArrayList<Symbol> seekTerminal(Variable v, ArrayList<Symbol> terms)
	{	
		//		if(!v.getRule().hasVariable())//stop when v is a "terminal rule"
		//		{
		terms.add(v.getRule().getRandomSymbol());//add one of v's terminals to the list
		return terms;//return the completed list!
		//		}
		//		else
		//		{
		//			terms.add(v.getRule().getRandomSymbol());//add one of v's terminals to the list
		//			return terms;//return the completed list!
		//		}
		//		return null;
	}

	/**
	 * Gets the grammar in this generator
	 * @return the grammar being used by this generator
	 */
	public Grammar getGrammar()
	{
		return gram;
	}

	/**
	 * Loads a text file version of a grammar
	 * @param inFileName name of input grammar text file
	 * @param startVar name of grammar's start variable
	 */
	private void loadGrammar(String inFileName)
	{   
		File f = new File(inFileName);//load a file

		try 
		{
			Scanner sc = new Scanner(f);  //parse it with Scanner

			String line = sc.nextLine();
			while(!line.equals("{"))
				line = sc.nextLine();//skip lines until you find "{"

			//DESIGNATE START VARIABLE
			line = sc.nextLine();//this ought to be the first variable //#j# but need not be! You're supposed to have the first variable be whatever the user specifies! You are so close on it!
			String startVar = "";
			if(line.charAt(0) == '<')
				startVar = line.substring(1, line.length()-1);

			gram = new Grammar(startVar);
			buildRule(sc.nextLine(), new ArrayList<Rule>(), startVar, sc);//build the rule for the start variable

			while(sc.hasNextLine())//for every other variable until we've run out of text file,
			{
				if(sc.nextLine().equals("{"))//while we're building one rule,
				{
					String varName = sc.nextLine();//the first var is the leftSide of the rule
					varName = varName.substring(1, varName.length()-1);//remove "<>" from varName

					String rightSideLine = sc.nextLine();
					ArrayList<Rule> ruleList = new ArrayList<Rule>();

					buildRule(rightSideLine, ruleList, varName, sc);//build the rule for varName and add var to the grammar
				}
			}
			associate();
			sc.close();
		}
		catch(Exception e) 
		{
			System.out.println("Error loading from file: "+e);
			e.printStackTrace();
		}
	}

	/**
	 * Helper for load grammar (the "meat" of loadGrammar)
	 * @param rightSideLine
	 * @param newRule
	 * @param varName
	 * @param sc
	 */
	public void buildRule(String rightSideLine, ArrayList<Rule> ruleList, String varName, Scanner sc)
	{
		while(!rightSideLine.equals("}"))//until the rule is closed
		{
			Rule newRule = new Rule();
			if(rightSideLine.charAt(0) == '<')//if you have another variable in your rule
			{
				parse(rightSideLine, newRule);//separate out the terminals and variables in this line 
			}
			else//but if you have a terminal,
			{
				Terminal newTerm = new Terminal(rightSideLine);//make it a new terminal
				newRule.add(newTerm);
			}
			ruleList.add(newRule);
			rightSideLine = sc.nextLine();//then move on to the next line!
		}

		Variable newVar = new Variable(varName, ruleList);
		gram.addVariable(newVar);
	}

	/**
	 * Connects variable names in each variable's rule with rules in this grammar 
	 */
	private void associate()
	{
		for(Variable v : gram.getVarList())//for each variable in this grammar
		{
			ArrayList<Rule> ruleList = v.getRuleList();
			for(Rule r : ruleList)
				for(Symbol sym : r)//for each symbol in that rule,
					if(sym instanceof Variable)//if that symbol is a variable //#J# ick. This shouldn't be necessary...
						if(!sym.hasRuleList())//but it has no Rule
							//give it the ruleList in this Grammar that has its same name
							((Variable) sym).setRuleList(gram.getVariableRuleList(sym.getName())); //#j# weird casting going on here...
		}
	}

	/**
	 * Parses a line for terminals and variables and adds these to the rightSide rule
	 * @param line line to parse for terminals and variables
	 * @param rightSide the rightside of the rule
	 */
	private void parse(String line, Rule rightSide)
	{
		int i=0; 
		while(i<line.length())
		{
			if(line.charAt(i) == '<')//start variable
			{
				String varName = "";
				i++;
				if(i>=line.length())
					return;
				while(line.charAt(i) != '>')//while the variable's still open
				{
					varName = varName+line.charAt(i);//add that to the variable's name
					i++;
				}
				Variable newVar = new Variable(varName);
				rightSide.add(newVar);
			}
			else
			{
				String termName = "";
				i++;
				if(i>=line.length())
					return;

				while(i<line.length() && line.charAt(i) != '<')
				{
					termName = termName+line.charAt(i);
					i++;

				}
				Terminal newTerm = new Terminal(termName);
				rightSide.add(newTerm);
			}
		} //#j# good
	}
}
