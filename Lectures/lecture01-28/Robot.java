
/**
 * 
 * @author joel
 * @version Jan 27, 2013
 */
public class Robot
{
	//instance variables
	private int xPosition;
	private int yPosition;
	private Direction direction; //0, 1, 2, 3 for North East South West
	//private String facing = "North";
	
	//enumerated type (enum)
	public enum Direction {NORTH, EAST, SOUTH, WEST};
	
	public Robot()
	{
		xPosition = 0;
		yPosition = 0;
		direction = Direction.NORTH;
	}
	
	public void turnLeft()
	{
		if(direction == Direction.NORTH)
		{
			
		}
		//...
	}
	
	public void moveForward()
	{
		//...
	}
	
	
	
}
