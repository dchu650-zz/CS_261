import java.awt.Point;
import java.util.Random;

/**
 * A Human object that moves, orients its movement to the direction its facing and looks forward
 * @author Jessica Penick and Andrew Graham and Darren Chu
 *
 */
public class Zombie extends Agent
{
	private Random rando;
	private Point possPoint; //point the Zombie wants to move to
	private final int SIGHT_RANGE = 11; //number of steps ahead Zombie can see
	private final int AGENT_TYPE = 2; //Zombie type agent

	public Zombie(Agent human)
	{
		rando = new Random();
		isBit = true; //all zombies are bitten
		isPanicked = false; //zombies are never panicked!
		dir = rando.nextInt(4);
		city = human.city; //take the human's city
		pos = human.pos; //take the human's position
	}

	/**
	 * Matches proposed x and y coordinates to direction facing (neg y for North, etc)
	 */
	public Point orient()
	{
		Point possPoint = pos;
		if(dir == 0)//North
		{
			int y = pos.y - 1; //make y negative to move North
			possPoint = new Point(pos.x, y);
		}
		else if(dir == 1)//East
		{
			int x = pos.x + 1; //make x positive to move East
			possPoint = new Point(x, pos.y);
		}
		else if(dir == 2)//South
		{
			int y = pos.y + 1; //make y positive to move South
			possPoint = new Point(pos.x, y);
		}
		else if(dir == 3)//West
		{
			int x = pos.x - 1; //make x negative to move West
			possPoint = new Point(x, pos.y);
		}
		return possPoint;
	}

	/**
	 * Moves the Agent
	 */
	public void move()
	{
		possPoint = orient(); //orient the direction facing with the step to take 

		if(lookAhead(possPoint.x, possPoint.y) == false)
		{
			dir = rando.nextInt(4);
		}
		else
		{
			if(!lookForward(possPoint.x, possPoint.y)) //if there's no obstacles
			{
				//System.out.println("Hiya!");			
				city.getAgents()[possPoint.x][possPoint.y] = this;//make those coordinates yours
				pos.x = possPoint.x;
				pos.y = possPoint.y;
			}
			else if(city.getAgents()[possPoint.x][possPoint.y]!=null)
				this.changeDir(possPoint.x,possPoint.y);
		}
	}

	/**
	 * Allows the agent to see ahead.
	 * @return Returns true if there is an obstacle
	 */
	public boolean lookForward(int x, int y)
	{
		boolean obstacle = false;

		if(city.getAgents()[x][y] != null)//check for Humans!
			if(city.getAgents()[x][y].getAgentType() == 0 || city.getAgents()[x][y].getAgentType() == 1)//if agent is human
				city.getAgents()[x][y].isBitten(true);//bite them
		//obstacle is true
		if(city.getWall(x, y))//check for wall obstacle
			obstacle = true;
		return obstacle;

	}

	public boolean lookAhead(int x, int y)
	{
		boolean doesSee = false;
		for(int i=0; i<SIGHT_RANGE; i++)
		{
			if(dir == 0)//North
			{
				y = pos.y - i; //make y negative to move North
				if(x>0 && x<city.WIDTH && y>0 && y<city.HEIGHT)
					if(city.getWall(x, y) == false)
						if(city.getAgents()[x][y] != null)
							doesSee = true;
			}
			else if(dir == 1)//East
			{
				x = pos.x + i; //make x positive to move East
				if(x>0 && x<city.WIDTH && y>0 && y<city.HEIGHT)
					if(city.getWall(x, y) == false)
						if(city.getAgents()[x][y] != null)
							doesSee = true;
			}
			else if(dir == 2)//South
			{
				y = pos.y + i; //make y positive to move South
				if(x>0 && x<city.WIDTH && y>0 && y<city.HEIGHT)
					if(city.getWall(x, y) == false)
						if(city.getAgents()[x][y] != null)
							doesSee = true;
			}
			else if(dir == 3)//West
			{
				x = pos.x - i; //make x negative to move West
				if(x>0 && x<city.WIDTH && y>0 && y<city.HEIGHT)
					if(city.getWall(x, y) == false)
						if(city.getAgents()[x][y] != null)
							doesSee = true;
			}
		}
		return doesSee;
	}
	private void changeDir(int x, int y)
	{
		int otherDir = city.getAgents()[x][y].getDir(); //get the dir of the offfending agent
		if( otherDir != dir)// if their dir are different
			if(Math.abs(otherDir-dir)==2) //and if they are opposites
			{
				//make a list of potential directions (all dir minus current dir)
				//choose one randomly
				int d = rando.nextInt(1);
				if(d == 1)
				{	
					dir++;
					if(dir == 4)
					{
						dir = 0;
					}
				}
				if(d == 0)
				{
					dir--;
					if(dir == -1)
					{
						dir = 3;
					}
				}
			}
	}

	/**
	 * Gets  this agent's type
	 * @return 0 for Human, 1 for PanickedHuman, or 2 for Zombie 
	 */
	public int getAgentType()
	{
		return AGENT_TYPE;
	}
	/**
	 * Getter for direction
	 */
	public int getDir() 
	{
		return dir;
	}

	@Override
	public void isBitten(boolean bitten) {
		// TODO Auto-generated method stub

	}

	@Override
	public void isPanicked(boolean panicked) {
		// TODO Auto-generated method stub

	}
}
