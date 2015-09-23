
public class ReferenceTest {

		public static void main(String[] args)
	{
		//proof that Java is pass by value (it passes references by value):
		Exception e = new Exception("hello");
		Exception e2 = new Exception("goodbye");
		swap(e,e2);
		System.out.println(e);
		System.out.println(e2);
		
	}

	public static void swap(Object o1, Object o2)
	{
		Object temp = o1;
		o1 = o2;
		o2 = temp;
	}
	
}
