import java.util.ArrayList;

/**
 * Class that sets the movie library
 * @author Darren Chu
 * @version 1.29.13 
 */
public class MovieLibrary 
	{
		private ArrayList<Movie> movieLibrary;
		private String[] theActors;
		private Movie newMovie;

	public MovieLibrary()
		{
	movieLibrary = new ArrayList<Movie>();
		}
	/**
	 * Adds a movie to the movie library
	 */
	public void addMovie(Movie movie)
	{
		movieLibrary.add(movie);
		if(movieLibrary.contains(movie))
		{
			System.out.println("Already contains this movie");
		}
	}
	
	/**
	 * Adds a list of movies to the movie library
	 */
	public void addMOvies(ArrayList<Movie> movies)
	{
		for(Movie theMovie: movies)
		{
			movieLibrary.add(theMovie);
		}
	}
	
	/**
	 * Removes a movie from the movie library
	 */
	public void removeMovie(Movie movie)
	{
		movieLibrary.remove(movie);
	}
	
	/**
	 * Searches for a movie within the movie library
	 * @param String takes in a title
	 * @return Movie will return a movie, but if the movie is not available, it will return null
	 */
	public Movie findMovie(String title)
	{
		for(Movie theMovie : movieLibrary)
		{
			if(theMovie.getTitle().equals(title))
			{
				return theMovie;
			}
		}
		System.out.println("There Is No Movie");
		return null;
		
	}
	
	/**
	 * Searches for an artist
	 * @param String takes in a string
	 * @return ArrayList<Movie> returns the list of movies
	 */
	public ArrayList<Movie> findArtist(String artist)
	{
		ArrayList<Movie> theMovie = new ArrayList<Movie>();
		for(int i =0; i<movieLibrary.size();i++)
		{
			newMovie = movieLibrary.get(i);
			theActors = newMovie.getActors();
		}
		for(int j = 0; j < theActors.length; j++)
		{
			if(artist.equals(theActors[j]))
			{
				theMovie.add(newMovie);
			}
		}
		if(artist.equals(newMovie.getDirector()))theMovie.add(newMovie);
		return theMovie;
	}
	/**
	 * Loads the list of movies
	 * @return ArrayList<Movie> returns the movie library
	 */
	public ArrayList<Movie> load()
	{
		movieLibrary = Movie.loadMoviesFromFile();
		return movieLibrary; 
	}
	/**
	 * Saves the movie library
	 */
	public void save()
	{
		Movie.saveMoviesToFile(movieLibrary);
	}
	/**
	 * Returns a movie list
	 * @return ArrayList<Movie> a movie list
	 */
	public ArrayList<Movie> getMovieList()
	{
		return movieLibrary;
	}
	/**
	 * Sets the movie list
	 * @param movie
	 */
	public void setMovieList(ArrayList<Movie> movie)
	{
		movieLibrary = movie; 
	}
}
