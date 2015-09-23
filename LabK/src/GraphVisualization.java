import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

/**
 * A Panel & Frame to display a Graph.
 * Note that this is implemented as a Panel that has a frame rather than a frame that has a panel, 
 * as the emphasis is on the display rather than the window
 * @author Joel
 * @version Sp 2013
 */
public class GraphVisualization extends JPanel implements MouseListener, MouseMotionListener, ActionListener
{
	private final Color NODE_COLOR = new Color(49,161,0); //a nice green color for the node
	
	private VisualGraph graph;
	private VisualGraph.Node selected; //the node the user is dragging around

	private int width; //size of the display
	private int height;
	
	private JButton newGraphButton, layoutStartButton, layoutStopButton;
	private Timer clock;
	
	public GraphVisualization(VisualGraph graph)
	{
		this.graph = graph;
		this.width = graph.getWidth();
		this.height = graph.getHeight();
		this.selected = null;
		
		this.setPreferredSize(new Dimension(graph.getWidth(),graph.getHeight()));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		//make a frame to hold the panel
		JFrame frame = new JFrame("Graph Layout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(this,BorderLayout.CENTER);
		
		//layout for buttons
		newGraphButton = new JButton("Generate Graph");
		newGraphButton.addActionListener(this);
		layoutStartButton = new JButton("Layout Graph");
		layoutStartButton.addActionListener(this);
		layoutStopButton = new JButton("Stop Layout");
		layoutStopButton.addActionListener(this);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,3));
		buttonPanel.add(newGraphButton);
		buttonPanel.add(layoutStartButton);
		buttonPanel.add(layoutStopButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		//timer to do animation; 60fps
		clock = new Timer(15,this);

		frame.pack();
		frame.setVisible(true);
	}
	
	//repaint the panel -- draws the graph
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;

		//clear the screen
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		g2d.setStroke(new BasicStroke(2));
		
		//draw the edges
		g2d.setPaint(Color.GRAY);
		List<VisualGraph.Edge> edges = graph.getEdges();
		for(VisualGraph.Edge e : edges)
		{
			g2d.drawLine(e.start().x(), e.start().y(), e.end().x(), e.end().y());
		}
		
		//draw the nodes
		List<VisualGraph.Node> nodes = graph.getNodes();
		for(VisualGraph.Node n : nodes)
		{
			int x = n.x();
			int y = n.y();
			int r = n.radius();
			g2d.setPaint(NODE_COLOR);
			g2d.fillOval(x-r, y-r, r+r, r+r);
			g2d.setPaint(Color.WHITE);
			g2d.drawOval(x-r, y-r, r+r, r+r);
		}
		
	}
	
	//Respond to action events
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == clock) //repaint on the clock
		{
			graph.layout(selected);
			repaint();
		}
		else if(e.getSource() == newGraphButton) //make a new graph
		{
			clock.stop();
			String msg = "";
			int nodes = 0;
			double ratio = 0.0;
			do //prompt for inputs; looping while invalid
			{
				try{
					String result = JOptionPane.showInputDialog(msg+"How many nodes?");
					if(result == null)
						return;
					nodes = Integer.parseInt(result);
					result = JOptionPane.showInputDialog("Chance of an edge (between 0.0 and 1.0)");
					if(result == null)
						return;
					ratio = Double.parseDouble(result);
					msg = "";
				}catch(NumberFormatException nfe){
					msg = "Invalid entry. ";
				}
			} while(!msg.equals(""));
			graph.makeRandom(nodes, ratio);
			repaint();
		}
		else if(e.getSource() == layoutStartButton) //start the animation
		{
			clock.restart();
		}
		else if(e.getSource() == layoutStopButton) //stop the animation
		{
			clock.stop();
		}
	}

	//When the mouse is pressed, grab the selected node
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX(); //mouse location
		int my = e.getY();
		List<VisualGraph.Node> nodes = graph.getNodes();
		for(int i=nodes.size()-1; i>=0; i--) //start from the back, that we get the last-painted nodes first
		{
			VisualGraph.Node n = nodes.get(i);
			double dist = Math.sqrt((mx-n.x())*(mx-n.x())+(my-n.y())*(my-n.y())); //distance from center
			if(dist < n.radius()) //if within circle
			{
				selected = n;
				return;
			}
			
		}
		
	}
	
	//when the mouse is released, we are not moving the node
	public void mouseReleased(MouseEvent e)
	{
		selected = null;
	}

	//when the mouse is dragged, update the position of the selected node
	public void mouseDragged(MouseEvent e)
	{
		if(selected != null)
		{
			if(e.getX() > 0 && e.getX() < width)
				selected.setX(e.getX());
			if(e.getY() > 0 && e.getY() < height)
				selected.setY(e.getY());
			repaint();
		}
	}

	//mouse methods that are not used
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}


	//main method for testing
	public static void main(String[] args)
	{
		//tests with a 500x500 graph
		new GraphVisualization(new VisualGraph(500,500));
	}
}
