
/**
 * A class representing a mathematical expression of the form f(x,y)
 * @author Joel
 * @version Sp 2013
 */
public abstract class Expression
{
	/**
	 * Evaluates the expression with the given x,y values
	 * @param x x-value
	 * @param y y-value
	 * @return the value of the function with the given parameters.
	 */
	public abstract double evaluate(double x, double y);
}
