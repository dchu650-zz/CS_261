import java.awt.Color;
import java.awt.Graphics2D;
import java.io.PrintStream;

/*
 * @author Eli Albarran and Andrew Graham 
 * @version 3.05.13
 * This class is incomplete however it does draw a section of a fractal tree
 */
public class TheFractaleTree extends FractalDrawing
{
	
	public TheFractaleTree(String title, int width, int height)
	{
		
		super(title, width, height);
		
		drawTree(250, 250, -Math.PI/2, Math.PI/6, 50, 100, 0.9); //draws tree at specified dimensions
		
		
	}

	public void drawTree(int x, int y, double trunkAngle, double splitAngle, int length, int iterations, double ratio)
	{

		if (iterations == 0)
		{
			return;
		}
		else //for all cases where i!=0
		{	
			int newX =  (int) (x-(length*Math.cos(trunkAngle))); //local variable stores where next x should be
			int newY =  (int) (y+(length*Math.sin(trunkAngle))); //local variable stores where next y should be
 			length = (int) (length*ratio); //reduce the length of the next iteration
			trunkAngle = trunkAngle+splitAngle; //change the angle of the next iteration
			graphics.drawLine(x,y,newX,newY); //draw the line
			iterations--; //decrease the iterations
			drawTree(newX, newY, trunkAngle-2*splitAngle, splitAngle, length, iterations, ratio); //recurse
		}
	}



	public void drawFractal()
	{
	}
}