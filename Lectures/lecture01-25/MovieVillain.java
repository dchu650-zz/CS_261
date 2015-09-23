
public class MovieVillain extends MovieCharacter {

	private double evilness;
	
	public MovieVillain(String name) {
		this(name, null);
	}

	public MovieVillain(String name, Movie movie) {
		super(name, movie);
		evilness = 5.0;
	}

	public String toString()
	{
		return name + " , level "+evilness+" evil";
	}
	
	public void doEvil()
	{
		System.out.println("Evil!! Muahahaha");
	}
	
}
