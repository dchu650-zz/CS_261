import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A visually laid out graph.
 * 
 * @author modified by Darren Chu and Kyle Whitcomb
 * @version 4.23.2013
 */
public class VisualGraph
{
	private static final int BORDER = 20; //space to leave around the display
	private static final int NODE_SIZE = 7; //size of each node

	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;

	private int width,height;
	
	private double temp, k;//for use in layout calculations

	//make a new visual graph with the given width/height (in pixels)
	//defaults to a random graph with 20 nodes and 20% connection rate
	public VisualGraph(int width, int height)
	{
		this.width = width;
		this.height = height;
		temp = 2.5;//after testing different temperatures, this seemed like a nice middle ground
		makeRandom(20, 0.2);
	}

	/**
	 * Generate a random graph with the given nodes and connection rate
	 * @param numNodes number of nodes
	 * @param ratio edge ratio
	 */
	public void makeRandom(int numNodes, double ratio)
	{
		Random rand = new Random();

		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		
		//make the nodes
		for(int i=0; i<numNodes; i++)
		{
			nodes.add(new Node(BORDER+rand.nextInt(width-BORDER*2), BORDER+rand.nextInt(height-BORDER*2), NODE_SIZE));
		}
		//specify the edges
		for(int i=0; i<numNodes; i++)
		{
			for(int j=i+1; j<numNodes; j++)
			{
				if(rand.nextDouble() < ratio)
				{
					edges.add(new Edge(nodes.get(i), nodes.get(j)));
				}
			}
		}
		k = Math.sqrt(width*height/nodes.size());//depends on the number of nodes and area, so needs to be recalculated if nodes are reset
	}

	/**
	 * Layout the nodes neatly. The given excluded node should not be modified (i.e., it is being modified by the user)
	 * @param exclude one node to exclude from the automatic layout; null if all nodes should be considered
	 */
	public void layout(Node exclude)
	{
		for(Node v : nodes)//calculate repulsize forces on each node
		{
			v.disp.x = 0;//initialize displacement for this cycle
			v.disp.y = 0;
			for(Node u : nodes)//force due to each other node
			{
				if(!u.equals(v))//but not due to itself
				{
					double dx = v.x-u.x;//calculate x and y displacements
					double dy = v.y-u.y;
					Point2D.Double dist = new Point2D.Double(dx,dy);//vector to represent total displacement
					double factor = k*k/dist.distanceSq(0, 0);//factor (includes repulsive force equation) to calculate final displacement
					v.disp.x += dist.x * factor;//update displacement
					v.disp.y += dist.y * factor;
				}
			}
			Point2D.Double dist = new Point2D.Double(v.x-width/2, v.y-height/2);//additional attractive force from center of window
			double factor = 2.5*dist.distance(0, 0)/k;
			v.disp.x += -dist.x * factor;//update displacement
			v.disp.y += -dist.y * factor;
		}
		
		for(Edge e : edges)//attractive forces due to "springs" (edges)
		{
			double dx = e.v.x-e.u.x;//x and y displacements between the connected nodes
			double dy = e.v.y-e.u.x;
			Point2D.Double dist = new Point2D.Double(dx,dy);
			double factor = dist.distance(0, 0)/k;//includes attractive force equation
			dist.x *= factor;
			dist.y *= factor;
			e.v.disp.x += -dist.x;//update the displacements for each node
			e.v.disp.y += -dist.y;
			e.u.disp.x += dist.x;
			e.u.disp.y += dist.y;
		}
		
		if (exclude!=null)//if the user is holding onto a node, don't move that node
		{
			exclude.disp.x = 0;//set displacement to 0 so that it won't move
			exclude.disp.y = 0;
		}
		
		for (Node v : nodes)//finally, add the displaments to the positions of the nodes
		{
			double magnitude = v.disp.distance(0, 0);
			if (magnitude>temp)//to limit displacement for each cycle
			{
				v.disp.x *= temp/magnitude;
				v.disp.y *= temp/magnitude;
			}
			v.x += v.disp.x;//update positions!
			v.y += v.disp.y;
			//check bounds on each wall, don't let the node move outside the bounds
			if(v.x<0)
				v.x = 0;
			else if (v.x>width)
				v.x = width;
			if (v.y<0)
				v.y = 0;
			else if (v.y>height)
				v.y = height;
		}
	}
	
	
	
	
	/**
	 * Returns an unmodifiable list of the nodes.
	 * @return The nodes in this graph.
	 */
	public List<Node> getNodes()
	{
		return Collections.unmodifiableList(nodes); //can make unmodifiable?
	}

	/**
	 * Returns an unmodifiable list of the edges.
	 * @return The edges in this graph.
	 */
	public List<Edge> getEdges()
	{
		return Collections.unmodifiableList(edges); //can make unmodifiable?
	}

	/**
	 * Returns the width of the graph (in pixels)
	 * @return the width of the graph
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Returns the height of the graph (in pixels)
	 * @return the height of the graph
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * An inner class to represent a node in this graph.
	 */
	public static class Node
	{
		private double x; //position of the node
		private double y;
		private int radius; //radius of the node
		private Point2D.Double disp;//current displacement of the node

		public Node(double x, double y, int radius)
		{
			this.x = x;
			this.y = y;
			this.radius = radius;
			disp = new Point2D.Double(0,0);
		}

		public Node(int x, int y)
		{
			this(x,y,VisualGraph.NODE_SIZE);
		}
		
		//getter for the x position
		public int x()
		{
			return (int)x;
		}
		
		//getter for the y position
		public int y()
		{
			return (int)y;
		}
		
		//getter for the radius
		public int radius()
		{
			return radius;
		}
		
		//setter for the X (so others can move)
		public void setX(int x)
		{
			this.x = x;
		}
		
		//setter for the Y (so others can move)
		public void setY(int y)
		{
			this.y = y;
		}
	}

	/**
	 * An inner class representing an Edge in this graph.
	 */
	public static class Edge
	{
		private Node u; //the nodes the edge connects
		private Node v;

		public Edge(Node u, Node v)
		{
			this.u = u;
			this.v = v;
		}
		
		//the "first" node in the edge
		public Node start()
		{
			return u;
		}
		
		//the "second" node in the edge
		public Node end()
		{
			return v;
		}
	}

}
