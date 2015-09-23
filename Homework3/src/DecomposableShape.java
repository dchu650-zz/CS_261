import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JSlider;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Polygon;

public class DecomposableShape
{
	private static final String DELIM = ",";
	private Point thePoint;
	private PointNode head;
	private PointNode tail;
	private int size;
	private static Polygon theShape;
	public DecomposableShape()
	{
		theShape = loadPointsFromFile();
		size = 0;
		head = null; 
		tail = null;
	}

	/**
	 * 
	 */
	public Polygon createPolygon()
	{
		Polygon polygon = new Polygon();
		PointNode pointer = head;
		for(int i = 0; i < size; i++)
		{
			polygon.addPoint(pointer.data.x, pointer.data.y);
			pointer = pointer.next;
		}
		return polygon;
	}
	
	/**
	 * Parses a movie from a semicolon-separated entry
	 * @param input The string representing the movie
	 * @return the Movie object, or null if there was an error parsing
	 */
	private static Point parseFileString(String input)
	{ 
		Point thePoints = null;
		Scanner sc = new Scanner(input);
		sc.useDelimiter(DELIM);
		try
		{
			while(sc.hasNextLine()) //while we have values that aren't numbers, they must be actors
			{
				int x = sc.nextInt();
				int y = sc.nextInt();
				Point point = new Point(x,y);
				thePoints = point;
			}
			
		}
		catch(Exception e){
			System.out.println("Failed to parse points: "+input);
		}		
		sc.close();


		return thePoints;
	}
	/**
	 * Loads a list of movies from a text file (specified by the user)
	 * @return The list of movies loaded
	 */
	public static Polygon loadPointsFromFile()
	{
		ArrayList<Point> pointList = new ArrayList<Point>();        

		JFileChooser chooser = new JFileChooser(); //a file chooser
		chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt")); //for txt files
		int returnVal = chooser.showOpenDialog(chooser); //show the dialog to OPEN
		if(returnVal == JFileChooser.APPROVE_OPTION) //if they picked something
		{
			try {
				Scanner sc = new Scanner(chooser.getSelectedFile()); //parse it with Scanner
				while(sc.hasNextLine())
				{
					Point point = parseFileString(sc.nextLine());
					if(point != null)
					pointList.add(point);
				}
				sc.close();
			}
			catch(Exception e) {
				System.out.println("Error loading from file: "+e);
				e.printStackTrace();
			}
		}
		int[] xPoints = new int[pointList.size()];
		for(int i = 0; i<xPoints.length; i++)
		{
			xPoints[i] = pointList.get(i).x;
		}
		int[] yPoints = new int[pointList.size()];
		for(int j = 0; j < yPoints.length; j++)
		{
			yPoints[j] = pointList.get(j).y;
		}
		int nPoints = pointList.size();
		
		Polygon thePoly = new Polygon(xPoints, yPoints, nPoints);
		
		theShape = thePoly;
		
		return theShape;
	}
	public static Polygon getTheShape() {
		return theShape;
	}

	public static void setTheShape(Polygon polygon) {
		DecomposableShape.theShape = polygon;
	}
	
	public void addPoints()
	{
		
	}
}





//read through as a string.
//parse int then delim by the comma
//add both individual numbers to the list
//read next line
//repeat