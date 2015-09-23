/**
 * A class that takes in two expressions and calculates the average between both expressions
 * @author darrenchu, nolderman, zekerosenberg
 */
public class Average extends Expression{

	private Expression e1;
	private Expression e2;
	
	public Average(Expression exp1, Expression exp2){//finds the average of the two expressions
		e1 = exp1;
		e2 = exp2;
	}
	

	public String toString(){//prints out the average of the two expressions
		return "(" + e1 + " + " + e2 + ")/2";
	}
	
	@Override
	public double evaluate(double x, double y) {//evalutates the average of the two expressions
		return (e1.evaluate(x, y) + e2.evaluate(x, y))/2;
	}

}
