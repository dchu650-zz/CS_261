import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Sudoku 
{
	private static final String DELIM = ",";
	private int[][] numberList;
	private Scanner scanner;
	private final int SIZE = 9;
	
	public Sudoku()
	{
		numberList = loadFileWithScanner();
	}
	
	/**
	 *  Loads a list of points from a text file (specified by the user)
	 * @return The list of points loaded
	 */
	private int[][] loadFileWithScanner()
	{
		int[][] toReturn = new int[SIZE][SIZE];
		JFileChooser chooser = new JFileChooser(); //a file chooser
		chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt")); //for txt files
		int returnVal = chooser.showOpenDialog(chooser); //show the dialog to OPEN
		if(returnVal == JFileChooser.APPROVE_OPTION) //if they picked something
		{
			try 
			{
				scanner = new Scanner(chooser.getSelectedFile()); //parse it with Scanner
				//each integer that I read, must be inserted into a spot of the [][]
					//9 numbers per row and col
//				String title = scanner.nextLine();
//				System.out.println(title);
				loadMultiplePuzzles(toReturn, 0,scanner);
				//by using the row parameter I will recursively scan each possible scanner.nextLine()
				//then print it out
				//private void loadPuzzle(Scanner scan) load the puzzle with this scanner by row
				
				//for(int i = 0; i<SIZE; i++) // for each the row
				//	{
				//		String row = scanner.nextLine();
					//  System.out.println(row);
					//  Scanner scan = new Scanner(row);
					////read the line
					//for(int j = 0; j<SIZE; j++) //for each col
					//{
					//	scan.useDelimiter(DELIM);
					//	int number = scan.nextInt();
					//	//System.out.println("number equals..." + number);
					//	//System.out.println("number j equals..."+j);
					//	toReturn[i][j] = number; //adds the number to the spot of the [][]
					//	//use scanner to parse the numbers
					//}
				//  }
			}
			catch(Exception e) 
			{
				System.out.println("Error loading from file: "+e);
				e.printStackTrace();
			}
		}
		return toReturn;
	}
	
	/**
	 * This method solves the sudoku puzzle. Only works for several boxes. 
	 * Was unable to successfully complete the method. 
	 * @param col The column of the sudoku puzzle
	 * @param row The row of the sudoku puzzle
	 * @param num The number in the box of the puzzle
	 * @return Whether the spot is occupied or not and whether the number can be inserted at that spot
	 */
	private boolean solvePuzzle(int row, int col, int num)
	{ 
		if(row>=9)//if we don't have anymore rows
			{
			return true;//we found a solution
			}

		if(col<9)
		{ 
			if(numberList[row][col]!= 0)//if this spot is occupied
				if(col+1>8)//if we're at the end of the row
				{
					if(solvePuzzle(row+1, 0, 1))//check the next row!
						return true;
				}
				else
					return solvePuzzle(row, col+1, 1);//try to solve the next guy

			// Ê System.out.println("checkSpot "+row+","+col+" for "+target+" is "+checkSpot(col, row, target));
			else if(finalCheck(row, col, num) == true) //check this spot for target
			{
				numberList[row][col] = num; //if so, place target

				if(col+1>8)//if we're at the end of the row
				{
					if(solvePuzzle(row+1, 0, 1))//check the next row!
						return true;
				}
				else 
				{
					if(solvePuzzle(row, col+1, 1))//check next spot (across) ((solve the rest of the puzzle!))
						return true;
				}
				numberList[row][col] = 0;
			}

			if(num<9)
				return solvePuzzle(row, col, num+1);//check this spot for next target
			}


		return false;
	}

	/**
	 * Method that checks the rows, column and small 3x3 box
	 * Calls all the check methods into one method
	 * @param row The row of the sudoku puzzle
	 * @param col The column of the sudoku puzzle
	 * @param num The number that we are checking
	 * @return Returns whether or not the number can be inserted in that spot
	 */
	public boolean finalCheck(int row, int col, int num)
	{
		if(checkRow(0, col, num))
		{
			if(checkCol(row, 0, num))

			{
				if(checkBox(row, col, num) == true)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Method that checks the row
	 * @param row Checks the row and increments
	 * @param col The column number, but stays the same
	 * @param num The number that we are checking
	 * @return Returns whether the spot has a number or not
	 */
	public boolean checkRow(int row, int col, int num)
	{
		if(row < SIZE)
		{
			if(numberList[row][col] != num)
				return checkRow(row+1, col, num);
			else
				return false;
		}
		return true;
	}
	/**
	 * Method that checks the column
	 * @param row Checks the row, but starts the same
	 * @param col Checks the column and increments
	 * @param num The number that we are checking
	 * @return Returns whether the spot has a number or not
	 */
	public boolean checkCol(int row, int col, int num)
	{
		if(col < SIZE)
		{
			if(numberList[row][col] != num)
				return checkCol(row, col+1, num);
			else
				return false;
		}
		return true;
	}
	
	/**
	 * Method that checks the column. A helper method for the checkBox method
	 * @param row Checks the row, stays the same
	 * @param col Checks the column and increments 
	 * @param num The number we are checking
	 * @param last The last number in the small 3x3 box
	 * @return
	 */
	public boolean checkCol2(int row, int col, int num, int last)
	{
		if(col < last)
		{
			if(numberList[row][col] != num)
				return checkCol2(row, col+1, num, last);
			else
				return false;
		}
		return true;
	}
	
	/**
	 * Method that checks each small 3x3 box in the entire sudoku puzzle
	 * @param row The row of the 3x3 smaller box
	 * @param col The column of the 3x3 smaller box
	 * @param num The number that we are checking
	 * @return
	 */
	public boolean checkBox(int row, int col, int num)
	{
		int yValue = (row/3)*3; //gets the highest row of the 3x3 box of this point
		int xValue = (col/3)*3; //gets the far left col of the 3x3 box of this point 
		row = xValue + 3; //defines the last row that we are checking
		if(checkCol2(yValue, xValue, num, row) == false) //check first col in box if false
			return false;
		if(checkCol2(yValue + 1, xValue, num, row) == false) //check the second col in box if false
			return false;
		if(checkCol2(yValue + 2, xValue, num, row) == false) //check the third col in box if false
			return false;
		return true;
	}
	
	public void loadMultiplePuzzles(int[][] toReturn, int row, Scanner scan)
	{
		//loadPuzzle(toReturn, row, scan);
		if(scan.hasNextLine())
		{
			String title = scan.nextLine();
			System.out.println(title);
			loadPuzzle(toReturn, row, scan);
			numberList = toReturn;
			this.drawBoard();
			this.solvePuzzle(0, 0, 0);
			this.drawBoard();
			loadMultiplePuzzles(toReturn, row, scan);
		}
	}
	
	/**
	 * The method that will load the puzzle
	 * Uses nextLine() to cycle through the rows after the other methods are finished
	 * @param toReturn The 2d int array that will store the points
	 * @param row The row that we are checking
	 * @param scan The scanner that will scan the text files
	 */
	private void loadPuzzle(int[][] toReturn, int row, Scanner scan)
	{
		//row is the row that will be loaded from loadRow
				//scan will grab the text file
				//when current row is >= SIZE
					//return
				//if there is a remaining row, scan the string from the file, then make a scanner of the string row
				//and send that into load row
				//call loadRow
				//call loadPuzzle with the increased row by 1
		if(row >= SIZE)
			return;
		String rowOfNumbers = scan.nextLine();
		Scanner sc = new Scanner(rowOfNumbers); 
		loadRow(toReturn, row, 0, sc);
		loadPuzzle(toReturn, row+1, scan);
	}
	/**
	 * The method that sends the row to the load column method
	 * @param toReturn The 2d int array that will store the points
	 * @param row The row that we are checking
	 * @param col The column we are checking. The column increments
	 * @param scanner The scanner that will scan the text files
	 */
	private void loadRow(int[][] toReturn, int row, int col, Scanner scanner)
	{
		//stopping case col>=SIZE
		//return
		//call the loadCol on the scanned row
		//call itself with an incremented col
		if(col >= SIZE)
			return;
		loadCol(toReturn, row, col, scanner);
		loadRow(toReturn, row, col+1, scanner);
	}
	
	/**
	 * The method that takes the row that was sent from loadRow() and goes through the columns
	 * @param toReturn The 2d int array that will store the points
	 * @param row The row that we are checking
	 * @param col The column we are checking.
	 * @param scanner2 The scanner that will scan the text files
	 */
	private void loadCol(int[][] toReturn, int row, int col, Scanner scanner2)
	{
		scanner2.useDelimiter(DELIM);
		int number = scanner2.nextInt();
		toReturn[row][col] = number;
	}
	
	/**
	 * Method that draws the sudoku board
	 */
	public void drawBoard()
	{
		for(int i = 0; i<numberList.length; i++)
		{
			if(i%3 ==0)
			{
				System.out.println("+-------+-------+--------+");
			}
			for(int j = 0; j<numberList.length; j++)
			{
				if(j == 0)
					System.out.print(" |");
				if(j % 3 == 0 && j != 0)
					System.out.print("| ");
				if(numberList[i][j] == 0)
				{
					if(j!=8)
						System.out.print(". ");
					if(j == 8)
						System.out.print(". |");
				}
				else
				{
					if(j!=8)
						System.out.print("" + numberList[i][j] + " ");
					if(j==8)
						System.out.print("" + numberList[i][8] + " |");
				}
			}
			System.out.println("");
		}
		System.out.println("+-------+-------+--------+");
	}
}