import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A class to represent a movie in a collection.
 * 
 * @author Joel Ross
 * @version Sp13
 */
public class Movie
{
	public static final double DEFAULT_RATING = 5.0;
	private static final String DELIM = "; "; //for file writing

	//instance variables
	private String title;
	private int year;
	private String director;
	private ArrayList<String> actors;
	private int playCount;
	private double rating;

	/**
	 * Creates a new Movie object from the given parameters
	 * @param title The title of the film
	 * @param year The year the film was released
	 * @param director The director of the film
	 * @param actors A list of actors in the film
	 * @param playCount The number of times the movie has been played
	 * @param rating The movie's rating
	 */
	public Movie(String title, int year, String director, ArrayList<String> actors, int playCount, double rating)
	{
		this.title = title;
		this.year = year;
		this.director = director;
		this.actors = actors;
		this.playCount = playCount;
		this.rating = rating;
	}

	/**
	 * Creates a new Movie object from the given parameters
	 * Number of plays starts at 0, and movie has default rating.
	 * @param title The title of the film
	 * @param year The year the film was released
	 * @param director The director of the film
	 * @param actors A list of actors in the film
	 */
	public Movie(String title, int year, String director, ArrayList<String> actors)
	{
		this(title, year, director, actors, 0, Movie.DEFAULT_RATING);
	}


	/**
	 * Creates a new Movie object with the given title and year.
	 * The director is set to "Unknown" and the actor list is empty.
	 * @param title The title of the film
	 * @param year The year the film was released
	 */
	public Movie(String title, int year)
	{
		this(title, year, "Unknown", new ArrayList<String>());
	}

	/**
	 * Gets the actors in the film.
	 * @return the actors as an array (to avoid direct third-party access)
	 */
	public String[] getActors()
	{
		return actors.toArray(new String[0]);
	}

	/**
	 * Gets the director of the film
	 * @return the director
	 */
	public String getDirector()
	{
		return director;
	}

	/**
	 * Gets the title of the film
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Gets the year the film was released
	 * @return the year
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * Gets the number of times this movie has been played
	 * @return the playCount
	 */
	public int getPlayCount()
	{
		return playCount;
	}

	/**
	 * Gets the movie's current rating
	 * @return the rating
	 */
	public double getRating()
	{
		return rating;
	}

	/**
	 * "Plays" the movie (currently just increments the number of times the movie has been played).
	 */
	public void play()
	{
		this.playCount++;
	}

	/**
	 * Adds the actor to the list of actors
	 * @param actor The actor to add
	 */
	public void addActor(String actor)
	{
		if(!actors.contains(actor))
			actors.add(actor);
	}

	/**
	 * Removes the actor from the list of actors
	 * @param actor The actor to remove
	 */
	public void removeActor(String actor)
	{
		actors.remove(actor);
	}

	/**
	 * Sets the film's rating
	 * @param rating the rating to set
	 */
	public void setRating(double rating)
	{
		this.rating = rating;
	}

	
	
	////////////////////
	// FILE IO METHODS//
	////////////////////

	/**
	 * Returns a semicolon-separated list of movie details, for use in writing to file
	 * @return A string representation of the movie
	 */
	private String toFileString()
	{
		String temp = title+DELIM+year+DELIM+director+DELIM;
		for(String actor : actors)
			temp += actor+DELIM;
		temp += playCount+DELIM+rating;
		return temp;
	}

	/**
	 * Parses a movie from a semicolon-separated entry
	 * @param input The string representing the movie
	 * @return the Movie object, or null if there was an error parsing
	 */
	private static Movie parseFileString(String input)
	{
		Movie toReturn = null; //start as null

		Scanner sc = new Scanner(input);
		sc.useDelimiter(DELIM);
		try{ //in case we fail to parse anything, such as format didn't work
			String title = sc.next();
			int year = sc.nextInt(); //year is an int
			String director = sc.next();
			ArrayList<String> actors = new ArrayList<String>();
			while(!sc.hasNextInt()) //while we have values that aren't numbers, they must be actors
			{
				actors.add(sc.next());
			}
			int playCount = sc.nextInt();
			double rating = sc.nextDouble();

			toReturn = new Movie(title, year, director, actors, playCount, rating);			
		}
		catch(Exception e){
			System.out.println("Failed to parse film: "+input);
		}		
		sc.close();

		return toReturn;
	}

	/**
	 * Loads a list of movies from a text file (specified by the user)
	 * @return The list of movies loaded
	 */
	public static ArrayList<Movie> loadMoviesFromFile()
	{
		ArrayList<Movie> movieList = new ArrayList<Movie>();        

		JFileChooser chooser = new JFileChooser(); //a file chooser
		chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt")); //for txt files
		int returnVal = chooser.showOpenDialog(chooser); //show the dialog to OPEN
		if(returnVal == JFileChooser.APPROVE_OPTION) //if they picked something
		{
			try {
				Scanner sc = new Scanner(chooser.getSelectedFile()); //parse it with Scanner
				while(sc.hasNextLine())
				{
					Movie movie = parseFileString(sc.nextLine());
					if(movie != null)
						movieList.add(movie);
				}
				sc.close();
			}
			catch(Exception e) {
				System.out.println("Error loading from file: "+e);
				e.printStackTrace();
			}
		}
		return movieList;
	}


	/**
	 * Saves the given list of movies to a file (specified by the user)
	 * @param movies The list of movies to save
	 */
	public static void saveMoviesToFile(ArrayList<Movie> movies)
	{
		JFileChooser chooser = new JFileChooser(); //a file chooser
		chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt")); //for txt files
		int returnVal = chooser.showSaveDialog(chooser); //show the dialog to SAVE
		if(returnVal == JFileChooser.APPROVE_OPTION) //if they picked something
		{
			try{
				PrintWriter pw = new PrintWriter(chooser.getSelectedFile());
				for(Movie movie : movies)
					pw.println(movie.toFileString());
				pw.close();
			}
			catch(Exception e) {
				System.out.println("Error saving to file: "+e);
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Converts into a String 
	 */
	public String toString()
	{
		return title + this.getYear() + director;
	}

}