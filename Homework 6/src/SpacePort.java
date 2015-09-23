/**
 * An interface representing a SpacePort (generally a hash map mapping Strings to SpaceShips)
 * 
 * @author Joel Ross (adapted from Chuck Hommel)
 * @version Sp2013
 */
public interface SpacePort<K, V>
{
	/**
     * Access a ship by its "key"
     * @param  key    key for the space ship
     * @return        the space ship associated with this key, null if no such ship 
     */
    public V access(K key);
    
    /**
     * Docks a ship in the space port, registered with the given key. If the key
     * already exists, then the new SpaceShip takes its place (the old one is returned).
     * This method must report any collisions that occur to the SpacePortAuthority.
     * @param  key    key for the space ship
     * @param  value  Space ship to be docked
     * @return        previous ship associated with this key 
     */
    public V dock(K key, V value);

    /** 
     * Removes a ship from the space port
     * @param  key    key for the space ship
     * @return 		  the space ship associated with the key, null if the ship is lost
     */
    public V remove(K key);

    /**
     * Gets the number of ships currently docked in the space port
     * @return    the number of items in the port
     */
    public int size();

    /**
     * Sets the current authority that is overseeing this space port
     * @param spa The authority for the space port
     */
    public void setSpacePortAuthority(SpacePortAuthority spa);

    /**
     * Returns the current authority that is overseeing this space port
     * @return The authority for the space port
     */
    public SpacePortAuthority getSpacePortAuthority();
}
