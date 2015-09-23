/**
 * This is the edge class. It represents the connection between two documents.
 * The edge stores the names of the two documents and the number of similarities (common n-grams between documents), which will be thought of as the "weight of the edge".
 * @author Kramer Canfield & Darren Chu
 * @version 11 May 2013
 */
public class Edge
{
	//fields
	private int weight;//the weight of the edge
	private String fileName1, fileName2;//the names of the files
	
	/**
	 * This is the constructor for the Edge that assigns values to all three of the instance variables.
	 * @param weight The weight of the edge, the number of connections between the two documents.
	 * @param fileName1 The name of the first file.
	 * @param fileName2 The name of the second file.
	 */
	public Edge(int weight, String fileName1, String fileName2)
	{
		this.weight = weight;
		this.fileName1 = fileName1;
		this.fileName2 = fileName2;
	}
	
	/**
	 * This is the get method for the weight of the edge.
	 * @return The weight of the edge.
	 */
	public int getWeight()
	{
		return weight;
	}
	
	/**
	 * This is the get method for the names of the files connected by the edge. This is useful for printing. The names are separated by a comma and a space.
	 * @return The names of the files as Strings separated by a comma and a space.
	 */
	public String getNames()
	{
		return (fileName1+", "+fileName2);
	}
	
	/**
	 * The toString method, useful for debugging.
	 * @return A String representing the weight of the edge and the names of the files.
	 */
	public String toString()
	{
		return("weight: "+weight+" "+getNames());
	}
}