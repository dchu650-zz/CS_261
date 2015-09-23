import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DecomposableFrame extends JFrame implements ChangeListener {//, JSlider{
	private static final int PANEL_HEIGHT = 800; //the height of the frame
	private static final int PANEL_WIDTH = 800; //the width of the frame;
	private ScreenPanel panel;
	private JSlider slider;
	private Polygon poly;
	public DecomposableShape shape;
	
	public DecomposableFrame()
	{
		slider = new JSlider(SwingConstants.VERTICAL);
		add(slider, BorderLayout.WEST);
		panel = new ScreenPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		slider.setMaximum(100);
		slider.setValue(100);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		this.setTitle("Shape Decomposer");
		this.setResizable(true);
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.pack();
			this.setVisible(true);
	}
	/**
	 * An inner class that holds a JPanel that can draw the city
	 */
	private class ScreenPanel extends JPanel
	{
		public ScreenPanel()
		{
			shape = new DecomposableShape();
		}
		public void paintComponent(Graphics g)
		{
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT); //erase what has come before
			g.setColor(Color.BLACK);
			g.drawPolygon(shape.getTheShape());
		}
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
