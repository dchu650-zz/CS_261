
/**
 * A class that can perform simple mathematical operations with double precision.
 * @author Darren Chu & Andrew Graham
 * @version sp13
 */
public class Calculator
{
	private double value; //the value stored in our registry
		
	/**
	 * Creates a new Calculator with the given initial value stored.
	 * @param init The initial value of the calculator
	 */
	public Calculator(double init)
	{
		value = init;
	}
	
	/**
	 * Creates a new Calculator with an initial value of 0.
	 */
	public Calculator()
	{
		this(0.0);
	}

	
	/**
	 * Returns the current stored value.
	 * @return the value
	 */
	public double getValue()
	{
		return value;
	}

	/**
	 * Adds the given number to the stored value.
	 * @param num The number to add to the stored value
	 */
	public void add(double num)
	{
		value += num;
	}
	
	/**
	 * Subtracts the given number to the stored value.
	 * @param num The number to subtract from the stored value
	 */
	public void subtract(double num)
	{
		value -= num;
	}
	
	/**
	 * Multiplies the stored value by the given number.
	 * @param num The number to multiply the stored value by
	 */
	public void multiply(double num)
	{
		value *= num;
	}
	
	/**
	 * Divides the stored value by the given number.
	 * @param num The number to divide the stored value by
	 */
	public void divide(double num)
	{
		if(num != 0)
			value /= num;
	}

	/**
	 * Clears the stored value (resets it to 0).
	 */
	public void clear()
	{
		value = 0.0;
	}

}
