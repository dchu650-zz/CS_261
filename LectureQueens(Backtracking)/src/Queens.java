/**
 * A class to solve the n-Queens problem
 * @author CS261
 */
public class Queens {

	private boolean[][] board;
	
	public Queens(int size) {
		board = new boolean[size][size];
		
//		addQueen(board,2,3);
//		System.out.println(checkQueen(board,1,2));
	}

	public Queens()
	{
		this(8);
	}
	
	//add a queen to the board
	public void addQueen(boolean[][] board, int col, int row)
	{
		if(row < 0 || col < 0 || row > board.length || col > board.length)
			throw new IllegalArgumentException();

		board[row][col] = true;
	}
	
	//check if a queen can be placed in the spot
	public boolean checkQueen(boolean[][] board, int col, int row)
	{
		if(board[row][col])
			return false;
		
		for(int i=0; i<board.length; i++){ //run through row and col
			if(board[i][col] || board[row][i])
				return false;
		}
		
		for(int i=0; i<board.length; i++)
		{
			for(int j=0; j<board.length; j++)
			{
				int distRow = Math.abs(row-i);
				int distCol = Math.abs(col-j);
				if(distRow == distCol)
					if(board[i][j])
						return false;
			}
		}
		
		return true;
	}
	
	//remove a queen from the board
	public void removeQueen(boolean[][] board, int col, int row)
	{
		if(row < 0 || col < 0 || row > board.length || col > board.length)
			throw new IllegalArgumentException();

		board[row][col] = false;
	}
	
	//launcher method
	public void solve()
	{
		solve(board,0);
	}
	
	//launcher method that starts with a queen in the given row of the first column
	public void solve(int startRow)
	{
		addQueen(board,0,startRow);
		solve(board,1);
		
	}
	
	/**
	 * 
	 * @param board the board to work on
	 * @param col what col our "subboard" starts on
	 * @return whether or not we placed a queen at this col
	 */
	public boolean solve(boolean[][] board, int col)
	{
		if(col >= board.length)
		{
			return true; //found a solution

			//in order to show ALL solutions
			//printBoard(); //print out the currently found solution
			//return false; //lie and say not done, so we keep finding more!
		}

		for(int i=0; i<board.length; i++) //go down the entire column
		{
			if(checkQueen(board,col,i)) //see if we can place the queen
			{
				addQueen(board,col,i); //if so, place the queen
				printBoard(); //for animation
				pause();
				if(solve(board,col+1)) //try to solve the rest of the board
					return true; //if we could, great! We can stop
				removeQueen(board,col,i); //if not, remove the queen and keep going
				printBoard(); //for animation
				pause();
			}
		}
		
		return false; //couldn't place a queen in this col
	}
	//prints out the current board
	public void printBoard()
	{
		for(int i=0; i<board.length; i++)
		{
			for(int j=0; j<board[i].length; j++)
			{
				if(board[i][j])
					System.out.print(" Q ");
				else
					System.out.print(" - ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	//A pause method (for animations!)
	public void pause()
	{
		try{
			Thread.sleep(100);
		}catch(Exception e)
		{}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queens queens = new Queens(8);
		queens.solve();
		queens.printBoard(); //print final board
		
	}
}
