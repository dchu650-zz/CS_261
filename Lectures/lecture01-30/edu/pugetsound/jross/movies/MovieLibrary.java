package edu.pugetsound.jross.movies;
import java.util.ArrayList;

/**
 * 
 * @author joel
 *
 */
public class MovieLibrary
{
	private ArrayList<Movie> movies;
	
	public MovieLibrary()
	{
		movies = new ArrayList<Movie>();
	}

	public void addMovie(Movie movie)
	{
		if(!movies.contains(movie))
			movies.add(movie);
	}

	public void addMovies(ArrayList<Movie> movies)
	{
		for(Movie m : movies)
			addMovie(m);
	}

	public void saveMovies()
	{
		Movie.saveMoviesToFile(movies);
	}
	
	public void removeMovie(Movie movie)
	{
		movies.remove(movie);
	}

	public ArrayList<Movie> getMovies()
	{
		return movies;
	}
	
	/**
	 * returns null if the movie isn't found
	 */
	public Movie findMovie(String title)
	{
		for(Movie m : movies)
		{
			if(m.getTitle().equals(title))
				return m;
		}
		return null;
	}
	
	/**
	 * 
	 * @param artist
	 * @return
	 */
	public ArrayList<Movie> searchByArtist(String artist)
	{
		ArrayList<Movie> toReturn = new ArrayList<Movie>();
		for(Movie m : movies)
		{
			if(m.getDirector().equals(artist))
				toReturn.add(m);
			else
			{
				String[] actors = m.getActors();
				for(int i=0; i<actors.length; i++)
				{
					if(actors[i].equals(artist))
					{
						toReturn.add(m);
						i = actors.length; //break
					}
				}
			}
		}
		
		return toReturn;
	}
	
	public ArrayList<Movie> getRecommendations(int num)
	{
		ArrayList<Movie> toReturn = new ArrayList<Movie>();
	
		for(int i=0; i< movies.size()-1; i++) //for each spot except the last
		{
			int topIndex = i; //start comparing to that spot
			for(int j=1; j< movies.size(); j++) //check everyone else
			{
				if(movies.get(j).getRating() > movies.get(topIndex).getRating()) //if higher rated
					topIndex = j; //mark them as top
			}
			//ArrayList swap!
			Movie tmp = movies.remove(i);
			movies.add(topIndex, tmp);
			movies.add(0, movies.remove(topIndex+1));
		}
		
		for(int i=0; i<num && i < movies.size(); i++)
		{
			toReturn.add(movies.get(i));
		}

		return toReturn;
	}
}
