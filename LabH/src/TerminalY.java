/**
 * A class that returns the terminal Y, that will be used in the RandomExpression class to return a double
 * @author darrenchu, nolderman, zekerosenberg
 */
public class TerminalY extends Expression {
	
	public TerminalY(){
		
	}
	

	public String toString(){//returns a string representation of Y
		return "Y";
	}
	
	@Override
	public double evaluate(double x, double y) {//returns the double associated with the Y terminal
		return y;
	}

}
