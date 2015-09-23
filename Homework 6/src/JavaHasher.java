/**
 * Basic Java hasher
 * @author darrenchu
 *
 */
public class JavaHasher implements ShipHasher
{
	/**
	 * Constructor for JavaHasher
	 */
	public JavaHasher()
	{
	}
	
	/**
	 * Calculates the hashCode for the license
	 * @param String license
	 * @return code Returns the hashcode
	 */
	public int hash(String license) 
	{
		int code = license.hashCode();
		return code;
	}
}
