import java.util.ArrayList;

/**
 * This class contains some bugs. Your job is to find them.
 * This class will process Strings. It offers some basic, useful
 * String methods (some of which mimic the String methods in
 * the standard String class.) 
 * 
 * @author Chuck Hommel
 * @editors Darren Chu & Nate Olderman
 * @version 2012.09.22
 * 
 * 
 * Modified by: 
 * Modifications:
 * 		   toUpper():
 * 
 *         toLower():
 *      
 *      countWords(): 
 *      
 *   reverseString():
 *   
 *     splitString():
 *   
 *   avgWordLength():
 *      
 */
public class Buggy
{
	public Buggy()
	{
	}

	/**
	 * Converts string to all upper case. Does not change non-alphabetic characters
	 * @param s the String to convert to upper case
	 * @return s all in upper case
	 * 
	 */
	public String toUpper(String s)
	{
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String upperCase = "ABCDEFGHlJKLMNOPQRSTUVWXYZ";
		String temp = "";
		for (int i  = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if(lowerCase.contains("" + c )) //takes into account whether it is not a letter. If it is not, simply return the char (Darren Chu & Nate Olderman)
			{
				//System.out.println(""+c);
				int pos = lowerCase.indexOf(c);
				//System.out.println(""+pos);
				temp = temp + upperCase.charAt(pos); 
				//System.out.println(temp);
			}
			else
			{
				temp = temp + c;
			}
		}
		return temp;
	}

	/** 
	 * Converts a string to all lower case. Does not change non-alphabetic characters
	 * @param s the String to convert to lower case
	 * @return s all in lower case
	 * 
	 */
	public String toLower(String s)
	{
		String lowerCase = "abcdefghijklmnopqrstuvwxyz"; //the p and the q were in incorrect spots and there is no "c", it was replaced with an "e"
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		String temp = "";
		int i;

		for (i  = 0; i < s.length(); i++) //removed semi-colon (Darren Chu & Nate Olderman)
		{
			char c = s.charAt(i);
			if(upperCase.contains("" + c)) //takes into account whether it is not a letter. If it is not, simply return the char (Darren Chu & Nate Olderman)	
			{
				int pos = upperCase.indexOf(c);
				temp = temp + lowerCase.charAt(pos); 
				//System.out.println(temp);
			}
			else
			{
				temp = temp + c;
			}
		}
		return temp;
	}

	/**
	 * Counts the words in a String.
	 * This algorithm uses a fairly limited definition of a word. 
	 * A 'word' is a string of characters separated from another string of characters by one
	 * or more occurrences of the following separators:<br/>
	 * 
	 *
	 * <pre>
	     Character       Name 

                .            period 
                ,            comma 
                -            hyphen 
                ;            semicolon 
                :            colon 
                ?            question mark 
               ' '           space
                !            exclamation point 
      </pre>

	 * For simplicity in this exercise, no other characters are considered separators.<br/>
	 * 
     <pre> Examples:    Number of words    String  
	 *                     0           ""
	 *                     1           "abc" 
	 *                     2           "abc def"
	 *                     2           "hokey-pokey"
	 *                     2           "Justin            Bieber"
	 *                     4           "(for String s1 : myStringList)"
     </pre>
	 *                                                        
	 * @param s the String
	 * @return the number of words in s
	 */
	public int countWords(String s)
	{
		String separators = ".,-;:? !";
		//System.out.println(separators);
		int wordCount = 0; //Takes into account the very first word (Darren Chu & Nate Olderman)
		if(separators.indexOf(s.charAt(0)) == -1) //Takes into account if the first index is a separator or not. (Darren Chu & Nate Olderman)
			wordCount++;
		for (int i = 0; i < s.length()-1; i++)
		{
			if (separators.indexOf(s.charAt(i)) != -1 && separators.indexOf(s.charAt(i+1)) == -1) //Take into account when the character does not occur rather than every instance. (Darren & Nate Olderman)
			{
				wordCount++;
			}
		}
		return wordCount;
	}

	/** 
	 * Reverses the characters in a String
	 * @param s the String to reverse
	 * @return s reversed
	 * 
	 */
	public String reverseString(String s)
	{
		String temp = "";
		int i = s.length()-1; // reversed the order (Darren Chu & Nate Olderman)
		while( i+1 > 0)
		{
			temp = temp + s.charAt(i);
			//System.out.println(temp);
			i--; //stops the timer 
		}
		return temp;
	}

	/**
	 * Splits a String on a specified set of delimiters.
	 * Works like the String class method split() but returns ArrayList<String> instead of String[]
	 * Multiple delimiters in a row count as a single delimiter.
	 */
	public ArrayList<String> splitString(String s, String d)
	{
		//System.out.println(s.split(d)[0]);
		ArrayList<String> l = new ArrayList<String>(); //Instantiates a new arrayList (Darren Chu & Nate Olderman)
		String t = "";
		for (int i = 0; i<s.length(); i++)
		{
			char c = s.charAt(i);
			if (d.indexOf(c) != -1 && t != "")
			{
				l.add(t);
				t = "";
			}
			else if(d.indexOf(c) == -1)
			{ 
				t=t+c;
			}
		}
		if(t != "")
		{
			l.add(t);
		}
		return l;
	}

	/**
	 * Computes average word length in a string.
	 * A 'word' is a string of characters separated from another string of characters by one
	 * or more occurrences of the following separators:<br/>
	 * <pre>
	     Character       Name 

                .            period 
                ,            comma 
                -            hyphen 
                ;            semicolon 
                :            colon 
                ?            question mark 
               ' '           space
                !            exclamation point 
      </pre>

	 * For simplicity in this exercise, no other characters are considered separators.<br/>
	 * 
      <pre> Examples:    Number of words    String                              Average word length
	 *                     1           "abc"                                        3.0
	 *                     2           "abc def"                                    3.0
	 *                     2           "hokey-pokey"                                5.0
	 *                     2           "Justin            Bieber"                   6.0
	 *                     4           "(for String s1 : myStringList)"             6.25
       </pre>
	 * @param s the String to convert to upper case
	 * @return the average word length of the words in s
	 */
	public double avgWordLength(String s) 
	{
		String delims = ".,-;:? !";
		ArrayList<String> myList = splitString(s, delims);
		double totalChars = 0; //change the ints into a double (Darren Chu & Nate Olderman)
		for (String str : myList)
		{
			totalChars += str.length();
		}
		return(totalChars/myList.size());
	}
}
