/**
 * 
 * @author joel
 */
public class Album extends Media
{
	//instance variables
	private String title;
	private String musician;
	private int year;
	private int playCount;
	private double rating;
	
	public Album(String musician, String title, int year)
	{
		this.title = title;
		this.musician = musician;
		this.year = year;
		this.playCount = 0;
		this.rating = 5.0;
	}
	
	public void play()
	{
		
	}
	
	//getters/setters, etc...
	//...
	
}
