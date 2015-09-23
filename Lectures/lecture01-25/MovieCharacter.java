/**
 * A class that represents a character in a movie.
 * @author joel
 *
 */
public class MovieCharacter implements OnFilm
{
	//instance variables
	protected String name; //the character's name
	protected Movie movie;
	protected int timeStamp;
	
	public MovieCharacter(String name)
	{
		this.name = name;
	}
	
	public MovieCharacter(String name, Movie movie)
	{
		this.name = name;
		this.movie = movie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public String toString()
	{
		return name+" ("+movie.getTitle()+")";
	}

	@Override
	public int getTimestamp() {
		return timeStamp;
	}

	@Override
	public void setTimestamp(int t) {
		timeStamp = t;
		
	}

}
