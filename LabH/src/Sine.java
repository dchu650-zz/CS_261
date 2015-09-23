/**
 * A class that will take an expression and use the sin function on that expression
 * @author darrenchu, nolderman, zekerosenberg
 *
 */
public class Sine extends Expression{

	private Expression e1;//expression in which we input into the sine function
	
	public Sine(Expression exp1){
		e1 = exp1;
	}
	

	public String toString(){//returns a string representation of the sin function
		return "sin(pi * " + e1 + ")";
	}
	
	@Override
	public double evaluate(double x, double y) {//evaluates the expression in a sin function
		return Math.sin(Math.PI * e1.evaluate(x, y));
	}

}
