import java.awt.Color;

/**
 * 
 * @authors chris spalding, Darren Chu
 */
public class Necklace {
	
	private int size;
	private Bead head; //first bead in the list
	

	/**
	 * Constructor for objects of class Necklace
	 */
	public Necklace() 
	{
		size = 0; //there aren't any objects in the list yet
		head = null;
	}
	
	/**
	 * adds a bead to the necklace, places the bead in the correct spot on the necklace
	 * @param Bead the bead to add to the necklace
	 */
	public void addBead(Bead b2)
	{
		if(size == 0) //if there aren't any beads on the necklace
		{
			head = b2; //put this bead at the beginning of the necklace
		}
		if(head.compareTo(b2) == 1) //if this bead should go in front of the head bead
		{
			b2.next= head;
			head = b2; //make this bead the head
		}
		if(head.compareTo(b2) < 0)
		{
			Bead ptr = head; //make a pointer that is on the left of each object
			if(ptr.next == null)
			{
				ptr.next = b2; //set head as b2
			}
			else //if there is a head
			{
				while(ptr.next != null && b2.compareTo(ptr.next) > 0) //until you reach the end of the list
				//compare b2 to see if it needs to go in front of a bead.
				{
					ptr = ptr.next; //sets the bead that b2 needs to be behind to next pointer
				}
				b2.next = ptr.next; //sets the bead that b2 needs to be in front of behind b2
				ptr.next = b2; //inserts the b2 between the two beads
			}
		}
		size++; //add one to the size of the list
	}
	
	/**
	 * Removes a bead of a certain color from the necklace.
	 * This can be the first bead of that color 
	 * @param Color the color of the bead to be removed
	 * @return the bead removed
	 */
	public Bead removeBead(Color co)
	{
		boolean hasRemoved = false; //to check whether or not something has been removed
		Bead toReturn = null; //to check what, if anything, we should return
		if(size > 0) //as long as the necklace isn't empty
		{
			if(head.color == co) //at the index 0
			{
				size--; //trim the size
				toReturn = head;
				
			}
			else //if the head isn't the one that should be removed
			{
			Bead ptr = head.next; //make a pointer at the second bead and start checking from there
			while(ptr.color != co && ptr.next != null) //as long as the colors don't match and there is another bead to check
			{
				ptr = ptr.next; //move pointer over by one
				if(ptr.color == co) //if the colors match
				{
					toReturn = ptr;
					hasRemoved = true; //something has been removed
				}
			}
			if(ptr != null && hasRemoved == true) //if you removed something and you have to change the next value of a point
			{
				ptr = ptr.next; //reset the next value of ptr
				size--; //subtract from the size
				return toReturn;
			}
		}
		}
		return toReturn;
	}
	
	/**
	 * Returns bead at the specified index
	 * @param int the index associated with the bead that the user wants returned
	 * @return Bead the bead at that index
	 */
	public Bead getBead(int index)
	{
		if(index < size) //makes sure that there is something within our necklace
		{
			Bead b2 = head;
			for(int i = 0; i<index; i++) //for the amount of beads within the necklace size
			{
				b2 = b2.next; //check the bead after the head
			}
			return b2; //give use the bead of the index we are looking for 
		}
		return null; //if there are no beads return null
	}
	
	/**
	 * Returns the size of the necklace
	 * @return the size of the necklace
	 */
	public int size()
	{
		return size;
	}

}
