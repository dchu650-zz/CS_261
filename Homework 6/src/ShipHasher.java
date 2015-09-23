/**
 * An interface for "hashing" the license of a Ship (for inserting into a HashMap).
 * Different implementations can reflect different hashing algorithms.
 */
public interface ShipHasher
{
	/**
	 * Returns an integer representation (hashcode) of the given license.
	 * @param license the license to hash
	 * @return the hashcode
	 */
	public int hash(String license);
}
