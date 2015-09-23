package edu.ups.cs261;

public class StackTester {

	public static void main(String[] args)
	{
		StackArrayList<Integer> ints = new StackArrayList<Integer>();

		ints.push(3);
		ints.push(5);
		System.out.println(ints.peek());
		ints.push(7);
		System.out.println(ints.peek());
		System.out.println(ints.pop());
		System.out.println(ints.pop());
		System.out.println(ints.peek());
		ints.push(9);
		ints.push(11);
		ints.push(13);
		System.out.println(ints.pop());
		System.out.println(ints.pop());
		System.out.println(ints.pop());
		System.out.println(ints.pop());

		
		
//		for(int i=0; i<5; i++)
//		{
//		//	ints.add(i);
//		}

//		ints.add(3, 100);
		
		for(int i=0; i<6; i++)
		{
			//System.out.println(ints.get(i));
		}
		
		
	}

}
