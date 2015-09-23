import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		Movie alien = new Movie("Alien", 1979);
		Movie starWars = new Movie("Star Wars", 1977);
		Movie starTrek = new Movie("Star Trek", 1966);

		MovieCharacter ripley = new MovieCharacter("Ripley", alien);
		MovieCharacter leia = new MovieCharacter("Princess Leia", starWars);
		MovieCharacter uhura = new MovieCharacter("Uhura", starTrek);

		MovieVista tatooine = new MovieVista("Tatooine",starWars);
		
		MovieVillain vader = new MovieVillain("Darth Vader",starWars);
		
		OnFilm vaderOnFilm = vader;
		((MovieVillain)vaderOnFilm).doEvil(); //casting back
		//((MovieVillain)leia).doEvil(); //BAD CAST! Cannot make leia a villain!
		
		ArrayList<OnFilm> montage = new ArrayList<OnFilm>();
		montage.add(ripley);
		montage.add(tatooine);
		montage.add(leia);
		montage.add(vader);
		montage.add(uhura);
		
		for(OnFilm shot : montage)
		{
			System.out.println(shot);
			System.out.println(shot.getMovie() + " at " + shot.getTimestamp());
			System.out.println("");
			
		}

		

	}

}
