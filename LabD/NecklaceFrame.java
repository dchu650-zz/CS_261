import java.awt.*;
import javax.swing.*;


/**
 * A frame that draws a necklace. Can be refreshed.
 * @author Joel
 * @version Sp13
 */
public class NecklaceFrame extends JFrame
{
	private static final int PANEL_HEIGHT = 50; //the height of the frame
	private static final int PANEL_WIDTH = 800; //the width of the frame;
	private static final int CONNECTOR_SIZE = 5; //the length of the connecting twine between beads
	
	private Necklace necklace;
	private NecklacePanel panel; //the panel we'll draw the necklace on
	
	public NecklaceFrame(Necklace necklace)
	{
		this.necklace = necklace; //set the necklace
		panel = new NecklacePanel();
		panel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		panel.setBackground(Color.WHITE);
		this.getContentPane().add(panel);

    this.setTitle("Bead Necklace");
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * A private inner class that contains the panel that we're drawing on.
	 * We've overwritten paintComponent to specify our own painting.
	 */
	private class NecklacePanel extends JPanel {
		public void paintComponent(Graphics g)
		{
			//this will "white out" the background of the Panel
			g.setColor(Color.WHITE);
			g.clearRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
			g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT); //erase what has come before



			
			//TODO Your code to draw the necklace should be inserted HERE!!
			//remember to draw both the beads and their connectors!
			int x=0;
			int y=0;
			for(int i=0; i<necklace.size(); i++)
			{
				Bead b = necklace.getBead(i); //get the bead at this point in the necklace
				int width = b.getSize(); //get the size of this element of the necklace
				y = (PANEL_HEIGHT/2)-(b.getSize()/2); //set the y-coordinate to the middle of the screen
				int height = b.getSize(); //set the height of the circle to the element's size
				g.setColor(b.getColor()); //set the background color to the bead's color
				g.fillOval(x, y, width, height); //make an oval with all the specifications!
				x = x+b.getSize(); //increment the x-position so that the next oval doesn't draw on top of this one!
				g.setColor(Color.black); //make sure the line color doesn't change to the color of the circle (even though that would be psychadelic and cool)
				g.drawLine(x, PANEL_HEIGHT/2, x+5, PANEL_HEIGHT/2); //draw a connector, or the string for the necklace, if you will.
				x+=CONNECTOR_SIZE; //increment the x-position by the length of the connector
			}




			
		
		
		}
	}

	/**
	 * A method that "redraws" the frame to update the shown necklace.
	 */
	public void refreshNecklace()
	{
		panel.repaint();
	}
}
