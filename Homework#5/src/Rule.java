import java.util.ArrayList;

/**
 * This is the Rule class. It has a list of right sides.
 * @author Kramer Canfield
 * @version 9 April 2013
 *
 */
public class Rule
{
	//fields
	private ArrayList<RightSide> listRightSides;//the list of right sides for the variable
	
	
	/**
	 * Constructor for the Rule.
	 */
	public Rule()
	{
		listRightSides = new ArrayList<RightSide>();
	}
	
	
	public String toString()
	{
		return listRightSides.toString();
	}
	
	
	/**
	 * @return the listRightSides
	 */
	public ArrayList<RightSide> getListRightSides()
	{
		return listRightSides;
	}


	/**
	 * @param listRightSides the listRightSides to set
	 */
	public void setListRightSides(ArrayList<RightSide> listRightSides)
	{
		this.listRightSides = listRightSides;
	}



}
