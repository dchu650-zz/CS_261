import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class Grammar
{ 
	private String lines;
	private int position;
	private int endPosition;
	public static Symbol symbolList;
	public static Variable theRule;
	public static ArrayList<Symbol> mapValues;
	public static HashMap <Variable, ArrayList<Symbol>> map;
	public boolean firstSymbol;
	public Symbol startSymbol;
	private String totalSentence;
	private Random gen;
	private int random;

	public Grammar()
	{
		mapValues = new ArrayList<Symbol>();
		theRule = new Variable();
		saveWordsWithScanner();
		buildRandomSentences();
		gen = new Random();
		random = gen.nextInt(mapValues.size());
		startSymbol = null;
		firstSymbol = true;
	}

	private void saveWordsWithScanner()
	{
		firstSymbol = true;
		map = new HashMap<Variable, ArrayList<Symbol>>();
		try 
		{
			Scanner scanner = new Scanner(new File("Sports.txt")); //parse it with Scanner
			while(scanner.hasNextLine())
			{
				lines = scanner.nextLine();
				if(lines.isEmpty()!=true)
				{
					if(lines.charAt(0) == '{')//Checks when to start printing text
					{
						
						mapValues = new ArrayList<Symbol>();
						lines = scanner.nextLine();
						for(int i = 0; i < lines.length(); i++)
						{
							if(lines.charAt(i) == '<')
							{
								position = i+1;
							}
							else if(lines.charAt(i) == '>')
							{
								endPosition = i;
								Variable v = new Variable(lines.substring(position, endPosition)); 
//								System.out.println("this is variable v" + v);
//								System.out.println(firstSymbol);
								theRule = v; //implements a new arraylist of symbols with the substrings
								
								if(firstSymbol == true)
								{
									startSymbol = v;
									firstSymbol = false;
								}
							}
						}

						while(lines.charAt(0) != '}')
						{
							lines = scanner.nextLine();
							for(int i = 0; i < lines.length(); i++)
							{
								if(lines.charAt(i) == '<')
								{
									position = i+1;
								}
								else if(lines.charAt(i) == '>')
								{
									endPosition = i;
									symbolList = (new Variable(lines.substring(position, endPosition))); //implements a new arraylist of symbols with the substrings
									mapValues.add(symbolList);
								}
								//map.put(theRule, mapValues);
							}
							if(lines.charAt(0) != '<' && lines.charAt(0) != '}')
							{
								symbolList = (new Terminal(lines));
								mapValues.add(symbolList);
							}
						}
						map.put(theRule, mapValues);
						//System.out.println(mapValues);
					}
				}
			}
			//System.out.println(map.keySet());
//			Set<Variable> s = map.keySet();
//			
//			System.out.println("theRules:");
//			for(Variable r : s)
//			{
//				System.out.println(map.get(r) + "\n");
//			}
////			System.out.println(theRule);
////			ArrayList<ArrayList<Symbol>> s = map.get(theRule);
////			System.out.println(s.get(0));
//			//System.out.println(map.get(theRule).values());
		}
		catch(Exception e) 
		{
			System.out.println("Error loading from file: "+e);
			e.printStackTrace();
		}
	}

	public void buildRandomSentences()
	{
		ArrayList<Symbol> start = map.get(startSymbol);
		for(Symbol s : start)
			totalSentence += getTerminal(s);	
		System.out.println(totalSentence);
	}

	public String getTerminal(Symbol s)
	{
		if(s instanceof Terminal)
		{
			return s.toString();
		}
		else
		{
			Symbol s2 = mapValues.get(random);
			return getTerminal(s2);
		}
	}
	
	public static ArrayList<Symbol> getMapValues() {
		return mapValues;
	}

	public static void setMapValues(ArrayList<Symbol> mapValues) {
		Grammar.mapValues = mapValues;
	}
}