import java.util.ArrayList;
import java.util.Scanner;
/**
* A class that manages a collection of movies.
* 
* @author Darren Chu
* @version 1.29.13
*/
public class MovieLibraryManager {
private MovieLibrary movieCollection; //library that is loaded
private Movie movie;	
private Scanner scanner;//scanner that is used for reading lines
private String name;

/**
* Constructor for MovieLibraryManager
*/
public MovieLibraryManager()
{
	movieCollection = new MovieLibrary();
	System.out.println("Welcome to the movie library!"); //The main menu display
	System.out.println("Select an option:");
	System.out.println("1. Load a Library");
	System.out.println("2. List movies in Library");
	System.out.println("3. Look up a movie by title");
	System.out.println("4. Add a movie to the library");
	System.out.println("5 Remove a movie from the library");
	System.out.println("6. Look up an artist (director / actor)");
	System.out.println("7. Save movie library");
	System.out.println("8 Exit System");
	int num = 0;

	while (num != 8) 
		{
			if(num == 1) loadLibrary(); //react according to the user input
			else if(num == 2)
			{
				listMovies();
			}
			else if(num == 3)
			{
				lookUpMovie();
			}
			else if(num == 4) 
			{
				addMovie();
			}
			else if(num == 5) 
			{
				removeMovie();
			}
			else if(num == 6) 
			{
				lookUpArtist();
			}
			else if(num == 7) 
			{
				saveLibrary();
			}
		Scanner reader = new Scanner(System.in); //if the user inputs an 8, exit the program
		num = reader.nextInt();
		}
	System.out.println("Thank you come again!");
}
/**
 * Loads a Library	
 */
public void loadLibrary()
	{
		movieCollection.addMOvies(Movie.loadMoviesFromFile());
	}
/**
 * Saves a library	
 */
private void saveLibrary()
	{
		movie.saveMoviesToFile(movieCollection.getMovieList());
	}
/**
 *	Lists all of the movies within the library 
 */
public void listMovies()
	{
		ArrayList<Movie> theMovies = new ArrayList<Movie>();
		theMovies = movieCollection.getMovieList();
		int numMovies = theMovies.size();
		for(int i = 0; i< numMovies; i++)
		{ 
			//for each movie, print out the title
			Movie curMovie = theMovies.get(i);
			System.out.println(curMovie.getTitle()); 
		}
	}
	
/**
* Looks up the movie's information by using the movie's title
*/
private void lookUpMovie()
	{
		System.out.println("What is the title of the movie?");
		Scanner s1 = new Scanner(System.in);
		String title = s1.next();
		Movie movie = movieCollection.findMovie(title);//uses user input to search for movie

	if (movie != null) 
	{
		//if movie exists, provide the title, year, director, cast, play count, and rating.
		System.out.println("Movie Found:");
		System.out.println("Title: " + movie.getTitle());
		System.out.println("Year: " + movie.getYear());
		System.out.println("Directed by: " + movie.getDirector());
		String cast = "";
		for(int i = 0; i< movie.getActors().length;i++)
		{ //build the cast according to the number of actors
			cast = cast + movie.getActors()[i] + " "; //add actors to cast
		}
		System.out.println("Cast: " + cast);
		System.out.println("Play Count: " + movie.getPlayCount());
		System.out.println("Rating: " + movie.getRating());
	}
		else
		{	
			System.out.println("Movie not found"); //if the movie title does not exist, provide a message
		}
}

/**
* A method that adds a movie to the current library of movies
*/
	private void addMovie()
	{
		System.out.println("What is the title of the movie you would like to add to your library?");//prompt
		Scanner s1 = new Scanner(System.in);
		String title = s1.next(); //the User's Input

		System.out.println("When was the movie released?");
		s1 = new Scanner(System.in);
		int date = s1.nextInt();//the User's Input

		System.out.println("Who was the director?");
		s1 = new Scanner(System.in);
		String director = s1.next();//the User's Input

		ArrayList<String> actors = new ArrayList<String>(); 
		System.out.println("How many actors?");
		s1 = new Scanner(System.in);
		int numActors = s1.nextInt();
		for(int i = 0; i<numActors; i++)
		{
			System.out.println("Actor #" + (i+1) + "?");
			s1 = new Scanner(System.in);
			String actor = s1.next();
			actors.add(actor); //actors are created by specifying how many actors are in the cast
		}

		System.out.println("Playcount?");
		s1 = new Scanner(System.in);
		int playCount = s1.nextInt();//the User's Input

		System.out.println("Rating?");
		s1 = new Scanner(System.in);
		double rating =s1.nextDouble();

		Movie movie = new Movie(title, date, director, actors, playCount, rating);//puts all inputs together as a movie
		movieCollection.addMovie(movie);//adds movie to library

}

/**
* Method that removes movie from current library
*/
	private void removeMovie()
	{
		System.out.println("Which movie would you like to remove from your library?"); //prompt
		Scanner s1 = new Scanner(System.in);
		String title = s1.next();// user input
		Movie movie = movieCollection.findMovie(title);//use the provided title to serach the library for movie

		if (movie != null)
		{
			System.out.println("Are you sure you want to delete " + title + "? (yes/no)");//confirm
			Scanner s2 = new Scanner(System.in);
			String confirmation = s2.next();//user input

				if (confirmation.equals("yes"))
				{ 
					movieCollection.removeMovie(movie);//remove movie from library
					System.out.println("Movie removed from library.");
				}
				else
				{
					System.out.println("Movie not removed from library.");
				}

		}
		else
		{
			System.out.println("Movie not found");
		}
}
	
/**
* Method that looks up an Artist(director or actor) in all the movies in the library
*/
	private void lookUpArtist()
	{
		System.out.println("What is the name of the Artist?");
		Scanner s1 = new Scanner(System.in).useDelimiter("\\n");//tokenizes the given text
		String name = s1.next();//user input
		ArrayList<Movie> movies = movieCollection.findArtist(name);
		//fills the array with the results from the findArtist method

		if (movies.size() > 0) 
		{
			
			System.out.println("Artist found in following movies:");
			for(int i = 0; i< movies.size();i++)
			{
				System.out.println(movies.get(i).getTitle()); //print the titles of the movies
			}

		}
		else
		{
			System.out.println("Artist not found");
		}

	}
}