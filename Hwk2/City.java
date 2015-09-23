import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


/**
 * A city to hold and model the zombie outbreak.
 * Some functionality based on the original simulation, http://kevan.org/proce55ing/zombies/.
 * 
 * @author joel
 * @version Sp13
 */
public class City
{
	//constants
	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
	public static final int POPULATION = 5000;
	public static final Color ROAD_COLOR = Color.BLACK;
	public static final Color WALL_COLOR = Color.GRAY;

	private BufferedImage map; //hold the drawing of the map

	private boolean[][] walls; //a two-dimension array of whether a particular pixel is a wall or not

	private BufferedImage image; //holds the drawing of the whole city (re-used to speed rendering);
	private Graphics2D img2d; //a graphics context of the image that we draw on		
	private Random gen = new Random();

	private ArrayList<Point> pointList; //a list of spaces available to Agents
	private Agent[][] agents; //list of humans in the city

	/**
	 * Creates a new City object with walls, people, and a single zombie...
	 */
	public City()
	{
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB); //initialize the image canvas thing
		img2d = image.createGraphics();

		agents = new Agent[WIDTH][HEIGHT];//make an array of agents
		pointList = new ArrayList<Point>();

		generateWalls();
		legalSpace();

		Human h = null;
		for(int i=0; i<POPULATION; i++) //populate the city
		{
			h = new Human(this);
			agents[h.pos.x][h.pos.y] = h; //add each human to the list
			pointList.remove(h.pos); //remove this point from legal spaces
		}
		Zombie patient0 = new Zombie(h);
		agents[h.pos.x][h.pos.y] = patient0;
		
		Agent unlucky = null;
				while (unlucky == null)
				{
					unlucky = agents[gen.nextInt(WIDTH)][gen.nextInt(HEIGHT)];
				}
		Zombie patient1 = new Zombie(unlucky);
		agents[unlucky.pos.x][unlucky.pos.y] = patient1;
		
	}


	/**
	 * A method that creates walls and rooms in the city. In effect initializes the map.
	 */
	public void generateWalls()
	{
		map = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB); //our current drawing
		Graphics2D mapg2d = map.createGraphics();
		mapg2d.setPaint(ROAD_COLOR);
		mapg2d.fillRect(0, 0, WIDTH, HEIGHT);
		mapg2d.setPaint(WALL_COLOR); //default color
		mapg2d.fillRect(0, 0, WIDTH, HEIGHT); //empty the city

		mapg2d.setPaint(ROAD_COLOR);
		//draw some "rooms"
		for(int i=0; i<50; i++)
			mapg2d.fillRect(gen.nextInt(WIDTH), gen.nextInt(HEIGHT), 20+gen.nextInt(40), 20+gen.nextInt(20));

		//draw some "hallways"
		for(int i=0; i<100; i++)
			mapg2d.drawRect(gen.nextInt(WIDTH), gen.nextInt(HEIGHT), 10+gen.nextInt(60), 10+gen.nextInt(60));

		//add a border around the edge, so people don't wander outside
		mapg2d.setPaint(WALL_COLOR);
		mapg2d.drawRect(0,0,WIDTH-1,HEIGHT-1);

		walls = new boolean[WIDTH][HEIGHT];

		//go through the image and calculate walls (for speed later)
		int wallRGB = WALL_COLOR.getRGB(); //we use color to determine if there is a wall
		for(int i=0; i<WIDTH; i++)
		{
			for(int j=0; j<HEIGHT; j++)
			{
				if(map.getRGB(i,j)==wallRGB)
					walls[i][j] = true;
			}
		}
	}

	/**
	 * Renders and returns an image of the current state of the city (map and people)
	 * @return an Image of the city that can be drawn somewhere.
	 */
	public Image getMap()
	{
		img2d.drawImage(map, 0, 0, null); //draw the map onto this new image. Will overwrite old stuff, so creates a new frame

		for(int i=0; i<WIDTH; i++)
			for(int j=0; j<HEIGHT; j++)
				if(agents[i][j] != null)
					//					if(image.getRGB(i, j) != Color.PINK.getRGB())
				{
					Agent h = agents[i][j];
					
					if(h.getAgentType() == 0)//Human
						if(h.isBit == false && h.isPanicked == false)//if they're not bitten
							image.setRGB(h.pos.x, h.pos.y, Color.PINK.getRGB()); //draw the human there
						else if(h.isPanicked == true)
						{
							PanickedHuman pH = new PanickedHuman(h); //make a new zombie
							agents[h.pos.x][h.pos.y] = pH; //replace the human
						}	
						else 
						{
							Zombie z = new Zombie(h); //make a new zombie
							agents[h.pos.x][h.pos.y] = z; //replace the human
						}
					if(h.getAgentType() == 1)//Panicked Human
						image.setRGB(h.pos.x, h.pos.y, Color.YELLOW.getRGB()); //draw the PanickedHuman there
					if(h.getAgentType() == 2)//Zombie
						image.setRGB(h.pos.x, h.pos.y, Color.GREEN.getRGB()); //draw the PanickedHuman there
						
				}

		return image;
	}

	public void tick()
	{
		for(int i=0; i<City.WIDTH; i++)
			for(int j=0; j<City.HEIGHT; j++)
				if(agents[i][j] != null)
				{
					Agent a = agents[i][j];
					if(a.getAgentType() == 0)//human
						if(gen.nextInt(2)==1)//moves 50% of the time
							a.move();
					if(a.getAgentType() == 1)//panicked human ((moves every time))
							a.move();
					if(a.getAgentType() == 2)//zombie
						if(gen.nextInt(5)==1)//moves 20% of the time
							a.move();
				}
	}

	/**
	 * Tells whether or not there is a wall at the specified coordinates
	 * @param x X coordinate to check
	 * @param y Y coordinate to check
	 * @return A boolean of whether or not there is a wall there (true if there is a wall)
	 */
	public boolean getWall(int x, int y)
	{
		boolean wall = walls[x][y];
		return wall;
	}

	/**
	 * Gets the array of agents currently on the map
	 * @return list of agents on the map
	 */
	public Agent[][] getAgents() 
	{
		return agents;
	}

	/**
	 * Gets the list of legal points
	 * @return list of "legal" points for agents
	 */
	public ArrayList<Point> getPointList() 
	{
		return pointList;
	}
	
	public int rollCall()
	{
		int roll = 0;
		for(int i=0; i<WIDTH; i++)
			for(int j=0; j<HEIGHT; j++)
				if(agents[i][j] != null)
					roll++;
		return roll;
	}

	/**
	 * A helper method that creates a list of all the spaces available to Agents
	 */
	public void legalSpace()
	{
		for(int i=0; i<City.WIDTH; i++)
		{
			for(int j=0; j<City.HEIGHT; j++)
			{
				if(walls[i][j] == false)//if there is no wall
					if(agents[i][j] == null)//and if there are no agents
						pointList.add(new Point(i, j));//add to the list!
			}
		}
	}
}
