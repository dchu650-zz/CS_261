/**
 * Basic hasher that I created! 
 * @author darrenchu
 *
 */
public class MyHasher implements ShipHasher 
{
	/**
	 * @param license the license to hash
	 * @return the code
	 */
	public int hash(String license) 
	{
		int code = license.hashCode()%11;//Use a modular to offset the slots!
		return code;
	}

}
