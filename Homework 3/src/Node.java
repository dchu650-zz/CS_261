import java.awt.Point;
/**
 * @author Darren Chu
 * @version 3/1/13
 */
public class Node<E> {
	
	Point data; //the data the node is storing
	Node<Point> next;//points at the next node
	Node<Point> previous;//points at the previous node
	public double importance; //the importance of the node used for removing nodes
	
	Node(Point dataItem)
	{
		importance = 0;
		data = dataItem;
		next = null;
		previous = null;	
	}
	Node(Point dataItem, Node<Point> nodeRef)
	{
		data = dataItem;
		next = nodeRef;
	}
	
}
	

