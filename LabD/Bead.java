import java.util.Random;
import java.awt.Color;
/**
 * 
 * @author chris spalding, Darren Chu
 *
 */

public class Bead implements Comparable<Bead> {

	private int size;
	private Random gen;
	public Color color;
	public Bead next;
	
	/**
	 * @param args
	 */
	public Bead()
	{
		color = Palette.getRandomColor();
		setSize();
		next = null;
	}
	/**
	 * 
	 */
	@Override
	public int compareTo(Bead o) 
	{
		if(Palette.getIndex(color) > Palette.getIndex(o.color) || Palette.getIndex(color) == Palette.getIndex(o.color) && size < o.size)
		{
			return 1;
		}
		else if(Palette.getIndex(color) < Palette.getIndex(o.color) || Palette.getIndex(color) == Palette.getIndex(o.color) && size > o.size)
		{
			return -1;
		}
		else


		{
			return 0;
		}

	}

	/**
	 * sets the bead to a random size between 10 and 30 pixels
	 */
	public void setSize()
	{
	 gen = new Random();
	 size = gen.nextInt(21)+10;
	}

	/**
	 * returns the color of the bead
	 * @return Color the color of the bead
	 */
	public Color getColor() 
	{
		return color;
	}
	
	/**
	 * returns the size of the bead
	 */
	public int getSize()
	{
		return size;
	}
	

}
