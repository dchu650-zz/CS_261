package edu.ups.cs261;

public class LinkedListTester {

	public static void main(String[] args)
	{
		//		Node<String> b = new Node<String>("Bob");
		//		Node<String> s = new Node<String>("Sally");
		//		
		//		b.next = s;


		LinkedList<Integer> ints = new LinkedList<Integer>();
		for(int i=0; i<10; i++)
		{
			ints.add(i);
		}
		
		ints.add(5,100);
		
		for(int i=0; i<ints.size(); i++)
			System.out.println(ints.get(i));

		
		
		//				
		//		for(int i=0; i<ints.size(); i++)
		//		{
		//			System.out.println(ints.get(i));
		//		}

	}

}
