import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;

import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A class that runs a simulation of the SpacePort program. Acts as a "tester"
 * @author Joel
 * @version Sp 2013
 */
public class SpacePortSimulation
{
	//main method for running the simulation. You can modify the which port used for the simulation here
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Welcome to the StarTrek Enterprise Cargo Simulation");
		System.out.println("Press 1 to begin the simulation");
		int num = 0;
		Scanner reader = new Scanner(System.in);
		num = reader.nextInt();
		MyHasher hasher = new MyHasher(); //Use the Hasher that I created
		SpacePort port = new DimensionalSpacePort(); //Change the Spaceport input here
		
		SpacePortSimulation sim = new SpacePortSimulation(new SpacePortAuthority(port)); //make the sim
		if(num == 1)//Add a scanner input
		{
			System.out.print("Simulation beginning in...");
			for(int i = 0; i<5; i++) //Countdown
			{
				int initiate = 5-i;
				System.out.println("" + initiate);
				Thread.sleep(1000); //Slows the countdown 
			}

			System.out.println("Blast Off!");
			sim.runSimulation(100); //run the sim for the given number of "events"
		}
		else
		{
			System.out.println("You have successfully exited the simulation. Good luck with you and your future endevors.");
		}
	}
	
	
	//instance variable for the simulation
	private ArrayList<SpaceShip> ships;
	private SpacePortAuthority spa;
	private LinkedList<SpaceShip> dockedShips;//Create a linkedlist that makes a check of whether the spaceship has been docked or not.
	
	/**
	 * Makes a new simulation for the given Authority (which controls a particular port)
	 */
	public SpacePortSimulation(SpacePortAuthority spa)
	{
		dockedShips = new LinkedList<SpaceShip>();//instantiate a new dockedship list
		ships = loadShipsFromFile("ships.txt");
		this.spa = spa;
	}
	
	/**
	 * Runs the simulation for the given number of events (either a ship trying to arrive or trying to leave).
	 * Note that this simulation is pretty dumb: random ships try to arrive or leave, with no indication of whether
	 * They were in the dock in the first place. Feel free to modify this and make it more sensible!
	 */
	public void runSimulation(int numTests)
	{
		
        Random r = new Random();

        for(int i=0; i<numTests; i++)
        {
        	SpaceShip s = ships.get(r.nextInt(ships.size()));
        	boolean comingIn = true;
        	if(!dockedShips.contains(s)) //If the ship we are looking at is not been docked yet
        	{
        		comingIn = true;
        		dockedShips.add(s);//add the ship to the dockedship list
        	}
        	else
        	{
        		comingIn = false;	
        		dockedShips.remove(s);//remove the ship
        	}
        	 
        	boolean success = false;
        	if(comingIn)
        	{
        		try
        		{
        			Thread.sleep(r.nextInt(100));
        		}
        		catch(Exception e)
        		{
        			
        		}
        		System.out.print("Checking in " + s.getLicense() +  " carrying " + s.getCargo() + " ... ");
                success = spa.checkIn(s);
        	}
        	else //goingOut
        	{
        		try
        		{
        			Thread.sleep(r.nextInt(100));
        		}
        		catch(Exception e)
        		{
        			
        		}
        		System.out.print("Checking out " + s.getLicense() + " ... ");
                success = spa.checkOut(s);
               
        	}
                
            if (!success && !dockedShips.contains(s))
            {
                System.out.println(" Operation Failed. Intergalactic enforcement has been notified.");
            }
            else
            {
                System.out.println(" Successful action in the simulation! ");
            }
        }
        
        System.out.println();
        System.out.println(spa.budgetReport());
	}
	
	/**
	 * Helper method to load a list of ships from a file. Ship files have a new ship on each line, with a comma separating
	 * the license and the cargo.
	 * NOTE: You can use your random sentence generator to make longer or more interesting lists of ships!
	 * @param filename The file to load
	 * @return An ArrayList of ships
	 */
	public ArrayList<SpaceShip> loadShipsFromFile(String filename)
	{
		File f = null;
		if(filename != null)
			f = new File(filename);
		else
		{
			JFileChooser chooser = new JFileChooser(); //a file chooser
			chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt")); //for txt files
			int returnVal = chooser.showOpenDialog(chooser); //show the dialog to OPEN
			if(returnVal == JFileChooser.APPROVE_OPTION) //if they picked something
				f = chooser.getSelectedFile();
		}

		if(f != null)
		{
			ArrayList<SpaceShip> ships = new ArrayList<SpaceShip>();
			try {
				Scanner sc = new Scanner(f); //parse it with Scanner
				while(sc.hasNextLine())
				{
					String line = sc.nextLine();
					String[] parts = line.split(",");
					ships.add(new SpaceShip(parts[0],parts[1]));
				}
				sc.close();
				return ships;
			}
			catch(Exception e) {
				System.out.println("Error loading from file: "+e);
				e.printStackTrace();
			}
		}		
		
		return null;
	}

}
