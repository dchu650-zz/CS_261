package edu.ups.cs261;

public class DoubleLinkedListTester {

	public static void main(String[] args)
	{

		DoubleLinkedList<Integer> ints = new DoubleLinkedList<Integer>();
		for(int i=0; i<10; i++)
		{
			ints.add(i);
		}
		
		
//		for(int i=0; i<ints.size(); i++)
//			System.out.println(ints.get(i));
		
		while(ints.size() > 0)
			ints.remove(0);

//		
		for(int i=0; i<ints.size(); i++)
			System.out.println(ints.get(i));
		
		//				
		//		for(int i=0; i<ints.size(); i++)
		//		{
		//			System.out.println(ints.get(i));
		//		}

	}

}
