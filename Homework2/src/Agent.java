
import java.awt.Point;
/**
 * An abstract class for agents (humans, panicked humans, and zombies)
 * @author Jessica Penick and Andrew Graham and Darren Chu
 *
 *
 */
public abstract class Agent
{
	public Point pos;//the point an agent wants to move to
	protected int dir; //direction 0, 1, 2, 3 for N, E, S, W
	protected City city; //the city to draw the agents in
	protected boolean isBit; //bite status of agent
	protected boolean isPanicked; //panic status of agent
	
	public Agent()
	{
		
	}

	protected abstract boolean lookForward(int x, int y);
	protected abstract Point orient();
	
	public abstract void move();
	public abstract int getDir();
	public abstract int getAgentType();
	public abstract void isBitten(boolean bitten);
	public abstract void isPanicked(boolean panicked);
}