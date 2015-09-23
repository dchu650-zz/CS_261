package edu.ups.cs261;

/**
 * 
 * @author joel
 * @version Jan 31, 2013
 */
public class ArrayListTester
{
	public static void main(String[] args)
	{
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for(int i=0; i<10; i++)
		{
			ints.add(i);
		}
		
		ints.remove(-1);
		
		for(int i=0; i<ints.size(); i++)
		{
			System.out.println(ints.get(i));
		}
		
	}
	
}
