/**
 * A class that monitors a SpacePort; also handles the finances of the port!
 * @author joel
 * @version Apr 14, 2013
 */
public class SpacePortAuthority
{
	//prices and penalties
	public static final int DOCKING_PRICE = 3; //charged per ms
	public static final int EXPANSION_COST = 10;
	public static final int DELAY_PENALTY = 1; //per ms
	public static final int COLLISION_PENALTY = 100;

	public SpacePort<String, SpaceShipValet> port; //the port we're monitoring
	
	private int revenue; //earned revenue
	private int numCollisions; //number of collisions
	private int expansionCosts; //cost of expansions
	private int delayCosts; //costs of delays
	private int shipsServed;

	/**
	 * Creates a new SpacePortAuthority for the given SpacePort
	 * Note that currently the Authority only handles valet ports (where the valet keeps track of time docked)
	 * @param spacePort the port to monitor
	 */
	public SpacePortAuthority(SpacePort<String, SpaceShipValet> spacePort)
	{
		port = spacePort;
		port.setSpacePortAuthority(this); //tell the port we are watching!
		revenue = 0; //intialize earnings/penalties
		numCollisions = 0;
		expansionCosts = 0;
		delayCosts = 0;
	}

	/**
	 * Responds to a report of a collision
	 */
	public void reportCollision()
	{
		numCollisions++;
	}
	
	/**
	 * Responds to the report of an expansion
	 * @param sizeIncrease The the amount the size of the SpacePort increased (newSize - oldSize)
	 */
	public void reportExpansion(int sizeIncrease)
	{
		expansionCosts += EXPANSION_COST*sizeIncrease*sizeIncrease; //cost proportional to square of new space
	}

	/**
	 * Checks a spaceship in to the SpacePort
	 * @param s the SpaceShip to check in
	 * @return Whether or not there was already someone in the berth (so had the same license)
	 */
	public boolean checkIn(SpaceShip s)
	{
		shipsServed++;
		SpaceShipValet valet = new SpaceShipValet(s);
		
		long start = System.currentTimeMillis();
		SpaceShipValet previous = port.dock(valet.getKey(), valet);
		long total = System.currentTimeMillis() - start; //time to dock
		delayCosts += total*DELAY_PENALTY; //costs for docking time
		return (previous == null);
	}

	/**
	 * Check out a spaceship from the SpacePort
	 * @param s the SpaceShip to check out
	 * @return whether or not the SpaceShip was removed (false if the ship wasn't there to begin with!)
	 */
	public boolean checkOut(SpaceShip s)
	{
		long start = System.currentTimeMillis();
		SpaceShipValet valet = port.remove(s.getLicense());
		if (valet == null) 
		{
			return false;
		}
		long total = System.currentTimeMillis() - start; //time to retrieve
		delayCosts += total*DELAY_PENALTY; //costs for retrieving time
		revenue += valet.totalTimeIn()*DOCKING_PRICE; //charge the customer
		return true;
	}

	/**
	 * Returns a report of current earnings for the SpacePort
	 * @return a String (to print out) detailing the earnings of the port.
	 */
	public String budgetReport()
	{
		int collisionCosts = numCollisions*COLLISION_PENALTY;
		String out = "*****************\n";
		out += 		 "* BUDGET REPORT *\n";
		out += 		 "*****************\n";
		out +=		 "Customers served: "+shipsServed+"\n";
		out += 		 "  the space port currently hosts "+port.size()+" ships\n\n";
		out +=		 "Revenue earned:     + "+revenue+" creds\n";
		out +=		 "Expansion costs:    - "+expansionCosts+" creds\n";
		out += 		 "Delay costs:        - "+delayCosts+" creds\n";
		out +=		 "Collisions caused: "+numCollisions+"\n";
		out += 		 "        penalties:  - "+collisionCosts+" creds\n";
		out +=       "                    ---------------\n";
		out +=		 "TOTAL EARNINGS:         "+(revenue-(expansionCosts+delayCosts+collisionCosts))+"\n";
		return out;
	}
}
