import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ArrayList;


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
	public int wid;
	public int hei;
	public int num;
	public int num2;
	public int num3;
	public int num4;
	
	private BufferedImage map; //hold the drawing of the map
	private Human human;
	private Zombie zombie;
	private boolean[][] walls; //a two-dimension array of whether a particular pixel is a wall or not
	private ArrayList<Agent> agent;
	
	private BufferedImage image; //holds the drawing of the whole city (re-used to speed rendering);
	private Graphics2D img2d; //a graphics context of the image that we draw on	
	private Random gen = new Random();
	
	
	/**
	 * Creates a new City object with walls, people, and a single zombie...
	 */
	public City()
	{
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB); //initialize the image canvas thing
		img2d = image.createGraphics();
		generateWalls();
		agent = new ArrayList<Agent>();
		wid = gen.nextInt(WIDTH);
		hei = gen.nextInt(HEIGHT);
		num = 20+gen.nextInt(40);
		num2 = 20+gen.nextInt(20));
		num3 = 10+gen.nextInt(60);
		num4 = 10+gen.nextInt(60);
	    for(int i = 0; i<999; i++)
	    {
	    	human = new Human();
	    	agent.add(human);
	    }
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
			mapg2d.fillRect(wid, hei, num, num2);

		//draw some "hallways"
		for(int i=0; i<100; i++)
			mapg2d.drawRect(wid, hei, num3, num4);

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
	
	public boolean[][] getWalls()
	{
		return walls;
	}
	
	/**
	 * 
	 */
	public void tick()
	{
		
	}
	
	/**
	 * Renders and returns an image of the current state of the city (map and people)
	 * @return an Image of the city that can be drawn somewhere.
	 */
	public Image getMap()
	{
		Color myGreen = new Color(0, 250, 154);
		int rgb=myGreen.getRGB();
		int wallRGB = WALL_COLOR.getRGB();
		img2d.drawImage(map, 0, 0, null); //draw the map onto this new image. Will overwrite old stuff, so creates a new frame
		//TODO Draw the humans and the zombies on the map. Note that you can add them directly to the 'image' canvas using the setRGB() method of the BufferedImage class.
		for(Agent a : agent)
		{
				{
					//if(a.getX() < num && a.getX() > num && a.getY() < num2 && a.getY() > num2)
					for(int i=0; i<WIDTH; i++)
					{
						for(int j=0; j<HEIGHT; j++)
						{
							if(map.getRGB(i,j)==wallRGB)
							{
								walls[i][j] = true;
								if( walls[i][j]==false && a.getX()<num3 && a.getY()< num4 && a.getX() <=wid && a.getY() <=hei)
								{
								map.setRGB(a.getX(),a.getY(), rbg);
								}
							}
							
								
							
						}
					}
				}
		}
		return image;
	}
	
}
