/**
 * A class that represents a scenic vista found in a movie.
 * @author joel
 *
 */
public class MovieVista implements OnFilm {

	private String label;
	private Movie movie;
	
	public MovieVista(String label, Movie movie)
	{
		this.label = label;
		this.movie = movie;
	}

	public MovieVista(String label)
	{
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String toString()
	{
		return label+" ("+movie.getTitle()+")";
	}

	@Override
	public int getTimestamp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTimestamp(int t) {
		// TODO Auto-generated method stub
		
	}
}
