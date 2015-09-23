import java.util.Random;

/**
 * A class that randomly uses the Average, Cosine, Multiply, Power, Sine, TerminalX and TerminalY class
 * The class will use these classes to randomly expand the grammar until the overall expression is complete 
 * @author darrenchu, nolderman, zekerosenberg
 *
 */
public class RandomExpression extends Expression {

	private Random random;
	private Expression exp;
	
	
	public RandomExpression()
	{
		this(0);
	}
	
	public RandomExpression(int count){ //constructor that calls the buildRandomExpression method
		random = new Random();
		exp = buildRandomExpression(count);
	}
	
	public String toString()
	{
		return exp.toString();
	}

	public double evaluate(double x, double y) {
		return exp.evaluate(x, y);
	}
	
	public Expression buildRandomExpression(int count)//randomly calls each of the classes' evaluate methods in order to build the expression
	{
		
		int num = random.nextInt(100);
		if(count>10 && num<=50)
			return new TerminalX();

		if(count>10 && num>50)
			return new TerminalY();
		
		if(num<10)
			return new TerminalX();
		
		if(num>10&&num<=20)
			return new TerminalY();
		if(num>20&&num<=35)
			return new Multiply(new RandomExpression(count+1),new RandomExpression(count+1));
		
		if(num>35&&num<=50)
			return new Cosine(new RandomExpression(count+1));
		
		if(num>50&&num<=70)
			return new Sine(new RandomExpression(count+1));
		
		if(num>70&&num<=80)
		{
			return new Average(new RandomExpression(count+1),new RandomExpression(count+1));
		}
		
		if(num>80&&num<91)
		{
			return new Power(new RandomExpression(count+1), new RandomExpression(count+1));
			
		}
		if(num>90)
			return new Power(new RandomExpression(count+1),new RandomExpression(count+1));
		
		
		
		return new RandomExpression(count);
	}

}
