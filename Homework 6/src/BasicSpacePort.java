
/**
 * 
 * @author darrenchu (Adapted from Joel Ross)
 *
 */
public class BasicSpacePort implements SpacePort<String, SpaceShipValet>, ShipHasher {

	private SpacePort<String, SpaceShipValet>[] shipPort;//Array that stores the amount of ships in the SpaceShip.
	private static final int INITIALCAPACITY = 101; //Create an initial capacity for the shipPort
	private static final double LOAD_THRESHOLD = .75; //set a constant 
	private int numKeys; //number of keys within the hashmap
	private SpacePortAuthority theAuthorities; //Create an instance of SpacePortAuthority
	private SpacePort<String, SpaceShipValet> DELETED = new SpacePort<String, SpaceShipValet>(null, null); //Create a SpaceShip with parameters that are null. Basic way to delete the SpaceShip.
	
	/**
	 * Constructor for the BasicSpacePort
	 */
	public BasicSpacePort()
	{
		shipPort = new SpacePort[INITIALCAPACITY]; //Instantiate the shipPort
		numKeys = 0;//Instantiate the number of ships
	}
	
	/**
	 * Returns an integer representation (hashcode) of the given license.
	 * @param license the license to hash
	 * @return the hashcode
	 */
	public int hash(String license) 
	{
		int hashcode = license.hashCode() % shipPort.length;//Get the hashCode associated with the license number

        if (hashcode < 0)
        	hashcode += shipPort.length;//Increase the hashcode so it is not negative

        while ((shipPort[hashcode] != null) && (!license.equals(shipPort[hashcode].license)))//Check the slot to see if same or not empty
        {
        	hashcode++;
            if (hashcode >= shipPort.length) 
            	hashcode = 0;
        }
        return hashcode;//return the hashcode
	}

	/**
     * Access a ship by its "key" aka license
     * @param  license    key for the space ship
     * @return        the space ship associated with this key, null if no such ship 
     */
	public SpaceShipValet access(String license) 
	{
		int index = hash(license);//Get index of ship through hashCode
		if(shipPort[index] != null)//If these is a ship at that indes
			return shipPort[index].ship;//Give us the ship
		else
			return null;//There isn't a ship in that space
	}
	
	/**
     * Docks a ship in the space port, registered with the given key. If the key
     * already exists, then the new SpaceShip takes its place (the old one is returned).
     * This method must report any collisions that occur to the SpacePortAuthority.
     * @param  license    key for the space ship
     * @param  ship  Space ship to be docked
     * @return        previous ship associated with this key 
     */
	public SpaceShipValet dock(String license, SpaceShipValet ship) 
	{
		int index = hash(license); //Get the index for the shipPort

		if(shipPort[index] == null) //If there is nothing at the slot
		{
			shipPort[index] = new SpacePort<String, SpaceShipValet>(license, ship);//Add a new ship at that index
			numKeys++;//Increment the number of keys
			double loadFactor = numKeys/(double)shipPort.length;
			if (loadFactor > LOAD_THRESHOLD)
				rehash();//call the rehash method to fix the hashtable
			return null;
		}

		assert(shipPort[index] != null);

		SpaceShipValet oldShip = shipPort[index].ship;//Store the ship into a temp variable
		shipPort[index].ship = ship;//set the ship port equal to the new ship
		return oldShip;//return the previous ship
	}
	
	/**
	 * Creates a new port in the hashmap by increasing the number of keys when the precious key has been filled. 
	 * The array is updated by twice the size in order to reduce the amount of collisions
	 */
	private void rehash() 
	{
        numKeys = 0;
        SpacePort<String, SpaceShipValet>[]  oldPort = shipPort; //Create a new port
        shipPort = new SpacePort[shipPort.length*2];

        for(int i = 0; i < oldPort.length; i++)//For each of the slots in the port
        {
            if(oldPort[i] != null && !oldPort[i].equals(DELETED))//Check if there anything in the oldPort
            {
                dock(oldPort[i].getLicense(), oldPort[i].getValue());//Add the ship to the old slot.
                numKeys++;//Increment the number of ships
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
		SpaceShipValet value = access(license);//access the ship through license number
		if(value != null)
			shipPort[hash(license)] = DELETED;//Set the shipPort to null. Removes the ship
		return value;
	}

	/**
	 * Returns the size of the shipPort
	 * @return int returns the size of the shipPort
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