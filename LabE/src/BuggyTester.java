
public class BuggyTester {

	/**
	 * @author Darren Chu & Nate Olderman
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Buggy buggy = new Buggy();
		System.out.println(buggy.toUpper("12349manA"));
		System.out.println(buggy.toLower("SQUASHING2344&^*$#a"));
		System.out.println(buggy.countWords("a   1234 bananaman  - gvtyb asdasdsa a"));
		System.out.println(buggy.reverseString("the man with the can 1234 AISDASOIDB"));
		System.out.println(buggy.splitString("!green!eggs!and-ham!", "!,-"));
		System.out.println(buggy.avgWordLength("(123, 1234, 12345, 123456)"));
		
	
	}

}
