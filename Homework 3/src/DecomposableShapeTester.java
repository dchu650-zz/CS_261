
/**
 * 
 * @author Darren Chu
 * @version 3/1/13
 */

public class DecomposableShapeTester {

	/**Tester class
	 * I was unable to finish the slider. 
	 * Comment and uncomment the methods to see that I got the trim and restore method
	 * @param args
	 */
	public static void main(String[] args) {
		
		DecomposableFrame a = new DecomposableFrame();
		a.shape.trim(15);
		a.shape.restore(10);
		a.shape.trim(25);
		a.shape.trim(10);
		a.shape.restore(10);
		

	}

}
