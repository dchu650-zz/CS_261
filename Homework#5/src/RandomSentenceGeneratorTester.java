
public class RandomSentenceGeneratorTester {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println(5%10);
		System.out.println(3%10);
		RandomSentenceGenerator rsg = new RandomSentenceGenerator();
		rsg.readFromFile("dungeon.txt");
		rsg.print();
	}

}
