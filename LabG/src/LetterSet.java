/**
 * An inventory of letters, without order. Inventories contain the count of each letter.
 * Based on program by Stuart Reges
 * 
 * @author Joel Ross
 * @version Sp2013
 */
public class LetterSet
{
	private static final int A_OFFSET = 'a'; //the integer, ascii value for the letter 'a'. Tells us how much to offset everyone to put them in the array

	private int[] inventory; //an array of counts, one spot for each letter
	private int size; //the number of letters in our set
	
	/**
	 * Constructs a new LetterSet object that represents the alphabetic letters in the given string, 
	 * ignoring the case of letters and ignoring any non-alphabetic characters.
	 * @param data The String used to populate the LetterSet
	 */
	public LetterSet(String data)
	{
		inventory = new int[26]; //currently we deal only with alphabetical characters!
		size = 0;
		
		//process the data to initially fill our inventory
		char[] dataLetters = data.toLowerCase().toCharArray();
		for(int i=0; i<dataLetters.length; i++)
		{
			if(Character.isLetter(dataLetters[i])) //make sure it's a letter
			{
				inventory[(int)dataLetters[i]-A_OFFSET]++; //increment count of that letter
				size++; //increment the size
			}
		}
	}
	
	/**
	 * Constructs a new empty LetterSet object (all letters have count of 0)
	 */
	public LetterSet()
	{
		this("");
	}

	/**
	 * Returns a count of how many of this letter are in the set, ignoring case.
	 * @param c The character to get the count of
	 * @return The number of times the character appears in the set
	 */
	public int getCount(char c)
	{
		if(!Character.isLetter(c))
			throw new IllegalArgumentException();
		c = Character.toLowerCase(c);
		
		return inventory[c-A_OFFSET];
	}

	/**
	 * Sets the count of how many times this letter appears in the set, ignoring case.
	 * @param c The character to set the count of
	 * @param num The number of times the character should appear in the set
	 */
	public void setCount(char c, int num)
	{
		if(num < 0 || !Character.isLetter(c))
			throw new IllegalArgumentException();
		c = Character.toLowerCase(c);

		size = size - inventory[c-A_OFFSET] + num; //fix the size (subtract old, add new)
		inventory[c-A_OFFSET] = num;
	}

	/**
	 * Gives the "size" (the number of total characters) in the set.
	 * @return The size of the set
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Returns whether or not the set is empty (has size 0)
	 * @return Whether the set is empty.
	 */
	public boolean isEmpty()
	{
		return size==0;
	}
	
	/**
	 * Returns a String represention of the set. For example, an inventory of 4 a's, 1 b, 1 l and 1 m would be represented as "[aaaablm]".
	 */
	public String toString()
	{
		String toReturn = "[";
		for(int i=0; i<inventory.length; i++)
		{
			for(int j=0; j<inventory[i]; j++)
				toReturn += (char)(i+A_OFFSET);
		}
		return toReturn+"]";
	}

	/**
	 * Returns a new LetterSet object that represents the "sum" (the union) of this and the given LetterSet.
	 * The new LetterSet has the sum of the counts of each individual letter 
	 * @param other the LetterSet to add
	 * @return a new LetterSet with the sum of the letter counts
	 */
	public LetterSet add(LetterSet other)
	{
		LetterSet toReturn = new LetterSet();
		for(int i=0; i<inventory.length; i++)
		{
			toReturn.inventory[i] = inventory[i] + other.inventory[i]; //direct access fields of other objects; this is slightly rude but fast
			toReturn.size += toReturn.inventory[i]; //add that count to our sum
		}
		return toReturn;
	}

	/**
	 * Returns a new LetterSet object that represents the "difference" of this and the given LetterSet.
	 * The new LetterSet has letter counts that are the current counts minus the couts of the other object.
	 * If any of the letter counts would be negative (so the other cannot be subtracted), this method returns null
	 * @param other the LetterSet to subtract
	 * @return A new LetterSet with the subtracted letter counts, or null if could not be subtracted
	 */
	public LetterSet sub(LetterSet other)
	{
		LetterSet toReturn = new LetterSet();
		for(int i=0; i<inventory.length; i++)
		{
			toReturn.inventory[i] = inventory[i] - other.inventory[i]; //direct access fields of other objects; this is slightly rude but fast
			if(toReturn.inventory[i] < 0)
				return null;
			toReturn.size += toReturn.inventory[i]; //add that count to our sum
		}
		return toReturn;
	}
}
