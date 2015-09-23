import java.awt.Point;
import java.util.Random;

/**
 * A Human object that moves, orients its movement to the direction its facing and looks forward
 * @author Jessica Penick and Andrew Graham and Darren Chu
 *
 */
public class PanickedHuman extends Agent
{
	private Random rando;
	private Point possPoint; //point the Zombie wants to move to
	private final int SIGHT_RANGE = 11; //number of steps ahead Zombie can see
	private final int AGENT_TYPE = 2; //Zombie type agent

	public PanickedHuman(Agent human)
	{
		rando = new Random();
		isBit = false; //all zombies are bitten
		isPanicked = true; //zombies are never panicked!
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
		//		int possX = pos.x + 1; //propose a point
		//		int possY = pos.y;
		//		Point possPoint = new Point(possX, possY); //make a temporary point out of the proposal
		possPoint = orient(); //orient the direction facing with the step to take 

		if(!lookForward(possPoint.x, possPoint.y)) //if there's no obstacles
		{
			if(lookAhead(possPoint.x, possPoint.y) == true)
			{
				if(dir == 0) //changes the direction to the opposite facing. (North to South)
					dir = 2;
				if(dir == 1) //changes the direction to the opposite facing. (East to West)
					dir = 3;
				if(dir == 2) //changes the direction to the opposite facing. (South to North)
					dir = 0;
				if(dir == 3) //changes the direction to the opposite facing. (West to East)
					dir = 1;
			}
			//System.out.println("Hiya!");			
			city.getAgents()[possPoint.x][possPoint.y] = this;//make those coordinates yours
			pos.x = possPoint.x;
			pos.y = possPoint.y;
		}
		else if(city.getAgents()[possPoint.x][possPoint.y]!=null) // makes sure that there is an agent at those points
		{
			this.changeDir(possPoint.x,possPoint.y);
		}

		if(rando.nextInt(5)==1)
		{
			dir = rando.nextInt(4); //set a random direction
		}


	}

	/**
	 * Allows the agent to see ahead.
	 * @return Returns true if there is an obstacle
	 */
	public boolean lookForward(int x, int y)
	{
		boolean obstacle = false;

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
				if(city.getAgents()[x][y] != null)
					if(city.getWall(x, y) == false)
						if(city.getAgents()[x][y].getAgentType() == 2)
							doesSee = true;
			}
			else if(dir == 1)//East
			{
				x = pos.x + i; //make x positive to move East
				if(city.getAgents()[x][y] != null)
					if(city.getWall(x, y) == false)
						if(city.getAgents()[x][y].getAgentType() == 2)
							doesSee = true;				
			}
			else if(dir == 2)//South
			{
				y = pos.y + i; //make y positive to move South
				if(city.getAgents()[x][y] != null)
					if(city.getWall(x, y) == false)
						if(city.getAgents()[x][y].getAgentType() == 2)
							doesSee = true;
			}
			else if(dir == 3)//West
			{
				x = pos.x - i; //make x negative to move West
				if(city.getAgents()[x][y] != null)
					if(city.getWall(x, y) == false)
						if(city.getAgents()[x][y].getAgentType() == 2)
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

	/**
	 * Switches bitten status
	 * @param bitten 
	 */
	public void isBitten(boolean bitten) 
	{
		isBit = bitten;
	}

	/**
	 * Switched panicked status
	 * @param panicked
	 */
	public void isPanicked(boolean panicked) 
	{
		isPanicked = panicked;
	}
}
