import java.io.IOException;


/**
 * 
 * @author joel
 * @version Jan 29, 2013
 */
public class ExceptionTest
{

	/**
	 * 
	 */
	public ExceptionTest()
	{}

	public void outsideMethod()
	{
		insideMethod();
	}

	public void insideMethod()
	{
		creamyCenter();
	}
	
	public void creamyCenter() throws NullPointerException
	{
		throw new NullPointerException("haha");
	}
	
	
	public static void main(String[] args)
	{
		try{
			ExceptionTest et = new ExceptionTest();
			et.outsideMethod();
		}
		catch(Exception e)
		{
			System.out.println("gotchya!");
			e.printStackTrace();

		}
	}
}
