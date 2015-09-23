
/**
 * Raises the expression to the power of the second expression
 * aesthetically makes some strange squares in bottom right corner because of abs value... but we thought it looked cool
 * <power>::=[<expression> ^ absoluteValue(<expression>)]
 * @author darrenchu, nolderman, zekerosenberg
 *
 */
public class Power extends Expression {

	private Expression exp1;
	private Expression exp2;
	
	public Power(Expression e1,Expression e2)//raises the expression to the power of the second expression
	{
		exp1 = e1;
		exp2 = e2;
	}
	
	public String tostring()//prints out the power function
	{
		return exp1 + "^(" + exp2 + ")";
	}
	

	@Override
	public double evaluate(double x, double y) {//evaluates the function in which the first expression is raised to the power of the second expression 
		double num = Math.pow(Math.abs(exp1.evaluate(x,y)), Math.abs((exp2.evaluate(x, y))));
		if(exp2.evaluate(x,y)<0)
			num = -num;
		
		return num;
	}
	
}
