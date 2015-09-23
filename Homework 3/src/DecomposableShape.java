import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Darren Chu
 * @version 3/1/13
 */

public class DecomposableShape {

	private static final String DELIM = ","; //delimits the numbers by the comma
	private Stack<Node<Point>> stack; //the stack that store both of the added and removed points
	private Stack<Integer> stackInt; //stores when the index of the nodes which are removed
	private int changingSize = 0; //the changing number of points in the polygon
	private int size; //keeps track of the size of the list
	private Node<Point> head; //the start of the list
	private ArrayList<Point> pointList; //an arraylist for the linked list
	
	public DecomposableShape()
	{
		head = null; 
		pointList = loadPointsFromFile();
		int i =0;
		for(Point point: pointList)//adds the points to the linked list
		{
			add(i,point);
			i++;
		}
		changingSize=pointList.size();
		stack = new Stack<Node<Point>>(); 
		stackInt = new Stack<Integer>();
	}

	/**
	 * Parses a point from a comma-separated entry
	 * @param input The string representing the point
	 * @return the point object
	 */

	private static Point parseFileString(String input)
	{
		int x = 0;
		int y = 0;
		Scanner sc = new Scanner(input);
		sc.useDelimiter(DELIM);
		x = sc.nextInt();
		y = sc.nextInt();
		sc.close();
		Point point = new Point(x,y);
		return point;
	}

	/** 
	 * Loads a list of points from a text file (specified by the user)
	 * @return The list of points loaded
	 */

	public ArrayList<Point> loadPointsFromFile()
	{
		ArrayList<Point> pointList = new ArrayList<Point>();
		
		JFileChooser chooser = new JFileChooser(); //a file chooser
		chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt")); //for txt files
		int returnVal = chooser.showOpenDialog(chooser); //show the dialog to OPEN
		
		if(returnVal == JFileChooser.APPROVE_OPTION) //if they picked something
		{
			try {
				Scanner sc = new Scanner(chooser.getSelectedFile()); //parse it with Scanner
				while(sc.hasNextLine()) //while there's another line
				{
					Point point = parseFileString(sc.nextLine());
					if(point != null)
					{
						pointList.add(point);
					}	
				}
				sc.close();
			}
			catch(Exception e) {
				System.out.println("Error loading from file: "+e);
				e.printStackTrace();
			}
		}
		return pointList;
	}
	
