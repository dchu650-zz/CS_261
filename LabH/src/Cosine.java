/**
 * A class that evaluates the expression through the cosine function 
 * @author darrenchu, nolderman, zekerosenberg
 */
public class Cosine extends Expression{

private Expression e1;//expression to put in the cosine function
	
	public Cosine(Expression exp1){
		e1 = exp1;
	}
	

	public String toString(){
		return "cos(pi * " + e1 + ")";//prints out cosine
	}
	
	@Override
	public double evaluate(double x, double y) {
		return Math.cos(Math.PI * e1.evaluate(x, y));//evaluates expression in a cosine function
	}
}
