import java.util.LinkedList;

public class DimensionalSpacePort implements SpacePort<String, SpaceShipValet>
{
	private LinkedList<SpacePort<String, SpaceShipValet>>[] chainedPortList;
	private static final int INITIALCAPACITY = 101; //Create an initial capacity for the chainedPortList
	private static final double LOAD_THRESHOLD = 3; //set a constant 
	private int numKeys; //number of keys within the hashmap
	private SpacePortAuthority theAuthorities; //Create an instance of SpacePortAuthority
	private SpaceShipValet DELETED = new SpaceShipValet(null); //Create a SpaceShip with parameters that are null. Basic way to delete the SpaceShip.
	//private boolean checkSpacePort; Never used. Initially used as a checker for if the ship has left the spaceport or not.
	
	/**
	 * Basic Constructor for the DimentionalSpacePort
	 */
	public DimensionalSpacePort()
	{
		chainedPortList = new LinkedList[INITIALCAPACITY];
		numKeys = 0;
		//checkSpacePort = false;
		//No need for the checkSpacePort
	}

	/**
     * Access a ship by its "key"
     * @param  license    license for the space ship
     * @return        the space ship associated with this key, null if no such ship 
     */
	public SpaceShipValet access(String license) 
	{
		int index = license.hashCode() % chainedPortList.length; //Find the index based on the hashCode based on the first letter of the license
		if(index < 0)
			index += chainedPortList.length;
		if(chainedPortList[index] == null)
			return null;
		for(SpacePort<String, SpaceShipValet> nextShip : chainedPortList[index]) //Go through the entire SpacePort
		{
			if(nextShip.license.equals(license))//See if there is already a slot with a ship
				return nextShip.ship;
		}
		return null;
	}

	/**
     * Docks a ship in the space port, registered with the given key. If the key
     * already exists, then the new SpaceShip takes its place (the old one is returned).
     * This method must report any collisions that occur to the SpacePortAuthority.
     * @param  license    license for the space ship
     * @param  SpaceShipValet  Space ship to be docked
     * @return        previous ship associated with this key 
     */
	public SpaceShipValet dock(String license, SpaceShipValet ship) 
	{
		int index = license.hashCode() % chainedPortList.length; //Find the index based on the hashCode based on the first letter of the license
		if(index < 0)
			index += chainedPortList.length;
		
		if(chainedPortList[index] == null)
		{
			chainedPortList[index] = new LinkedList<SpacePort<String, SpaceShipValet>>();//Create a new linked list in the index! Line of code that sets the chaining
		}
		
		for(SpacePort<String, SpaceShipValet> nextSpacePort : chainedPortList[index]) //Go through the entire SpacePort
		{
			if(nextSpacePort.license.equals(license))//See if there is already a slot with a ship
			{
				SpaceShipValet previousShip = nextSpacePort.ship;
				nextSpacePort.setValue(ship);//Create the link from the first ship to the next ship in the LinkedList
				return previousShip;
			}
		}
		
		assert(chainedPortList[index] != null);
		chainedPortList[index].addFirst(new SpacePort<String, SpaceShipValet>(license, ship));//Add the Ship to the list if there is nothing there initially.
		numKeys++;//Increment the number of items
		if(numKeys > (LOAD_THRESHOLD * chainedPortList.length))
			rehash();
		return null;
		
	}

	/**
	 * Creates a new port in the hashmap by increasing the number of keys when the precious key has been filled. 
	 * The array is updated by twice the size in order to reduce the amount of collisions
	 */
	private void rehash() 
	{
		numKeys++;
        LinkedList<SpacePort<String, SpaceShipValet>>[]  oldPort = chainedPortList;
        chainedPortList = new LinkedList[chainedPortList.length*2];

        for(int i = 0; i < oldPort.length; i++)
        {
            if(oldPort[i] != null && !oldPort[i].equals(DELETED))//If there is something in the old port,
            {
            	for(SpacePort<String, SpaceShipValet> ports: oldPort[i])//For all the slots in the SpacePort
            		dock(ports.license, ports.ship);//Add a ship
            	numKeys++;//Increments the number of slots
            }
        }	

	}


	/** 
     * Removes a ship from the space port
     * @param  license    license for the space ship
     * @return 		  the space ship associated with the license, null if the ship is lost
     */
	public SpaceShipValet remove(String license) 
	{
		int index = license.hashCode() % chainedPortList.length;
		if(index<0)
			index += chainedPortList.length;
		if(chainedPortList[index] == null)
			return null;
		for(SpacePort<String, SpaceShipValet> nextSpacePort : chainedPortList[index])//For all of the slots in the SpacePort
		{
			if(nextSpacePort.license.equals(license))//If there is a match
			{
				nextSpacePort.ship = DELETED;//Set that ship to null
				if(chainedPortList[index]==null)//If it has been set null
					nextSpacePort.setValue(null);//The value of the port is null as well. Theses three steps will allow us to dock ships in the future!
				return nextSpacePort.ship;
			}
		}
		return null;	
	}

	/**
	 * Returns the size of the hashTable
	 * @return int numKeys
	 */
	public int size() 
	{
		return numKeys;
	}

	/**
	 * Sets anew SpacePortAuthority
	 * @param SpacePortAuthourity spa
	 */
	public void setSpacePortAuthority(SpacePortAuthority spa) 
	{
		theAuthorities = spa;
	}

	/**
	 * Returns a SpacePortAuthority
	 * @return SpacePortAuthoruty theAuthorities 
	 */
	public SpacePortAuthority getSpacePortAuthority() 
	{
		return theAuthorities;
	}

	/**
	 * Basic static class that can be implemented and used throughout the BasicSpacePort
	 * Contains basic getters and setters for the SpacePort
	 * @author darrenchu
	 *
	 * @param <String>
	 * @param <SpaceShipValet>
	 */
    private static class SpacePort<String, SpaceShipValet>
    {
        private String license;
        private SpaceShipValet ship;

        public SpacePort(String license, SpaceShipValet ship) 
        {
            this.license = license;
            this.ship = ship;
        }

        public String getLicense() 
        {
            return license;
        }

        public SpaceShipValet getValue() 
        {
            return ship;
        }

        public SpaceShipValet setValue(SpaceShipValet S) 
        {
            SpaceShipValet oldShip = ship;
            ship = S;
            return oldShip;
        }
    }


}
