/**
 * The purpose of this class is to hold a SpaceShip object and keep track of the time that
 * it has spent at the space port.
 * 
 * @author Troy Cornelius
 * @author Joel Ross (adapted)
 * @version Sp 2013
 */
public class SpaceShipValet
{
	private SpaceShip ship;
	private long timeIn;

	/**
	 * Default constructor for the SpaceShipValet. Each SpaceShipValet holds
	 * one SpaceShip which must be passed as a parameter when the valet is
	 * constructed.
	 * 
	 * @param s the SpaceShip that this SpaceShipValet will manage.
	 */
	public SpaceShipValet(SpaceShip s)
	{
		ship = s;
		timeIn = System.currentTimeMillis();
	}

	/**
	 * returns a reference to the SpaceShip this valet is in charge of.
	 * @return a reference to the SpaceShip this object refers to
	 */
	public SpaceShip getShip()
	{
		return ship;
	}

	/**
	 * Returns the key used to identify the ship that the valet is "parking"
	 * 
	 * @return the license of the ship that the valet is "parking"
	 */
	public String getKey()
	{
		return ship.getLicense();
	}

	/**
	 * This toString returns the toString output of the SpaceShip that this valet is in charge of.
	 * @return the toString of the SpaceShip this object refers to
	 */
	public String toString()
	{
		return ship.toString();
	}

	/**
	 * returns the difference between the time when the spaceship was
	 * checked in and when it was checked out in milliseconds.
	 * 
	 * @return the difference between the time checked in and the time
	 *         checked out in ms
	 */
	public long totalTimeIn()
	{
		return System.currentTimeMillis() - timeIn;
	}
}
