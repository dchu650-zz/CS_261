import java.awt.*;



import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * A frame that draws the shapes
 * @author Darren Chu
 * @version 2/13/13
 */
public class DecomposableFrame extends JFrame implements ChangeListener
{
	private static final int PANEL_HEIGHT = 800; //the height of the frame
	private static final int PANEL_WIDTH = 800; //the width of the frame;
	private JSlider slider; //creates the slider
	private ChangeListener theChangeListener;
	private DecomposerPanel panel;
	public DecomposableShape shape; //creates the shape associated with
	
	/**
	 * Constructor for a DecomposableFrame
	 */
	public DecomposableFrame()
	{
		slider = new JSlider(SwingConstants.VERTICAL);
		add(slider, BorderLayout.WEST);
		shape = new DecomposableShape();
		panel = new DecomposerPanel();
		slider.setMaximum(100);
		slider.setValue(100);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.addChangeListener(theChangeListener);
		slider.getValueIsAdjusting();	
		refreshPanel();
		
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		this.getContentPane().add(panel);
		
		this.setTitle("Shape Decomposer");
		this.setResizable(true);
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.pack();
			this.setVisible(true);	
		
	}

	/**
	 * A private inner class that contains the panel that we're drawing on.
	 * We've overwritten paintComponent to specify our own painting.
	 */
	private class DecomposerPanel extends JPanel{
		
	
		public void paintComponent(Graphics g)
		{
			g.setColor(Color.WHITE); //sets the background to white
			g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT); 
			g.setColor(Color.BLACK); //sets the point drawing to black
			g.drawPolygon(shape.toPolygon());
			
		}

	}
	
	/**
	 * I could not how to get the slider to work! 
	 * */
	public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (source.getValueIsAdjusting()) 
        {
        	int tickNumber = (int)source.getValue();
        	System.out.println(tickNumber+ "");
        	int nextTickNumber = (int)source.getValue()+tickNumber;
        	if(nextTickNumber > tickNumber)
        	{
        		this.shape.trim(20);
        	}
        	if(nextTickNumber < tickNumber)
        	{
        		this.shape.restore(20);
        	}
        }    
    }

	/**
	 * A method that "redraws" the frame to update the shown polygon
	 */
	public void refreshPanel()
	{
		panel.repaint();
	}
}