	/**
	 * Adds nodes after the first one has been made
	 * @param the node the new node will be connected to, the data stored in the new node
	 */
	private void addNext(Node<Point> node, Point item)
	{
		node.next = new Node<Point>(item, node.next);
		
		node.next.previous = node;
		//node.previous.next = node.next;

		if(node.next.next==null)
			head.previous = node.next;		
	}
	/**
	 * removes a specific node 
	 * @param the node to be removed
	 */
	public Node<Point> remove(int index)
	{
		if (index<0 || index >=size()) 
		{
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(index == 0) //works
		{
			Node<Point> toReturn = head;
			Node<Point> tempNode = head.next;
			head.next.previous = head.previous;
			head.previous.next = tempNode;
			size--;
			return toReturn;
		}
		if(this.getNode(index) != null) //if the node they entered is not null
		{
			Node<Point> toReturn = getNode(index);
			Node<Point> tempNode = getNode(index).next;
			getNode(index).next.previous = getNode(index).previous;
			getNode(index).previous.next = tempNode;
			size--;
			return toReturn;
		}
		else
		{
			return null;
		}
	}
	/*
	 * removes n number of points which are deemed "least important"
	 * @param n, the number of points to remove
	 */
	
	public void trim(int n)
	{
		int indexOfTheWeakest = 0;
		int difference = changingSize-n;
		
		Node<Point> theWeakestLink = new Node<Point>(null);
		while(difference<=changingSize)
		{
			for(int i=0;i<changingSize-1;i++) //determines the importance of all nodes
			{
				this.calculateImportanceNodeIndex(i);
			}
			for(int i =0; i<changingSize-1; i++) //figures out which node is the weakest
			{
				if(this.getNode(i).importance<this.getNode(i+1).importance)
				{
					 theWeakestLink = this.getNode(i);
					 indexOfTheWeakest = i;
				}
			}
			
			stack.push(theWeakestLink);
			stackInt.push(indexOfTheWeakest);
			calculateImportance(theWeakestLink.next);
			calculateImportance(theWeakestLink.previous);
			this.remove(indexOfTheWeakest);
			changingSize--;
		}
		
	}
	/*
	 * restores points removed by the trim method
	 * @param n, the number of points to restore
	 */
	public void restore(int n)
	{
		int i = n;
		while(i>0&& stack.isEmpty() ==false && stackInt.isEmpty() == false)
		{
			Node<Point> temp = stack.pop();
			Integer a = stackInt.pop();
			this.add(a, temp.data);
			calculateImportance(getNode(a).next);
			calculateImportance(getNode(a).previous);
			changingSize++;
			i--;
		}
		
	}



	/*
	 * gets a node at a specific index
	 * @param the index at which it should get the node
	 * @return the node at the parameter index
	 */
	public Node<Point> getNode(int index)
	{
		Node<Point> node = head;
		for(int i=0;i<index && node!=null;i++)
		{
			node = node.next;
		}
		return node;
	}
	
	/**
	 * returns the size of the linked list
	 * @return the size of the linkedlist
	 */
	public int size()
	{
		return size;
	}

	/*
	 * adds a new node to the linked list xxxWORKSxxx
	 * @param the data you want to add to the new node
	 */
	public void add(int index, Point item)
	{
		if (index<0 || index >size())
		{
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(index == 0)
		{
			head = new Node<Point>(item, head);	
			System.out.println("I added the head");
			size++;
		}
		else
		{

			Node<Point> node = getNode(index-1);
			addNext(node, item);
			size++;	
			System.out.println("I added the other ones");
			
			if(node.next == null) //if the node is the last node it's 'next' becomes the head
			{
				node.next = head;
				head.previous = node.next;
			}
			
		}
	}		
	

	/*
	 * Converts the X and Y coordinate arrays of the shape into a polygon the frame class can draw
	 * @param The shape we want to convert
	 */
	public Polygon toPolygon()
	{
		int[] xPoints = new int[changingSize];
		int[] yPoints = new int[changingSize];
		
		for(int i=0;i<changingSize;i++)
		{
			xPoints[i]=getNode(i).data.x;
			yPoints[i]=getNode(i).data.y;
			System.out.println(xPoints[i]);
		}
		
		Polygon polygon = new Polygon(xPoints,yPoints, changingSize);
		return polygon;
	}
	
	/**
	 * Calculates the importance of where the node will appear
	 * @param index
	 * @return a double
	 */
	public double calculateImportanceNodeIndex(int index)
	{
		
		//all attempts to get nodes return null.
		double a = getNode(index).data.x;
		double b = getNode(index).next.data.x;
		double c = getNode(index).data.y;
		double d = getNode(index).next.data.y;
		double e = getNode(index).previous.data.x;
		double g = getNode(index).previous.data.y;
		
		double distance1= calculateDistance(a,b,c,d);
		double distanceTwo = calculateDistance(a,e,c,g);
		double distanceThree = calculateDistance(b,e,d,g);

		double importance = distance1 + distanceTwo - distanceThree;
		getNode(index).importance = importance;
		return importance;
	}
	
	/**
	 * Calculates the importance of the node
	 * @param node
	 * @return a double
	 */
	public double calculateImportance(Node<Point> node)
	{
		
		double a = node.data.x;
		double b = node.next.data.x;
		double c = node.data.y;
		double d = node.next.data.y;
		double e = node.previous.data.x;
		double g = node.previous.data.y;
		
		double distance1= calculateDistance(a,b,c,d);
		double distanceTwo = calculateDistance(a,e,c,g);
		double distanceThree = calculateDistance(b,e,d,g);

		double importance = distance1 + distanceTwo - distanceThree;	
		node.importance = importance;
		return importance;
	}
	
	private double calculateDistance(double x1, double x2, double y1, double y2)
	{
		double toReturn = Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))); //this is messy, I apologize
		return toReturn;
	}
}



//read through as a string.
//parse int then delim by the comma
//add both individual numbers to the list
//read next line
//repeat



