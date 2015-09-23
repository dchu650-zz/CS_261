/**
 * A class that will take two expressions and multiply them together
 * @author darrenchu, nolderman, zekerosenberg
 */
public class Multiply extends Expression {

	private Expression e1;//expression in which we input into the multiply function
	private Expression e2;//another expression in which we multiply with the first expression
	
	public Multiply(Expression exp1, Expression exp2){
		e1 = exp1;
		e2 = exp2;
	}
	

	public String toString(){//prints out the multiplication function
		return e1 + " * " + e2;
	}
	
	@Override
	public double evaluate(double x, double y) {//evaluates the product of multiplying both the first and second expression
		return e1.evaluate(x, y) * e2.evaluate(x, y);
	}
	
	

}
