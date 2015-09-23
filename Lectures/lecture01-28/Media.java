
public abstract class Media {

	protected int playCount;
	protected double rating;
		
	public Media()
	{
		playCount = 0;
		rating = 5.0;
	}
	
	public abstract void play();
	
}
