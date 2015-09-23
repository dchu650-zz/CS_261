import java.awt.Point;
import java.math.*;
public class PointNode
{
	public PointNode next;
	public PointNode previous;
	public Point data;
	public double importance;
	public PointNode(Point point)
	{
		next = null;
		previous = null;
		data = point;
	}
	public void importanceOfSegment()
	{
	Point pointLeft = new Point(previous.data.x, previous.data.y);
	Point point = data;
	Point pointRight = new Point(next.data.x, next.data.y);
	double LP = Math.sqrt((pointLeft.x - point.x) * (pointLeft.x - point.x) + (point.y - pointRight.y) * (point.y-pointRight.y));
	double PR = Math.sqrt((point.x - pointRight.x) * (point.x-pointRight.x) + (point.y - pointRight.y) * (point.y-pointRight.y));
	double RL = Math.sqrt((pointRight.x - pointLeft.x) * (pointRight.x - pointLeft.x) + (pointRight.y-pointLeft.y) * (pointRight.y - pointLeft.y));
	importance = (LP + RL) - PR;
	}
}
