/**
 * A class that is used to return the terminal X within the RandomExpression class 
 * @author darrenchu, nolderman, zekerosenberg
 *
 */
public class TerminalX extends Expression{

	
	
	public TerminalX(){
		
	}
	

	public String toString(){//prints out the X terminal
		return "X";
	}
	
	@Override
	public double evaluate(double x, double y) {//returns the X terminal
		return x;
	}

}
